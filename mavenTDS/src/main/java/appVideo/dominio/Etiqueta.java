package appVideo.dominio;

public class Etiqueta {
	private int id;
	private String nombre;
	
	public Etiqueta(String nombre) {
		this.id = 0;
		this.nombre = nombre;
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
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Etiqueta) {
			if (((Etiqueta)o).getNombre().equals(nombre))
				return true;
		}
		return false;
	}
}
