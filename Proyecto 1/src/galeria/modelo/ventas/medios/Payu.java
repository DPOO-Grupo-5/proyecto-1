package galeria.modelo.ventas.medios;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import galeria.modelo.usuario.Cliente;

public class Payu implements MedioPago{

	DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Override
	public boolean procesarPago(Cliente comprador, double valor, LocalDate fecha, String numeroTarjeta, String numeroTransaccion) {
		String nombre = comprador.getNombre();
		String email = comprador.getEmail();
		String fechaString = fecha.format(formatoFecha);
		String valorString = Double.toString(valor);
		registrarTransaccion( "./datos/PayPayu.txt", nombre, email, valorString, fechaString, numeroTarjeta, numeroTransaccion);
		return true;
	}
	
	public void registrarTransaccion(String archivo, String nombre, String email, String valor, String fecha,  String numeroTarjeta, String numeroTransaccion) {
		try {
			PrintWriter writer = new PrintWriter( archivo );
			
			writer.println(numeroTransaccion + ":" + fecha + ":" + nombre + ":" + email + ":" + numeroTarjeta + ":" + valor);
			writer.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
