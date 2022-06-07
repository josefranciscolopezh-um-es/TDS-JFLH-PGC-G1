package um.tds.mavenTDS.vista;

import javax.swing.JPanel;

public class PanelMisListas extends JPanel {
	private static PanelMisListas unicaInstancia;
	
	public static PanelMisListas getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new PanelMisListas();
		return unicaInstancia;
	}
	/**
	 * Create the panel.
	 */
	public PanelMisListas() {

	}

}
