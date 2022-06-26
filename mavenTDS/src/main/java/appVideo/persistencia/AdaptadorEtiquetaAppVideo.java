package appVideo.persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import appVideo.dominio.Etiqueta;
import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorEtiquetaAppVideo implements IAdaptadorEtiquetaDAO {
	private static final String ETIQUETA = "Etiqueta";

	private static final String NOMBRE = "nombre";
	
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorEtiquetaAppVideo unicaInstancia = null;

	public static AdaptadorEtiquetaAppVideo getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorEtiquetaAppVideo();
		else
			return unicaInstancia;
	}

	private AdaptadorEtiquetaAppVideo() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	@Override
	public void createEtiqueta(Etiqueta etiqueta) {
		Entidad eEtiqueta = null;

		// Si la entidad esta registrada no la registra de nuevo
		try {
			eEtiqueta = servPersistencia.recuperarEntidad(etiqueta.getId());
		} catch (NullPointerException e) {}
		if (eEtiqueta != null) return;

		// crear entidad
		eEtiqueta = new Entidad();
		eEtiqueta.setNombre(ETIQUETA);
		eEtiqueta.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad(NOMBRE, etiqueta.getNombre()))));

		// registrar entidad
		eEtiqueta = servPersistencia.registrarEntidad(eEtiqueta);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		etiqueta.setId(eEtiqueta.getId());
	}

	@Override
	public void deleteEtiqueta(Etiqueta etiqueta) {
		Entidad eEtiqueta = servPersistencia.recuperarEntidad(etiqueta.getId());

		servPersistencia.borrarEntidad(eEtiqueta);
	}

	@Override
	public void updateEtiqueta(Etiqueta etiqueta) {
		Entidad eEtiqueta = servPersistencia.recuperarEntidad(etiqueta.getId());

		for (Propiedad prop : eEtiqueta.getPropiedades()) {
			if (prop.getNombre().equals(NOMBRE)) {
				prop.setValor(etiqueta.getNombre());
			}
			
			servPersistencia.modificarPropiedad(prop);
		}
	}

	@Override
	public Etiqueta getEtiqueta(int id) {
		Entidad eEtiqueta = servPersistencia.recuperarEntidad(id);
		
		String nombre = servPersistencia.recuperarPropiedadEntidad(eEtiqueta, NOMBRE);

		Etiqueta etiqueta = new Etiqueta(nombre);
		
		etiqueta.setId(eEtiqueta.getId());

		return etiqueta;
	}

	@Override
	public List<Etiqueta> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(ETIQUETA);

		List<Etiqueta> etiquetas = new LinkedList<Etiqueta>();
		for (Entidad eEtiqueta : entidades) {
			etiquetas.add(getEtiqueta(eEtiqueta.getId()));
		}

		return etiquetas;
	}
}
