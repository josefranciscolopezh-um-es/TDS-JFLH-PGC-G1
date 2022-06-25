package appVideo.vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import appVideo.controlador.Controlador;
import appVideo.dominio.Etiqueta;
import appVideo.dominio.Video;
import tds.video.VideoWeb;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import java.awt.Color;

@SuppressWarnings("serial")
public class PanelReproductor extends JPanel {
	private static PanelReproductor unicaInstancia;

	private JLabel lblTitulo;
	private JLabel lblVisualizaciones;
	private JTextField txtNuevaEtiqueta;
	private JPanel pnlEtiquetasVideo;
	
	private Video videoActual;
	
	private static VideoWeb videoWeb;
	
	public static PanelReproductor getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new PanelReproductor();
		return unicaInstancia;
	}
	
	/**
	 * Create the panel.
	 */
	public PanelReproductor() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTitulo = new JPanel();
		add(pnlTitulo, BorderLayout.NORTH);
		pnlTitulo.setLayout(new BoxLayout(pnlTitulo, BoxLayout.Y_AXIS));
		
		lblTitulo = new JLabel("Titulo");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 22));
		lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlTitulo.add(lblTitulo);
		
		lblVisualizaciones = new JLabel("Visualizaciones");
		lblVisualizaciones.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlTitulo.add(lblVisualizaciones);
		
		JPanel pnlEtiquetas = new JPanel();
		add(pnlEtiquetas, BorderLayout.SOUTH);
		pnlEtiquetas.setLayout(new BoxLayout(pnlEtiquetas, BoxLayout.Y_AXIS));
		
		pnlEtiquetasVideo = new JPanel();
		pnlEtiquetas.add(pnlEtiquetasVideo);
		pnlEtiquetasVideo.setLayout(new BoxLayout(pnlEtiquetasVideo, BoxLayout.X_AXIS));
		
		
		videoWeb = new VideoWeb();
		add(videoWeb, BorderLayout.CENTER);
		
		JPanel pnlAddEtiqueta = new JPanel();
		pnlEtiquetas.add(pnlAddEtiqueta);
		pnlAddEtiqueta.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlAddEtiqueta.setLayout(new BoxLayout(pnlAddEtiqueta, BoxLayout.X_AXIS));
		
		JLabel lblNuevaEtiqueta = new JLabel("Nueva etiqueta: ");
		pnlAddEtiqueta.add(lblNuevaEtiqueta);
		
		txtNuevaEtiqueta = new JTextField();
		pnlAddEtiqueta.add(txtNuevaEtiqueta);
		txtNuevaEtiqueta.setColumns(10);
		
		JButton btnAdd = new JButton("Añadir");
		pnlAddEtiqueta.add(btnAdd);
		
		addManejadorBtnAddEtiqueta(btnAdd);
	}

	public VideoWeb getVideoWeb() {
		return videoWeb;
	}
	
	public void reproducir(Video video) {
		detener();
		lblTitulo.setText(video.getTitulo());
		lblVisualizaciones.setText(video.getNumRepro() + " visualizaciones");
		for (Etiqueta etiqueta : video.getEtiquetas())
			pnlEtiquetasVideo.add(crearEtiquetaVideo(etiqueta.getNombre()));
		videoActual = video;
		repaint();
		
		videoWeb.playVideo(video.getUrl());
	}

	public void detener() {
		videoWeb.cancel();
		txtNuevaEtiqueta.setText("");
		pnlEtiquetasVideo.removeAll();
		videoActual = null;
		repaint();
	}
	
	private JLabel crearEtiquetaVideo(String etiqueta) {
		JLabel lblEtiqueta = new JLabel(etiqueta);
		lblEtiqueta.setBorder(new LineBorder(Color.ORANGE, 2, true));
		
		return lblEtiqueta;
	}
	
	private void addManejadorBtnAddEtiqueta(JButton btnAdd) {
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nuevaEtiqueta = txtNuevaEtiqueta.getText().trim();
				
				for (Component etiqueta : pnlEtiquetasVideo.getComponents()) {
					if (((JLabel)etiqueta).getText().equals(nuevaEtiqueta)) {
						JOptionPane.showMessageDialog(PanelReproductor.getUnicaInstancia(), "Este video ya tiene esa etiqueta", "Error", JOptionPane.ERROR_MESSAGE);
						txtNuevaEtiqueta.setText("");
						return;
					}
				}
				
				Controlador.getUnicaInstancia().addEtiqueta(videoActual, nuevaEtiqueta);
				pnlEtiquetasVideo.add(crearEtiquetaVideo(nuevaEtiqueta));
				repaint();
				
				JOptionPane.showMessageDialog(PanelReproductor.getUnicaInstancia(), "Nueva etiqueta añadida correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

}
