package galeria.modelo.pieza;
import java.time.LocalDate;
import java.util.List;

import galeria.modelo.usuario.Usuario;

/**
 * Esta clase se encarga de modelar una pieza, contiene ciertos metodos para su manejo.
 */
public abstract class Pieza {

	/**
	 * Numero con el que se identifica la pieza.
	 */
	private String codigo;
	/**
	 * Titulo de la obra
	 */
	private String titulo;
	/**
	 * Año de creacion de la obra
	 */
	private String yearCreacion;
	/**
	 * Lugar donde se elaboro la obra
	 */
	private String lugarCreacion;
	/**
	 * Lista que contiene el autor o los autores de la pieza, el autor tambien puede ser anonimo
	 */
	private List<String> autor;
	/**
	 * Estado en que se encuentra la pieza. (BODEGA, EXHIBICION, SUBASTA, DEVUELTO, BLOQUEADO, VENDIDO)
	 */
	private Estado estado;
	/**
	 * Una de las dimensiones de la pieza, la altura.
	 */
	private double alto;
	/**
	 * Una de las dimensiones de la pieza, el ancho.
	 */
	private double ancho;
	/**
	 * Clasificacion otorgada para la obra. (PINTURA, ESCULTURA, VIDEO, FOTOGRAFIA, IMPRESION, OTRO)
	 */
	private Tipo tipo;
	/**
	 * Indica si la pieza se encuentra disponible para la venta por un valor fijo
	 */
	private boolean disponibilidadVentaDirecta;
	/**
	 * Valor por el cual se puede realizar la venta directa
	 */
	private double ValorFijoVentaDirecta;
	/**
	 * Indica si la pieza fue adquirida en consigancion
	 */
	private boolean esConsignacion;
	/**
	 * Indica si la fecha de inicio de la consignacion, si es el caso
	 */
	private LocalDate fechaInicioConsignacion;
	/**
	 * Indica si la fecha final de la consignacion, si es el caso
	 */
	private LocalDate fechaFinConsignacion;
	/**
	 * Establece el porpietario de la pieza que se obtuvo en consignacion, si es el caso
	 */
	private Usuario propietario;
	
	/**
     * Construye una pieza y asigna los valores a sus atributos. Constructor utilizado cuando no se encuentre disponible para venta directa por un valor fijo y no sea adquirida en consignacion
     */
	public Pieza(String codigo, String titulo, String yearCreacion, String lugarCreacion, List<String> autor,
			Estado estado, double alto, double ancho, Tipo tipo) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.yearCreacion = yearCreacion;
		this.lugarCreacion = lugarCreacion;
		this.autor = autor;
		this.estado = estado;
		this.alto = alto;
		this.ancho = ancho;
		this.tipo = tipo;
		this.disponibilidadVentaDirecta = false;
		this.ValorFijoVentaDirecta = 0;
		this.esConsignacion = false;
		this.fechaInicioConsignacion = null;
		this.fechaFinConsignacion = null;
		this.propietario = null;
	}
	/**
     * Construye una pieza y asigna los valores a sus atributos. Constructor utilizado cuando no sea adquirida en consignacion
     */
	public Pieza(String codigo, String titulo, String yearCreacion, String lugarCreacion, List<String> autor,
			Estado estado, double alto, double ancho, Tipo tipo, boolean disponibilidadVentaDirecta,
			double valorFijoVentaDirecta) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.yearCreacion = yearCreacion;
		this.lugarCreacion = lugarCreacion;
		this.autor = autor;
		this.estado = estado;
		this.alto = alto;
		this.ancho = ancho;
		this.tipo = tipo;
		this.disponibilidadVentaDirecta = disponibilidadVentaDirecta;
		this.ValorFijoVentaDirecta = valorFijoVentaDirecta;
		this.esConsignacion = false;
		this.fechaInicioConsignacion = null;
		this.fechaFinConsignacion = null;
		this.propietario = null;
	}
	/**
     * Construye una pieza de tipo y asigna los valores a sus atributos. Constructor utilizado cuando se encuentre disponible para venta directa por un valor fijo y sea adquirida en consignacion
     */
	public Pieza(String codigo, String titulo, String yearCreacion, String lugarCreacion, List<String> autor,
			Estado estado, double alto, double ancho, Tipo tipo, boolean disponibilidadVentaDirecta,
			double valorFijoVentaDirecta, boolean esConsignacion, LocalDate fechaInicioConsignacion,
			LocalDate fechaFinConsignacion, Usuario propietario) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.yearCreacion = yearCreacion;
		this.lugarCreacion = lugarCreacion;
		this.autor = autor;
		this.estado = estado;
		this.alto = alto;
		this.ancho = ancho;
		this.tipo = tipo;
		this.disponibilidadVentaDirecta = disponibilidadVentaDirecta;
		this.ValorFijoVentaDirecta = valorFijoVentaDirecta;
		this.esConsignacion = esConsignacion;
		this.fechaInicioConsignacion = fechaInicioConsignacion;
		this.fechaFinConsignacion = fechaFinConsignacion;
		this.propietario = propietario;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getYearCreacion() {
		return yearCreacion;
	}
	public void setYearCreacion(String yearCreacion) {
		this.yearCreacion = yearCreacion;
	}
	public String getLugarCreacion() {
		return lugarCreacion;
	}
	public void setLugarCreacion(String lugarCreacion) {
		this.lugarCreacion = lugarCreacion;
	}
	public List<String> getAutor() {
		return autor;
	}
	public void setAutor(List<String> autor) {
		this.autor = autor;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public double getAlto() {
		return alto;
	}
	public void setAlto(double alto) {
		this.alto = alto;
	}
	public double getAncho() {
		return ancho;
	}
	public void setAncho(double ancho) {
		this.ancho = ancho;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public boolean isDisponibilidadVentaDirecta() {
		return disponibilidadVentaDirecta;
	}
	public void setDisponibilidadVentaDirecta(boolean disponibilidadVentaDirecta) {
		this.disponibilidadVentaDirecta = disponibilidadVentaDirecta;
	}
	public double getValorFijoVentaDirecta() {
		return ValorFijoVentaDirecta;
	}
	public void setValorFijoVentaDirecta(double valorFijoVentaDirecta) {
		ValorFijoVentaDirecta = valorFijoVentaDirecta;
	}
	public boolean isEsConsignacion() {
		return esConsignacion;
	}
	public void setEsConsignacion(boolean esConsignacion) {
		this.esConsignacion = esConsignacion;
	}
	public LocalDate getFechaInicioConsignacion() {
		return fechaInicioConsignacion;
	}
	public void setFechaInicioConsignacion(LocalDate fechaInicioConsignacion) {
		this.fechaInicioConsignacion = fechaInicioConsignacion;
	}
	public LocalDate getFechaFinConsignacion() {
		return fechaFinConsignacion;
	}
	public void setFechaFinConsignacion(LocalDate fechaFinConsignacion) {
		this.fechaFinConsignacion = fechaFinConsignacion;
	}
	public Usuario getPropietario() {
		return propietario;
	}
	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	} 
	/**
	 * Cambia el estado de una pieza a DEVUELTO
	 */
	public void Devolucion (Pieza pieza) { 
		pieza.setEstado(Estado.DEVUELTO);
	}
}
