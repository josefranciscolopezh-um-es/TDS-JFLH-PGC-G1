package appVideo.vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import appVideo.controlador.Controlador;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class PanelFiltros extends JPanel{
	private static PanelFiltros unicaInstancia;
	
	private JPanel pnlFiltros;
	private ButtonGroup bg;
	
	public static PanelFiltros getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new PanelFiltros();
		return unicaInstancia;
	}
	
	public PanelFiltros() {
		setLayout(new BorderLayout(0, 0));
		
		pnlFiltros = new JPanel();
		pnlFiltros.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Filtros", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		add(pnlFiltros, BorderLayout.CENTER);
		pnlFiltros.setLayout(new BoxLayout(pnlFiltros, BoxLayout.Y_AXIS));
		
		bg = new ButtonGroup();
		
		addBotonesFiltros();
		
		JPanel pnlAplicar = new JPanel();
		add(pnlAplicar, BorderLayout.SOUTH);
		
		JButton btnAplicar = new JButton("Aplicar");
		pnlAplicar.add(btnAplicar);
		
		addManejadorBotonAplicar(btnAplicar);
	}

	private void addBotonesFiltros() {
		List<String> nombresFiltros = Controlador.getUnicaInstancia().getAllNombresFiltros();
		String filtroActual = Controlador.getUnicaInstancia().getNombreFiltroUsuario();
		
		for (String filtro : nombresFiltros) {
			JRadioButton rdbtnFiltro = new JRadioButton(filtro);
			bg.add(rdbtnFiltro);
			pnlFiltros.add(rdbtnFiltro);
			
			if (filtro.equals(filtroActual))
				rdbtnFiltro.setSelected(true);
		}
	}
	
	private void addManejadorBotonAplicar(JButton btnAplicar) {
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filtro = getFiltroSeleccionado();
				
				if (filtro != null) {
					Controlador.getUnicaInstancia().setFiltroUsuario(filtro);
				}
			}
		});
	}
	
	private String getFiltroSeleccionado() {
		for (Enumeration<AbstractButton> botones = bg.getElements(); botones.hasMoreElements();) {
	        AbstractButton boton = botones.nextElement();

	        if (boton.isSelected()) {
	            return boton.getText();
	        }
	    }
		return null;
	}
	
	public void reload() {
		for (Component boton : pnlFiltros.getComponents())
			bg.remove((AbstractButton)boton);
		pnlFiltros.removeAll();
		
		addBotonesFiltros();
	}
}
