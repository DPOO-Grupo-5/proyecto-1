package galeria.modelo.ventas;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import galeria.modelo.usuario.Cliente;

public class PagoPayPalLog implements MedioPago {
	
	DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Override
	public boolean ProcesarPago(Cliente comprador, double valor, LocalDate fecha, String numeroCuenta, String numeroTransaccion) {
		String nombre = comprador.getNombre();
		String email = comprador.getEmail();
		String fechaString = fecha.format(formatoFecha);
		String valorString = Double.toString(valor);
		registrarTransaccion( "./datos/PayPal.log", nombre, email, valorString, fechaString, numeroCuenta, numeroTransaccion);
		return true;
	}
	
	public void registrarTransaccion(String archivo, String nombre, String email, String valor, String fecha,  String numeroCuenta, String numeroTransaccion) {
		try {
			PrintWriter writer = new PrintWriter( archivo );
			
			writer.println(numeroTransaccion + ";" + fecha + ";" + nombre + ";" + email + ";" + numeroCuenta + ";" + valor);
			writer.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


}
