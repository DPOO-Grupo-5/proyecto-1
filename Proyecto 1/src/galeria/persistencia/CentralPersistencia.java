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
/*
	private IPersistenciaUsuarios persistenciaUsuarios;
    private IPersistenciaPiezas persistenciaPiezas;
    private IPersistenciaAcciones persistenciaAcciones;

    public CentralPersistencia(IPersistenciaUsuarios persistenciaUsuarios, IPersistenciaPiezas persistenciaPiezas, IPersistenciaAcciones persistenciaAcciones)
    {
        this.persistenciaUsuarios = persistenciaUsuarios;
        this.persistenciaPiezas = persistenciaPiezas;
        this.persistenciaAcciones = persistenciaAcciones;
    }

    public void cargarGaleria(String directorio) {
        for (File archivo : new File(directorio).listFiles()) {
            String nombreArchivo = archivo.getName();
            String tipoArchivo = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1);

           try {
               switch (tipoArchivo) {
                    case "usuarios":
                        persistenciaUsuarios.cargarUsuarios(archivo, galeria);
                        break;
                    case "piezas":
                        persistenciaPiezas.cargarPiezas(archivo, galeria);
                        break;
                    case "acciones":
                        persistenciaAcciones.cargarAcciones(archivo, galeria);
                        break;
                    default:
                        System.err.println("Tipo de archivo no reconocido: " + tipoArchivo);
                }
            } catch (IOException e) {
                System.err.println("Error al cargar archivo: " + e.getMessage());
            }
        }
    }

    public void salvarGaleria(String directorio) 
    {
        Galeria galeria = new Galeria();

        persistenciaUsuarios.cargarUsuarios(new File(directorio, "usuarios"), galeria);
        persistenciaPiezas.cargarPiezas(new File(directorio, "piezas"), galeria);
        persistenciaAcciones.cargarAcciones(new File(directorio, "acciones");
    }
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
    	return new PersistenciaUsuarios( );
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
        return new PersistenciaPiezas( );

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
        return new PersistenciaAcciones( );

    }
}
