package galeria.modelo.usuario;

import java.util.LinkedList;

/**
 * Esta clase modela a los clientes de la galeria, tanto los propietarios como los compradores
 */
public class Cliente extends Usuario 
{
	/**
	 * Especifica el valor maximo que puede utilizar el cliente para realizar compras dentro de la galeria, se estipula como el 10% de su capacidad adquisitiva
	 */
	private double valorMaximo;
	/**
	 * Indica si el cliente se encuentra verificado por el administrador o no
	 */
	private boolean verificado;
	/**
	 * Telefono de contacto del cliente
	 */
	private String telefono;
	/**
	 * Email de contacto del cliente
	 */
	private String email;
	/**
	 * Capacidad adquisitiva ingresada por el cliente
	 */
	private double capacidadAdquisitiva;
	
	/**
     * Construye un nuevo cliente, ingresa los valores
     */
	public Cliente(String login, String password, String nombre, String telefono, String email, double capacidadAdquisitiva, LinkedList<String> piezas) {
		super(Rol.CLIENTE, login, password, nombre);
		this.valorMaximo = 0;
		this.verificado = false;
		this.telefono = telefono;
		this.email = email;
		this.capacidadAdquisitiva = capacidadAdquisitiva;
	}
	
	public double getValorMaximo() {
		return valorMaximo;
	}
	public void setValorMaximo(double valorMaximo) {
		this.valorMaximo = valorMaximo;
	}
	public boolean isVerificado() {
		return verificado;
	}
	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getCapacidadAdquisitiva() {
		return capacidadAdquisitiva;
	}
	public void setCapacidadAdquisitiva(double capacidadAdquisitiva) {
		this.capacidadAdquisitiva = capacidadAdquisitiva;
	}
	/**
	 * En caso de que no se pueda realizar el pago debido a que no se cuenta con fondos en el valor maximo de las compras, podra demostrar que ha aumentado su capacidad adquisitiva
	 */
	public boolean demostrarCapacidadAdquisitiva(double nuevaCapacidadAdquisitiva) {
		if (nuevaCapacidadAdquisitiva>this.capacidadAdquisitiva) {
			this.setCapacidadAdquisitiva(nuevaCapacidadAdquisitiva);
			this.setValorMaximo(nuevaCapacidadAdquisitiva*0.1);
			return true;
		}
		//else
			//excep
		return false;
	}

}
