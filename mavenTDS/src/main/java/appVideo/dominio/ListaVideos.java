package appVideo.dominio;

import java.util.Collections;
import java.util.List;

public class ListaVideos {
	private int id;
	private String nombre;
	private List<Video> videos;
	
	public ListaVideos(String nombre, List<Video> videos) {
		this.id = 0;
		this.nombre = nombre;
		this.videos = videos;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public List<Video> getVideos(){
		return Collections.unmodifiableList(videos);
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
}
