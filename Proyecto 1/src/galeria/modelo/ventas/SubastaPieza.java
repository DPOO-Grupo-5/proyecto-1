package galeria.modelo.ventas;
import java.util.Date;
import java.util.List;

import galeria.modelo.pieza.Estado;
import galeria.modelo.pieza.Pieza;

/**
 * Esta clase modela las piezas que ingresan a ser subastadas
 */
public class SubastaPieza {
	
	/**
	 * Hace referencia al valor inicial para la subasta de la pieza
	 */
	private double valorInicial;
	/**
	 * Hace referencia al valor minimo por el cual se puede vender la pieza
	 */
	private double valorMinimo;
	/**
	 * Hace referencia al valor que va ganando la subasta
	 */
	private double valorActual;
	/**
	 * Lista que almacena todas las ofertas realizadas para la pieza
	 */
	private List<Oferta> ofertas;
	/**
	 * Pieza que se esta subastando
	 */
	private Pieza piezaSubastada;
	/**
	 * Al finalizar la oferta procede a la venta y el pago de la pieza
	 */
	private Venta pago;
	/**
	 * Fecha en la que inicia la subasta
	 */
	private Date tiempoInicial;
	/**
	 * Fecha en la que finaliza la subasta
	 */
	private Date tiempoFinal;
	
	/**
	 * A partir de sus atributos crea una pieza en subasta
	 */
	public SubastaPieza(double valorInicial, double valorMinimo, double valorOfertado, double valorActual, Pieza piezaSubastada, Date tiempoInicial, Date tiempoFinal) {
		this.valorInicial = valorInicial;
		this.valorMinimo = valorMinimo;
		this.valorActual = valorInicial;
		this.ofertas = null;
		this.piezaSubastada = piezaSubastada;
		this.pago = null;
		this.tiempoInicial = tiempoInicial;
		this.tiempoFinal = tiempoFinal;
		this.getPiezaSubastada().setEstado(Estado.SUBASTA);
	}
	
	public double getValorInicial() {
		return valorInicial;
	}
	public void setValorInicial(double valorInicial) {
		this.valorInicial = valorInicial;
	}
	public double getValorMinimo() {
		return valorMinimo;
	}
	public void setValorMinimo(double valorMinimo) {
		this.valorMinimo = valorMinimo;
	}
	public double getValorActual() {
		return valorActual;
	}
	public void setValorActual(double valorActual) {
		this.valorActual = valorActual;
	}
	public List<Oferta> getOfertas() {
		return ofertas;
	}
	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}
	public Pieza getPiezaSubastada() {
		return piezaSubastada;
	}
	public void setPiezaSubastada(Pieza piezaSubastada) {
		this.piezaSubastada = piezaSubastada;
	}
	public Venta getPago() {
		return pago;
	}
	public void setPago(Venta pago) {
		this.pago = pago;
	}
	public Date getTiempoInicial() {
		return tiempoInicial;
	}
	public void setTiempoInicial(Date tiempoInicial) {
		this.tiempoInicial = tiempoInicial;
	}
	public Date getTiempoFinal() {
		return tiempoFinal;
	}
	public void setTiempoFinal(Date tiempoFinal) {
		this.tiempoFinal = tiempoFinal;
	}
	/**
	 * Verifica una oferta con respecto al valor actual y la ingresa en la lista de ofertas
	 * @param oferta a validar
	 * @return indica si la oferta
	 */
	public boolean recibirOferta(Oferta oferta) {
		if (this.getValorInicial()<oferta.getValor()) {
			this.setValorActual(oferta.getValor());
			oferta.setAceptada(true);
			this.getOfertas().addFirst(oferta);
		}
		//else
		//oferta.setAceptada(false);
			//excep
		return oferta.isAceptada();
	}
	
}
