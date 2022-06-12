package appVideo.persistencia;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorEtiquetaAppVideo implements IAdaptadorEtiquetaDAO {
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
}
