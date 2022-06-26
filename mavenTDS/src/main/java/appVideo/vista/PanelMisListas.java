package appVideo.vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;

import appVideo.controlador.Controlador;
import appVideo.dominio.Video;

import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class PanelMisListas extends JPanel {
	private static PanelMisListas unicaInstancia;
	
	private JComboBox<String> listasVideos;
	private JPanel pnlVideosLista;
	private JPanel pnlVideo;
	
	public static PanelMisListas getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new PanelMisListas();
		return unicaInstancia;
	}
	
	/**
	 * Create the panel.
	 */
	private PanelMisListas() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(10, 0));
		
		JPanel pnlSeleccion = new JPanel();
		pnlSeleccion.setBorder(new EmptyBorder(0, 0, 10, 0));
		add(pnlSeleccion, BorderLayout.WEST);
		pnlSeleccion.setLayout(new BorderLayout(0, 10));
		
		JPanel pnlSeleccionLista = new JPanel();
		pnlSeleccionLista.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new EmptyBorder(5, 5, 5, 5)));
		pnlSeleccion.add(pnlSeleccionLista, BorderLayout.NORTH);
		pnlSeleccionLista.setLayout(new GridLayout(2, 1, 5, 5));
		
		JLabel lblSeleccionar = new JLabel("Seleccione la lista: ");
		lblSeleccionar.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlSeleccionLista.add(lblSeleccionar);
		
		listasVideos = new JComboBox<String>();
		pnlSeleccionLista.add(listasVideos);
		
		addListas();
		
		JScrollPane scrollVideos = new JScrollPane();
		scrollVideos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnlSeleccion.add(scrollVideos, BorderLayout.CENTER);
		
		pnlVideosLista = new JPanel();
		scrollVideos.setViewportView(pnlVideosLista);
		pnlVideosLista.setLayout(new BoxLayout(pnlVideosLista, BoxLayout.Y_AXIS));
		
		pnlVideo = new JPanel();
		add(pnlVideo, BorderLayout.CENTER);
		pnlVideo.setLayout(new BorderLayout(0, 0));

	}
	
	private void addListas() {
		if (listasVideos.getItemCount() > 0)
			listasVideos.removeAllItems();
		listasVideos.addItem("");
		listasVideos.setSelectedIndex(0);
		
		List<String> nombresListas = Controlador.getUnicaInstancia().getNombresListasUsuario();
		for (String lista : nombresListas)
			listasVideos.addItem(lista);
		
		listasVideos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object i = listasVideos.getSelectedItem();
				if (i == null) 
					return;
				if (listasVideos.getSelectedItem().equals(""))
					pnlVideosLista.removeAll();
				else
					mostrarVideos((String)(listasVideos.getSelectedItem()));
			}
		});
		
	}
	
	private void mostrarVideos(String lista) {
		pnlVideosLista.removeAll();
		
		List<Video> videos = Controlador.getUnicaInstancia().getVideosLista(lista);
		
		for (Video v : videos) {
			JButton boton = crearBotonVideo(v);
			pnlVideosLista.add(boton);
		}
		
		pnlVideosLista.revalidate();
		pnlVideosLista.repaint();
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
		addListas();
		pnlVideosLista.removeAll();
		pnlVideo.removeAll();
		PanelReproductor.getUnicaInstancia().detener();
	}

}
