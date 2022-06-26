package appVideo.vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import appVideo.controlador.Controlador;
import appVideo.dominio.Video;

import javax.swing.JToggleButton;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class PanelExplorar extends JPanel {
	private static PanelExplorar unicaInstancia;
	private JTextField txtBusqueda;
	private JPanel pnlBtnsEtiquetas;
	private JPanel pnlResultados;
	
	public static PanelExplorar getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new PanelExplorar();
		return unicaInstancia;
	}
	
	/**
	 * Create the panel.
	 */
	private PanelExplorar() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(10, 10));
		
		JPanel pnlEtiquetas = new JPanel();
		pnlEtiquetas.setBorder(new TitledBorder(null, "Filtrar por Etiquetas: ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(pnlEtiquetas, BorderLayout.EAST);
		pnlEtiquetas.setLayout(new BoxLayout(pnlEtiquetas, BoxLayout.Y_AXIS));
		
		JScrollPane scrollEtiquetas = new JScrollPane();
		scrollEtiquetas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnlEtiquetas.add(scrollEtiquetas);
		
		pnlBtnsEtiquetas = new JPanel();
		scrollEtiquetas.setViewportView(pnlBtnsEtiquetas);
		pnlBtnsEtiquetas.setLayout(new BoxLayout(pnlBtnsEtiquetas, BoxLayout.Y_AXIS));
		
		addBotonesEtiquetas();
		
		JPanel pnlBusqueda = new JPanel();
		add(pnlBusqueda, BorderLayout.CENTER);
		pnlBusqueda.setLayout(new BorderLayout(10, 10));
		
		JPanel pnlBotones = new JPanel();
		pnlBotones.setAlignmentY(Component.TOP_ALIGNMENT);
		pnlBotones.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new EmptyBorder(5, 5, 5, 5)));
		pnlBusqueda.add(pnlBotones, BorderLayout.NORTH);
		pnlBotones.setLayout(new BorderLayout(10, 15));
		
		JLabel lblBuscarTitulo = new JLabel("Buscar Título: ");
		lblBuscarTitulo.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlBotones.add(lblBuscarTitulo, BorderLayout.WEST);
		
		txtBusqueda = new JTextField();
		pnlBotones.add(txtBusqueda, BorderLayout.CENTER);
		txtBusqueda.setColumns(20);
		
		JButton btnBuscar = new JButton("Buscar");
		pnlBotones.add(btnBuscar, BorderLayout.EAST);
		addManejadorBotonBuscar(btnBuscar);
		
		JButton btnLimpiar = new JButton("Limpiar Resultados");
		pnlBotones.add(btnLimpiar, BorderLayout.SOUTH);
		
		addManejadorBotonLimpiar(btnLimpiar);
		
		JScrollPane scrollResultados = new JScrollPane();
		scrollResultados.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnlBusqueda.add(scrollResultados, BorderLayout.CENTER);
		
		pnlResultados = new JPanel();
		scrollResultados.setViewportView(pnlResultados);
		pnlResultados.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new EmptyBorder(5, 5, 5, 5)));
		pnlResultados.setLayout(new BoxLayout(pnlResultados, BoxLayout.Y_AXIS));
	}
	
	private void addBotonesEtiquetas() {
		pnlBtnsEtiquetas.removeAll();
		List<String> etiquetas = Controlador.getUnicaInstancia().getAllNombresEtiquetas();
		
		for (String nombre : etiquetas)
			crearBotonEtiqueta(nombre);
		
		pnlBtnsEtiquetas.revalidate();
		pnlBtnsEtiquetas.repaint();
	}

	private void crearBotonEtiqueta(String nombre) {
		JToggleButton tglbtnEtiqueta = new JToggleButton(nombre);
		tglbtnEtiqueta.setPreferredSize(new Dimension(160, 25));
		tglbtnEtiqueta.setMaximumSize(new Dimension(160, 25));
		tglbtnEtiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlBtnsEtiquetas.add(tglbtnEtiqueta);
	}
	
	private void buscarVideos() {
		clearVideos();

		List<String> etiquetas = new ArrayList<String>();
		for (Component boton : pnlBtnsEtiquetas.getComponents()) {
			if (((JToggleButton)boton).isSelected())
				etiquetas.add(((JToggleButton)boton).getText());
		}

		String titulo = txtBusqueda.getText().trim();
		List<Video> videos = Controlador.getUnicaInstancia().buscarVideo(titulo, etiquetas);

		for (Video v : videos) {
			JButton boton = crearBotonVideo(v);
			pnlResultados.add(boton);
		}

		pnlResultados.revalidate();
		pnlResultados.repaint();
		txtBusqueda.setText("");
	}

	public void clearVideos() {
		pnlResultados.removeAll();
		pnlResultados.revalidate();
		pnlResultados.repaint();
		PanelReproductor.getUnicaInstancia().detener();
	}
	
	private JButton crearBotonVideo(Video video) {
		JButton boton = new JButton(video.getTitulo());
		boton.setActionCommand(video.getTitulo());
		boton.setIcon(PanelReproductor.getUnicaInstancia().getVideoWeb().getSmallThumb(video.getUrl()));
		
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearVideos();
				pnlResultados.add(PanelReproductor.getUnicaInstancia());
				Controlador.getUnicaInstancia().reproducirVideo(Controlador.getUnicaInstancia().getVideoPorTitulo(e.getActionCommand()));
			}
		});
		
		return boton;
	}
	
	private void addManejadorBotonBuscar(JButton btnBuscar) {
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarVideos();
			}
		});
	}
	
	private void addManejadorBotonLimpiar(JButton btnLimpiar) {
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearVideos();
			}
		});
	}
	
	public void reload() {
		txtBusqueda.setText("");
		addBotonesEtiquetas();
		clearVideos();
	}

}
