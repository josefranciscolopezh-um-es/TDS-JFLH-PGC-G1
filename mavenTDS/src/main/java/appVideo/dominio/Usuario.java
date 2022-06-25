package appVideo.dominio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Usuario {
	
	public static final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("dd/MM/yyyy");
	private static final int CAPACIDAD_RECIENTES = 5;
	
	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String fechaNacimiento;
	
	private String login;
	private String password;
	
	private boolean premium;
	
	private ArrayList<ListaVideos> listas;
	private LinkedList<Video> recientes;
	private Filtro filtro;
	
	public Usuario(String nombre, String apellidos, String email, String fechaNacimiento, String login, String password) {
		this.id = 0;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.login = login;
		this.password = password;
		this.listas = new ArrayList<ListaVideos>();
		this.recientes = new LinkedList<Video>();
		this.filtro = Filtro.NO_FILTRO;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isPremium() {
		return premium;
	}
	
	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	
	public List<ListaVideos> getListas(){
		return Collections.unmodifiableList(listas);
	}
	
	public void addLista(ListaVideos lista) {
		listas.add(lista);
	}
	
	public boolean removeLista(ListaVideos lista) {
		return listas.remove(lista);
	}
	
	public List<Video> getRecientes(){
		return Collections.unmodifiableList(recientes);
	}
	
	public void addReciente(Video video) {
		recientes.addFirst(video);
		if (recientes.size() > CAPACIDAD_RECIENTES)
			recientes.removeLast();
	}

	public void setFiltro(Filtro filtro) {
		this.filtro = filtro;
	}

	public boolean isVideoInListas(Video v) {
		return listas.stream().flatMap(l -> l.getVideos().stream()).anyMatch(video -> video.equals(v));
	}

	public Filtro getFiltro() {
		return filtro;
	}
}
