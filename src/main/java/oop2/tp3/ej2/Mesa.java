package oop2.tp3.ej2;

public class Mesa {
    private int id;
    private Pedido pedido;

    public Mesa(int id) {
        this.id = id;
        this.pedido = null;
    }

    public int getId() {
        return id;
    }

    public void asignarPedido(Pedido pedido) {
        if (this.pedido != null) {
            throw new IllegalStateException("La mesa ya tiene un pedido asignado.");
        }
        this.pedido = pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }
}
