package um.tds.mavenTDS.vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class PanelNuevaLista extends JPanel {
	private static PanelNuevaLista unicaInstancia;
	private JTextField textField;
	private JTextField txtNombreLista;
	
	public static PanelNuevaLista getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new PanelNuevaLista();
		return unicaInstancia;
	}
	/**
	 * Create the panel.
	 */
	public PanelNuevaLista() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(10, 10));
		
		JPanel pnlBusqueda = new JPanel();
		add(pnlBusqueda, BorderLayout.CENTER);
		pnlBusqueda.setLayout(new BorderLayout(10, 10));
		
		JPanel pnlBotones = new JPanel();
		pnlBotones.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new EmptyBorder(10, 5, 10, 5)));
		pnlBotones.setAlignmentY(0.0f);
		pnlBusqueda.add(pnlBotones, BorderLayout.NORTH);
		pnlBotones.setLayout(new BorderLayout(10, 15));
		
		JLabel lblBuscarTitulo = new JLabel("Buscar Título: ");
		lblBuscarTitulo.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlBotones.add(lblBuscarTitulo, BorderLayout.WEST);
		
		textField = new JTextField();
		textField.setColumns(20);
		pnlBotones.add(textField, BorderLayout.CENTER);
		
		JButton btnBuscar = new JButton("Buscar");
		pnlBotones.add(btnBuscar, BorderLayout.EAST);
		
		JButton btnLimpiarResultados = new JButton("Limpiar Resultados");
		pnlBotones.add(btnLimpiarResultados, BorderLayout.SOUTH);
		
		JPanel pnlResultados = new JPanel();
		pnlResultados.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new EmptyBorder(5, 5, 5, 5)));
		pnlBusqueda.add(pnlResultados, BorderLayout.CENTER);
		pnlResultados.setLayout(new BoxLayout(pnlResultados, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		pnlResultados.add(scrollPane_1);
		
		JList list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		
		JPanel pnlLista = new JPanel();
		add(pnlLista, BorderLayout.WEST);
		pnlLista.setLayout(new BorderLayout(10, 10));
		
		JPanel pnlNombre = new JPanel();
		pnlNombre.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new EmptyBorder(5, 5, 5, 5)));
		pnlLista.add(pnlNombre, BorderLayout.NORTH);
		pnlNombre.setLayout(new BoxLayout(pnlNombre, BoxLayout.Y_AXIS));
		
		JLabel lblIntroducirNombreLista = new JLabel("Introducir nombre lista:");
		lblIntroducirNombreLista.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblIntroducirNombreLista.setHorizontalAlignment(SwingConstants.CENTER);
		pnlNombre.add(lblIntroducirNombreLista);
		
		JPanel pnlBusquedaNombre = new JPanel();
		pnlNombre.add(pnlBusquedaNombre);
		
		txtNombreLista = new JTextField();
		pnlBusquedaNombre.add(txtNombreLista);
		txtNombreLista.setColumns(10);
		
		JButton btnBuscarLista = new JButton("Buscar");
		pnlBusquedaNombre.add(btnBuscarLista);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlNombre.add(btnEliminar);
		
		JPanel pnlContenido = new JPanel();
		pnlContenido.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new EmptyBorder(5, 5, 5, 5)));
		pnlLista.add(pnlContenido, BorderLayout.CENTER);
		pnlContenido.setLayout(new BoxLayout(pnlContenido, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		pnlContenido.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JPanel pnlBotonesLista = new JPanel();
		pnlBotonesLista.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new EmptyBorder(5, 30, 5, 30)));
		pnlLista.add(pnlBotonesLista, BorderLayout.SOUTH);
		pnlBotonesLista.setLayout(new BorderLayout(10, 10));
		
		JButton btnAadir = new JButton("Añadir");
		btnAadir.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlBotonesLista.add(btnAadir, BorderLayout.WEST);
		
		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.setHorizontalAlignment(SwingConstants.LEFT);
		pnlBotonesLista.add(btnQuitar, BorderLayout.EAST);
		
		JButton btnAceptar = new JButton("Aceptar");
		pnlBotonesLista.add(btnAceptar, BorderLayout.SOUTH);

	}

}
