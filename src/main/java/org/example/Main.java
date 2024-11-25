package org.example;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.example.GUI.ServidorGUI;
import org.example.comunicacion.InicializadorServidor;



public class Main {
    public static void main(String[] args) {
        ServidorGUI servidorGUI= new ServidorGUI();
        int puerto = 1907;
        EventLoopGroup grupoPrincipal = new NioEventLoopGroup();
        EventLoopGroup grupoTrabajo= new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap= new ServerBootstrap();
            bootstrap.group(grupoPrincipal,grupoTrabajo)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new InicializadorServidor(servidorGUI));
            ChannelFuture futuro=bootstrap.bind(puerto).sync();
            futuro.channel().closeFuture().sync();

				}catch (Exception e){
            e.printStackTrace();
        }finally {

            grupoPrincipal.shutdownGracefully();
            grupoTrabajo.shutdownGracefully();

        }
    }
}
