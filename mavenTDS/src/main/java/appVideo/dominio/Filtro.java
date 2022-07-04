package appVideo.dominio;

import java.util.function.Predicate;

import appVideo.controlador.Controlador;

public enum Filtro {
	NO_FILTRO	(v -> true),
	MENORES		(v -> Controlador.getUnicaInstancia().videoContainsEtiqueta(v, "Adultos")),
	MIS_LISTAS	(v -> !(Controlador.getUnicaInstancia().isVideoInListasUsuario(v)));
	
	private Filtro(Predicate<Video> filtro) {
		isOk = filtro;
	}
	
	public Predicate<Video> isOk;
}
