package appVideo.persistencia;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorUsuarioAppVideo implements IAdaptadorUsuarioDAO {
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioAppVideo unicaInstancia = null;

	public static AdaptadorUsuarioAppVideo getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorUsuarioAppVideo();
		else
			return unicaInstancia;
	}

	private AdaptadorUsuarioAppVideo() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
}
