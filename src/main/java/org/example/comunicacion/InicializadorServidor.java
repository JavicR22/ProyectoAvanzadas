package org.example.comunicacion;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.example.GUI.ServidorGUI;
import org.example.controlRemoto.EnviarEventos;

public class InicializadorServidor extends ChannelInitializer <SocketChannel>{

    private final ServidorGUI servidorGUI;

    public InicializadorServidor(ServidorGUI servidorGUI){
        this.servidorGUI=servidorGUI;
    }
    @Override
    protected void initChannel(SocketChannel ch) {
        ManejadorVisualImagen manejadorVisualImagen = new ManejadorVisualImagen(servidorGUI);

        ch.pipeline().addLast(
                new LengthFieldBasedFrameDecoder(2048576, 0, 4, 0, 4),
                new ByteArrayDecoder(),
                new StringEncoder(),
                new StringDecoder(),
                new LineBasedFrameDecoder(10240),
                manejadorVisualImagen,
                new ManejadorEnvioEventos(servidorGUI.obtenerPanelConImagen(),manejadorVisualImagen.getAncho(),manejadorVisualImagen.getAltura())
        );
    }

}
