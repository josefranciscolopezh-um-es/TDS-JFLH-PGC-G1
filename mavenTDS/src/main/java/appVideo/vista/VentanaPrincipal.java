package appVideo.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.border.SoftBevelBorder;

import appVideo.controlador.Controlador;

import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Component;
import javax.swing.Box;

public class VentanaPrincipal {
	private static VentanaPrincipal unicaInstancia;
	private JFrame frmPrincipal;
	private JPanel pnlPrincipal;
	private JLabel lblLogin;
	
	public static VentanaPrincipal getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new VentanaPrincipal();
		return unicaInstancia;
	}

	/**
	 * Create the application.
	 */
	private VentanaPrincipal() {
		initialize();
	}
	
	public void mostrarVentana() {
		frmPrincipal.setLocationRelativeTo(null);
		frmPrincipal.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.setMinimumSize(new Dimension(875, 130));
		frmPrincipal.setBounds(100, 100, 1048, 659);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnlSuperior = new JPanel();
		pnlSuperior.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		frmPrincipal.getContentPane().add(pnlSuperior, BorderLayout.NORTH);
		pnlSuperior.setLayout(new BoxLayout(pnlSuperior, BoxLayout.Y_AXIS));
		
		JPanel pnlSesion = new JPanel();
		pnlSesion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlSuperior.add(pnlSesion);
		pnlSesion.setLayout(new BorderLayout(0, 0));
		
		JLabel lblAppvideo = new JLabel("AppVideo");
		pnlSesion.add(lblAppvideo, BorderLayout.WEST);
		lblAppvideo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblAppvideo.setLabelFor(frmPrincipal);
		lblAppvideo.setForeground(Color.RED);
		lblAppvideo.setBackground(Color.WHITE);
		lblAppvideo.setFont(new Font("L M Mono10", Font.BOLD, 30));
		lblAppvideo.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblLogin = new JLabel("Inicia Sesión o Registrate");
		pnlSesion.add(lblLogin, BorderLayout.CENTER);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel pnlCuenta = new JPanel();
		pnlSesion.add(pnlCuenta, BorderLayout.EAST);
		pnlCuenta.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JButton btnReg = new JButton("Registrarse");
		pnlCuenta.add(btnReg);
		btnReg.setHorizontalAlignment(SwingConstants.RIGHT);
		addManejadorBotonRegistro(btnReg);
		
		JButton btnLogin = new JButton("Iniciar Sesión");
		pnlCuenta.add(btnLogin);
		btnLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		addManejadorBotonLogin(btnLogin);
		
		JButton btnLogout = new JButton("Cerrar Sesión");
		btnLogout.setBackground(UIManager.getColor("Button.background"));
		pnlCuenta.add(btnLogout);
		addManejadorBotonLogout(btnLogout);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		pnlCuenta.add(rigidArea);
		
		JButton btnPremium = new JButton("Premium");
		btnPremium.setBackground(SystemColor.info);
		pnlCuenta.add(btnPremium);
		addManejadorBotonPremium(btnPremium);
		
		JPanel pnlMenu = new JPanel();
		FlowLayout fl_pnlMenu = (FlowLayout) pnlMenu.getLayout();
		fl_pnlMenu.setAlignment(FlowLayout.LEFT);
		pnlSuperior.add(pnlMenu);
		
		JButton btnExplorar = new JButton("Explorar");
		btnExplorar.setBackground(Color.ORANGE);
		pnlMenu.add(btnExplorar);
		addManejadorBotonExplorar(btnExplorar);
		
		JButton btnMisListas = new JButton("Mis Listas");
		btnMisListas.setBackground(Color.ORANGE);
		pnlMenu.add(btnMisListas);
		addManejadorBotonMisListas(btnMisListas);
		
		JButton btnRecientes = new JButton("Recientes");
		btnRecientes.setBackground(Color.ORANGE);
		pnlMenu.add(btnRecientes);
		addManejadorBotonRecientes(btnRecientes);
		
		JButton btnNuevaLista = new JButton("Nueva Lista");
		btnNuevaLista.setBackground(Color.ORANGE);
		pnlMenu.add(btnNuevaLista);
		addManejadorBotonNuevaLista(btnNuevaLista);
		
		JButton btnPopulares = new JButton("Populares");
		btnPopulares.setBackground(Color.ORANGE);
		pnlMenu.add(btnPopulares);
		addManejadorBotonPopulares(btnPopulares);
		
		pnlPrincipal = new JPanel();
		frmPrincipal.getContentPane().add(pnlPrincipal, BorderLayout.CENTER);
		cambiarPanel(PanelLogin.getUnicaInstancia());
	}
	
	private void addManejadorBotonLogin(JButton btnLogin) {
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlPrincipal.setLayout(new FlowLayout());
				cambiarPanel(PanelLogin.getUnicaInstancia());
			}
		});
	}
	
	private void addManejadorBotonRegistro(JButton btnReg) {
		btnReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlPrincipal.setLayout(new FlowLayout());
				cambiarPanel(PanelRegistro.getUnicaInstancia());
			}
		});
	}
	
	private void addManejadorBotonLogout(JButton btnLogout) {
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Controlador.getUnicaInstancia().getUsuarioActual() == null) {
					JOptionPane.showMessageDialog(frmPrincipal, "No hay ninguna sesión iniciada", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					boolean logout = Controlador.getUnicaInstancia().logoutUsuario();
					if (logout) {
						JOptionPane.showMessageDialog(frmPrincipal, "Sesión cerrada correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
						pnlPrincipal.setLayout(new FlowLayout());
						cambiarPanel(PanelLogin.getUnicaInstancia());
						lblLogin.setText("Inicia Sesión o Registrate");
					} else {
						JOptionPane.showMessageDialog(PanelLogin.getUnicaInstancia(), "Se ha producido un error\nInténtelo de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
	
	private void addManejadorBotonPremium(JButton btnPremium) {
		btnPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Controlador.getUnicaInstancia().getUsuarioActual() == null) {
					JOptionPane.showMessageDialog(frmPrincipal, "No hay ninguna sesión iniciada", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
				}
			}
		});
	}
	
	private void addManejadorBotonExplorar(JButton btnExplorar) {
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Controlador.getUnicaInstancia().getUsuarioActual() == null) {
					JOptionPane.showMessageDialog(frmPrincipal, "No hay ninguna sesión iniciada", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					pnlPrincipal.setLayout(new BorderLayout());
					cambiarPanel(PanelExplorar.getUnicaInstancia());
				}
			}
		});
	}
	
	private void addManejadorBotonMisListas(JButton btnMisListas) {
		btnMisListas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Controlador.getUnicaInstancia().getUsuarioActual() == null) {
					JOptionPane.showMessageDialog(frmPrincipal, "No hay ninguna sesión iniciada", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					pnlPrincipal.setLayout(new BorderLayout());
					cambiarPanel(PanelMisListas.getUnicaInstancia());
				}
			}
		});
	}
	
	private void addManejadorBotonRecientes(JButton btnRecientes) {
		btnRecientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Controlador.getUnicaInstancia().getUsuarioActual() == null) {
					JOptionPane.showMessageDialog(frmPrincipal, "No hay ninguna sesión iniciada", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					pnlPrincipal.setLayout(new BorderLayout());
					cambiarPanel(PanelRecientes.getUnicaInstancia());
				}
			}
		});
	}
	
	private void addManejadorBotonNuevaLista(JButton btnNuevaLista) {
		btnNuevaLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Controlador.getUnicaInstancia().getUsuarioActual() == null) {
					JOptionPane.showMessageDialog(frmPrincipal, "No hay ninguna sesión iniciada", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					pnlPrincipal.setLayout(new BorderLayout());
					cambiarPanel(PanelNuevaLista.getUnicaInstancia());
				}
			}
		});
	}
	
	private void addManejadorBotonPopulares(JButton btnPopulares) {
		btnPopulares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Controlador.getUnicaInstancia().getUsuarioActual() == null) {
					JOptionPane.showMessageDialog(frmPrincipal, "No hay ninguna sesión iniciada", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					pnlPrincipal.setLayout(new BorderLayout());
					cambiarPanel(PanelPopulares.getUnicaInstancia());
				}
			}
		});
	}
	
	public void cambiarPanel(JPanel panel) {
		pnlPrincipal.removeAll();
		pnlPrincipal.add(panel);
		pnlPrincipal.revalidate();
		pnlPrincipal.repaint();
	}

	public void loginRealizado() {
		JOptionPane.showMessageDialog(frmPrincipal, "Sesión iniciada correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		pnlPrincipal.setLayout(new BorderLayout());
		cambiarPanel(PanelRecientes.getUnicaInstancia());
		lblLogin.setText("Bienvenido/a " + Controlador.getUnicaInstancia().getNombreUsuario());
	}

}
