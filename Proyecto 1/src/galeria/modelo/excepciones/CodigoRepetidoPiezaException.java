public class CodigoRepetidoPiezaException extends Exception {
            public CodigoRepetidoPiezaException(String mensaje) {
                super(mensaje);
            }
        }
        
        public class Pieza {
            private String codigo;
            private String nombre;
        
            public Pieza(String codigo, String nombre) {
                this.codigo = codigo;
                this.nombre = nombre;
            }
        
            public String getCodigo() {
                return codigo;
            }
        
            public String getNombre() {
                return nombre;
            }
        }
        
        public class InventarioPiezas {
            private List<Pieza> piezas = new ArrayList<>();
        
            public void agregarPieza(Pieza nuevaPieza) throws CodigoRepetidoPiezaException {
                for (Pieza pieza : piezas) {
                    if (pieza.getCodigo().equals(nuevaPieza.getCodigo())) {
                        throw new CodigoRepetidoException("El código '" + nuevaPieza.getCodigo() + "' ya está asignado a otra pieza.");
                    }
                }
                piezas.add(nuevaPieza);
                System.out.println("Pieza agregada: Código '" + nuevaPieza.getCodigo() + "', Nombre '" + nuevaPieza.getNombre() + "'.");
            }
        }
        
        public class Main {
            public static void main(String[] args) {
                InventarioPiezas inventario = new InventarioPiezas();
                try {
                    inventario.agregarPieza(new Pieza("0014", "HO111"));
                    inventario.agregarPieza(new Pieza("0021", "ZD13A"));
                    inventario.agregarPieza(new Pieza("0014", "HO111")); 
                } catch (CodigoRepetidoException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }