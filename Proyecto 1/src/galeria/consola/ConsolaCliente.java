package galeria.consola;

import galeria.modelo.Galeria;

public class ConsolaCliente extends ConsolaBasica
{
    private Galeria galeria;
    private String cliente;

    public ConsolaCliente(Galeria galeria, String cliente)
    {
        this.galeria = galeria;
        this.cliente = cliente;
    }

    public void correrAplicacion()
    {
        boolean continuar = true;
        while (continuar)	
        {
            int opcion = mostrarMenu("Menú Cliente", new String[] {
                "Ver información personal",
                "Consignar pieza",
                "Ver piezas consignadas",
                "Actualizar pieza",
                "Cerrar sesión",
                "Eliminar cuenta"
            });

            switch (opcion)
            {
                case 1:
                    verInformacionPersonal();
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
                    // Cerrar sesión
                    continuar = false;
                    break;
                case 6:
                    eliminarCuenta();
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void verInformacionPersonal()
    {
        boolean continuar = true;
        while (continuar)
        {
            int opcion = mostrarMenu("Información Personal", new String[] {
                "Registrar nuevo teléfono",
                "Registrar nuevo correo",
                "Volver"
            });

            switch (opcion)
            {
                case 1:
                    registrarNuevoTelefono();
                    break;
                case 2:
                    registrarNuevoCorreo();
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void registrarNuevoTelefono()
    {
        // Implementa la lógica para registrar un nuevo teléfono
    }

    private void registrarNuevoCorreo()
    {
        // Implementa la lógica para registrar un nuevo correo
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

    private void eliminarCuenta()
    {
        // Implementa la lógica para eliminar la cuenta
    }
}
