package appVideo.vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import com.toedter.calendar.JDateChooser;

import appVideo.controlador.Controlador;

import java.awt.Dimension;

@SuppressWarnings("serial")
public class PanelRegistro extends JPanel {
	private static PanelRegistro unicaInstancia;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JDateChooser fechaNacimiento;
	private JTextField txtEmail;
	private JTextField txtLogin;
	private JPasswordField txtPassword;
	private JPasswordField txtPasswordChk;
	
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
		
		fechaNacimiento = new JDateChooser();
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
		
		txtLogin = new JTextField();
		pnlUsuario.add(txtLogin, BorderLayout.EAST);
		txtLogin.setColumns(15);
		
		JPanel pnlPassword = new JPanel();
		pnlCredenciales.add(pnlPassword);
		pnlPassword.setLayout(new BorderLayout(0, 0));
		
		JLabel lblPassword = new JLabel("* Contraseña: ");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlPassword.add(lblPassword, BorderLayout.CENTER);
		
		txtPassword = new JPasswordField();
		txtPassword.setColumns(15);
		pnlPassword.add(txtPassword, BorderLayout.EAST);
		
		JPanel pnlPasswordChk = new JPanel();
		pnlCredenciales.add(pnlPasswordChk);
		pnlPasswordChk.setLayout(new BorderLayout(0, 0));
		
		JLabel lblPasswordChk = new JLabel("* PasswordChk Contraseña: ");
		lblPasswordChk.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlPasswordChk.add(lblPasswordChk, BorderLayout.CENTER);
		
		txtPasswordChk = new JPasswordField();
		txtPasswordChk.setColumns(15);
		pnlPasswordChk.add(txtPasswordChk, BorderLayout.EAST);
		
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
		
		crearManejadorBotonRegistro(btnRegistro);

	}
	
	private void crearManejadorBotonRegistro(JButton btnRegistro) {
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkFields()) {
					boolean registrado = false;
					registrado = Controlador.getUnicaInstancia().registrarUsuario(txtNombre.getText(),
							txtApellidos.getText(), txtEmail.getText(), txtLogin.getText(),
							new String(txtPassword.getPassword()), 
							Controlador.getUnicaInstancia().getFormatoFecha().format(fechaNacimiento.getDate()));
					if (registrado) {
						clearFields();
						VentanaPrincipal.getUnicaInstancia().registroRealizado();
					} else {
						JOptionPane.showMessageDialog(PanelRegistro.getUnicaInstancia(), "No se ha podido llevar a cabo el registro",
								"Registro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	private boolean checkFields() {
		String password = new String(txtPassword.getPassword());
		String passwordChk = new String(txtPasswordChk.getPassword());
		
		// Comprobar que se hayan rellenado todos los campos obligatorios
		if (txtNombre.getText().trim().isEmpty() || fechaNacimiento.getDate() == null || txtLogin.getText().trim().isEmpty() || password.isEmpty() || passwordChk.isEmpty()) {
			JOptionPane.showMessageDialog(PanelRegistro.getUnicaInstancia(), "Se deben rellenar todos los campos con un asterisco", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		// Comprobar que las contraseñas son iguales
		else if (!password.equals(passwordChk)) {
			JOptionPane.showMessageDialog(PanelRegistro.getUnicaInstancia(), "Las contraseñas introducidas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// Comprobar que no exista otro usuario con igual login
		else if (Controlador.getUnicaInstancia().esUsuarioRegistrado(txtLogin.getText())) {
			JOptionPane.showMessageDialog(PanelRegistro.getUnicaInstancia(), "Ese nombre de usuario ya está en uso", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		else return true;
	}
	
	private void clearFields() {
		// Limpia todos los campos de texto
		
		txtNombre.setText("");
		txtApellidos.setText("");
		fechaNacimiento.setDate(new Date());
		txtEmail.setText("");
		txtLogin.setText("");
		txtPassword.setText("");
		txtPasswordChk.setText("");
	}
}
