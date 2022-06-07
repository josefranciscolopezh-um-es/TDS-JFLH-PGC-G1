package um.tds.mavenTDS;

import java.awt.EventQueue;

import um.tds.mavenTDS.vista.VentanaPrincipal;

public class Lanzador {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
