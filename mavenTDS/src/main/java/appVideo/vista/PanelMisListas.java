package appVideo.vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextField;

public class PanelMisListas extends JPanel {
	private static PanelMisListas unicaInstancia;
	private JTextField txtNuevaEtiqueta;
	
	public static PanelMisListas getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new PanelMisListas();
		return unicaInstancia;
	}
	/**
	 * Create the panel.
	 */
	private PanelMisListas() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(10, 0));
		
		JPanel pnlSeleccion = new JPanel();
		pnlSeleccion.setBorder(new EmptyBorder(0, 0, 10, 0));
		add(pnlSeleccion, BorderLayout.WEST);
		pnlSeleccion.setLayout(new BorderLayout(0, 10));
		
		JPanel pnlSeleccionLista = new JPanel();
		pnlSeleccionLista.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new EmptyBorder(5, 5, 5, 5)));
		pnlSeleccion.add(pnlSeleccionLista, BorderLayout.NORTH);
		pnlSeleccionLista.setLayout(new GridLayout(3, 1, 5, 5));
		
		JLabel lblSeleccioneLaLista = new JLabel("Seleccione la lista: ");
		lblSeleccioneLaLista.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlSeleccionLista.add(lblSeleccioneLaLista);
		
		JComboBox comboBox = new JComboBox();
		pnlSeleccionLista.add(comboBox);
		
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
