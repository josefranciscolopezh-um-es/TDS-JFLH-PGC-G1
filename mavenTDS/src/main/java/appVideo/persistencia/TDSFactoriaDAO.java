package appVideo.persistencia;

public class TDSFactoriaDAO extends FactoriaDAO {
	public TDSFactoriaDAO () {
	}
	
	@Override
	public IAdaptadorUsuarioDAO getUsuarioDAO() {
		return AdaptadorUsuarioAppVideo.getUnicaInstancia();
	}

	@Override
	public IAdaptadorVideoDAO getVideoDAO() {
		return AdaptadorVideoAppVideo.getUnicaInstancia();
	}

	@Override
	public IAdaptadorListaVideosDAO getListaVideosDAO() {
		return AdaptadorListaVideosAppVideo.getUnicaInstancia();
	}

	@Override
	public IAdaptadorEtiquetaDAO getEtiquetaDAO() {
		return AdaptadorEtiquetaAppVideo.getUnicaInstancia();
	}

}
