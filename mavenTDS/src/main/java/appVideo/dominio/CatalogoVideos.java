package appVideo.dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import appVideo.persistencia.DAOException;
import appVideo.persistencia.FactoriaDAO;

public class CatalogoVideos {
	private static CatalogoVideos unicaInstancia;
	private FactoriaDAO factoria;

	private HashMap<Integer, Video> videosPorID;
	private HashMap<String, Video> videosPorTitulo;

	public static CatalogoVideos getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new CatalogoVideos();
		return unicaInstancia;
	}

	private CatalogoVideos (){
		videosPorID = new HashMap<Integer, Video>();
		videosPorTitulo = new HashMap<String, Video>();
		
		try {
			factoria = FactoriaDAO.getInstancia();
			
			List<Video> listaAsistentes = factoria.getVideoDAO().getAll();
			for (Video video : listaAsistentes) {
				videosPorID.put(video.getId(), video);
				videosPorTitulo.put(video.getTitulo(), video);
			}
		} catch (DAOException eDAO) {
			   eDAO.printStackTrace();
		}
	}
	
	public List<Video> getVideos() {
		return new ArrayList<Video>(videosPorTitulo.values());
	}
	
	public Video getVideo(String titulo) {
		return videosPorTitulo.get(titulo);
	}

	public Video getVideo(int id) {
		return videosPorID.get(id);
	}
	
	public void addVideo(Video video) {
		videosPorID.put(video.getId(), video);
		videosPorTitulo.put(video.getTitulo(), video);
	}
	
	public void removeVideo(Video video) {
		videosPorID.remove(video.getId());
		videosPorTitulo.remove(video.getTitulo());
	}
	
}
