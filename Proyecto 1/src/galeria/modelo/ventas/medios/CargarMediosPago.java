package galeria.modelo.ventas.medios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

import galeria.modelo.usuario.Cliente;

public class CargarMediosPago {
	
	public CargarMediosPago() {
		
	}
	
	public static MedioPago cargarMedioPago(String medioElegido, Cliente comprador, double valor, LocalDate fecha) {
		try (BufferedReader reader = new BufferedReader(new FileReader( "./../datos/mediosPago.txt" ))) {
            String line = reader.readLine();
            while (line != null) {
            	String[] partes = line.split( ":" );
	            if (partes[4].equals(medioElegido)) {
	            	Class<?> clase;
					try {
						clase = Class.forName(medioElegido);
						Constructor<?> constructor;
						try {
							constructor = clase.getDeclaredConstructor(Cliente.class, double.class, Date.class);
							try {
								MedioPago medioPago = (MedioPago) constructor.newInstance(comprador, valor, fecha);
								return medioPago;
							} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
									| InvocationTargetException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (NoSuchMethodException | SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}	
}