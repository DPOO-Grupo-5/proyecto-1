package galeria.persistencia;

import java.io.File;

import galeria.modelo.Galeria;

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
                        persistenciaUsuarios.cargarUsuario(archivo);
                        break;
                    case "piezas":
                        persistenciaPiezas.cargarPieza(archivo);
                        break;
                    case "acciones":
                        persistenciaAcciones.cargarAccion(archivo);
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

        persistenciaUsuarios.cargarUsuario(new File(directorio, "usuarios"), galeria);
        persistenciaPiezas.cargarPieza(new File(directorio, "piezas"), galeria);
        persistenciaAcciones.cargarAccion(new File(directorio, "acciones");
    }
}
