package galeria.modelo.pieza;
import java.util.Date;
import java.util.List;

import galeria.modelo.usuario.Usuario;

/**
 * Esta clase se encarga de modelar una pieza de tipo pintura.
 */
public class Pintura extends Pieza 
{
	/**
	 * La lista almacena las tecnicas utilizadas para la realizacion de la pintura
	 */
	private List<String> tecnicas;
	/**
	 * Inidca el peso del cuadro
	 */
	private double peso;
	
	/**
     * Construye una pieza de tipo pintura y asigna los valores a sus atributos. Constructor utilizado cuando no se encuentre disponible para venta directa por un valor fijo y no sea adquirida en consignacion
     */
	public Pintura(String codigo, String titulo, Date yearCreacion, String lugarCreacion, List<String> autor,
			Estado estado, double alto, double ancho, boolean disponibilidadVentaDirecta,
			List<String> tecnicas, double peso) {
		super(codigo, titulo, yearCreacion, lugarCreacion, autor, estado, alto, ancho, Tipo.PINTURA);
		this.tecnicas = tecnicas;
		this.peso = peso;
	}
	/**
     * Construye una pieza de tipo pintura y asigna los valores a sus atributos. Constructor utilizado cuando no sea adquirida en consignacion
     */
	public Pintura(String codigo, String titulo, Date yearCreacion, String lugarCreacion, List<String> autor,
			Estado estado, double alto, double ancho, boolean disponibilidadVentaDirecta,
			double valorFijoVentaDirecta, List<String> tecnicas, double peso) {
		super(codigo, titulo, yearCreacion, lugarCreacion, autor, estado, alto, ancho, Tipo.PINTURA, disponibilidadVentaDirecta,
				valorFijoVentaDirecta);
		this.tecnicas = tecnicas;
		this.peso = peso;
	}
	/**
     * Construye una pieza de tipo pintura y asigna los valores a sus atributos. Constructor utilizado cuando se encuentre disponible para venta directa por un valor fijo y sea adquirida en consignacion
     */
	public Pintura(String codigo, String titulo, Date yearCreacion, String lugarCreacion, List<String> autor,
			Estado estado, double alto, double ancho, boolean disponibilidadVentaDirecta,
			double valorFijoVentaDirecta, boolean esConsignacion, Date fechaInicioConsignacion,
			Date fechaFinConsignacion, Usuario propietarioConsignacion, List<String> tecnicas, double peso) {
		super(codigo, titulo, yearCreacion, lugarCreacion, autor, estado, alto, ancho, Tipo.PINTURA, disponibilidadVentaDirecta,
				valorFijoVentaDirecta, esConsignacion, fechaInicioConsignacion, fechaFinConsignacion,
				propietarioConsignacion);
		this.tecnicas = tecnicas;
		this.peso = peso;
	}
	
	public List<String> getTecnicas() {
		return tecnicas;
	}
	public void setTecnicas(List<String> tecnicas) {
		this.tecnicas = tecnicas;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	

}
