package galeria.consola;

import java.io.IOException;
import java.text.ParseException;

import galeria.excepciones.InformacionInconsistenteException;
import galeria.modelo.Galeria;
import galeria.modelo.usuario.Rol;
import galeria.modelo.usuario.Usuario;
import galeria.persistencia.CentralPersistencia;
import galeria.persistencia.TipoInvalidoException;

public class ConsolaPrincipal extends ConsolaBasica
{
    private Galeria galeria;

    /**
     * Es un método que corre la aplicación y realmente no hace nada interesante: sólo muestra cómo se podría utilizar la clase Aerolínea para hacer pruebas.
     * @throws ParseException 
     */
    
    public void correrAplicacion( ) throws ParseException
    {
    	try
        {
            galeria = new Galeria( );
            // String archivo = this.pedirCadenaAlUsuario( "Digite el nombre del archivo json con la información de una aerolinea" );            
            String archivoPiezas = "piezas.txt"; 
            galeria.cargarPiezas( "./datos/" + archivoPiezas, CentralPersistencia.PLAIN );
            
            String archivoUsuarios = "usuarios.txt"; 
            galeria.cargarUsuarios( "./datos/" + archivoUsuarios, CentralPersistencia.PLAIN );
            
            String archivoAcciones = "acciones.txt"; 
            galeria.cargarAcciones( "./datos/" + archivoAcciones, CentralPersistencia.PLAIN );
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
                    
                    boolean inicioSesion = galeria.IniciarSesion(login, password);

                    if (inicioSesion)
                    {
                    	Usuario usuario = galeria.ConsultarUsuario(login);
	                    if (usuario.getRol() == Rol.ADMINISTRADOR)
	                    {
	                        // Menú administrador
	                        ConsolaAdministrador consolaAdmin = new ConsolaAdministrador(galeria, login);
	                        consolaAdmin.correrAplicacion();
	                    }
	                    else if (usuario.getRol() == Rol.CLIENTE)
	                    {
	                        // Menú cliente
	                        ConsolaCliente consolaCliente = new ConsolaCliente(galeria, login);
	                        consolaCliente.correrAplicacion();
	                    }
                    
            		}
	                else
                    {
                        System.out.println("Usuario o contraseña incorrecta");
                    }
                    break;
                case 2:
                    // Registrarse
                    String nuevoUsuario = pedirCadenaAlUsuario("Nuevo usuario");
                    String nuevaContraseña = pedirCadenaAlUsuario("Nueva contraseña");
                    //registrarNuevoUsuario(nuevoUsuario, nuevaContraseña);
                    System.out.println("Registro exitoso.");
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

    public static void main( String[] args ) throws ParseException
    {
        ConsolaPrincipal cp = new ConsolaPrincipal( );
        cp.correrAplicacion( );
    }
}