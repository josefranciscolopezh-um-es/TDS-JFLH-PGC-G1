package appVideo.vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import appVideo.controlador.Controlador;
import appVideo.dominio.Video;

import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class PanelRecientes extends JPanel {
	private static PanelRecientes unicaInstancia;
	
	private JPanel pnlVideos;
	private JPanel pnlVideo;
	
	public static PanelRecientes getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new PanelRecientes();
		return unicaInstancia;
	}
	
	/**
	 * Create the panel.
	 */
	private PanelRecientes() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(10, 0));
		
		JPanel pnlSeleccion = new JPanel();
		pnlSeleccion.setBorder(new EmptyBorder(0, 0, 10, 0));
		add(pnlSeleccion, BorderLayout.WEST);
		pnlSeleccion.setLayout(new BorderLayout(0, 10));
		
		JScrollPane scrollVideos = new JScrollPane();
		scrollVideos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnlSeleccion.add(scrollVideos, BorderLayout.CENTER);
		
		pnlVideos = new JPanel();
		scrollVideos.setViewportView(pnlVideos);
		pnlVideos.setLayout(new BoxLayout(pnlVideos, BoxLayout.Y_AXIS));
		
		JLabel lblRecientes = new JLabel("Videos reproducidos recientemente:");
		lblRecientes.setBorder(new EmptyBorder(10, 5, 10, 5));
		pnlSeleccion.add(lblRecientes, BorderLayout.NORTH);
		lblRecientes.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		pnlVideo = new JPanel();
		add(pnlVideo, BorderLayout.CENTER);
		pnlVideo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}
	
	private void mostrarVideos() {
		pnlVideos.removeAll();
		
		List<Video> videos = Controlador.getUnicaInstancia().getRecientes();
		
		for (Video v : videos) {
			JButton boton = crearBotonVideo(v);
			pnlVideos.add(boton);
		}
		
		pnlVideos.revalidate();
		pnlVideos.repaint();
	}
	
	private JButton crearBotonVideo(Video video) {
		JButton boton = new JButton(video.getTitulo());
		boton.setActionCommand(video.getTitulo());
		boton.setIcon(PanelReproductor.getUnicaInstancia().getVideoWeb().getSmallThumb(video.getUrl()));
		
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlVideo.removeAll();
				pnlVideo.add(PanelReproductor.getUnicaInstancia());
				Controlador.getUnicaInstancia().reproducirVideo(Controlador.getUnicaInstancia().getVideoPorTitulo(e.getActionCommand()));
			}
		});
		
		return boton;
	}
	
	public void reload() {
		mostrarVideos();
		pnlVideo.removeAll();
		PanelReproductor.getUnicaInstancia().detener();
	}

}
