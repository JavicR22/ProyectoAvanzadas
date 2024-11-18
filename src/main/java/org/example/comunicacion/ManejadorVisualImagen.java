package org.example.comunicacion;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.example.GUI.ServidorGUI;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.Serial;
import javax.imageio.ImageIO;
public class ManejadorVisualImagen extends SimpleChannelInboundHandler<byte[]>{
    private final ServidorGUI servidorGUI;
    private  int ancho;
    private int altura;


    public ManejadorVisualImagen(ServidorGUI servidorGUI){
        this.servidorGUI=servidorGUI;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,byte[] msg){
        try {


            // Convertir bytes a imagen
            ByteArrayInputStream bais = new ByteArrayInputStream(msg);
            BufferedImage image = ImageIO.read(bais);

            // Mostrar en Swing (JLabel)
            if (image != null) {
                ImageIcon icon = new ImageIcon(image);
                this.altura=icon.getIconHeight();
                this.ancho=icon.getIconWidth();
                System.out.println("Dimensiones de la imagen: Ancho=" + ancho + ", Altura=" + altura);
                SwingUtilities.invokeLater(() -> {
                    servidorGUI.obtenerImagenLabel().setIcon(icon);
                    servidorGUI.obtenerImagenLabel().repaint();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getAncho() {
        return ancho;
    }

    public int getAltura() {
        return altura;
    }

}
