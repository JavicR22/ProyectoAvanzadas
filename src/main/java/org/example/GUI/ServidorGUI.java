package org.example.GUI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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

        setVisible(true);
    }

    public JLabel obtenerImagenLabel(){
        return imagenLabel;
    }
    public JPanel obtenerPanelConImagen() {
        return panelConImagen;
    }
}
