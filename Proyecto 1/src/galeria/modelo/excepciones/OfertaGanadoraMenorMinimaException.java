public class OfertaGanadoraMenorMinimaException extends Exception {
            public OfertaGanadoraMenorMinimaException(String mensaje) {
                super(mensaje);
            }
        }
        
        public class PiezaSubasta {
            private String nombre;
            private double valorMinimo;
            private double ofertaActual;
        
            public PiezaSubasta(String nombre, double valorMinimo) {
                this.nombre = nombre;
                this.valorMinimo = valorMinimo;
                this.valorActual = valorActual;
            }
        
            public void realizarOferta(double valorOferta) throws OfertaGanadoraMenorMinimaException {
                if (valorOferta < valorMinimo) {
                    throw new OfertaGanadoraMenorMinimaException("La oferta de " + valorOferta + " es menor que el valor mínimo de la pieza '" + nombre + "', que es " + valorMinimo);
                }
                ofertaActual = valorOferta;
                System.out.println("Nueva oferta realizada por la pieza '" + nombre + "': " + valorOferta);
            }
        
            public double obtenerOfertaActual() {
                return ofertaActual;
            }
        }
        
        public class Main {
            public static void main(String[] args) {
                PiezaSubasta pieza = new PiezaSubasta("Escultura", 1000);
        
                try {
                    pieza.realizarOferta(900); // Intentamos ofertar un valor menor al mínimo
                } catch (OfertaGanadoraMenorMinimaException e) {
                    System.out.println("Error: " + e.getMessage());
                }
        
                try {
                    pieza.realizarOferta(1200); // Oferta válida
                } catch (OfertaGanadoraMenorMinimaException e) {
                    System.out.println("Error: " + e.getMessage());
                }
        
                System.out.println("Oferta actual por la pieza: " + pieza.obtenerOfertaActual());
            }
        }