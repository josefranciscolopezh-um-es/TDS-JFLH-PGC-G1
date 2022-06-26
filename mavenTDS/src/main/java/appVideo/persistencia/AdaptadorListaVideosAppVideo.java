package appVideo.persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import appVideo.dominio.ListaVideos;
import appVideo.dominio.Video;
import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorListaVideosAppVideo implements IAdaptadorListaVideosDAO {
	private static final String LISTA = "Lista";

	private static final String NOMBRE = "nombre";
	private static final String VIDEOS = "videos";
	
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorListaVideosAppVideo unicaInstancia = null;

	public static AdaptadorListaVideosAppVideo getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorListaVideosAppVideo();
		else
			return unicaInstancia;
	}

	private AdaptadorListaVideosAppVideo() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	@Override
	public void createListaVideos(ListaVideos lista) {
		Entidad eLista = null;

		// Si la entidad esta registrada no la registra de nuevo
		try {
			eLista = servPersistencia.recuperarEntidad(lista.getId());
		} catch (NullPointerException e) {}
		if (eLista != null) return;

		// registrar primero los atributos que son objetos
		AdaptadorVideoAppVideo adaptadorVideo = AdaptadorVideoAppVideo.getUnicaInstancia();
		for (Video v : lista.getVideos())
			adaptadorVideo.createVideo(v);

		// crear entidad
		eLista = new Entidad();
		eLista.setNombre(LISTA);
		eLista.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad(NOMBRE, lista.getNombre()),
							  new Propiedad(VIDEOS, obtenerCodigosVideos(lista.getVideos())))));

		// registrar entidad
		eLista = servPersistencia.registrarEntidad(eLista);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		lista.setId(eLista.getId());		
	}

	@Override
	public void deleteListaVideos(ListaVideos lista) {
		Entidad eLista = servPersistencia.recuperarEntidad(lista.getId());

		servPersistencia.borrarEntidad(eLista);			
	}

	@Override
	public void updateListaVideos(ListaVideos lista) {
		Entidad eLista = servPersistencia.recuperarEntidad(lista.getId());

		for (Propiedad prop : eLista.getPropiedades()) {
			if (prop.getNombre().equals(NOMBRE)) {
				prop.setValor(lista.getNombre());
			} else if (prop.getNombre().equals(VIDEOS)) {
				prop.setValor(obtenerCodigosVideos(lista.getVideos()));
			}
			
			servPersistencia.modificarPropiedad(prop);
		}
	}

	@Override
	public ListaVideos getListaVideos(int id) {
		Entidad eLista = servPersistencia.recuperarEntidad(id);
		
		String nombre = servPersistencia.recuperarPropiedadEntidad(eLista, NOMBRE);
		List<Video> videos = obtenerVideosDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eLista, VIDEOS));

		ListaVideos lista = new ListaVideos(nombre, videos);
		
		lista.setId(eLista.getId());

		return lista;
	}

	@Override
	public List<ListaVideos> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(LISTA);

		List<ListaVideos> listas = new LinkedList<ListaVideos>();
		for (Entidad eLista : entidades) {
			listas.add(getListaVideos(eLista.getId()));
		}

		return listas;
	}

	private String obtenerCodigosVideos(List<Video> videos) {
		String aux = "";
		for (Video v : videos) {
			aux += v.getId() + " ";
		}
		return aux.trim();
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
