package appVideo.vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class PanelRecientes extends JPanel {
	private static PanelRecientes unicaInstancia;
	private JTextField txtNuevaEtiqueta;
	
	public static PanelRecientes getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new PanelRecientes();
		return unicaInstancia;
	}
	/**
	 * Create the panel.
	 */
	private PanelRecientes() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(10, 0));
		
		JPanel pnlSeleccion = new JPanel();
		pnlSeleccion.setBorder(new EmptyBorder(0, 0, 10, 0));
		add(pnlSeleccion, BorderLayout.WEST);
		pnlSeleccion.setLayout(new BorderLayout(0, 10));
		
		JPanel pnlSeleccionLista = new JPanel();
		pnlSeleccionLista.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new EmptyBorder(5, 5, 5, 5)));
		pnlSeleccion.add(pnlSeleccionLista, BorderLayout.NORTH);
		pnlSeleccionLista.setLayout(new GridLayout(2, 1, 5, 5));
		
		JLabel lblRecientes = new JLabel("Videos reproducidos recientemente: ");
		lblRecientes.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlSeleccionLista.add(lblRecientes);
		
		JButton btnReproducir = new JButton("Reproducir");
		btnReproducir.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlSeleccionLista.add(btnReproducir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlSeleccion.add(btnCancelar, BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane();
		pnlSeleccion.add(scrollPane, BorderLayout.CENTER);
		
		JPanel pnlVideo = new JPanel();
		add(pnlVideo, BorderLayout.CENTER);
		pnlVideo.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTitulo = new JPanel();
		pnlVideo.add(pnlTitulo, BorderLayout.NORTH);
		pnlTitulo.setLayout(new BoxLayout(pnlTitulo, BoxLayout.Y_AXIS));
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 22));
		lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlTitulo.add(lblTitulo);
		
		JLabel lblVisualizaciones = new JLabel("Visualizaciones: ");
		lblVisualizaciones.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlTitulo.add(lblVisualizaciones);
		
		JPanel pnlAddEtiqueta = new JPanel();
		pnlAddEtiqueta.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlVideo.add(pnlAddEtiqueta, BorderLayout.SOUTH);
		pnlAddEtiqueta.setLayout(new BoxLayout(pnlAddEtiqueta, BoxLayout.X_AXIS));
		
		JLabel lblNuevaEtiqueta = new JLabel("Nueva etiqueta: ");
		pnlAddEtiqueta.add(lblNuevaEtiqueta);
		
		txtNuevaEtiqueta = new JTextField();
		pnlAddEtiqueta.add(txtNuevaEtiqueta);
		txtNuevaEtiqueta.setColumns(10);
		
		JButton btnAdd = new JButton("Añadir");
		pnlAddEtiqueta.add(btnAdd);
	}

}
