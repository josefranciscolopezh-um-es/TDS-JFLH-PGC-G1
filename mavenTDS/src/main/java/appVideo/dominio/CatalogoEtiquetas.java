package appVideo.dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import appVideo.persistencia.DAOException;
import appVideo.persistencia.FactoriaDAO;

public class CatalogoEtiquetas {
	private static CatalogoEtiquetas unicaInstancia;
	private FactoriaDAO factoria;

	private HashMap<Integer, Etiqueta> etiquetasPorID;
	private HashMap<String, Etiqueta> etiquetasPorNombre;

	public static CatalogoEtiquetas getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new CatalogoEtiquetas();
		return unicaInstancia;
	}

	private CatalogoEtiquetas (){
		etiquetasPorID = new HashMap<Integer, Etiqueta>();
		etiquetasPorNombre = new HashMap<String, Etiqueta>();
		
		try {
			factoria = FactoriaDAO.getInstancia();
			
			List<Etiqueta> listaAsistentes = factoria.getEtiquetaDAO().getAll();
			for (Etiqueta etiqueta : listaAsistentes) {
				etiquetasPorID.put(etiqueta.getId(), etiqueta);
				etiquetasPorNombre.put(etiqueta.getNombre(), etiqueta);
			}
		} catch (DAOException eDAO) {
			   eDAO.printStackTrace();
		}
	}
	
	public List<Etiqueta> getEtiquetas() {
		return new ArrayList<Etiqueta>(etiquetasPorNombre.values());
	}
	
	public Etiqueta getEtiqueta(String nombre) {
		return etiquetasPorNombre.get(nombre);
	}

	public Etiqueta getEtiqueta(int id) {
		return etiquetasPorID.get(id);
	}
	
	public void addEtiqueta(Etiqueta etiqueta) {
		etiquetasPorID.put(etiqueta.getId(), etiqueta);
		etiquetasPorNombre.put(etiqueta.getNombre(), etiqueta);
	}
	
	public void removeEtiqueta(Etiqueta etiqueta) {
		etiquetasPorID.remove(etiqueta.getId());
		etiquetasPorNombre.remove(etiqueta.getNombre());
	}
}
