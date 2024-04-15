package galeria.modelo.ventas;

import galeria.modelo.usuario.Usuario;

/**
 * Esta clase se encarga de modelar las ofertas que realizan los clientes para participar en la subasta de una pieza especifica
 */
public class Oferta {
	/**
	 * Hace referencia al usuario que realizo la oferta
	 */
	private Usuario ofertador;
	/**
	 * Indica si la oferta ha sido aceptada o no, segun el valor que vaya ganado la subasta
	 */
	private boolean aceptada;
	/**
	 * Monto por el cual se realiza la oferta
	 */
	private double valor;
	/**
	 * Indica si la oferta es la ganadora de la subasta
	 */
	private boolean esGanador;
	
	/**
	 * Crea una oferta a partir de el cliente y el valor
	 */
	public Oferta(Usuario ofertador, double valor) {
		this.ofertador = ofertador;
		this.aceptada = false;
		this.valor = valor;
		this.esGanador = false;
	}
	
	public Usuario getOfertador() {
		return ofertador;
	}
	public void setOfertador(Usuario ofertador) {
		this.ofertador = ofertador;
	}
	public boolean isAceptada() {
		return aceptada;
	}
	public void setAceptada(boolean aceptada) {
		this.aceptada = aceptada;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public boolean isEsGanador() {
		return esGanador;
	}
	public void setEsGanador(boolean esGanador) {
		this.esGanador = esGanador;
	}

}
