public class UsuarioNoExistente extends Exception {
            public UsuarioNoExistente(String mensaje) {
                super(mensaje);
            }
        }
        
        public class Cliente {
            private String login;
            private String password;
        
            public Cliente(String login, String password) {
                this.login = login;
                this.password = password;
            }
        
            public String getlogin() {
                return login;
            }
        
            public String getpassword() {
                return password;
            }
        }
        
        public class SistemaClientes {
            private List<Cliente> listaClientes = new ArrayList<>();
        
            public void registrarCliente(String login, String password) throws CredencialesExistenteException {
                for (Cliente cliente : listaClientes) {
                    if (cliente.getNombreUsuario().equals(login) && cliente.getContrasena().equals(password)) {
                        throw new CredencialesExistenteException("Ya existe un cliente con estas credenciales.");
                    }
                }
                listaClientes.add(new Cliente(login, password));
                System.out.println("Cliente registrado exitosamente: " + login);
            }
        }
        
        public class Main {
            public static void main(String[] args) {
                SistemaClientes sistema = new SistemaClientes();
        
                try {
                    sistema.registrarCliente("nicolas", "password123");
                    sistema.registrarCliente("santiago", "password456");
                    sistema.registrarCliente("ismael", "password123"); 
                } catch (CredencialesExistenteException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }