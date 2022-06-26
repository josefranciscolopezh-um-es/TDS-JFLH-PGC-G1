package appVideo.persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import appVideo.dominio.Filtro;
import appVideo.dominio.ListaVideos;
import appVideo.dominio.Usuario;
import appVideo.dominio.Video;
import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorUsuarioAppVideo implements IAdaptadorUsuarioDAO {
	private static final String USUARIO = "Usuario";

	private static final String NOMBRE = "nombre";
	private static final String APELLIDOS = "apellidos";
	private static final String EMAIL = "email";
	private static final String FECHA_NACIMIENTO = "fechaNacimiento";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String PREMIUM = "premium";
	private static final String LISTAS = "listas";
	private static final String RECIENTES = "recientes";
	private static final String FILTRO = "filtro";
	
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioAppVideo unicaInstancia = null;

	public static AdaptadorUsuarioAppVideo getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorUsuarioAppVideo();
		else
			return unicaInstancia;
	}

	private AdaptadorUsuarioAppVideo() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	@Override
	public void createUsuario(Usuario usuario) {
		Entidad eUsuario = null;

		// Si la entidad esta registrada no la registra de nuevo
		try {
			eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		} catch (NullPointerException e) {}
		if (eUsuario != null) return;

		// registrar primero los atributos que son objetos
		AdaptadorVideoAppVideo adaptadorVideo = AdaptadorVideoAppVideo.getUnicaInstancia();
		for (Video v : usuario.getRecientes())
			adaptadorVideo.createVideo(v);
		
		AdaptadorListaVideosAppVideo adaptadorListaVideos = AdaptadorListaVideosAppVideo.getUnicaInstancia();
		for (ListaVideos l : usuario.getListas())
			adaptadorListaVideos.createListaVideos(l);

		// crear entidad
		eUsuario = new Entidad();
		eUsuario.setNombre(USUARIO);
		eUsuario.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad(NOMBRE, usuario.getNombre()),
							  new Propiedad(APELLIDOS, usuario.getApellidos()),
							  new Propiedad(EMAIL, usuario.getEmail()),
							  new Propiedad(FECHA_NACIMIENTO, usuario.getFechaNacimiento()),
							  new Propiedad(LOGIN, usuario.getLogin()),
							  new Propiedad(PASSWORD, usuario.getPassword()),
							  new Propiedad(PREMIUM, String.valueOf(usuario.isPremium())),
							  new Propiedad(LISTAS, obtenerCodigosListas(usuario.getListas())),
							  new Propiedad(RECIENTES, obtenerCodigosVideos(usuario.getRecientes())),
							  new Propiedad(FILTRO, usuario.getFiltro().name()))));

		// registrar entidad
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		usuario.setId(eUsuario.getId());
	}

	@Override
	public void deleteUsuario(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

		servPersistencia.borrarEntidad(eUsuario);		
	}

	@Override
	public void updateUsuario(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals(NOMBRE)) {
				prop.setValor(usuario.getNombre());
			} else if (prop.getNombre().equals(APELLIDOS)) {
				prop.setValor(usuario.getApellidos());
			} else if (prop.getNombre().equals(EMAIL)) {
				prop.setValor(usuario.getEmail());
			} else if (prop.getNombre().equals(FECHA_NACIMIENTO)) {
				prop.setValor(usuario.getFechaNacimiento());
			} else if (prop.getNombre().equals(LOGIN)) {
				prop.setValor(usuario.getLogin());
			} else if (prop.getNombre().equals(PASSWORD)) {
				prop.setValor(usuario.getPassword());
			} else if (prop.getNombre().equals(PREMIUM)) {
				prop.setValor(String.valueOf(usuario.isPremium()));
			} else if (prop.getNombre().equals(LISTAS)) {
				prop.setValor(obtenerCodigosListas(usuario.getListas()));
			} else if (prop.getNombre().equals(RECIENTES)) {
				prop.setValor(obtenerCodigosVideos(usuario.getRecientes()));
			} else if (prop.getNombre().equals(FILTRO)) {
				prop.setValor(usuario.getFiltro().name());
			}
			
			servPersistencia.modificarPropiedad(prop);
		}
	}

	@Override
	public Usuario getUsuario(int id) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(id);
		
		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
		String apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, APELLIDOS);
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, EMAIL);
		String fechaNacimiento = servPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO);
		String login = servPersistencia.recuperarPropiedadEntidad(eUsuario, LOGIN);
		String password = servPersistencia.recuperarPropiedadEntidad(eUsuario, PASSWORD);

		Usuario usuario = new Usuario(nombre, apellidos, email, fechaNacimiento, login, password);
		usuario.setPremium(Boolean.valueOf(servPersistencia.recuperarPropiedadEntidad(eUsuario, PREMIUM)));
		List<ListaVideos> listas = obtenerListasVideosDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eUsuario, LISTAS));
		for (ListaVideos l : listas)
			usuario.addLista(l);
		List<Video> recientes = obtenerVideosDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eUsuario, RECIENTES));
		for (Video v : recientes)
			usuario.addReciente(v);
		usuario.setFiltro(Filtro.valueOf(servPersistencia.recuperarPropiedadEntidad(eUsuario, FILTRO)));
		
		usuario.setId(eUsuario.getId());

		return usuario;
	}

	@Override
	public List<Usuario> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(USUARIO);

		List<Usuario> usuarios = new LinkedList<Usuario>();
		for (Entidad eUsuario : entidades) {
			usuarios.add(getUsuario(eUsuario.getId()));
		}

		return usuarios;
	}
	
	private String obtenerCodigosListas(List<ListaVideos> listas) {
		String aux = "";
		for (ListaVideos l : listas) {
			aux += l.getId() + " ";
		}
		return aux.trim();
	}
	
	private String obtenerCodigosVideos(List<Video> videos) {
		String aux = "";
		for (Video v : videos) {
			aux += v.getId() + " ";
		}
		return aux.trim();
	}
	
	private List<ListaVideos> obtenerListasVideosDesdeCodigos(String codigos) {
		List<ListaVideos> listas = new LinkedList<ListaVideos>();
		StringTokenizer strTok = new StringTokenizer(codigos, " ");
		AdaptadorListaVideosAppVideo adaptadorListaVideos = AdaptadorListaVideosAppVideo.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			listas.add(adaptadorListaVideos.getListaVideos(Integer.valueOf((String) strTok.nextElement())));
		}
		return listas;
	}
	
	private List<Video> obtenerVideosDesdeCodigos(String codigos) {
		List<Video> videos = new LinkedList<Video>();
		StringTokenizer strTok = new StringTokenizer(codigos, " ");
		AdaptadorVideoAppVideo adaptadorVideo = AdaptadorVideoAppVideo.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			videos.add(adaptadorVideo.getVideo(Integer.valueOf((String) strTok.nextElement())));
		}
		return videos;
	}
}
