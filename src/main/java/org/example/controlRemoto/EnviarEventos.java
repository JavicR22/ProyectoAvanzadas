package org.example.controlRemoto;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.Channel;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class EnviarEventos implements KeyListener, MouseListener, MouseMotionListener {
    private final Channel channel;
    private final JPanel panel;
    private final double w;
    private final double h;
    private final ObjectMapper objectMapper=new ObjectMapper();

    public EnviarEventos(Channel channel, JPanel panel, int ancho, int altura) {
        this.channel = channel;
        this.panel = panel;
        this.w = ancho;
        this.h = altura;

        panel.addKeyListener(this);
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
    }

    private void enviarEvento(int tipo, int x, int y) {
        try {
            Map<String, Object> evento = new HashMap<>();
            evento.put("tipo", tipo);
            evento.put("x", x);
            evento.put("y", y);

            String jsonEvento = objectMapper.writeValueAsString(evento)+"\n";
            channel.writeAndFlush(jsonEvento); // Enviar JSON como String
            System.out.println("Enviado al servidor: " + jsonEvento);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_WINDOWS) {
            enviarEvento(Comandos.PRESS_KEY.getAbbrev(), keyCode, -1);
            System.out.println("Tecla Windows presionada");
        } else {
            enviarEvento(Comandos.PRESS_KEY.getAbbrev(), keyCode, -1);
            System.out.println("Tecla presionada: " + keyCode);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_WINDOWS) {
            enviarEvento(Comandos.RELEASE_KEY.getAbbrev(), keyCode, -1);
            System.out.println("Tecla Windows presionada");
        } else {
            enviarEvento(Comandos.RELEASE_KEY.getAbbrev(), keyCode, -1);
            System.out.println("Tecla presionada: " + keyCode);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int button = e.getButton() == MouseEvent.BUTTON3 ? 4 : 16; // Mapea botón derecho a 4, izquierdo a 16
        enviarEvento(Comandos.PRESS_MOUSE.getAbbrev(), button, -1);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int button = e.getButton() == MouseEvent.BUTTON3 ? 4 : 16;
        enviarEvento(Comandos.RELEASE_MOUSE.getAbbrev(), button, -1);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        double xScale = w / (double) panel.getWidth();
        double yScale = h / (double) panel.getHeight();

        int scaledX = (int) (e.getX() * xScale);
        int scaledY = (int) (e.getY() * yScale);
        enviarEvento(Comandos.MOVE_MOUSE.getAbbrev(), scaledX,scaledY);
    }

    // Otros métodos opcionales del listener
}
