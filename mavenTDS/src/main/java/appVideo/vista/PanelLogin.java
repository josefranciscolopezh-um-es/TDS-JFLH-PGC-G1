package appVideo.vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.FlowLayout;

import appVideo.controlador.Controlador;

public class PanelLogin extends JPanel {
	private static PanelLogin unicaInstancia;
	private JTextField txtUsuario;
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
		
		txtUsuario = new JTextField();
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		pnlUsuario.add(txtUsuario, BorderLayout.EAST);
		txtUsuario.setColumns(15);
		
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

	}
	
	private void addManejadorBotonLogin(JButton btnLogin) {
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean login = Controlador.getUnicaInstancia().loginUsuario(txtUsuario.getText(),
						new String(txtPassword.getPassword()));

				if (login) {
					VentanaPrincipal.getUnicaInstancia().loginRealizado();
				} else
					JOptionPane.showMessageDialog(PanelLogin.getUnicaInstancia(), "Nombre de usuario o contraseña no válido",
							"Error", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
}
