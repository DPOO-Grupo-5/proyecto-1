package galeria.modelo.pieza;

import java.util.Date;
import java.util.List;

import galeria.modelo.usuario.Usuario;

/**
 * Esta clase se encarga de modelar una pieza de tipo video.
 */
public class Video extends Pieza
{
	/**
	 * Inidica la duracion en segundos del video.
	 */
	public int duracion;
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
     * Construye una pieza de tipo video y asigna los valores a sus atributos. Constructor utilizado cuando no se encuentre disponible para venta directa por un valor fijo y no sea adquirida en consignacion
     */
	public Video(String codigo, String titulo, String yearCreacion, String lugarCreacion, List<String> autor,
			Estado estado, double alto, double ancho, int duracion, String resolucion, int tamano, String formato) {
		super(codigo, titulo, yearCreacion, lugarCreacion, autor, estado, alto, ancho, Tipo.VIDEO);
		this.duracion = duracion;
		this.resolucion = resolucion;
		this.tamano = tamano;
		this.formato = formato;
	}
	/**
     * Construye una pieza de tipo video y asigna los valores a sus atributos. Constructor utilizado cuando no sea adquirida en consignacion
     */
	public Video(String codigo, String titulo, String yearCreacion, String lugarCreacion, List<String> autor,
			Estado estado, double alto, double ancho, boolean disponibilidadVentaDirecta,
			double valorFijoVentaDirecta, int duracion, String resolucion, int tamano, String formato) {
		super(codigo, titulo, yearCreacion, lugarCreacion, autor, estado, alto, ancho, Tipo.VIDEO, disponibilidadVentaDirecta,
				valorFijoVentaDirecta);
		this.duracion = duracion;
		this.resolucion = resolucion;
		this.tamano = tamano;
		this.formato = formato;
	}
	/**
     * Construye una pieza de tipo video y asigna los valores a sus atributos. Constructor utilizado cuando se encuentre disponible para venta directa por un valor fijo y sea adquirida en consignacion
     */
	public Video(String codigo, String titulo, String yearCreacion, String lugarCreacion, List<String> autor,
			Estado estado, double alto, double ancho, boolean disponibilidadVentaDirecta,
			double valorFijoVentaDirecta, boolean esConsignacion, Date fechaInicioConsignacion,
			Date fechaFinConsignacion, Usuario propietarioConsignacion, int duracion, String resolucion, int tamano,
			String formato) {
		super(codigo, titulo, yearCreacion, lugarCreacion, autor, estado, alto, ancho, Tipo.VIDEO, disponibilidadVentaDirecta,
				valorFijoVentaDirecta, esConsignacion, fechaInicioConsignacion, fechaFinConsignacion,
				propietarioConsignacion);
		this.duracion = duracion;
		this.resolucion = resolucion;
		this.tamano = tamano;
		this.formato = formato;
	}
	
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
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
