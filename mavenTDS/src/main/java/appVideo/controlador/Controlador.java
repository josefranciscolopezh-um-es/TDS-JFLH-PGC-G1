package appVideo.controlador;

import java.util.ArrayList;
import java.util.List;

import appVideo.dominio.CatalogoUsuarios;
import appVideo.dominio.Etiqueta;
import appVideo.dominio.ListaVideos;
import appVideo.dominio.Usuario;
import appVideo.dominio.Video;
import appVideo.persistencia.DAOException;
import appVideo.persistencia.FactoriaDAO;

public final class Controlador {
	private Usuario usuarioActual;
	private static Controlador unicaInstancia;
	private FactoriaDAO factoria;

	private Controlador() {
		usuarioActual = null;
		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public static Controlador getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new Controlador();
		return unicaInstancia;
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public boolean esUsuarioRegistrado(String login) {
		return CatalogoUsuarios.getUnicaInstancia().getUsuario(login) != null;
	}

	public boolean loginUsuario(String nombre, String password) {
		Usuario usuario = CatalogoUsuarios.getUnicaInstancia().getUsuario(nombre);
		if (usuario != null && usuario.getPassword().equals(password)) {
			this.usuarioActual = usuario;
			return true;
		}
		return false;
	}

	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, String password,
			String fechaNacimiento) {

		if (esUsuarioRegistrado(login))
			return false;
		Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fechaNacimiento);

		UsuarioDAO usuarioDAO = factoria
				.getUsuarioDAO(); /* Adaptador DAO para almacenar el nuevo Usuario en la BD */
		usuarioDAO.create(usuario);

		CatalogoUsuarios.getUnicaInstancia().addUsuario(usuario);
		return true;
	}

	/*
	public boolean borrarUsuario(Usuario usuario) {
		if (!esUsuarioRegistrado(usuario.getLogin()))
			return false;

		UsuarioDAO usuarioDAO = factoria.getUsuarioDAO(); // Adaptador DAO para borrar el Usuario de la BD 
		usuarioDAO.delete(usuario);

		CatalogoUsuarios.getUnicaInstancia().removeUsuario(usuario);
		return true;
	}
	*/
	
	public List<Video> buscarTitulo(String titulo){
		// TODO Se da una cadena de búsqueda y devuelve un ArrayList<Video> con los videos cuyos títulos contengan la cadena
		return new ArrayList<Video>();
	}
	
	public List<Video> buscarTitulo(String titulo, List<Etiqueta> etiquetas){
		// TODO Se da una cadena de búsqueda y una lista de etiquetas y devuelve un ArrayList<Video> con los videos cuyos títulos contengan la cadena y estén marcados con esas etiquetas
		return new ArrayList<Video>();
	}
	
	public List<ListaVideos> getListasUsuario(){
		// TODO Devuelve un ArrayList<ListaVideos> con las listas creadas por el usuario actual
		return new ArrayList<ListaVideos>();
	}

	public boolean logoutUsuario() {
		// TODO Cierra la sesión del usuario actual y devuelve un booleano indicando si la operación ha tenido éxito
		if (usuarioActual == null)
			return false;
		else {
			usuarioActual = null;
			return true;
		}
	}

	public String getNombreUsuario() {
		// TODO Devuelve el nombre del usuario actual
		return null;
	}
	
}
