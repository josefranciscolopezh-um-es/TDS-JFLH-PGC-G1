package appVideo.persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import appVideo.dominio.Etiqueta;
import appVideo.dominio.Video;
import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorVideoAppVideo implements IAdaptadorVideoDAO {
	private static final String VIDEO = "Video";

	private static final String TITULO = "titulo";
	private static final String URL = "url";
	private static final String NUM_REPRO = "numRepro";
	private static final String ETIQUETAS = "etiquetas";
	
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorVideoAppVideo unicaInstancia = null;

	public static AdaptadorVideoAppVideo getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorVideoAppVideo();
		else
			return unicaInstancia;
	}

	private AdaptadorVideoAppVideo() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	@Override
	public void createVideo(Video video) {
		Entidad eVideo = null;

		// Si la entidad esta registrada no la registra de nuevo
		try {
			eVideo = servPersistencia.recuperarEntidad(video.getId());
		} catch (NullPointerException e) {}
		if (eVideo != null) return;

		// registrar primero los atributos que son objetos
		AdaptadorEtiquetaAppVideo adaptadorEtiqueta = AdaptadorEtiquetaAppVideo.getUnicaInstancia();
		for (Etiqueta e : video.getEtiquetas())
			adaptadorEtiqueta.createEtiqueta(e);

		// crear entidad
		eVideo = new Entidad();
		eVideo.setNombre(VIDEO);
		eVideo.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad(TITULO, video.getTitulo()),
							  new Propiedad(URL, video.getUrl()),
							  new Propiedad(NUM_REPRO, String.valueOf(video.getNumRepro())),
							  new Propiedad(ETIQUETAS, obtenerCodigosEtiquetas(video.getEtiquetas())))));

		// registrar entidad
		eVideo = servPersistencia.registrarEntidad(eVideo);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		video.setId(eVideo.getId());
	}

	@Override
	public void deleteVideo(Video video) {
		Entidad eVideo = servPersistencia.recuperarEntidad(video.getId());

		servPersistencia.borrarEntidad(eVideo);	
	}

	@Override
	public void updateVideo(Video video) {
		Entidad eVideo = servPersistencia.recuperarEntidad(video.getId());

		for (Propiedad prop : eVideo.getPropiedades()) {
			if (prop.getNombre().equals(TITULO)) {
				prop.setValor(video.getTitulo());
			} else if (prop.getNombre().equals(URL)) {
				prop.setValor(video.getUrl());
			} else if (prop.getNombre().equals(NUM_REPRO)) {
				prop.setValor(String.valueOf(video.getNumRepro()));
			} else if (prop.getNombre().equals(ETIQUETAS)) {
				prop.setValor(obtenerCodigosEtiquetas(video.getEtiquetas()));
			}
			
			servPersistencia.modificarPropiedad(prop);
		}
	}

	@Override
	public Video getVideo(int id) {
		Entidad eVideo = servPersistencia.recuperarEntidad(id);
		
		String titulo = servPersistencia.recuperarPropiedadEntidad(eVideo, TITULO);
		String url = servPersistencia.recuperarPropiedadEntidad(eVideo, URL);

		Video video = new Video(titulo, url);

		int numRepro = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eVideo, NUM_REPRO));
		video.setNumRepro(numRepro);
		List<Etiqueta> etiquetas = obtenerEtiquetasDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eVideo, ETIQUETAS));
		for (Etiqueta e : etiquetas)
			video.addEtiqueta(e);
		
		video.setId(eVideo.getId());

		return video;
		
	}

	@Override
	public List<Video> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(VIDEO);

		List<Video> videos = new LinkedList<Video>();
		for (Entidad eVideo : entidades) {
			videos.add(getVideo(eVideo.getId()));
		}

		return videos;
	}

	private String obtenerCodigosEtiquetas(List<Etiqueta> etiquetas) {
		String aux = "";
		for (Etiqueta e : etiquetas) {
			aux += e.getId() + " ";
		}
		return aux.trim();
	}
	
	private List<Etiqueta> obtenerEtiquetasDesdeCodigos(String codigos) {
		List<Etiqueta> etiquetas = new LinkedList<Etiqueta>();
		StringTokenizer strTok = new StringTokenizer(codigos, " ");
		AdaptadorEtiquetaAppVideo adaptadorEtiqueta = AdaptadorEtiquetaAppVideo.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			etiquetas.add(adaptadorEtiqueta.getEtiqueta(Integer.valueOf((String) strTok.nextElement())));
		}
		return etiquetas;
	}
}
