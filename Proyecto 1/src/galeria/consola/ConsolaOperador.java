package galeria.consola;

import java.time.LocalDate;

import galeria.modelo.Galeria;
import galeria.modelo.pieza.Estado;
import galeria.modelo.ventas.SubastaPieza;

public class ConsolaOperador extends ConsolaBasica {

    public ConsolaOperador(Galeria galeria)
    {
        super(galeria);
    }

    public void correrAplicacion()
    {
        boolean continuar = true;
        while (continuar)
        {
            int opcion = mostrarMenu("Menú Operador", new String[] {
            	"Consultar piezas",
                "Modificar estado pieza",
                "Subastar una pieza",
                "Habilitar venta directa de una pieza",
                "Cerrar sesión"
            });

            switch (opcion)
            {
                case 1:
                    consultar();
                    break;
                case 2:
                    modificarPieza();
                    break;
                case 3:
                    subastarPieza();
                    break;
                case 4:
                	ponerEnVenta();
                case 5:
                    // Cerrar sesión
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void modificarPieza()
    {
    	String codigo = this.pedirCadenaAlUsuario("Codigo de la pieza que se va a modificar");
    	int opcion = mostrarMenu("Estados", new String[] {
            	"Bodega",
                "Exhibicion"
            });
    	switch (opcion)
        {
            case 1:
                this.laGaleria.ConsultarPieza(codigo).setEstado(Estado.BODEGA);
                System.out.println("La pieza se ha transferido a la bodega.");
                break;
            case 2:
            	this.laGaleria.ConsultarPieza(codigo).setEstado(Estado.EXHIBICION);
            	System.out.println("La pieza se ha puesto en exhibicion.");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private void subastarPieza()
    {
    	String codigo = this.pedirCadenaAlUsuario("Codigo de la pieza que se va a subastar");
    	Double valorInicial = pedirDobleAlUsuario("Valor inicial de la subasta");
    	Double valorMinimo = pedirDobleAlUsuario("Valor minimo por el cual se puede vender la pieza");
    	LocalDate tiempoFinal = pedirFechaAlUsuario("Fecha de finalizacion de la subasta");
    	SubastaPieza pieza = new SubastaPieza(valorInicial, valorMinimo, this.laGaleria.ConsultarPieza(codigo), tiempoFinal);
    	this.laGaleria.getSubastas().add(pieza);
    	System.out.println("La pieza entro a la subasta.");
    }
    private void ponerEnVenta()
    {
    	String codigo = this.pedirCadenaAlUsuario("Codigo de la pieza que se va a vender");
    	this.laGaleria.ConsultarPieza(codigo).setDisponibilidadVentaDirecta(true);
    	this.laGaleria.ConsultarPieza(codigo).setValorFijoVentaDirecta(this.pedirDobleAlUsuario("Valor de la pieza para venta directa"));
    	System.out.println("La pieza esta disponible para la venta.");
    }
}
