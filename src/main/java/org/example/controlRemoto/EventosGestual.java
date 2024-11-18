package org.example.controlRemoto;

import java.io.Serializable;

public class EventosGestual implements Serializable {
    private final int tipo;
    private final int x;
    private int y;

    public EventosGestual(int tipo, int x, int y) {
        this.tipo = tipo;
        this.x = x;
        this.y = y;
    }

    public EventosGestual(int tipo, int code) {
        this.tipo=tipo;
        this.x=code;
    }

    public int getTipo() { return tipo; }
    public int getX() { return x; }
    public int getY() { return y; }
}
