package appVideo.vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JToggleButton;
import javax.swing.JTextPane;
import javax.swing.JList;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class PanelExplorar extends JPanel {
	private static PanelExplorar unicaInstancia;
	private JTextField textField;
	
	public static PanelExplorar getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new PanelExplorar();
		return unicaInstancia;
	}
	
	/**
	 * Create the panel.
	 */
	private PanelExplorar() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(10, 10));
		
		JPanel pnlEtiquetas = new JPanel();
		pnlEtiquetas.setBorder(new TitledBorder(null, "Filtrar por Etiquetas: ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(pnlEtiquetas, BorderLayout.EAST);
		pnlEtiquetas.setLayout(new BoxLayout(pnlEtiquetas, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		pnlEtiquetas.add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JToggleButton tglbtnDibujosAnimados = new JToggleButton("Dibujos Animados");
		tglbtnDibujosAnimados.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(tglbtnDibujosAnimados);
		
		JToggleButton tglbtnPelicula = new JToggleButton("Película");
		tglbtnPelicula.setPreferredSize(new Dimension(160, 25));
		tglbtnPelicula.setMaximumSize(new Dimension(160, 25));
		tglbtnPelicula.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(tglbtnPelicula);
		
		JToggleButton tglbtnSerie = new JToggleButton("Serie");
		tglbtnSerie.setPreferredSize(new Dimension(160, 25));
		tglbtnSerie.setMaximumSize(new Dimension(160, 25));
		tglbtnSerie.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(tglbtnSerie);
		
		JToggleButton tglbtnIntriga = new JToggleButton("Intriga");
		tglbtnIntriga.setPreferredSize(new Dimension(160, 25));
		tglbtnIntriga.setMaximumSize(new Dimension(160, 25));
		tglbtnIntriga.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(tglbtnIntriga);
		
		JToggleButton tglbtnTerror = new JToggleButton("Terror");
		tglbtnTerror.setPreferredSize(new Dimension(160, 25));
		tglbtnTerror.setMaximumSize(new Dimension(160, 25));
		tglbtnTerror.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(tglbtnTerror);
		
		JToggleButton tglbtnVideoclip = new JToggleButton("Videoclip");
		tglbtnVideoclip.setPreferredSize(new Dimension(160, 25));
		tglbtnVideoclip.setMaximumSize(new Dimension(160, 25));
		tglbtnVideoclip.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(tglbtnVideoclip);
		
		JToggleButton tglbtnInfantil = new JToggleButton("Infantil");
		tglbtnInfantil.setPreferredSize(new Dimension(160, 25));
		tglbtnInfantil.setMaximumSize(new Dimension(160, 25));
		tglbtnInfantil.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(tglbtnInfantil);
		
		JPanel pnlBusqueda = new JPanel();
		add(pnlBusqueda, BorderLayout.CENTER);
		pnlBusqueda.setLayout(new BorderLayout(10, 10));
		
		JPanel pnlBotones = new JPanel();
		pnlBotones.setAlignmentY(Component.TOP_ALIGNMENT);
		pnlBotones.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new EmptyBorder(5, 5, 5, 5)));
		pnlBusqueda.add(pnlBotones, BorderLayout.NORTH);
		pnlBotones.setLayout(new BorderLayout(10, 15));
		
		JLabel lblBuscarTitulo = new JLabel("Buscar Título: ");
		lblBuscarTitulo.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlBotones.add(lblBuscarTitulo, BorderLayout.WEST);
		
		textField = new JTextField();
		pnlBotones.add(textField, BorderLayout.CENTER);
		textField.setColumns(20);
		
		JButton btnBuscar = new JButton("Buscar");
		pnlBotones.add(btnBuscar, BorderLayout.EAST);
		
		JButton btnLimpiarResultados = new JButton("Limpiar Resultados");
		pnlBotones.add(btnLimpiarResultados, BorderLayout.SOUTH);
		
		JPanel pnlResultados = new JPanel();
		pnlResultados.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new EmptyBorder(5, 5, 5, 5)));
		pnlBusqueda.add(pnlResultados, BorderLayout.CENTER);
		pnlResultados.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}

}
