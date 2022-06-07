package um.tds.mavenTDS.vista;

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

	private JFrame frmPrincipal;
	private JPanel pnlPrincipal;

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
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
		
		JLabel lblIniciaSesinO = new JLabel("Inicia Sesión o Registrate");
		pnlSesion.add(lblIniciaSesinO, BorderLayout.CENTER);
		lblIniciaSesinO.setHorizontalAlignment(SwingConstants.CENTER);
		
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
		
		JButton btnRecientes = new JButton("Recientes");
		btnRecientes.setBackground(Color.ORANGE);
		pnlMenu.add(btnRecientes);
		
		JButton btnNuevaLista = new JButton("Nueva Lista");
		btnNuevaLista.setBackground(Color.ORANGE);
		pnlMenu.add(btnNuevaLista);
		addManejadorBotonNuevaLista(btnNuevaLista);
		
		JButton btnPopulares = new JButton("Populares");
		btnPopulares.setBackground(Color.ORANGE);
		pnlMenu.add(btnPopulares);
		
		pnlPrincipal = new JPanel();
		frmPrincipal.getContentPane().add(pnlPrincipal, BorderLayout.CENTER);
		//frmPrincipal.getContentPane().add(PanelLogin.getUnicaInstancia(), BorderLayout.CENTER);
	}
	
	private void addManejadorBotonLogin(JButton btnLogin) {
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlPrincipal.removeAll();
				pnlPrincipal.setLayout(new FlowLayout());
				pnlPrincipal.add(PanelLogin.getUnicaInstancia());
				
				pnlPrincipal.revalidate();
				pnlPrincipal.repaint();
				/*frmPrincipal.remove(((BorderLayout)(frmPrincipal.getContentPane().getLayout())).getLayoutComponent(BorderLayout.CENTER));
				frmPrincipal.getContentPane().add(PanelLogin.getUnicaInstancia(), BorderLayout.CENTER);
				
				frmPrincipal.getContentPane().revalidate();
				frmPrincipal.getContentPane().repaint();*/
			}
		});
	}
	
	private void addManejadorBotonRegistro(JButton btnReg) {
		btnReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlPrincipal.removeAll();
				pnlPrincipal.setLayout(new FlowLayout());
				pnlPrincipal.add(PanelRegistro.getUnicaInstancia());
				
				pnlPrincipal.revalidate();
				pnlPrincipal.repaint();
				
				/*frmPrincipal.remove(((BorderLayout)(frmPrincipal.getContentPane().getLayout())).getLayoutComponent(BorderLayout.CENTER));
				frmPrincipal.getContentPane().add(PanelRegistro.getUnicaInstancia(), BorderLayout.CENTER);
				
				frmPrincipal.getContentPane().revalidate();
				frmPrincipal.getContentPane().repaint();*/
			}
		});
	}
	
	private void addManejadorBotonLogout(JButton btnLogout) {
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(frmPrincipal, "No hay ninguna sesión iniciada", "Error", JOptionPane.ERROR_MESSAGE);
				//Controlador.getUnicaInstancia().logOut()
				JOptionPane.showMessageDialog(frmPrincipal, "Sesión cerrada correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
	
	private void addManejadorBotonPremium(JButton btnPremium) {
		btnPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frmPrincipal, "No hay ninguna sesión iniciada", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
	
	private void addManejadorBotonExplorar(JButton btnExplorar) {
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(frmPrincipal, "No hay ninguna sesión iniciada", "Error", JOptionPane.ERROR_MESSAGE);
				pnlPrincipal.removeAll();
				pnlPrincipal.setLayout(new BorderLayout());
				pnlPrincipal.add(PanelExplorar.getUnicaInstancia(), BorderLayout.CENTER);
				
				pnlPrincipal.revalidate();
				pnlPrincipal.repaint();
			}
		});
	}
	
	private void addManejadorBotonMisListas(JButton btnMisListas) {
		btnMisListas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(frmPrincipal, "No hay ninguna sesión iniciada", "Error", JOptionPane.ERROR_MESSAGE);
				pnlPrincipal.removeAll();
				pnlPrincipal.setLayout(new BorderLayout());
				pnlPrincipal.add(PanelMisListas.getUnicaInstancia(), BorderLayout.CENTER);
				
				pnlPrincipal.revalidate();
				pnlPrincipal.repaint();
			}
		});
	}
	
	private void addManejadorBotonRecientes(JButton btnRecientes) {
		btnRecientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(frmPrincipal, "No hay ninguna sesión iniciada", "Error", JOptionPane.ERROR_MESSAGE);
				pnlPrincipal.removeAll();
				pnlPrincipal.setLayout(new BorderLayout());
				pnlPrincipal.add(PanelRecientes.getUnicaInstancia(), BorderLayout.CENTER);
				
				pnlPrincipal.revalidate();
				pnlPrincipal.repaint();
			}
		});
	}
	
	private void addManejadorBotonNuevaLista(JButton btnNuevaLista) {
		btnNuevaLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(frmPrincipal, "No hay ninguna sesión iniciada", "Error", JOptionPane.ERROR_MESSAGE);
				pnlPrincipal.removeAll();
				pnlPrincipal.setLayout(new BorderLayout());
				pnlPrincipal.add(PanelNuevaLista.getUnicaInstancia(), BorderLayout.CENTER);
				
				pnlPrincipal.revalidate();
				pnlPrincipal.repaint();
			}
		});
	}
	
	private void addManejadorBotonPopulares(JButton btnPopulares) {
		btnPopulares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(frmPrincipal, "No hay ninguna sesión iniciada", "Error", JOptionPane.ERROR_MESSAGE);
				pnlPrincipal.removeAll();
				pnlPrincipal.setLayout(new BorderLayout());
				pnlPrincipal.add(PanelPopulares.getUnicaInstancia(), BorderLayout.CENTER);
				
				pnlPrincipal.revalidate();
				pnlPrincipal.repaint();
			}
		});
	}

}
