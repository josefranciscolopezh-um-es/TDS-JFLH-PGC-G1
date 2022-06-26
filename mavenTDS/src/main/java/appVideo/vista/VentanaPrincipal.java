package appVideo.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.border.SoftBevelBorder;

import appVideo.controlador.Controlador;
import pulsador.IEncendidoListener;
import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.UIManager;
import java.awt.Dimension;
import javax.swing.border.EtchedBorder;
import java.awt.Component;
import javax.swing.Box;
import pulsador.Luz;

public class VentanaPrincipal implements IEncendidoListener{
	private static VentanaPrincipal unicaInstancia;
	private JFrame frmPrincipal;
	private JPanel pnlPrincipal;
	private JLabel lblLogin;
	private JButton btnPopulares;
	private JButton btnFiltros;
	private JButton btnPdf;
	
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
		
		Luz luz = new Luz();
		luz.setColor(Color.RED);
		luz.addEncendidoListener(this);
		pnlMenu.add(luz);
		
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
		
		btnPopulares = new JButton("Populares");
		btnPopulares.setBackground(Color.ORANGE);
		pnlMenu.add(btnPopulares);
		addManejadorBotonPopulares(btnPopulares);
		btnPopulares.setVisible(false);
		
		btnFiltros = new JButton("Filtros");
		btnFiltros.setBackground(Color.ORANGE);
		pnlMenu.add(btnFiltros);
		addManejadorBotonFiltros(btnFiltros);
		btnFiltros.setVisible(false);
		
		btnPdf = new JButton("Generar PDF");
		btnPdf.setBackground(Color.ORANGE);
		pnlMenu.add(btnPdf);
		addManejadorBotonPdf(btnPdf);
		btnPdf.setVisible(false);
		
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
						logoutRealizado();
					} else {
						JOptionPane.showMessageDialog(PanelLogin.getUnicaInstancia(), "Se ha producido un error al intentar cerrar sesión", "Error", JOptionPane.ERROR_MESSAGE);
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
					int respuesta;
					if (Controlador.getUnicaInstancia().isUsuarioPremium()) {
						respuesta = JOptionPane.showConfirmDialog(PanelNuevaLista.getUnicaInstancia(), "Actualmente su cuenta es Premium. ¿Desea dejar de serlo?", "Aviso", JOptionPane.YES_NO_OPTION);

						if (respuesta == JOptionPane.YES_OPTION) {
							Controlador.getUnicaInstancia().removeUsuarioPremium();
							
							btnPopulares.setVisible(false);
							btnFiltros.setVisible(false);
							btnPdf.setVisible(false);
							frmPrincipal.repaint();
							
							pnlPrincipal.setLayout(new BorderLayout());
							cambiarPanel(PanelRecientes.getUnicaInstancia());
							
							JOptionPane.showMessageDialog(frmPrincipal, "Ya no es usuario Premium", "Aviso", JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						respuesta = JOptionPane.showConfirmDialog(PanelNuevaLista.getUnicaInstancia(), "Actualmente su cuenta no es Premium. ¿Desea serlo?", "Aviso", JOptionPane.YES_NO_OPTION);
						
						if (respuesta == JOptionPane.YES_OPTION) {
							Controlador.getUnicaInstancia().setUsuarioPremium();
							
							btnPopulares.setVisible(true);
							btnFiltros.setVisible(true);
							btnPdf.setVisible(true);
							frmPrincipal.repaint();
							
							JOptionPane.showMessageDialog(frmPrincipal, "Es ahora usuario Premium", "Aviso", JOptionPane.INFORMATION_MESSAGE);
						}
					}
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
					PanelExplorar.getUnicaInstancia().reload();
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
					PanelMisListas.getUnicaInstancia().reload();
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
					PanelRecientes.getUnicaInstancia().reload();
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
					PanelNuevaLista.getUnicaInstancia().reload();
					pnlPrincipal.setLayout(new BorderLayout());
					cambiarPanel(PanelNuevaLista.getUnicaInstancia());
				}
			}
		});
	}
	
	private void addManejadorBotonPopulares(JButton btnPopulares) {
		btnPopulares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelPopulares.getUnicaInstancia().reload();
				pnlPrincipal.setLayout(new BorderLayout());
				cambiarPanel(PanelPopulares.getUnicaInstancia());
			}
		});
	}
	
	private void addManejadorBotonFiltros(JButton btnFiltros) {
		btnFiltros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelFiltros.getUnicaInstancia().reload();
				pnlPrincipal.setLayout(new FlowLayout());
				cambiarPanel(PanelFiltros.getUnicaInstancia());
			}
		});
	}
	
	private void addManejadorBotonPdf(JButton btnPdf) {
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(PanelNuevaLista.getUnicaInstancia(), "¿Desea generar un PDF con sus listas de videos?", "Aviso", JOptionPane.YES_NO_OPTION);
				
				if (respuesta == JOptionPane.YES_OPTION) {
					if (Controlador.getUnicaInstancia().generarPdf()) {
						JOptionPane.showMessageDialog(frmPrincipal, "Documento generado correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(frmPrincipal, "Se ha producido un error al intentar generar el documento", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
	
	private void cambiarPanel(JPanel panel) {
		// Cambia el panel principal de la ventana
		PanelReproductor.getUnicaInstancia().detener();
		pnlPrincipal.removeAll();
		pnlPrincipal.add(panel);
		pnlPrincipal.revalidate();
		pnlPrincipal.repaint();
	}

	public void loginRealizado() {
		JOptionPane.showMessageDialog(frmPrincipal, "Sesión iniciada correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);

		PanelRecientes.getUnicaInstancia().reload();
		pnlPrincipal.setLayout(new BorderLayout());
		cambiarPanel(PanelRecientes.getUnicaInstancia());
		
		lblLogin.setText("Bienvenido/a " + Controlador.getUnicaInstancia().getNombreUsuario());
		
		if (Controlador.getUnicaInstancia().isUsuarioPremium()) {
			btnPopulares.setVisible(true);
			btnFiltros.setVisible(true);
			btnPdf.setVisible(true);
			frmPrincipal.repaint();
		}
	}
	
	public void registroRealizado() {
		JOptionPane.showMessageDialog(frmPrincipal, "Usuario registrado correctamente", "Registro", JOptionPane.INFORMATION_MESSAGE);
		
		cambiarPanel(PanelLogin.getUnicaInstancia());
	}
	
	private void logoutRealizado() {
		btnPopulares.setVisible(false);
		btnFiltros.setVisible(false);
		btnPdf.setVisible(false);
		frmPrincipal.repaint();
		
		JOptionPane.showMessageDialog(frmPrincipal, "Sesión cerrada correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		
		pnlPrincipal.setLayout(new FlowLayout());
		cambiarPanel(PanelLogin.getUnicaInstancia());
		
		lblLogin.setText("Inicia Sesión o Registrate");
	}
	
	@Override
	public void enteradoCambioEncendido(EventObject e) {
		JFileChooser ventanaFichero = new JFileChooser();

		if (ventanaFichero.showOpenDialog(frmPrincipal) == JFileChooser.APPROVE_OPTION) {
			String fichero = ventanaFichero.getSelectedFile().getAbsolutePath();
			
			if (fichero != null)
				Controlador.getUnicaInstancia().getNuevosVideos(fichero);
		}
	}
}
