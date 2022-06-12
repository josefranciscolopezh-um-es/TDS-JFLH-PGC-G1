package appVideo.persistencia;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorListaVideosAppVideo implements IAdaptadorListaVideosDAO {
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorListaVideosAppVideo unicaInstancia = null;

	public static AdaptadorListaVideosAppVideo getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorListaVideosAppVideo();
		else
			return unicaInstancia;
	}

	private AdaptadorListaVideosAppVideo() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
}
