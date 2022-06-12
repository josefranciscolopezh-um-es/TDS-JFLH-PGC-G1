package appVideo;

import java.awt.EventQueue;

import appVideo.vista.VentanaPrincipal;

public class Lanzador {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal.getUnicaInstancia().mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
