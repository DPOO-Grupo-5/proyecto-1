public class CapacitadAdquisitivaInsuficienteException extends Exception {
            public CapacitadAdquisitivaInsuficienteException(String mensaje) {
                super(mensaje);
            }
        }
        
        public class Cliente {
            private double capacidadAdquisitiva;
        
            public Cliente(double capacidadAdquisitiva) {
                this.capacidadAdquisitiva = capacidadAdquisitiva;
            }
        
            public double getCapacidadAdquisitiva() {
                return capacidadAdquisitiva;
            }
        
            public void comprarPieza(boolean DisponibilidadVentaDirecta, double precio) throws CapacitadAdquisitivaInsuficienteException {
                if (DisponibilidadVentaDirecta && precio > capacidadAdquisitiva) {
                    throw new CapacitadAdquisitivaInsuficienteException("No tienes suficiente capacidad adquisitiva para comprar esta pieza.");
                }
                // Lógica para realizar la compra
                System.out.println("¡Pieza adquirida con éxito!");
            }
        }
        
        public class Main {
            public static void main(String[] args) {
                Cliente cliente = new Cliente(500); // Capacidad adquisitiva del cliente
        
                try {
                    cliente.comprarPieza(true, 600); // Intentamos comprar una pieza con precio superior a la capacidad adquisitiva
                } catch (CapacitadAdquisitivaInsuficienteException e) {
                    System.out.println("Error: " + e.getMessage());
                }
        
                try {
                    cliente.comprarPieza(true, 400); // Compra exitosa
                } catch (CapacitadAdquisitivaInsuficienteException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }