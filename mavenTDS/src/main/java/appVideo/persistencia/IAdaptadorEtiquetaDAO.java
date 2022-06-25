package appVideo.persistencia;

import java.util.List;

import appVideo.dominio.Etiqueta;

public interface IAdaptadorEtiquetaDAO {
	public void createEtiqueta(Etiqueta etiqueta);
	public void deleteEtiqueta(Etiqueta etiqueta);
	public void updateEtiqueta(Etiqueta etiqueta);
	public Etiqueta getEtiqueta(int codigo);
	public List<Etiqueta> getAll();
}
