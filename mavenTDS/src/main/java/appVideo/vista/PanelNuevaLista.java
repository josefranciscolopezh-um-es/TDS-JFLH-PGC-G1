package appVideo.vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import appVideo.controlador.Controlador;
import appVideo.dominio.Video;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class PanelNuevaLista extends JPanel {
	private static PanelNuevaLista unicaInstancia;
	private JTextField txtTitulo;
	private JPanel pnlResultados;
	private JTextField txtNombreLista;
	private JPanel pnlContenido;
	
	private String listaActual;
	private String videoSeleccionado;
	private List<Video> videosLista;
	
	public static PanelNuevaLista getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new PanelNuevaLista();
		return unicaInstancia;
	}
	
	/**
	 * Create the panel.
	 */
	private PanelNuevaLista() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(10, 10));
		
		JPanel pnlBusqueda = new JPanel();
		add(pnlBusqueda, BorderLayout.CENTER);
		pnlBusqueda.setLayout(new BorderLayout(10, 10));
		
		JPanel pnlBotones = new JPanel();
		pnlBotones.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new EmptyBorder(10, 5, 10, 5)));
		pnlBotones.setAlignmentY(0.0f);
		pnlBusqueda.add(pnlBotones, BorderLayout.NORTH);
		pnlBotones.setLayout(new BorderLayout(10, 15));
		
		JLabel lblBuscarTitulo = new JLabel("Buscar Título: ");
		lblBuscarTitulo.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlBotones.add(lblBuscarTitulo, BorderLayout.WEST);
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(20);
		pnlBotones.add(txtTitulo, BorderLayout.CENTER);
		
		JButton btnBuscar = new JButton("Buscar");
		pnlBotones.add(btnBuscar, BorderLayout.EAST);
		
		addManejadorBotonBuscarVideos(btnBuscar);
		
		JButton btnLimpiarResultados = new JButton("Limpiar Resultados");
		pnlBotones.add(btnLimpiarResultados, BorderLayout.SOUTH);
		
		addManejadorBotonLimpiar(btnLimpiarResultados);
		
		JScrollPane scrollResultados = new JScrollPane();
		scrollResultados.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnlBusqueda.add(scrollResultados, BorderLayout.CENTER);
		
		pnlResultados = new JPanel();
		scrollResultados.setViewportView(pnlResultados);
		pnlResultados.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new EmptyBorder(5, 5, 5, 5)));
		pnlResultados.setLayout(new BoxLayout(pnlResultados, BoxLayout.Y_AXIS));
		
		JPanel pnlLista = new JPanel();
		add(pnlLista, BorderLayout.WEST);
		pnlLista.setLayout(new BorderLayout(10, 10));
		
		JPanel pnlNombre = new JPanel();
		pnlNombre.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new EmptyBorder(5, 5, 5, 5)));
		pnlLista.add(pnlNombre, BorderLayout.NORTH);
		pnlNombre.setLayout(new BoxLayout(pnlNombre, BoxLayout.Y_AXIS));
		
		JLabel lblIntroducirNombreLista = new JLabel("Introducir nombre lista:");
		lblIntroducirNombreLista.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblIntroducirNombreLista.setHorizontalAlignment(SwingConstants.CENTER);
		pnlNombre.add(lblIntroducirNombreLista);
		
		JPanel pnlBusquedaNombre = new JPanel();
		pnlNombre.add(pnlBusquedaNombre);
		
		txtNombreLista = new JTextField();
		pnlBusquedaNombre.add(txtNombreLista);
		txtNombreLista.setColumns(10);
		
		JButton btnBuscarLista = new JButton("Buscar");
		pnlBusquedaNombre.add(btnBuscarLista);
		
		addManejadorBotonBuscarLista(btnBuscarLista);
		
		JButton btnEliminarLista = new JButton("Eliminar Lista");
		btnEliminarLista.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlNombre.add(btnEliminarLista);
		
		addManejadorBotonEliminarLista(btnEliminarLista);
		
		JScrollPane scrollContenido = new JScrollPane();
		scrollContenido.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnlLista.add(scrollContenido, BorderLayout.CENTER);
		
		pnlContenido = new JPanel();
		scrollContenido.setViewportView(pnlContenido);
		pnlContenido.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new EmptyBorder(5, 5, 5, 5)));
		pnlContenido.setLayout(new BoxLayout(pnlContenido, BoxLayout.Y_AXIS));
		
		JPanel pnlBotonesLista = new JPanel();
		pnlBotonesLista.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new EmptyBorder(5, 5, 5, 5)));
		pnlLista.add(pnlBotonesLista, BorderLayout.SOUTH);
		pnlBotonesLista.setLayout(new BoxLayout(pnlBotonesLista, BoxLayout.X_AXIS));
		
		JButton btnQuitar = new JButton("Quitar Video");
		pnlBotonesLista.add(btnQuitar);
		
		addManejadorBotonQuitarVideo(btnQuitar);
		
		JButton btnGuardar = new JButton("Guardar Lista");
		pnlBotonesLista.add(btnGuardar);
		
		addManejadorBotonGuardar(btnGuardar);

	}

	private void addManejadorBotonBuscarVideos(JButton btnBuscar) {
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarVideos();
			}
		});
	}

	private void addManejadorBotonBuscarLista(JButton btnBuscarLista) {
		btnBuscarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNombreLista.getText().equals("")) {
					JOptionPane.showMessageDialog(PanelNuevaLista.getUnicaInstancia(), "Introduzca el nombre de la lista", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				clearListaVideos();
				videosLista = Controlador.getUnicaInstancia().getVideosLista(txtNombreLista.getText());
				
				if (videosLista == null) {
					int respuesta = JOptionPane.showConfirmDialog(PanelNuevaLista.getUnicaInstancia(), "Lista no encontrada. ¿Desea crearla?", "Aviso", JOptionPane.YES_NO_OPTION);

					if (respuesta == JOptionPane.YES_OPTION)
						videosLista = new ArrayList<Video>();
					else {
						txtNombreLista.setText("");
						return;
					}
				} else {
					for (Video v : videosLista)
						pnlContenido.add(crearBotonVideoLista(v));
					pnlContenido.revalidate();
					pnlContenido.repaint();
				}
				
				listaActual = txtNombreLista.getText();
			}
		});
	}
	
	private void addManejadorBotonLimpiar(JButton btnLimpiar) {
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearResultados();
			}
		});
	}

	private void addManejadorBotonEliminarLista(JButton btnEliminarLista) {
		btnEliminarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listaActual == null) {
					JOptionPane.showMessageDialog(PanelNuevaLista.getUnicaInstancia(), "No hay ninguna lista seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Controlador.getUnicaInstancia().removeLista(listaActual);
				
				JOptionPane.showMessageDialog(PanelNuevaLista.getUnicaInstancia(), "Lista '" + listaActual + "' eliminada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				
				txtNombreLista.setText("");
				clearListaVideos();
			}
		});
	}

	private void addManejadorBotonQuitarVideo(JButton btnQuitar) {
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (videoSeleccionado != null) {
					videosLista.remove(Controlador.getUnicaInstancia().getVideoPorTitulo(videoSeleccionado));
					
					for (Component btnVideo : pnlContenido.getComponents()) {
						if (((JButton)btnVideo).getText().equals(videoSeleccionado)) {
							pnlContenido.remove(btnVideo);
							pnlContenido.revalidate();
							pnlContenido.repaint();
							videoSeleccionado = null;
							return;
						}
					}
				}
			}
		});
	}

	private void addManejadorBotonGuardar(JButton btnGuardar) {
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listaActual == null) {
					JOptionPane.showMessageDialog(PanelNuevaLista.getUnicaInstancia(), "No hay ninguna lista seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				} else if (videosLista.isEmpty()) {
					JOptionPane.showMessageDialog(PanelNuevaLista.getUnicaInstancia(), "No puede guardar una lista vacía", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Controlador.getUnicaInstancia().guardarLista(listaActual, videosLista);
				
				JOptionPane.showMessageDialog(PanelNuevaLista.getUnicaInstancia(), "Lista '" + listaActual + "' guardada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
	
	private void buscarVideos() {
		pnlResultados.removeAll();

		String titulo = txtTitulo.getText().trim();
		List<Video> videos = Controlador.getUnicaInstancia().buscarVideo(titulo);

		for (Video v : videos) {
			JButton boton = crearBotonVideoBusqueda(v);
			pnlResultados.add(boton);
		}

		pnlResultados.revalidate();
		pnlResultados.repaint();
		txtTitulo.setText("");
	}
	
	private JButton crearBotonVideoBusqueda(Video video) {
		JButton boton = new JButton(video.getTitulo());
		boton.setActionCommand(video.getTitulo());
		boton.setIcon(PanelReproductor.getUnicaInstancia().getVideoWeb().getSmallThumb(video.getUrl()));
		
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listaActual == null) {
					JOptionPane.showMessageDialog(PanelNuevaLista.getUnicaInstancia(), "Seleccione una lista a la que añadir el video primero", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					addVideoToLista(e.getActionCommand());
				}
			}
		});
		
		return boton;
	}
	
	private void addVideoToLista(String titulo) {
		Video video = Controlador.getUnicaInstancia().getVideoPorTitulo(titulo);
		videosLista.add(video);
		
		JButton boton = crearBotonVideoLista(video);
		pnlContenido.add(boton);
		pnlContenido.revalidate();
		pnlContenido.repaint();
	}
	
	private JButton crearBotonVideoLista(Video video) {
		JButton boton = new JButton(video.getTitulo());
		boton.setActionCommand(video.getTitulo());
		boton.setIcon(PanelReproductor.getUnicaInstancia().getVideoWeb().getSmallThumb(video.getUrl()));
		
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				videoSeleccionado = e.getActionCommand();
			}
		});
		
		return boton;
	}
	
	private void clearResultados() {
		pnlResultados.removeAll();
		pnlResultados.revalidate();
		pnlResultados.repaint();
	}
	
	private void clearListaVideos() {
		listaActual = null;
		videoSeleccionado = null;
		videosLista = null;
		pnlContenido.removeAll();
		pnlContenido.revalidate();
		pnlContenido.repaint();
	}
	
	public void reload() {
		txtTitulo.setText("");
		txtNombreLista.setText("");
		clearResultados();
		clearListaVideos();
	}

}
