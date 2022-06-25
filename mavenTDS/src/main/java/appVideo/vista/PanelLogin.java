package appVideo.vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import appVideo.controlador.Controlador;

@SuppressWarnings("serial")
public class PanelLogin extends JPanel {
	private static PanelLogin unicaInstancia;
	private JTextField txtLogin;
	private JPasswordField txtPassword;
	
	public static PanelLogin getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new PanelLogin();
		return unicaInstancia;
	}

	/**
	 * Create the panel.
	 */
	private PanelLogin() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlCampos = new JPanel();
		pnlCampos.setBorder(new TitledBorder(null, "Iniciar Sesión", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		add(pnlCampos, BorderLayout.CENTER);
		pnlCampos.setLayout(new BoxLayout(pnlCampos, BoxLayout.Y_AXIS));
		
		JPanel pnlUsuario = new JPanel();
		pnlCampos.add(pnlUsuario);
		pnlUsuario.setLayout(new BorderLayout(0, 0));
		
		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlUsuario.add(lblUsuario, BorderLayout.CENTER);
		
		txtLogin = new JTextField();
		txtLogin.setHorizontalAlignment(SwingConstants.CENTER);
		pnlUsuario.add(txtLogin, BorderLayout.EAST);
		txtLogin.setColumns(15);
		
		JPanel pnlContrasea = new JPanel();
		pnlCampos.add(pnlContrasea);
		pnlContrasea.setLayout(new BorderLayout(0, 0));
		
		JLabel lblContrasea = new JLabel("Contraseña: ");
		lblContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlContrasea.add(lblContrasea, BorderLayout.CENTER);
		
		txtPassword = new JPasswordField();
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setColumns(15);
		pnlContrasea.add(txtPassword, BorderLayout.EAST);
		
		JPanel pnlBotones = new JPanel();
		pnlBotones.setBorder(new EmptyBorder(5, 0, 5, 0));
		add(pnlBotones, BorderLayout.SOUTH);
		
		JButton btnLogin = new JButton("Iniciar Sesión");
		pnlBotones.add(btnLogin);
		
		addManejadorBotonLogin(btnLogin);

	}
	
	private void addManejadorBotonLogin(JButton btnLogin) {
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkFields()) {
					boolean login = Controlador.getUnicaInstancia().loginUsuario(txtLogin.getText(),
							new String(txtPassword.getPassword()));
	
					if (login) {
						clearFields();
						VentanaPrincipal.getUnicaInstancia().loginRealizado();
					} else
						JOptionPane.showMessageDialog(PanelLogin.getUnicaInstancia(), "Nombre de usuario o contraseña incorrecto",
								"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	private boolean checkFields() {
		String password = new String(txtPassword.getPassword());
		
		// Comprobar que se hayan rellenado todos los campos obligatorios
		if (txtLogin.getText().trim().isEmpty() || password.isEmpty()) {
			JOptionPane.showMessageDialog(PanelLogin.getUnicaInstancia(), "Se deben rellenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		else return true;
	}
	
	private void clearFields() {
		// Limpia todos los campos de texto
		
		txtLogin.setText("");
		txtPassword.setText("");
	}
}
