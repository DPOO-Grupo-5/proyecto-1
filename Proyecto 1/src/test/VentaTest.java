/**
package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.List;

import galeria.modelo.pieza.Estado;
import galeria.modelo.pieza.Pieza;
import galeria.modelo.pieza.Tipo;
import galeria.modelo.usuario.Cliente;
import galeria.modelo.ventas.MedioPago;
import galeria.modelo.ventas.Venta;

// Subclase concreta de Pieza para usar en pruebas
class TestablePieza extends Pieza {
    public TestablePieza(String codigo, String titulo, String yearCreacion, String lugarCreacion, List<String> autor,
                         Estado estado, double alto, double ancho, Tipo tipo, boolean disponibilidadVentaDirecta,
                         double valorFijoVentaDirecta) {
        super(codigo, titulo, yearCreacion, lugarCreacion, autor, estado, alto, ancho, tipo, disponibilidadVentaDirecta, valorFijoVentaDirecta);
    }
}

public class VentaTest {

    private Venta venta;
    private Cliente cliente;
    private Pieza pieza;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("usuario1", "password1", "Juan Pérez", "123456789", "juan@correo.com", 10000, 15.000, true, new LinkedList<>());
        pieza = new TestablePieza("001", "La obra maestra", "1999", "México", new LinkedList<>(), Estado.EXHIBICION, 2.0, 3.0, Tipo.PINTURA, true, 5.000);
        venta = new Venta(cliente, pieza); 
    }
    

    @Test
    void testCrearVentaConValor() {
        assertEquals(5.000, venta.getValor());
        assertEquals(cliente, venta.getComprador());
        assertEquals(pieza, venta.getPiezaVenta());
        assertEquals(Estado.BLOQUEADO, pieza.getEstado());
        assertFalse(venta.isAceptada());
    }

    @Test
    void testCrearVentaSinEspecificarValor() {
        Venta ventaDirecta = new Venta(cliente, pieza);
        assertEquals(pieza.getValorFijoVentaDirecta(), ventaDirecta.getValor());
        assertEquals(Estado.BLOQUEADO, pieza.getEstado());
    }

    @Test
    void testDescontarPagoSuficiente() {
        assertTrue(venta.descontarPago(venta));
        assertEquals(10.000, cliente.getValorMaximo());
        
        
    }
    

    @Test
    void testDescontarPagoInsuficiente() {
        venta.setValor(15000);  
        assertFalse(venta.descontarPago(venta));
    }

    @Test
    void testEfectuarVentaExitosa() {
        assertTrue(venta.efectuarVenta(venta, MedioPago.EFECTIVO));
        assertEquals(MedioPago.EFECTIVO, venta.getMedioPago());
        assertTrue(venta.isAceptada());
        assertEquals(Estado.VENDIDO, pieza.getEstado());
        assertNotNull(pieza.getPropietario());
        assertEquals(cliente, pieza.getPropietario());
        assertNotNull(venta.getFecha());
    }

    @Test
    void testEfectuarVentaFallida() {
        venta.setValor(15000);  
        assertFalse(venta.efectuarVenta(venta, MedioPago.EFECTIVO));
        assertFalse(venta.isAceptada());
        assertEquals(Estado.BODEGA, pieza.getEstado());
    }
}
**/