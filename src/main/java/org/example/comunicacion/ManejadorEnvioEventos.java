package org.example.comunicacion;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.example.controlRemoto.EnviarEventos;

import javax.swing.*;
import java.awt.event.KeyListener;

public class ManejadorEnvioEventos extends ChannelInboundHandlerAdapter {

    private final JPanel panel;
    private final int ancho;
    private final int altura;
    public ManejadorEnvioEventos(JPanel panel, int ancho, int altura){

        this.panel = panel;
        this.ancho = ancho;
        this.altura = altura;
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        new EnviarEventos(ctx.channel(),panel,ancho,altura);


    }

}
