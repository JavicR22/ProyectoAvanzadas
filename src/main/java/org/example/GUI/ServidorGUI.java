package org.example.GUI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServidorGUI  extends JFrame{
    private JLabel imagenLabel;
    private JPanel panelConImagen;

    public ServidorGUI(){
        setTitle("Pantalla Remota");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el JPanel que contendrá el JLabel
        panelConImagen = new JPanel(new BorderLayout());
        panelConImagen.setBorder(new EmptyBorder(10, 0, 0, 0)); // Margen superior de 10 píxeles

        // Inicializar el JLabel y añadirlo al panel
        imagenLabel = new JLabel();
        panelConImagen.add(imagenLabel, BorderLayout.CENTER);

        // Añadir el panel al JFrame
        setLayout(new BorderLayout());
        add(panelConImagen, BorderLayout.CENTER);
        panelConImagen.setFocusable(true);
        panelConImagen.requestFocusInWindow();

			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					// Confirmar si el usuario realmente desea cerrar
					int opcion = JOptionPane.showConfirmDialog(
						ServidorGUI.this,
						"¿Estás seguro de que deseas cerrar el servidor?",
						"Confirmar cierre",
						JOptionPane.YES_NO_OPTION
					);

					if (opcion == JOptionPane.YES_OPTION) {

						new Thread(() -> {try {
							HttpClient client = HttpClient.newHttpClient();
							HttpRequest request = HttpRequest.newBuilder()
								.uri(URI.create("http://localhost:8080/api/grabacion/guardar-todos"))
								.header("Content-Type", "application/octet-stream")
								.POST(HttpRequest.BodyPublishers.noBody())
								.build();
							HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
							System.out.println("Respuesta del servidor: " + response.body());
						} catch (Exception ex) {
							System.err.println("Error al enviar la solicitud HTTP: " + ex.getMessage());
						}
							// Cerrar la ventana y terminar el programa
							SwingUtilities.invokeLater(() -> {
								dispose();
								System.exit(0);
							});
						}).start();
					}

				}
			});

        setVisible(true);
    }

    public JLabel obtenerImagenLabel(){
        return imagenLabel;
    }
    public JPanel obtenerPanelConImagen() {
        return panelConImagen;
    }
}
