package galeria.consola;

import galeria.modelo.Galeria;
import galeria.modelo.usuario.Cliente;

public class ConsolaCliente extends ConsolaBasica
{
    private Galeria laGaleria;
    private String login;

    public ConsolaCliente(Galeria galeria, String login)
    {
        this.laGaleria = galeria;
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

    public void correrAplicacion()
    {
    	Cliente cliente = (Cliente) laGaleria.ConsultarUsuario(login);
    	boolean continuar = true;
        while (continuar)	
        {
            int opcion = mostrarMenu("Menú Cliente", new String[] {
                "Ver información personal",
                "Consignar pieza",
                "Ver piezas consignadas",
                "Actualizar pieza",
                "Ver catálogo de piezas",
                "Cerrar sesión",
                "Eliminar cuenta"
            });

            switch (opcion)
            {
                case 1:
                    verInformacionPersonal(cliente);
                    break;
                case 2:
                    consignarPieza();
                    break;
                case 3:
                    verPiezasConsignadas();
                    break;
                case 4:
                    actualizarPieza();
                    break;
                case 5:
                	verCatalogoPiezas();
                	break;
                case 6:
                    // Cerrar sesión
                    continuar = false;
                    break;
                case 7:
                    eliminarCuenta(cliente);
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void verInformacionPersonal(Cliente cliente)
    {
        boolean continuar = true;
        while (continuar)
        {
        	String nombre = cliente.getNombre();
        	System.out.println("Nombre: " + nombre);
        	String telefono = cliente.getTelefono();
        	System.out.println("Telefono: " + telefono);
        	String email = cliente.getEmail();
        	System.out.println("Email: " + email);
        	double capacidadAdquisitiva = cliente.getCapacidadAdquisitiva();
        	System.out.println("Capacidad Adquisitiva: " + Double.toString(capacidadAdquisitiva));
        	
            int opcion = mostrarMenu("Actualizar Información Personal", new String[] {
                "Registrar nuevo teléfono",
                "Registrar nuevo correo",
                "Cambiar capacidad adquisitiva",
                "Volver"
            });

            switch (opcion)
            {
                case 1:
                    registrarNuevoTelefono(cliente);
                    System.out.println("Registro de número de teléfono exitoso");
                    break;
                case 2:
                    registrarNuevoCorreo(cliente);
                    System.out.println("Registro de email exitoso");
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void registrarNuevoTelefono(Cliente cliente)
    {
    	String telefono = pedirCadenaAlUsuario("Nuevo número de telefono: ");
    	cliente.setTelefono(telefono);
    	
    }

    private void registrarNuevoCorreo(Cliente cliente)
    {
    	String email = pedirCadenaAlUsuario("Nuevo email: ");
    	cliente.setEmail(email);
    }

    private void consignarPieza()
    {
        // Implementa la lógica para consignar una pieza
    }

    private void verPiezasConsignadas()
    {
        // Implementa la lógica para ver las piezas consignadas
    }

    private void actualizarPieza()
    {
        // Implementa la lógica para actualizar una pieza
    }
    
    private void verCatalogoPiezas()
    {
    	
    }

    private void eliminarCuenta(Cliente cliente)
    {
        laGaleria.EliminarCliente(cliente);
    }
}
