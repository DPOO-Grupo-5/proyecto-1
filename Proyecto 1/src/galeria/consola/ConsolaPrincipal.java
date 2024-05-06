package galeria.consola;

import java.io.IOException;
import java.text.ParseException;

import galeria.excepciones.InformacionInconsistenteException;
import galeria.modelo.Galeria;
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
                    String usuario = pedirCadenaAlUsuario("Usuario");
                    String password = pedirCadenaAlUsuario("Contraseña");

                    if (esAdministrador(usuario, password))
                    {
                        // Menú administrador
                        ConsolaAdministrador consolaAdmin = new ConsolaAdministrador(galeria);
                        consolaAdmin.correrAplicacion();
                    }
                    else if (esCliente(usuario, password))
                    {
                        // Menú cliente
                        ConsolaCliente consolaCliente = new ConsolaCliente(galeria, usuario);
                        consolaCliente.correrAplicacion();
                    }
                    else
                    {
                        System.out.println("Credenciales incorrectas.");
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
    
    private boolean esAdministrador(String usuario, String contraseña)
    {
        // Implementa la lógica para verificar si es un administrador
        return "admin".equals(usuario) && "admin".equals(contraseña);
    }

    private boolean esCliente(String usuario, String contraseña)
    {
        // Implementa la lógica para verificar si es un cliente
        return "cliente".equals(usuario) && "cliente".equals(contraseña);
    }

    public static void main( String[] args ) throws ParseException
    {
        ConsolaPrincipal cp = new ConsolaPrincipal( );
        cp.correrAplicacion( );
    }
}