public class PiezaNoExistenteException extends Exception {
            public PiezaNoExistenteException(String mensaje) {
                super(mensaje);
            }
        }
        
        public class InventarioPiezas {
            private Set<String> piezas = new HashSet<>();
        
            public void agregarPieza(String codigo) {
                inventario.put(codigo);
                System.out.println("Pieza agregada al inventario: Código '" + codigo)
            }
        
            public boolean buscarPieza(String codigo) throws PiezaNoExistenteException {
                if (!piezas.contains(codigo)) {
                    throw new PiezaNoExistenteException("No se encontró ninguna pieza con el código '" + codigo + "'.");
                }
                return true;
            }
        }
        
        public class Main {
            public static void main(String[] args) {
                InventarioPiezas inventario = new InventarioPiezas();
                inventario.agregarPieza("001");
                inventario.agregarPieza("002");
        
                try {
                    boolean piezaEncontrada = inventario.buscarPieza("003"); 
                    System.out.println("Pieza encontrada: " + piezaEncontrada);
                } catch (PiezaNoEncontradaException e) {
                    System.out.println("Error: " + e.getMessage());
                }
        
                try {
                    boolean piezaEncontrada = inventario.buscarPieza("001"); 
                    System.out.println("Pieza encontrada: " + piezaEncontrada);
                } catch (PiezaNoEncontradaException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }