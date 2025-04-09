package oop2.tp3.test;

import oop2.tp3.ej2.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestauranteTest {
    private Menu menu;
    private Mesa mesa;
    private Pedido pedido;

    @BeforeEach
    void setUp() {
        menu = new Menu();
        mesa = new Mesa(1);
        pedido = new Pedido(mesa.getId());
        mesa.asignarPedido(pedido);
    }

    @Test
    void testPagoConVisa() {
        pedido.agregarItem("bebidas", "coca-cola", 2, menu);
        pedido.agregarItem("platos", "hamburguesa", 1, menu);
        pedido.confirmarPedido();

        Pago pago = new Pago(pedido, new TarjetaVisa(), 5);
        assertEquals(4662.0, pago.calcularTotal(menu));
    }

    @Test
    void testPagoConMastercard() {
        pedido.agregarItem("bebidas", "coca-cola", 2, menu);
        pedido.agregarItem("platos", "hamburguesa", 1, menu);
        pedido.confirmarPedido();

        Pago pago = new Pago(pedido, new TarjetaMastercard(), 5);
        assertEquals(4672.5, pago.calcularTotal(menu));
    }

    @Test
    void testPagoConComarcaPlus() {
        pedido.agregarItem("bebidas", "coca-cola", 2, menu);
        pedido.agregarItem("platos", "hamburguesa", 1, menu);
        pedido.confirmarPedido();

        Pago pago = new Pago(pedido, new TarjetaComarcaPlus(), 5);
        assertEquals(4630.5, pago.calcularTotal(menu));
    }

    @Test
    void testPagoSinDescuento() {
        pedido.agregarItem("bebidas", "coca-cola", 2, menu);
        pedido.agregarItem("platos", "hamburguesa", 1, menu);
        pedido.confirmarPedido();

        Pago pago = new Pago(pedido, new TarjetaGenerica(), 5);
        assertEquals(4725.0, pago.calcularTotal(menu));
    }
}
