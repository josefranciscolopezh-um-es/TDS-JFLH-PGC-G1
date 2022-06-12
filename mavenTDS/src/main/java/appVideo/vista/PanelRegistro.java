package appVideo.vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Component;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;

public class PanelRegistro extends JPanel {
	private static PanelRegistro unicaInstancia;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtEmail;
	private JTextField txtUsuario;
	private JPasswordField txtContrasea;
	private JPasswordField txtConfirmar;
	
	public static PanelRegistro getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new PanelRegistro();
		return unicaInstancia;
	}

	/**
	 * Create the panel.
	 */
	private PanelRegistro() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pnlCampos = new JPanel();
		pnlCampos.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Registrarse", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		add(pnlCampos);
		pnlCampos.setLayout(new BoxLayout(pnlCampos, BoxLayout.Y_AXIS));
		
		JPanel pnlDatosPersonales = new JPanel();
		pnlDatosPersonales.setBorder(new TitledBorder(null, "Datos Personales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCampos.add(pnlDatosPersonales);
		pnlDatosPersonales.setLayout(new BoxLayout(pnlDatosPersonales, BoxLayout.Y_AXIS));
		
		JPanel pnlNombre = new JPanel();
		pnlDatosPersonales.add(pnlNombre);
		pnlNombre.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNombre = new JLabel("* Nombre: ");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlNombre.add(lblNombre);
		
		txtNombre = new JTextField();
		pnlNombre.add(txtNombre, BorderLayout.EAST);
		txtNombre.setColumns(20);
		
		JPanel pnlApellidos = new JPanel();
		pnlDatosPersonales.add(pnlApellidos);
		pnlApellidos.setLayout(new BorderLayout(0, 0));
		
		JLabel lblApellidos = new JLabel("Apellidos: ");
		lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlApellidos.add(lblApellidos, BorderLayout.CENTER);
		
		txtApellidos = new JTextField();
		pnlApellidos.add(txtApellidos, BorderLayout.EAST);
		txtApellidos.setColumns(20);
		
		JPanel pnlNacimiento = new JPanel();
		pnlDatosPersonales.add(pnlNacimiento);
		pnlNacimiento.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNacimiento = new JLabel("* Fecha de Nacimiento: ");
		lblNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlNacimiento.add(lblNacimiento, BorderLayout.CENTER);
		
		JDateChooser fechaNacimiento = new JDateChooser();
		fechaNacimiento.setPreferredSize(new Dimension(130, 19));
		fechaNacimiento.setDateFormatString("dd/MM/yyyy");
		pnlNacimiento.add(fechaNacimiento, BorderLayout.EAST);
		
		JPanel pnlEmail = new JPanel();
		pnlDatosPersonales.add(pnlEmail);
		pnlEmail.setLayout(new BorderLayout(0, 0));
		
		JLabel lblEmail = new JLabel("E-mail: ");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlEmail.add(lblEmail, BorderLayout.CENTER);
		
		txtEmail = new JTextField();
		pnlEmail.add(txtEmail, BorderLayout.EAST);
		txtEmail.setColumns(20);
		
		JPanel pnlCredenciales = new JPanel();
		pnlCredenciales.setBorder(new TitledBorder(null, "Credenciales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCampos.add(pnlCredenciales);
		pnlCredenciales.setLayout(new BoxLayout(pnlCredenciales, BoxLayout.Y_AXIS));
		
		JPanel pnlUsuario = new JPanel();
		pnlCredenciales.add(pnlUsuario);
		pnlUsuario.setLayout(new BorderLayout(0, 0));
		
		JLabel lblUsuario = new JLabel("* Usuario: ");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlUsuario.add(lblUsuario, BorderLayout.CENTER);
		
		txtUsuario = new JTextField();
		pnlUsuario.add(txtUsuario, BorderLayout.EAST);
		txtUsuario.setColumns(15);
		
		JPanel pnlContraseña = new JPanel();
		pnlCredenciales.add(pnlContraseña);
		pnlContraseña.setLayout(new BorderLayout(0, 0));
		
		JLabel lblContrasea = new JLabel("* Contraseña: ");
		lblContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlContraseña.add(lblContrasea, BorderLayout.CENTER);
		
		txtContrasea = new JPasswordField();
		txtContrasea.setColumns(15);
		pnlContraseña.add(txtContrasea, BorderLayout.EAST);
		
		JPanel pnlConfirmar = new JPanel();
		pnlCredenciales.add(pnlConfirmar);
		pnlConfirmar.setLayout(new BorderLayout(0, 0));
		
		JLabel lblConfirmar = new JLabel("* Confirmar Contraseña: ");
		lblConfirmar.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlConfirmar.add(lblConfirmar, BorderLayout.CENTER);
		
		txtConfirmar = new JPasswordField();
		txtConfirmar.setColumns(15);
		pnlConfirmar.add(txtConfirmar, BorderLayout.EAST);
		
		JPanel pnlBotones = new JPanel();
		pnlBotones.setBorder(new EmptyBorder(5, 0, 5, 0));
		add(pnlBotones);
		pnlBotones.setLayout(new BoxLayout(pnlBotones, BoxLayout.Y_AXIS));
		
		JButton btnRegistro = new JButton("Registrarse");
		btnRegistro.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlBotones.add(btnRegistro);
		
		JLabel lblAviso = new JLabel("* Los campos con un asterisco son OBLIGATORIOS");
		lblAviso.setHorizontalAlignment(SwingConstants.LEFT);
		lblAviso.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlBotones.add(lblAviso);

	}

}
