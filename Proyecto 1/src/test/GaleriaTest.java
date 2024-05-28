/**
package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.LinkedList;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import galeria.modelo.*;
import galeria.modelo.pieza.Estado;
import galeria.modelo.pieza.Pieza;
import galeria.modelo.pieza.Tipo;
import galeria.modelo.usuario.*;
import galeria.modelo.ventas.Oferta;
import galeria.modelo.ventas.SubastaPieza;

// Subclase concreta de Pieza para usar en pruebas
class TestablePieza ext ends Pieza {
    public TestablePieza(String codigo, String titulo, String yearCreacion, String lugarCreacion, List<String> autor,
                         Estado estado, double alto, double ancho, Tipo tipo, boolean disponibilidadVentaDirecta,
                         double valorFijoVentaDirecta) {
        super(codigo, titulo, yearCreacion, lugarCreacion, autor, estado, alto, ancho, tipo, disponibilidadVentaDirecta, valorFijoVentaDirecta);
    }
}

public class GaleriaTest {
    private Galeria galeria;
    private Pieza pieza;
    private Pieza pieza2;

    @Before
    public void setUp() {
        galeria = new Galeria();
        pieza = new TestablePieza("001", "La obra maestra", "1999", "México", new LinkedList<>(), Estado.EXHIBICION, 2.0, 3.0, Tipo.PINTURA, true, 5.000);
        pieza2 = new TestablePieza("002", "La noche estrellada", "1889", "Saint-Rémy-de-Provence", new LinkedList<>(), Estado.EXHIBICION, 2.0, 3.0, Tipo.PINTURA, true, 5.000);

        pieza.setEsConsignacion(true);
        pieza.setFechaInicioConsignacion(LocalDate.of(2023, 1, 1));
        pieza.setFechaFinConsignacion(LocalDate.now().plusDays(10)); 

        pieza2.setEsConsignacion(true);
        pieza2.setFechaInicioConsignacion(LocalDate.of(2023, 1, 1));
        pieza2.setFechaFinConsignacion(LocalDate.now().minusDays(1)); 


        // Añadiendo piezas al inventario
        galeria.getInventario().add(pieza);
        galeria.getInventario().add(pieza2);
        
        Cliente cliente1 = new Cliente(
            "client1",
            "password1",
            "John Doe",
            "123-456-7890",
            "johndoe@example.com",
            50000,
            5000,
            true,
            new LinkedList<>(Arrays.asList("P001", "P002"))
        );

        Cliente cliente2 = new Cliente(
            "client2",
            "password2",
            "Jane Doe",
            "098-765-4321",
            "janedoe@example.com",
            30000,
            3000,
            false,
            new LinkedList<>(Arrays.asList("P003"))
        );
        

         // Añadiendo piezas al inventario
        

        galeria.getUsuarios().add(cliente1);  
        galeria.getUsuarios().add(cliente2);
    }

    //Inicio sesion 
    @Test
    public void testIniciarSesionCorrecto() {
        assertTrue("El inicio de sesión debería ser exitoso con credenciales correctas.", galeria.IniciarSesion("client1", "password1"));
    }

	@Test
    public void testIniciarSesionIncorrecto() {
        assertFalse("El inicio de sesión debería fallar con contraseña incorrecta.", galeria.IniciarSesion("client1", "wrongpassword"));
    }

    @Test
    public void testIniciarSesionUsuarioInexistente() {
        assertFalse("El inicio de sesión debería fallar con un usuario inexistente.", galeria.IniciarSesion("client999", "password"));
    }

    //Eliminar Cliente y registro

    @Test
    public void testRegistrarYEliminarCliente() {
        Cliente nuevoCliente = new Cliente(
            "client3",
            "password3",
            "Alice Smith",
            "321-654-9870",
            "alice@example.com",
            40000,
            4000,
            false,
            new LinkedList<>()
        );

        galeria.getUsuarios().add(nuevoCliente);  
        assertTrue("El cliente debería haber sido agregado a la lista de usuarios.", galeria.getUsuarios().contains(nuevoCliente));

        galeria.getUsuarios().remove(nuevoCliente); 
        assertFalse("El cliente debería haber sido eliminado de la lista de usuarios.", galeria.getUsuarios().contains(nuevoCliente));
    }

    //Comprobar usuario 

    @Test
    public void testComprobarUsuarioExistente() {
        assertTrue("Debería retornar true para un usuario existente", galeria.ComprobarUsuario("client1"));
    }

    @Test
    public void testComprobarUsuarioNoExistente() {
        assertFalse("Debería retornar false para un usuario no existente", galeria.ComprobarUsuario("client999"));
    }

    //Consultar usuario

    @Test
    public void testConsultarUsuarioExistente() {
        Usuario resultado = galeria.ConsultarUsuario("client1");
        assertNotNull("Debería retornar un objeto Usuario para un login existente", resultado);
        assertEquals("El usuario retornado debe ser el correcto", "John Doe", resultado.getNombre());
    }

    @Test
    public void testConsultarUsuarioNoExistente() {
        Usuario resultado = galeria.ConsultarUsuario("client999");
        assertNull("Debería retornar null para un login no existente", resultado);
    }

    // Test para verificar el método ConsultarPieza
    @Test
    public void testConsultarPiezaExistente() {
        galeria.getInventario().add(pieza);  
        Pieza resultado = galeria.ConsultarPieza("001");
        assertNotNull("Debería retornar un objeto Pieza para un código existente", resultado);
        assertEquals("El código de la pieza retornada debe ser el correcto", "001", resultado.getCodigo());
    }

    @Test
    public void testConsultarPiezaNoExistente() {
        Pieza resultado = galeria.ConsultarPieza("999"); 
        assertNull("Debería retornar null para un código no existente", resultado);
    }

    @Test
    public void testConsultarInventarioConPiezas() {
        List<Pieza> resultado = galeria.ConsultarInventario(Estado.EXHIBICION);
        assertFalse("La lista no debería estar vacía", resultado.isEmpty());
        assertEquals("Debería haber una pieza en estado EXHIBICION", 2, resultado.size());
        assertEquals("El código de la pieza en estado EXCELENTE debe ser '001'", "001", resultado.get(0).getCodigo());
    }

    @Test
    public void testConsultarInventarioSinPiezas() {
        List<Pieza> resultado = galeria.ConsultarInventario(Estado.VENDIDO);
        assertTrue("La lista debería estar vacía para un estado sin piezas", resultado.isEmpty());
    }

    @Test
    public void testActualizarEstadosPiezasConsignacion() {
        galeria.ActualizarEstadosPiezasConsignacion();
        assertFalse("La pieza que debía ser devuelta no debe estar en consignación", galeria.getInventario().get(1).isEsConsignacion());
        assertNull("La fecha de fin de consignación de la pieza devuelta debe ser null", galeria.getInventario().get(1).getFechaFinConsignacion());
        
        assertTrue("La pieza que no debe ser devuelta debe seguir en consignación", galeria.getInventario().get(0).isEsConsignacion());
        assertNotNull("La fecha de fin de consignación de la pieza no devuelta no debe ser null", galeria.getInventario().get(0).getFechaFinConsignacion());
    }

    // Finalizar subasta

   @Test
    public void testFinalizarSubastaOfertaGanadora() {
        Cliente cliente1 = new Cliente(
            "client1",
            "password1",
            "John Doe",
            "123-456-7890",
            "johndoe@example.com",
            50000,
            5000,
            true,
            new LinkedList<>(Arrays.asList("P001", "P002"))
        );
        LocalDate tiempoInicial = LocalDate.now();
        LocalDate tiempoFinal = tiempoInicial.plusDays(1);
        List<Oferta> ofertas = new ArrayList<>();
        ofertas.add(new Oferta(cliente1, 6000));
        SubastaPieza subasta = new SubastaPieza(5000, 4000, 6000, pieza, tiempoInicial, tiempoFinal, ofertas);
        assertTrue("Debería ser posible finalizar la subasta con una oferta ganadora", galeria.FinalizarSubasta(subasta));
    }

    @Test
    public void testFinalizarSubastaOfertaPerdedora() {
        Cliente cliente1 = new Cliente(
            "client1",
            "password1",
            "John Doe",
            "123-456-7890",
            "johndoe@example.com",
            50000,
            5000,
            true,
            new LinkedList<>(Arrays.asList("P001", "P002"))
        );
        LocalDate tiempoInicial = LocalDate.now();
        LocalDate tiempoFinal = tiempoInicial.plusDays(1);
        List<Oferta> ofertas = new ArrayList<>();
        ofertas.add(new Oferta(cliente1, 4000));
        SubastaPieza subasta = new SubastaPieza(5000, 4000, 4000, pieza, tiempoInicial, tiempoFinal, ofertas);
        assertFalse("No debería ser posible finalizar la subasta con una oferta perdedora", galeria.FinalizarSubasta(subasta));
    }

    @Test
    public void testOfertaGanadoraMayorQueValorMinimo() {
        Cliente cliente1 = new Cliente(
            "client1",
            "password1",
            "John Doe",
            "123-456-7890",
            "johndoe@example.com",
            50000,
            5000,
            true,
            new LinkedList<>(Arrays.asList("P001", "P002"))
        );
        Oferta oferta = new Oferta(cliente1, 6000); 
        assertTrue("La oferta debería ser ganadora", galeria.OfertaGanadora(oferta, 4000));
    }

    @Test
    public void testOfertaGanadoraIgualQueValorMinimo() {
        Cliente cliente1 = new Cliente(
            "client1",
            "password1",
            "John Doe",
            "123-456-7890",
            "johndoe@example.com",
            50000,
            5000,
            true,
            new LinkedList<>(Arrays.asList("P001", "P002"))
        );
        Oferta oferta = new Oferta(cliente1, 4000); 
        assertFalse("La oferta no debería ser ganadora cuando es igual al valor mínimo", galeria.OfertaGanadora(oferta, 4000));
    }
    

    @Test
    public void testOfertaGanadoraMenorQueValorMinimo() {
        Cliente cliente1 = new Cliente(
            "client1",
            "password1",
            "John Doe",
            "123-456-7890",
            "johndoe@example.com",
            50000,
            5000,
            true,
            new LinkedList<>(Arrays.asList("P001", "P002"))
        );
        Oferta oferta = new Oferta(cliente1, 3000); 
        assertFalse("La oferta no debería ser ganadora", galeria.OfertaGanadora(oferta, 4000));
    }


}
**/