package galeria.modelo.ventas;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Date;

import galeria.modelo.pieza.Estado;
import galeria.modelo.pieza.Pieza;
import galeria.modelo.usuario.Cliente;
import galeria.modelo.ventas.medios.CargarMediosPago;
import galeria.modelo.ventas.medios.MedioPago;

/**
 * Esta clase se encarga de realizar las ventas y pagos de las piezas de la galeria
 */
public class Venta {
	/**
	 * Hace referencia al usuario que realizo la oferta
	 */
	private double valor;
	/**
	 * Hace referencia al comprador de la pieza
	 */
	private Cliente comprador;
	/**
	 * Indica si la venta fue exitosa
	 */
	private boolean aceptada;
	/**
	 * Indica si el propietario de la pieza la tiene en el momento de la consulta
	 */
	private boolean activo;
	/**
	 * Indica el medio de pago con el que se realizo la compra
	 */
	private MedioPago medioPago;
	/**
	 * Indica la pieza que se esta comprando
	 */
	private Pieza piezaVenta;
	/**
	 * Indica la fecha de la venta
	 */
	private LocalDate fecha;
	
	private String medioElegido;
	
	/**
	 * Crea una venta relacionando el cliente con el valor y la pieza, en el caso de una subasta. Ademas cambia el estado de la pieza a bloqueado hasta que se efectue el pago
	 */
	public Venta(double valor, Cliente comprador, Pieza piezaVenta, String medioElegido){
		this.valor = valor;
		this.comprador = comprador;
		this.aceptada = false;
		this.activo = false;
		this.piezaVenta = piezaVenta;
		this.fecha = null;
		this.getPiezaVenta().setEstado(Estado.BLOQUEADO);
		this.getPiezaVenta().setDisponibilidadVentaDirecta(false);
		this.medioElegido = medioElegido;
		
		this.medioPago = CargarMediosPago.cargarMedioPago(medioElegido, this.comprador, this.valor, this.fecha);

		
		
	}
	/**
	 * Crea una venta relacionando el cliente y la pieza, en el caso de una venta directa. Ademas cambia el estado de la pieza a bloqueado hasta que se efectue el pago
	 */
	public Venta(Cliente comprador, Pieza piezaVenta, String medioElegido) {
		this.valor = piezaVenta.getValorFijoVentaDirecta();
		this.comprador = comprador;
		this.aceptada = false;
		this.activo = false;
		this.piezaVenta = piezaVenta;
		this.fecha = null;
		this.getPiezaVenta().setEstado(Estado.BLOQUEADO);
		this.getPiezaVenta().setDisponibilidadVentaDirecta(false);
		this.medioElegido = medioElegido;
		
		this.medioPago = CargarMediosPago.cargarMedioPago(medioElegido, this.comprador, this.valor, this.fecha);
	}
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Cliente getComprador() {
		return comprador;
	}
	public void setComprador(Cliente comprador) {
		this.comprador = comprador;
	}
	public boolean isAceptada() {
		return aceptada;
	}
	public void setAceptada(boolean aceptada) {
		this.aceptada = aceptada;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public MedioPago getMedioPago() {
		return medioPago;
	}
	public void setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}
	public Pieza getPiezaVenta() {
		return piezaVenta;
	}
	public void setPiezaVenta(Pieza piezaVenta) {
		this.piezaVenta = piezaVenta;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public String getMedioElegido() {
		return medioElegido;
	}

	public void setMedioElegido(String medioElegido) {
		this.medioElegido = medioElegido;
	}
	
	/**
	 * Descuenta el valor de la compra de el valor maximo de compras asignado para el cliente
	 * @param venta que se va a descontar
	 * @return indica si se tenian los fondos suficientes dentro de su valor maximo de compras en la galeria
	 */
	public boolean descontarPago(Venta venta) {
		if (venta.getComprador().getValorMaximo()-venta.getValor()>0) {
			venta.getComprador().setValorMaximo(venta.getComprador().getValorMaximo()-venta.getValor());
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Efectua y registra la venta
	 * @param venta que se va a efectuar
	 * @param medio de pago con el que se realiza la venta
	 * @return indica si la venta fue exitosa
	 */
	public boolean efectuarVenta(Venta venta) {
		if(venta.descontarPago(venta)) {
			venta.setAceptada(true);
			venta.getPiezaVenta().setEstado(Estado.VENDIDO);
			LocalDate datenow = LocalDate.now();
			venta.setFecha(datenow);
			venta.getPiezaVenta().setPropietario(venta.getComprador());
			return true;
		}
		else {
			venta.setAceptada(false);
			venta.getPiezaVenta().setEstado(Estado.BODEGA);
			return false;
		}
	}
	
	
}
