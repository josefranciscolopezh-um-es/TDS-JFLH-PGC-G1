package appVideo.persistencia;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorVideoAppVideo implements IAdaptadorVideoDAO {
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorVideoAppVideo unicaInstancia = null;

	public static AdaptadorVideoAppVideo getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorVideoAppVideo();
		else
			return unicaInstancia;
	}

	private AdaptadorVideoAppVideo() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
}
