package appVideo.dominio;

import java.util.ArrayList;
import java.util.List;

public class Video {

	private int id;
	private String titulo;
	private final String url;
	private int numRepro;
	private List<Etiqueta> etiquetas;
	
	public Video(String titulo, String url) {
		this.id = 0;
		this.titulo = titulo;
		this.url = url;
		this.numRepro = 0;
		this.etiquetas = new ArrayList<Etiqueta>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getUrl() {
		return url;
	}
	
	public int getNumRepro() {
		return numRepro;
	}
	
	public void setNumRepro(int numRepro) {
		this.numRepro = numRepro;
	}
	
	public void aumentarRepro() {
		numRepro++;
	}
	
	public List<Etiqueta> getEtiquetas() {
		return etiquetas;
	}
	
	public void addEtiqueta(Etiqueta etiqueta) {
		etiquetas.add(etiqueta);
	}
	
	public boolean removeEtiqueta(Etiqueta etiqueta) {
		return etiquetas.remove(etiqueta);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Video) {
			if (((Video)o).getUrl().equals(url))
				return true;
		}
		return false;
	}
}
