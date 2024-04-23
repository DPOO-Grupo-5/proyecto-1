package galeria.modelo.pieza;

import java.util.Date;
import java.util.List;

import galeria.modelo.usuario.Usuario;

public class Impresion extends Pieza
{
	/**
     * Especifica el material en el cual se realizo la impresion
     */
	private String material;
	/**
     * Especifica el metodo con el cual se realizo la impresion
     */
	private String tipoImpresion;
	
	/**
     * Construye una pieza de tipo impresion y asigna los valores a sus atributos. Constructor utilizado cuando no se encuentre disponible para venta directa por un valor fijo y no sea adquirida en consignacion
     */
	public Impresion(String codigo, String titulo, String yearCreacion, String lugarCreacion, List<String> autor,
			Estado estado, double alto, double ancho, String material,
			String tipoImpresion) {
		super(codigo, titulo, yearCreacion, lugarCreacion, autor, estado, alto, ancho, Tipo.IMPRESION);
		this.material = material;
		this.tipoImpresion = tipoImpresion;
	}

	/**
     * Construye una pieza de tipo impresion y asigna los valores a sus atributos. Constructor utilizado cuando no sea adquirida en consignacion
     */
	public Impresion(String codigo, String titulo, String yearCreacion, String lugarCreacion, List<String> autor,
			Estado estado, double alto, double ancho, boolean disponibilidadVentaDirecta,
			double valorFijoVentaDirecta, String material, String tipoImpresion) {
		super(codigo, titulo, yearCreacion, lugarCreacion, autor, estado, alto, ancho, Tipo.IMPRESION, disponibilidadVentaDirecta,
				valorFijoVentaDirecta);
		this.material = material;
		this.tipoImpresion = tipoImpresion;
	}

	/**
     * Construye una pieza de tipo impresion y asigna los valores a sus atributos. Constructor utilizado cuando se encuentre disponible para venta directa por un valor fijo y sea adquirida en consignacion
     */
	public Impresion(String codigo, String titulo, String yearCreacion, String lugarCreacion, List<String> autor,
			Estado estado, double alto, double ancho, boolean disponibilidadVentaDirecta,
			double valorFijoVentaDirecta, boolean esConsignacion, Date fechaInicioConsignacion,
			Date fechaFinConsignacion, Usuario propietarioConsignacion, String material, String tipoImpresion) {
		super(codigo, titulo, yearCreacion, lugarCreacion, autor, estado, alto, ancho, Tipo.IMPRESION, disponibilidadVentaDirecta,
				valorFijoVentaDirecta, esConsignacion, fechaInicioConsignacion, fechaFinConsignacion,
				propietarioConsignacion);
		this.material = material;
		this.tipoImpresion = tipoImpresion;
	}
	
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getTipoImpresion() {
		return tipoImpresion;
	}
	public void setTipoImpresion(String tipoImpresion) {
		this.tipoImpresion = tipoImpresion;
	}

}
