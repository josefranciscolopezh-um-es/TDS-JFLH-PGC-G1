package appVideo.persistencia;

import java.util.List;
import appVideo.dominio.ListaVideos;

public interface IAdaptadorListaVideosDAO {
	public void createListaVideos(ListaVideos listaVideos);
	public void deleteListaVideos(ListaVideos listaVideos);
	public void updateListaVideos(ListaVideos listaVideos);
	public ListaVideos getListaVideos(int codigo);
	public List<ListaVideos> getAll();
}
