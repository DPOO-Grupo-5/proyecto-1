package galeria.consola;

import galeria.modelo.Galeria;

public class ConsolaAdministrador extends ConsolaBasica
{
    private Galeria galeria;

    public ConsolaAdministrador(Galeria galeria)
    {
        this.galeria = galeria;
    }

    public void correrAplicacion()
    {
        boolean continuar = true;
        while (continuar)
        {
            int opcion = mostrarMenu("Menú Administrador", new String[] {
                "Registrar nueva pieza",
                "Actualizar pieza",
                "Eliminar pieza",
                "Cerrar sesión"
            });

            switch (opcion)
            {
                case 1:
                    registrarNuevaPieza();
                    break;
                case 2:
                    actualizarPieza();
                    break;
                case 3:
                    eliminarPieza();
                    break;
                case 4:
                    // Cerrar sesión
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void registrarNuevaPieza()
    {
        // Implementa la lógica para registrar una nueva pieza
    }

    private void actualizarPieza()
    {
        // Implementa la lógica para actualizar una pieza
    }

    private void eliminarPieza()
    {
        // Implementa la lógica para eliminar una pieza
    }
}
