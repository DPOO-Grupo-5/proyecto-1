package galeria.modelo.pieza;
import java.util.Date;
import java.util.List;

import galeria.modelo.usuario.Usuario;

/**
 * Esta clase se encarga de modelar una pieza de tipo escultura.
 */
public class Escultura extends Pieza
{
	/**
	 * Inidica la profunidad de la escultura
	 */
	private double profundidad;
	/**
	 * La lista almacena los materiales utilizados en la elaboracion de la escultura
	 */
	private List<String> materiales;
	/**
	 * Inidica el peso de la escultura
	 */
	private double peso;
	/**
	 * Indica si la estructura requiere electricidad o no
	 */
	private boolean electricidad;
	/**
	 * La lista almacena todos los detalles que se deben tener en cuenta para su instalacion
	 */
	private List<String> detallesInstalacion;
	
	/**
     * Construye una pieza de tipo escultura y asigna los valores a sus atributos. Constructor utilizado cuando no se encuentre disponible para venta directa por un valor fijo y no sea adquirida en consignacion
     */
	public Escultura(String codigo, String titulo, Date yearCreacion, String lugarCreacion, List<String> autor,
			Estado estado, double alto, double ancho, double profundidad,
			List<String> materiales, double peso, boolean electricidad, List<String> detallesInstalacion) {
		super(codigo, titulo, yearCreacion, lugarCreacion, autor, estado, alto, ancho, Tipo.ESCULTURA);
		this.profundidad = profundidad;
		this.materiales = materiales;
		this.peso = peso;
		this.electricidad = electricidad;
		this.detallesInstalacion = detallesInstalacion;
	}

	/**
     * Construye una pieza de tipo escultura y asigna los valores a sus atributos. Constructor utilizado cuando no sea adquirida en consignacion
     */
	public Escultura(String codigo, String titulo, Date yearCreacion, String lugarCreacion, List<String> autor,
			Estado estado, double alto, double ancho, boolean disponibilidadVentaDirecta,
			double valorFijoVentaDirecta, double profundidad, List<String> materiales, double peso,
			boolean electricidad, List<String> detallesInstalacion) {
		super(codigo, titulo, yearCreacion, lugarCreacion, autor, estado, alto, ancho, Tipo.ESCULTURA, disponibilidadVentaDirecta,
				valorFijoVentaDirecta);
		this.profundidad = profundidad;
		this.materiales = materiales;
		this.peso = peso;
		this.electricidad = electricidad;
		this.detallesInstalacion = detallesInstalacion;
	}

	/**
     * Construye una pieza de tipo escultura y asigna los valores a sus atributos. Constructor utilizado cuando se encuentre disponible para venta directa por un valor fijo y sea adquirida en consignacion
     */
	public Escultura(String codigo, String titulo, Date yearCreacion, String lugarCreacion, List<String> autor,
			Estado estado, double alto, double ancho, boolean disponibilidadVentaDirecta,
			double valorFijoVentaDirecta, boolean esConsignacion, Date fechaInicioConsignacion,
			Date fechaFinConsignacion, Usuario propietarioConsignacion, double profundidad, List<String> materiales,
			double peso, boolean electricidad, List<String> detallesInstalacion) {
		super(codigo, titulo, yearCreacion, lugarCreacion, autor, estado, alto, ancho, Tipo.ESCULTURA, disponibilidadVentaDirecta,
				valorFijoVentaDirecta, esConsignacion, fechaInicioConsignacion, fechaFinConsignacion,
				propietarioConsignacion);
		this.profundidad = profundidad;
		this.materiales = materiales;
		this.peso = peso;
		this.electricidad = electricidad;
		this.detallesInstalacion = detallesInstalacion;
	}

	public double getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(double profundidad) {
		this.profundidad = profundidad;
	}

	public List<String> getMateriales() {
		return materiales;
	}

	public void setMateriales(List<String> materiales) {
		this.materiales = materiales;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public boolean isElectricidad() {
		return electricidad;
	}

	public void setElectricidad(boolean electricidad) {
		this.electricidad = electricidad;
	}

	public List<String> getDetallesInstalacion() {
		return detallesInstalacion;
	}

	public void setDetallesInstalacion(List<String> detallesInstalacion) {
		this.detallesInstalacion = detallesInstalacion;
	}
		
}
