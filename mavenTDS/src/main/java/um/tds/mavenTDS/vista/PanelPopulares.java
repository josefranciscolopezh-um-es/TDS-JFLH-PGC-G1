package um.tds.mavenTDS.vista;

import javax.swing.JPanel;

public class PanelPopulares extends JPanel {
	private static PanelPopulares unicaInstancia;
	
	public static PanelPopulares getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new PanelPopulares();
		return unicaInstancia;
	}
	/**
	 * Create the panel.
	 */
	public PanelPopulares() {

	}

}
