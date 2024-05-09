package galeria.consola;

import java.io.IOException;
import java.text.ParseException;

import galeria.modelo.Galeria;
import galeria.modelo.usuario.Cliente;
import galeria.modelo.ventas.Oferta;
import galeria.modelo.ventas.SubastaPieza;
import galeria.modelo.ventas.Venta;
import galeria.persistencia.TipoInvalidoException;

public class ConsolaCliente extends ConsolaBasica
{
	private String login;
    
    public ConsolaCliente(Galeria galeria, String login) {
		super(galeria);
		this.login = login;
	}

	public void registrarCliente()
    {
    	String password = pedirCadenaAlUsuario("Nueva contraseña: ");	
    	String nombre = pedirCadenaAlUsuario("Nombre: ");
    	String telefono = pedirCadenaAlUsuario("Número de telefono: ");
    	String email = pedirCadenaAlUsuario("Email: ");
    	double capacidadAdquisitiva = pedirNumeroAlUsuario("Capacidad adquisitiva: ");
    	Cliente cliente = new Cliente(login, password, nombre, telefono, email, capacidadAdquisitiva, 0, false, null);
    	laGaleria.RegistrarCliente(cliente);
    }

    public void correrAplicacion() throws TipoInvalidoException, IOException, ParseException
    {
        boolean continuar = true;
        while (continuar)	
        {
            int opcion = mostrarMenu("Menú Cliente", new String[] {
                "Consultar información personal",
                "Modificar datos de contacto",
                "Cambiar contraseña",
                "Consultar piezas",
                "Consultar historial de compras",
                "Ofertar en una subasta",
                "Demostrar nuevos ingresos",
                "Comprar pieza",
                "Cerrar sesión",
                "Eliminar cuenta"
            });

            switch (opcion)
            {
                case 1:
                	System.out.println("Informacion personal:");
                	this.imprimirCliente((Cliente) this.laGaleria.ConsultarUsuario(login));
                    break;
                case 2:
                    modificarDatosContacto();
                    break;
                case 3:
                    cambiarContraseña();
                    break;
                case 4:
                	this.consultar();
                    break;
                case 5:
                    this.consultarComprador(login);
                    break;
                case 6:
                    ofertarSubasta();
                    break;
                case 7:
                    nuevosIngresos();
                    break;
                case 8:
                    comprarPieza();
                    break;
                case 9:
                    // Cerrar sesión
                	cerrarSesion();
                    continuar = false;
                    break;
                case 10:
                    eliminarCuenta();
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    
    private void modificarDatosContacto()
    {
    	int opcion = mostrarMenu("Modificar Dato de Contacto", new String[] {
                "Telefono",
                "E-mail"
            });

            switch (opcion)
            {
                case 1:
                	registrarNuevoTelefono();
                    break;
                case 2:
                	registrarNuevoCorreo();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
    }
    
    private void registrarNuevoTelefono()
    {
    	String telefono = pedirCadenaAlUsuario("Nuevo telefono");	
    	this.laGaleria.ConsultarCliente(login).setTelefono(telefono);
    	
    }

    private void registrarNuevoCorreo()
    {
    	String correo = pedirCadenaAlUsuario("Nuevo correo");
    	this.laGaleria.ConsultarCliente(login).setEmail(correo);
    }
    
    private void cambiarContraseña()
    {
    	String password = pedirCadenaAlUsuario("Nueva contraseña");
    	this.laGaleria.ConsultarCliente(login).setPassword(password);
    }
    
    private void ofertarSubasta()
    {
        this.piezasSubasta();
        String codigo = pedirCadenaAlUsuario("Codiga de la pieza que se quiere ofertar");
        double cantidad = pedirDobleAlUsuario("Valor por el cual se desea ofertar");
        Oferta oferta = new Oferta(this.laGaleria.ConsultarCliente(login), cantidad);
        for(SubastaPieza pieza : this.laGaleria.getSubastas()) {
        	if(pieza.getPiezaSubastada().getCodigo().equals(codigo)) {
        		if(pieza.RecibirOferta(oferta)) {
            		pieza.getOfertas().add(oferta);
            		System.out.println("Se ha registrado tu oferta correctamente.");
        		}
        		else {
        			System.out.println("No se pudo registrar tu oferta.");
        		}
        	}
        }
        
    }
    
    private void nuevosIngresos()
    {
    	double cantidad = pedirDobleAlUsuario("Nueva capacidad adquisitiva");
        if(this.laGaleria.ConsultarCliente(login).demostrarCapacidadAdquisitiva(cantidad)) {
        	System.out.println("Capacidad adquisitiva actualizada.");
        }
        else {
        	System.out.println("No se pudo actualizar su capacidad adquisitiva.");
        }
    }
    
    private void comprarPieza()
    {
        this.piezasDisponiblesVenta();
        String codigo = pedirCadenaAlUsuario("Codiga de la pieza que se quiere ofertar");
        Venta venta = new Venta(this.laGaleria.ConsultarCliente(codigo), this.laGaleria.ConsultarPieza(codigo));
        System.out.println("Se registro su deseo de compra, debe esperar a que el administrador autorice la venta.");
        
    }

    private void eliminarCuenta()
    {
        this.laGaleria.getUsuarios().remove(this.laGaleria.ConsultarCliente(login));
    }
}
