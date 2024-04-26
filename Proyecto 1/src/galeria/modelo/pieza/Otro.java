package galeria.modelo.pieza;
import java.time.LocalDate;
import java.util.List;

import galeria.modelo.usuario.Usuario;

public class Otro extends Pieza 
{	
	/**
     * Construye una pieza de tipo pintura y asigna los valores a sus atributos. Constructor utilizado cuando no se encuentre disponible para venta directa por un valor fijo y no sea adquirida en consignacion
     */
	public Otro(String codigo, String titulo, String yearCreacion, String lugarCreacion, List<String> autor,
			Estado estado, double alto, double ancho) {
		super(codigo, titulo, yearCreacion, lugarCreacion, autor, estado, alto, ancho, Tipo.OTRO);
	}
	/**
     * Construye una pieza de tipo pintura y asigna los valores a sus atributos. Constructor utilizado cuando no sea adquirida en consignacion
     */
	public Otro(String codigo, String titulo, String yearCreacion, String lugarCreacion, List<String> autor,
			Estado estado, double alto, double ancho, boolean disponibilidadVentaDirecta,
			double valorFijoVentaDirecta) {
		super(codigo, titulo, yearCreacion, lugarCreacion, autor, estado, alto, ancho, Tipo.OTRO, disponibilidadVentaDirecta,
				valorFijoVentaDirecta);
	}
	/**
     * Construye una pieza de tipo pintura y asigna los valores a sus atributos. Constructor utilizado cuando se encuentre disponible para venta directa por un valor fijo y sea adquirida en consignacion
     */
	public Otro(String codigo, String titulo, String yearCreacion, String lugarCreacion, List<String> autor,
			Estado estado, double alto, double ancho, boolean disponibilidadVentaDirecta,
			double valorFijoVentaDirecta, boolean esConsignacion, LocalDate fechaInicioConsignacion,
			LocalDate fechaFinConsignacion, Usuario propietarioConsignacion) {
		super(codigo, titulo, yearCreacion, lugarCreacion, autor, estado, alto, ancho, Tipo.OTRO, disponibilidadVentaDirecta,
				valorFijoVentaDirecta, esConsignacion, fechaInicioConsignacion, fechaFinConsignacion,
				propietarioConsignacion);
	}
}