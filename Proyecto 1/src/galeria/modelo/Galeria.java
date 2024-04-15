package galeria.modelo;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import galeria.modelo.pieza.Estado;
import galeria.modelo.pieza.Pieza;
import galeria.modelo.usuario.Cliente;
import galeria.modelo.usuario.Rol;
import galeria.modelo.usuario.Usuario;
import galeria.modelo.ventas.Oferta;
import galeria.modelo.ventas.SubastaPieza;
import galeria.modelo.ventas.Venta;

/**
 * Esta clase se encarga de realizar la mayoria de las actividades del funionamiento de la galeria.
 */
public class Galeria {
	/**
     * Es un lista que almacena todos los usuarios de la galeria.
     */
	private List<Usuario> usuarios;
	/**
     * Es un lista que almacena todas las piezas de la galeria.
     */
	private List<Pieza> inventario;
	/**
     * Es un lista que almacena todas las ventas realizadas por la galeria.
     */
	private List<Venta> ventas;
	/**
     * Es un lista que almacena todas las piezas que se encuentran en subasta.
     */
	private List<SubastaPieza> subastas;

	/**
     * Construye una nueva galeria e inicializa todas las listas con estructuras vacías
     */
	public Galeria() {
		usuarios = new LinkedList<Usuario> ( );
        inventario = new LinkedList<Pieza>( );
        ventas = new LinkedList<Venta>( );
        subastas = new LinkedList<SubastaPieza>( );
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public List<Pieza> getInventario() {
		return inventario;
	}
	public void setInventario(List<Pieza> inventario) {
		this.inventario = inventario;
	}
	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}
	public List<SubastaPieza> getSubastas() {
		return subastas;
	}

	public void setSubastas(List<SubastaPieza> subastas) {
		this.subastas = subastas;
	}

	/**
     * Valida el usuario y la contraseña de un usuario, utilizando el metodo comprobar usuario y se encarga de validar que la contraseña ingresada sea igual a la contraseña registrada para el usuario indicado
     * @param login login que identifica al usuario
     * @param password contraseña resgitrada por el usuario
     * @return Verdadero si los datos son correctos, de lo contrario retorna falso.
     */
	public boolean IniciarSesion (String login, String password) {  
		if (this.ComprobarUsuario(login)){
			for (Usuario user : this.getUsuarios()) {
				if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
					return true;
				}
			}
		}
		return false;
	}
	/**
     * Se encaraga de validar que el login ingresado exista
     * @param login login que identifica al usuario
     * @boolean inidica si el usuario ya existe o no
     */
	public boolean ComprobarUsuario (String login) {   
		for (Usuario user : this.getUsuarios()) {
			if (user.getLogin().equals(login)) {
				return true;
			}
		}
		return false;
	}
	/**
     * Se encaraga de validar que el telefono ingresado exista
     * @param telefono resgitrado por el usuario
     * @boolean inidica si el telefono ya existe o no
     */
	public boolean VerificarTelefono (String telefono) {   
		for (Usuario user : this.getUsuarios()) {
			if (user instanceof Cliente) {
				Cliente cliente = (Cliente) user;
                if (cliente.getTelefono().equals(telefono)) {
                    return true;
                }
			}
		}
		return false;
	}
	/**
     * Se encaraga de validar que el email ingresado exista
     * @param email resgitrado por el usuario
     * @boolean inidica si el email ya existe o no
     */
	public boolean VerificarEmail (String email) {   
		for (Usuario user : this.getUsuarios()) {
			if (user instanceof Cliente) {
				Cliente cliente = (Cliente) user;
                if (cliente.getEmail().equals(email)) {
                    return true;
                }
			}
		}
		return false;
	}
	/**
     * Verifica que el usuario recibido tenga el rol que se envia por parametro
     * @retrun inidica si el usuario cumple con el rol indicado
     */
	public boolean VerificarRol (Usuario usuario, Rol rol) {    
		if (usuario.getRol().equals(rol)) {
			return true;
		}
		return false;
	}
	/**
     *  Verifica que la fecha inicial sea menor que la final
     *  @param fecha inicial que se revisa
     *  @param fecha final que se valida
     *  @return revisa si la fecha inicial es antes que la fecha final
     */
	public boolean VerificarFechas (Date fechaInicial, Date fechaFinal) {
		if ((fechaInicial.compareTo(fechaFinal))<=0) {
			return true;
		}
		return false;
	}
	/**
     * Se encaraga de validar que el codigo de la pieza ingresada exista
     * @param codigo de la pieza que se quiere verificar
     * @boolean indica si el codigo de la pieza ya existe o no
     */
	public boolean VerificarCodigoPieza (String codigo) {    
		for (Pieza obra : this.getInventario()) {
			if (obra.getCodigo().equals(codigo)) {
				return true;
			}
		}
		return false;
	}
	/**
     * Recibe el login de un usuario y retorna toda su informacion
     * @param login del usuario que se quiere consultar
     * @retrun usuario correspondiente al login ingresado
     */
	public Usuario ConsultarUsuario (String login) {     
		for (Usuario user : this.getUsuarios()) {
			if (user.getLogin().equals(login)) {
				return user;
			}
		}
		//excepcion
		return null;
	}
	/**
     * Recibe el codigo de una pieza y retorna toda su informacion
     * @param codigo de la pieza que se quiere consultar
     * @retrun pieza correspondiente al codigo ingresado
     */
	public Pieza ConsultarPieza (String codigo) {     
		if (this.VerificarCodigoPieza(codigo)) {
			for (Pieza obra : this.getInventario()) {
				return obra;
			}
		}
		//excepcion
		return null;
	}
	/** 
     * Recibe un estado a consultar y retorna una lista con todas las piezas que se encuentran en este
     * @param estado de las piezas que se quieren consultar
     * @rertun lista con todas las piezas que se encuentren en ese estado
     */
	public List<Pieza> ConsultarInventario (Estado estado) {
		LinkedList<Pieza> catalogo = new LinkedList<Pieza>();
		for (Pieza obra : this.getInventario()) {
			if (obra.getEstado().equals(estado)) {
				catalogo.add(obra);
			}
		}
		if(catalogo.isEmpty()) 
			return catalogo;
		else 
			//exepcion
			return catalogo;
	}
	/**
     * Se encarga de asignar el valor maximo, el cual se calcula como el 10% de su capacidad adquisitiva y cambia su atributo "verificado" a verdadero
     * @param cliente que se quiere verificar
     */
	public void VerificarCliente (Usuario usuario) {
		if (usuario instanceof Cliente) {
			Cliente cliente = (Cliente) usuario;
			cliente.setValorMaximo((cliente.getCapacidadAdquisitiva()*0.1));
			cliente.setVerificado(true);
		}
		//else 
			//exepcion
	}
	/**
     * Crea una lista de piezas, luego recorre la lista de piezas del inventario revisando su propietario, si el estado es el cliente que recibió por parametro, la ingresa en la lista que se retornará.
     * @param login del usuario que quiere consultar sus piezas
     * @return la lista con todas las piezas del usuario
     */
	public List <Pieza> ConsultarPiezas (String login) { 
		if (this.ConsultarUsuario(login) instanceof Cliente) {
			LinkedList<Pieza> piezascliente = new LinkedList<Pieza>( );
			for (Pieza obra : this.getInventario()) {
				if(obra.getPropietario().equals(this.ConsultarUsuario(login))) {
					piezascliente.add(obra);
				}
			}
			return piezascliente;
		}
		//else 
			//exepcion
		return null;
	}
	/**
     * Recorre la lista de piezas del inventario revisando las fechas de consignacion para verificar si se debe realizar una devolucion
    */
	public void ActualizarEstadosPiezasConsignacion () { 
		Date datenow = new Date();
		for (Pieza obra : this.getInventario()) {
			if(obra.isEsConsignacion()) {
				if(obra.getFechaFinConsignacion().after(datenow)) {
					obra.Devolucion(obra);
					obra.setEsConsignacion(false);
					obra.setFechaFinConsignacion(null);
					obra.setFechaInicioConsignacion(null);
				}
			}
		}
	}
	/**
     * Recorre la lista de piezas del inventario revisando las fechas de la subasta, para verificar si la pieza debe volver a la bodega si la subasta culminó.
    */
	public void ActualizarEstadosPiezasSubasta() { 
		Date datenow = new Date();
		for (SubastaPieza obra : this.getSubastas()) {
			if(obra.getTiempoFinal().after(datenow)){
				if(this.FinalizarSubasta(obra)) {
					subastas.remove(obra);
				}
				else 
					obra.getPiezaSubastada().setEstado(Estado.BODEGA);
			}
		}
	}
	/**
     * Registra un usuario en la lista de usuarios de la galeria
     * @param usuario que se registra en la galeria
    */
	public void RegistrarOperador (Usuario usuario) {
		usuarios.add(usuario);
	}
	
	/**
     * Registra un usuario en la lista de usuarios de la galeria
     * @param cliente que se registra en la galeria
    */
	public void RegistrarCliente (Cliente cliente) {
		usuarios.add(cliente);
	}
	/**
     * Registra una pieza en la lista de piezas de la galeria
     * @param pieza que va a ingresar al inventario
    */
	public void RegistrarPieza (Pieza pieza) {
		inventario.add(pieza);
	}
	/**
     * Registra una venta en la lista de ventas de la galeria
     * @param venta que se va a registrar
    */
	public void RegistrarVenta (Venta venta) {
		ventas.add(venta);
	}
	/**
     * Registra una venta en la lista de ventas de la galeria
     * @param pieza que ingresa a la subasta
    */
	public void RegistrarSubasta (SubastaPieza piezasubastar) {
		subastas.add(piezasubastar);
	}
	/**
     * Finaliza la subasta, enviando la oferta ganadora para efectuar la verificacion y el pago
     * @param pieza que se va a verificar
     * @return indica si fue posible crear la venta de la pieza
    */
	public boolean FinalizarSubasta (SubastaPieza piezasubastar) {
		if(this.OfertaGanadora(piezasubastar.getOfertas().getFirst(), piezasubastar.getValorMinimo())) {
			Venta pago = new Venta(piezasubastar.getValorActual(), (Cliente) piezasubastar.getOfertas().getFirst().getOfertador(), piezasubastar.getPiezaSubastada());
			piezasubastar.setPago(pago);
			return true;
		}
		return false;
	}
	/**
     * Verifica que la oferta ganadora sea mayor que el valor minimo
     * @param oferta mayor al culminar la subasta
     * @param valor minimo por el cual se puede realizar la venta de la pieza
     * @return indica si la oferta es valida y se puede proceder al pago
    */
	public boolean OfertaGanadora (Oferta oferta, double valormin) {
		if (oferta.getValor()>valormin) {
			oferta.setEsGanador(true);
			return true;
		}
		oferta.setEsGanador(false);
		return false;
		//excep
	}
}


