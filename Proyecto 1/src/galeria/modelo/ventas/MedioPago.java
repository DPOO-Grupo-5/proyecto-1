package galeria.modelo.ventas;

import java.time.LocalDate;

import galeria.modelo.usuario.Cliente;

/**
 * Inidca el medio de pago utilizado para realizar la compra
 */
public interface MedioPago {

	boolean ProcesarPago(Cliente comprador, double valor, LocalDate fecha, String numeroCuenta, String numeroTransaccion);
	
}
