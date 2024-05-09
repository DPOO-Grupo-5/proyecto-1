package galeria.modelo.pieza;

import java.time.LocalDate;
import java.util.List;

import galeria.modelo.usuario.Usuario;

/**
 * Esta clase se encarga de modelar una pieza de tipo fotografia.
 */
public class Fotografia extends Pieza
{	
	/**
	 * Indica la resolucion de la fotografia
	 */
	private String resolucion;
	/**
	 * Especifica el tamaño del archivo de la fotografia
	 */
	private int tamano;
	/**
	 * Especifica el formato en el cual se almacena la fotografia
	 */
	private String formato;
	
	/**
     * Construye una pieza de tipo fotografia y asigna los valores a sus atributos. Constructor utilizado cuando no se encuentre disponible para venta directa por un valor fijo y no sea adquirida en consignacion
     */
	public Fotografia(String codigo, String titulo, String yearCreacion, String lugarCreacion, List<String> autor,
			Estado estado, double alto, double ancho, String resolucion,
			int tamano, String formato) {
		super(codigo, titulo, yearCreacion, lugarCreacion, autor, estado, alto, ancho, Tipo.FOTOGRAFIA);
		this.resolucion = resolucion;
		this.tamano = tamano;
		this.formato = formato;
	}

	/**
     * Construye una pieza de tipo fotografia y asigna los valores a sus atributos. Constructor utilizado cuando no sea adquirida en consignacion
     */
	public Fotografia(String codigo, String titulo, String yearCreacion, String lugarCreacion, List<String> autor,
			Estado estado, double alto, double ancho, boolean disponibilidadVentaDirecta,
			double valorFijoVentaDirecta, String resolucion, int tamano, String formato) {
		super(codigo, titulo, yearCreacion, lugarCreacion, autor, estado, alto, ancho, Tipo.FOTOGRAFIA, disponibilidadVentaDirecta,
				valorFijoVentaDirecta);
		this.resolucion = resolucion;
		this.tamano = tamano;
		this.formato = formato;
	}

	/**
     * Construye una pieza de tipo fotografia y asigna los valores a sus atributos. Constructor utilizado cuando se encuentre disponible para venta directa por un valor fijo y sea adquirida en consignacion
     */
	public Fotografia(String codigo, String titulo, String yearCreacion, String lugarCreacion, List<String> autor,
			Estado estado, double alto, double ancho, boolean disponibilidadVentaDirecta,
			double valorFijoVentaDirecta, boolean esConsignacion, LocalDate fechaInicioConsignacion,
			LocalDate fechaFinConsignacion, Usuario propietarioConsignacion, String resolucion, int tamano, String formato) {
		super(codigo, titulo, yearCreacion, lugarCreacion, autor, estado, alto, ancho, Tipo.FOTOGRAFIA, disponibilidadVentaDirecta,
				valorFijoVentaDirecta, esConsignacion, fechaInicioConsignacion, fechaFinConsignacion,
				propietarioConsignacion);
		this.resolucion = resolucion;
		this.tamano = tamano;
		this.formato = formato;
	}
	
	public Fotografia() {
		super();
	}

	public String getResolucion() {
		return resolucion;
	}
	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}
	public int getTamano() {
		return tamano;
	}
	public void setTamano(int tamano) {
		this.tamano = tamano;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	
}
