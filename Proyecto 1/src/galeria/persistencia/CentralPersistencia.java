package galeria.persistencia;

import java.io.File;
import java.io.IOException;

import galeria.modelo.Galeria;
import galeria.persistencia.IPersistenciaUsuarios;
import galeria.persistencia.IPersistenciaPiezas;
import galeria.persistencia.IPersistenciaAcciones;
import galeria.persistencia.PersistenciaUsuarios;
import galeria.persistencia.PersistenciaPiezas;
import galeria.persistencia.PersistenciaAcciones;
import galeria.persistencia.TipoInvalidoException;

/**
 * Esta clase cumple el rol de una fábrica de componentes que se encargan de manejar la persistencia de una aerolínea y de sus tiquetes
 */
public class CentralPersistencia
{

    /**
     * Este método retorna una nueva instancia de una clase capaz de cargar y salvar los datos de un usuario.
     * 
     * Las clases concretas que se pueden retornar son PersistenciaAerolineaJson y PersistenciaAerolineaPlaintext
     * 
     * @param tipoArchivo El tipo del archivo que será usado para cargar la información de la aerolínea
     * @return El objeto que debería usarse para cargar y salvar la información
     * @throws TipoInvalidoException Se lanza esta excepción si se utiliza un tipo de archivo que no es válido
     */

	/**
     * La cadena utilizada para identificar a los archivos en formato JSON
     */
    public static final String JSON = "JSON";

    /**
     * La cadena utilizada para identificar a los archivos en texto plano
     */
    public static final String PLAIN = "PlainText";
    
    
    public static IPersistenciaUsuarios getPersistenciaUsuarios( String tipoArchivo ) throws TipoInvalidoException
    {
    	if( PLAIN.equals( tipoArchivo ) )
    		return new PersistenciaUsuarios( );
    	else
            throw new TipoInvalidoException( tipoArchivo );
    }

    /**
     * Este método retorna una nueva instancia de una clase capaz de cargar y salvar los datos de los tiquetes de una aerolínea
     * 
     * La única clase concreta que se puede retornar es PersistenciaTiquetesJson
     * 
     * @param tipoArchivo El tipo del archivo que será usado para cargar la información de los tiquetes
     * @return  El objeto que debería usarse para cargar y salvar la información
     * @throws TipoInvalidoException Se lanza esta excepción si se utiliza un tipo de archivo que no es válido
     */
    public static IPersistenciaPiezas getPersistenciaPiezas( String tipoArchivo ) throws TipoInvalidoException
    {
    	if( PLAIN.equals( tipoArchivo ) )
    		return new PersistenciaPiezas( );
    	else
            throw new TipoInvalidoException( tipoArchivo );

    }
    
    /**
     * Este método retorna una nueva instancia de una clase capaz de cargar y salvar los datos de los tiquetes de una aerolínea
     * 
     * La única clase concreta que se puede retornar es PersistenciaTiquetesJson
     * 
     * @param tipoArchivo El tipo del archivo que será usado para cargar la información de los tiquetes
     * @return  El objeto que debería usarse para cargar y salvar la información
     * @throws TipoInvalidoException Se lanza esta excepción si se utiliza un tipo de archivo que no es válido
     */
    public static IPersistenciaAcciones getPersistenciaAcciones( String tipoArchivo ) throws TipoInvalidoException
    {
    	if( PLAIN.equals( tipoArchivo ) )
    		return new PersistenciaAcciones( );
    	else
            throw new TipoInvalidoException( tipoArchivo );

    }
}
