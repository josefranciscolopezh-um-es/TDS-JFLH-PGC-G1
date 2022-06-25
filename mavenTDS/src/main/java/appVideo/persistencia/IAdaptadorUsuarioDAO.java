package appVideo.persistencia;

import java.util.List;
import appVideo.dominio.Usuario;

public interface IAdaptadorUsuarioDAO {
	public void createUsuario(Usuario usuario);
	public void deleteUsuario(Usuario usuario);
	public void updateUsuario(Usuario usuario);
	public Usuario getUsuario(int id);
	public List<Usuario> getAll();
}
