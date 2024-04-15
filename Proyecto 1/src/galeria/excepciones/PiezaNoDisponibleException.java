public class PiezaNoDisponibleException extends Exception {
    public PiezaNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}

public class TiendaPiezas {
    private boolean disponible;

    public DisponibilidadVentaDirecta(boolean getDisponibilidadVentaDirecta) {
        this.DisponibilidadVentaDirecta = DisponibilidadVentaDirecta;
    }

    public void getValorFijoVentaDirecta() throws PiezaNoDisponibleException {
        if (!DisponibilidadVentaDirecta) {
            throw new PiezaNoDisponibleException("La pieza no está disponible para la compra en este momento.");
        }
      
        System.out.println("Pieza disponible para compra");