package galeria.consola;

import java.io.IOException;

import galeria.excepciones.InformacionInconsistenteException;
import galeria.modelo.Galeria;
import galeria.persistencia.CentralPersistencia;
//import uniandes.dpoo.aerolinea.persistencia.TipoInvalidoException;
import galeria.persistencia.TipoInvalidoException;

public class ConsolaPrincipal extends ConsolaBasica
{
    private Galeria galeria;

    /**
     * Es un método que corre la aplicación y realmente no hace nada interesante: sólo muestra cómo se podría utilizar la clase Aerolínea para hacer pruebas.
     */
    public void correrAplicacion( )
    {
        try
        {
            galeria = new Galeria( );
            // String archivo = this.pedirCadenaAlUsuario( "Digite el nombre del archivo json con la información de una aerolinea" );
            String archivoUsuarios = "usuarios.json"; 
            galeria.cargarUsuarios( "./datos/" + archivoUsuarios, CentralPersistencia.PLAIN );
            
            String archivoPiezas = "piezas.json"; 
            galeria.cargarUsuarios( "./datos/" + archivoPiezas, CentralPersistencia.PLAIN );
            
            String archivoAcciones = "acciones.json"; 
            galeria.cargarUsuarios( "./datos/" + archivoAcciones, CentralPersistencia.PLAIN );
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
    }

    public static void main( String[] args )
    {
        ConsolaPrincipal cp = new ConsolaPrincipal( );
        cp.correrAplicacion( );
    }
}