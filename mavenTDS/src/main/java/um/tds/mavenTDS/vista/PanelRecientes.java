package um.tds.mavenTDS.vista;

import javax.swing.JPanel;

public class PanelRecientes extends JPanel {
	private static PanelRecientes unicaInstancia;
	
	public static PanelRecientes getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new PanelRecientes();
		return unicaInstancia;
	}
	/**
	 * Create the panel.
	 */
	public PanelRecientes() {

	}

}
