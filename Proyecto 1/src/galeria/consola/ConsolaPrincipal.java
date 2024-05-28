package galeria.consola;

import java.io.IOException;
import java.text.ParseException;

import galeria.excepciones.InformacionInconsistenteException;
import galeria.modelo.Galeria;
import galeria.modelo.usuario.Empleado;
import galeria.modelo.usuario.Rol;
import galeria.modelo.usuario.Usuario;
import galeria.persistencia.CentralPersistencia;
import galeria.persistencia.TipoInvalidoException;

public class ConsolaPrincipal extends ConsolaBasica
{
	
    public ConsolaPrincipal() {
		super();
	}

	/**
     * Es un método que corre la aplicación y realmente no hace nada interesante: sólo muestra cómo se podría utilizar la clase Aerolínea para hacer pruebas.
     * @throws ParseException 
	 * @throws IOException 
	 * @throws TipoInvalidoException 
     */
    
    public void correrAplicacion( ) throws ParseException, TipoInvalidoException, IOException
    {
    	try
        {
            laGaleria = new Galeria( );
            // String archivo = this.pedirCadenaAlUsuario( "Digite el nombre del archivo json con la información de una aerolinea" );            
            String archivoPiezas = "piezas.txt"; 
            laGaleria.cargarPiezas( "./datos/" + archivoPiezas, CentralPersistencia.PLAIN );
            
            String archivoUsuarios = "usuarios.txt"; 
            laGaleria.cargarUsuarios( "./datos/" + archivoUsuarios, CentralPersistencia.PLAIN );
            
            String archivoAcciones = "acciones.txt"; 
            laGaleria.cargarAcciones( "./datos/" + archivoAcciones, CentralPersistencia.PLAIN );
            
            Empleado administrador = new Empleado(Rol.ADMINISTRADOR, "admin", "1234", "admin");
            laGaleria.getUsuarios().add(administrador);
        }
        catch( TipoInvalidoException e )
        {
            e.printStackTrace( );
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
        catch( InformacionInconsistenteException e )
        {
            e.printStackTrace();
        }
    	
    	correrMenuInicial();

    }

    public void correrMenuInicial() throws TipoInvalidoException, IOException, ParseException
    {
    	boolean continuar = true;
        while (continuar)
        {
            int opcion = mostrarMenu("Menú Inicial", new String[] {
                "Iniciar sesión",
                "Registrarse",
                "Salir"
            });

            switch (opcion)
            {
                case 1:
                    // Iniciar sesión
                    String login = pedirCadenaAlUsuario("Usuario");
                    String password = pedirCadenaAlUsuario("Contraseña");
                    
                    boolean inicioSesion = laGaleria.IniciarSesion(login, password);

                    if (inicioSesion)
                    {
                    	Usuario usuario = laGaleria.ConsultarUsuario(login);
	                    if (usuario.getRol() == Rol.ADMINISTRADOR)
	                    {
	                        // Menú administrador
	                        ConsolaAdministrador consolaAdmin = new ConsolaAdministrador(laGaleria);
	                        consolaAdmin.correrAplicacion();
	                    }
	                    else if (usuario.getRol() == Rol.CLIENTE)
	                    {
	                        // Menú cliente
	                        ConsolaCliente consolaCliente = new ConsolaCliente(laGaleria, login);
	                        consolaCliente.correrAplicacion();
	                    }
	                    else if (usuario.getRol() == Rol.OPERADOR)
	                    {
	                        // Menú cliente
	                        ConsolaOperador consolaOperador = new ConsolaOperador(laGaleria);
	                        consolaOperador.correrAplicacion();
	                    }
                    
            		}
	                else
                    {
                        System.out.println("Usuario o contraseña incorrecta");
                    }
                    break;
                case 2:
                    // Registrarse
                	String nuevoUsuario = pedirCadenaAlUsuario("Nuevo login");
                    if (laGaleria.ComprobarUsuario(nuevoUsuario)) {
                    	System.out.println("Usuario ya existente");
                    } else {
                    	ConsolaCliente consolaCliente = new ConsolaCliente(laGaleria, nuevoUsuario);
                        consolaCliente.registrarCliente();
                        System.out.println("Registro exitoso");
                    }
                    correrMenuInicial();
                    break;
                case 3:
                    // Salir
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void main( String[] args ) throws ParseException, TipoInvalidoException, IOException
    {
        ConsolaPrincipal cp = new ConsolaPrincipal();
        cp.correrAplicacion( );
    }
}