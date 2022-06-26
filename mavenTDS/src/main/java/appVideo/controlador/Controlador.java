package appVideo.controlador;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EventObject;
import java.util.List;
import java.util.stream.Collectors;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import appVideo.dominio.CatalogoEtiquetas;
import appVideo.dominio.CatalogoUsuarios;
import appVideo.dominio.CatalogoVideos;
import appVideo.dominio.Etiqueta;
import appVideo.dominio.Filtro;
import appVideo.dominio.ListaVideos;
import appVideo.dominio.Usuario;
import appVideo.dominio.Video;
import appVideo.persistencia.DAOException;
import appVideo.persistencia.FactoriaDAO;
import appVideo.persistencia.IAdaptadorEtiquetaDAO;
import appVideo.persistencia.IAdaptadorListaVideosDAO;
import appVideo.persistencia.IAdaptadorUsuarioDAO;
import appVideo.persistencia.IAdaptadorVideoDAO;
import appVideo.vista.PanelReproductor;
import umu.tds.componente.CargadorVideos;
import umu.tds.componente.VideosEvent;
import umu.tds.componente.VideosListener;

public final class Controlador implements VideosListener {

	public static final int NUM_POPULARES = 10;

	private static Controlador unicaInstancia;
	
	private FactoriaDAO factoria;
	
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorVideoDAO adaptadorVideo;
	private IAdaptadorEtiquetaDAO adaptadorEtiqueta;
	private IAdaptadorListaVideosDAO adaptadorListaVideos;
	
	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoVideos catalogoVideos;
	private CatalogoEtiquetas catalogoEtiquetas;
	
	private Usuario usuarioActual;
	
	private CargadorVideos cargadorVideos;

	private Controlador() {
		usuarioActual = null;
		
		cargadorVideos = CargadorVideos.getUnicaInstancia();
		cargadorVideos.addVideosListener(this);
		
		inicializarAdaptadores();
		
		inicializarCatalogos();
	}

	public static Controlador getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new Controlador();
		return unicaInstancia;
	}
	
	private void inicializarAdaptadores() {
		factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		adaptadorUsuario = factoria.getUsuarioDAO();
		adaptadorVideo = factoria.getVideoDAO();
		adaptadorListaVideos = factoria.getListaVideosDAO();
		adaptadorEtiqueta = factoria.getEtiquetaDAO();
	}

	private void inicializarCatalogos() {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
		catalogoEtiquetas = CatalogoEtiquetas.getUnicaInstancia();
		catalogoVideos = CatalogoVideos.getUnicaInstancia();
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public boolean esUsuarioRegistrado(String login) {
		return catalogoUsuarios.getUsuario(login) != null;
	}

	public boolean loginUsuario(String nombre, String password) {
		Usuario usuario = catalogoUsuarios.getUsuario(nombre);
		if (usuario != null && usuario.getPassword().equals(password)) {
			this.usuarioActual = usuario;
			return true;
		}
		return false;
	}

	public boolean registrarUsuario(String nombre, String apellidos, String email, String fechaNacimiento, String login, String password) {
		
		if (esUsuarioRegistrado(login))
			return false;
		Usuario usuario = new Usuario(nombre, apellidos, email, fechaNacimiento, login, password);

		adaptadorUsuario.createUsuario(usuario);

		catalogoUsuarios.addUsuario(usuario);
		
		return true;
	}
	
	public List<Video> buscarVideo(String titulo){
		// Se da una cadena de búsqueda y devuelve un ArrayList<Video> con los videos cuyos títulos contengan la cadena
		
		titulo = titulo.toLowerCase();						// Se pasa todo a minúsculas para que la búsqueda no sea "case sensitive"
		
		List<Video> allVideos = catalogoVideos.getVideos();			// Se obtienen todos los videos
		List<Video> resultado = new ArrayList<Video>();
		
		for (Video video : allVideos) {						// Si el título del video contiene la cadena de búsqueda, se incluye en el resultado
			if (video.getTitulo().toLowerCase().contains(titulo))
				resultado.add(video);
		}
		
		return resultado.stream().filter(usuarioActual.getFiltro().isOk).collect(Collectors.toList());
	}
	
	public List<Video> buscarVideo(String titulo, List<String> nombreEtiquetas){
		// Se da una cadena de búsqueda y una lista de etiquetas y devuelve un ArrayList<Video> con los videos cuyos títulos contengan la cadena y estén marcados con esas etiquetas
		
		List<Etiqueta> etiquetas = nombreEtiquetas.stream().map(n -> catalogoEtiquetas.getEtiqueta(n)).collect(Collectors.toList());
		
		List<Video> videos = buscarVideo(titulo);			// Se utiliza el método anterior para filtrar por título
		List<Video> resultado = new ArrayList<Video>();
		
		for (Video video : videos) {						// Si el video tiene asociado todas las etiquetas requeridas, se incluye en el resultado
			if (video.getEtiquetas().containsAll(etiquetas))
				resultado.add(video);
		}
		
		return resultado;
	}
	
	public List<ListaVideos> getListasUsuario(){
		// Devuelve un ArrayList<ListaVideos> con las listas creadas por el usuario actual
		
		if (usuarioActual != null)
			return usuarioActual.getListas();
		else return null;
	}

	public boolean logoutUsuario() {
		// Cierra la sesión del usuario actual y devuelve un booleano indicando si la operación ha tenido éxito
		if (usuarioActual == null)
			return false;
		else {
			usuarioActual = null;
			return true;
		}
	}

	public String getNombreUsuario() {
		// Devuelve el nombre del usuario actual
		
		if (usuarioActual != null)
			return usuarioActual.getNombre();
		else return null;
	}
	
	public List<Video> getRecientes(){
		if (usuarioActual != null)
			return usuarioActual.getRecientes();
		else return null;
	}

	public boolean isUsuarioPremium() {
		if (usuarioActual != null)
			return usuarioActual.isPremium();
		else return false;
	}
	
	public boolean setFiltroUsuario(String filtro) {
		if (usuarioActual != null) {
			usuarioActual.setFiltro(Filtro.valueOf(filtro));
			adaptadorUsuario.updateUsuario(usuarioActual);
			return true;
		}
		else return false;
	}
	
	public List<String> getAllNombresFiltros() {
		ArrayList<String> resultado = new ArrayList<String>();
		for (Filtro f : Filtro.values())
			resultado.add(f.name());
		return resultado;
	}
	
	public List<String> getAllNombresEtiquetas() {
		
		return CatalogoEtiquetas.getUnicaInstancia().getEtiquetas().stream().map(e -> e.getNombre()).collect(Collectors.toList());
	}
	
	public boolean videoContainsEtiqueta(Video video, String nombre) {
		Etiqueta etiqueta = catalogoEtiquetas.getEtiqueta(nombre);
		
		return video.getEtiquetas().contains(etiqueta);
	}

	public boolean isVideoInListasUsuario(Video v) {
		if (usuarioActual != null)
			return usuarioActual.isVideoInListas(v);
		else return false;
	}

	public DateFormat getFormatoFecha() {
		return Usuario.FORMATO_FECHA;
	}

	public void removeUsuarioPremium() {
		usuarioActual.setPremium(false);
		usuarioActual.setFiltro(Filtro.NO_FILTRO);
		
		adaptadorUsuario.updateUsuario(usuarioActual);
	}

	public void setUsuarioPremium() {
		usuarioActual.setPremium(true);
		
		adaptadorUsuario.updateUsuario(usuarioActual);
	}
	
	public String getNombreFiltroUsuario() {
		if (usuarioActual != null)
			return usuarioActual.getFiltro().name();
		else return null;
	}

	public void addEtiqueta(Video video, String nuevaEtiqueta) {
		Etiqueta etiqueta = catalogoEtiquetas.getEtiqueta(nuevaEtiqueta);
		
		if (etiqueta == null) {
			etiqueta = new Etiqueta(nuevaEtiqueta);
			
			adaptadorEtiqueta.createEtiqueta(etiqueta);
			catalogoEtiquetas.addEtiqueta(etiqueta);
		}
		
		video.addEtiqueta(etiqueta);
		adaptadorVideo.updateVideo(video);
	}

	public void reproducirVideo(Video video) {
		video.aumentarRepro();
		adaptadorVideo.updateVideo(video);
		
		usuarioActual.addReciente(video);
		adaptadorUsuario.updateUsuario(usuarioActual);
		
		PanelReproductor.getUnicaInstancia().reproducir(video);
	}
	
	public Video getVideoPorTitulo(String nombre) {
		return catalogoVideos.getVideo(nombre);
	}

	public List<String> getNombresListasUsuario() {
		if (usuarioActual != null)
			return usuarioActual.getListas().stream().map(l -> l.getNombre()).collect(Collectors.toList());
		else return null;
	}

	public List<Video> getVideosLista(String nombre) {
		for (ListaVideos l : usuarioActual.getListas()) {
			if (l.getNombre().equals(nombre))
				return l.getVideos();
		}
		return null;
	}

	public void removeLista(String nombreLista) {
		for (ListaVideos l : usuarioActual.getListas()) {
			if (l.getNombre().equals(nombreLista)) {
				
				usuarioActual.removeLista(l);
				adaptadorListaVideos.deleteListaVideos(l);
				
				return;
			}
		}
	}

	public void guardarLista(String nombre, List<Video> videos) {
		for (ListaVideos l : usuarioActual.getListas()) {
			if (l.getNombre().equals(nombre)) {
				l.setVideos(videos);
				return;
			}
		}
		
		ListaVideos lista = new ListaVideos(nombre, videos);
		usuarioActual.addLista(lista);
		adaptadorListaVideos.createListaVideos(lista);
	}
	
	public List<Video> getPopulares(){
		return catalogoVideos.getVideos().stream().sorted(Comparator.comparingInt(Video::getNumRepro).reversed()).limit(NUM_POPULARES).collect(Collectors.toList());
	}

	public boolean generarPdf() {
		if (usuarioActual != null && usuarioActual.isPremium()) {
			Document pdf = new Document();
			try {
				PdfWriter.getInstance(pdf, new FileOutputStream("AppVideo-MisListas.pdf"));

				pdf.open();
				
				pdf.addTitle("AppVideo - Listas de videos de " + usuarioActual.getNombre() + " " + usuarioActual.getApellidos());
				pdf.add(new Paragraph("AppVideo - Listas de videos de " + usuarioActual.getNombre() + " " + usuarioActual.getApellidos()));
				pdf.add(new Paragraph("\n"));
	
				for (ListaVideos l : usuarioActual.getListas()) {
					pdf.add(new Paragraph("Lista '" + l.getNombre() + "':\n"));
					
					for (Video v : l.getVideos()) {
						pdf.add(new Paragraph("--> " + v.getTitulo()));
					}
	
					pdf.add(new Paragraph("\n"));
				}
	
				pdf.close();
				return true;
			} catch (FileNotFoundException | DocumentException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	@Override
	public void enteradoCambioVideos(EventObject e) {
		if (e instanceof VideosEvent) {
			Video video;
			for (umu.tds.componente.Video v : ((VideosEvent) e).getVideos().getVideo()) {
				if (catalogoVideos.getVideo(v.getTitulo()) == null) {
					video = new Video(v.getTitulo(), v.getURL());
					adaptadorVideo.createVideo(video);
					catalogoVideos.addVideo(video);
					
					for (String etiqueta : v.getEtiqueta())
						addEtiqueta(video, etiqueta);
				}
			}
		}
	}

	public void getNuevosVideos(String fichero) {
		cargadorVideos.setFileVideo(fichero);
	}
}
