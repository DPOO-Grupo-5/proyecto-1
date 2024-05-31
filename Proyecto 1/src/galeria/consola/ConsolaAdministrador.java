package galeria.consola;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import galeria.modelo.Galeria;
import galeria.modelo.pieza.Escultura;
import galeria.modelo.pieza.Estado;
import galeria.modelo.pieza.Fotografia;
import galeria.modelo.pieza.Impresion;
import galeria.modelo.pieza.Otro;
import galeria.modelo.pieza.Pieza;
import galeria.modelo.pieza.Pintura;
import galeria.modelo.pieza.Tipo;
import galeria.modelo.pieza.Video;
import galeria.modelo.usuario.Cliente;
import galeria.modelo.usuario.Empleado;
import galeria.modelo.usuario.Rol;
import galeria.modelo.usuario.Usuario;
import galeria.modelo.ventas.Venta;
import galeria.persistencia.TipoInvalidoException;

public class ConsolaAdministrador extends ConsolaBasica
{

    public ConsolaAdministrador(Galeria galeria)
    {
        super(galeria);
    }
    
	public void correrAplicacion() throws TipoInvalidoException, IOException, ParseException
    {
        boolean continuar = true;
        while (continuar)
        {
            int opcion = mostrarMenu("Menú Administrador", new String[] {
            	"Consultar piezas",
            	"Consultar historial comprador",
                "Registrar ingreso pieza",
                "Registrar operador",
                "Revisar solicitudes de venta",
                "Confirmar devoluciones",
                "Verificar usuario",
                "Cerrar sesión"
            });

            switch (opcion)
            {
                case 1:
                    consultar();
                    break;
                case 2:
                	String login = this.pedirCadenaAlUsuario("Login Usuario");
                    consultarComprador(login);
                    break;
                case 3:
                    registrarPieza();
                    break;
                case 4:
                	registrarOperador();
                	break;
                case 5:
                	solicitudesVenta();
                	break;
                case 6:
                	confirmarDevoluciones();
                	break;
                case 7:
                	verificarUsuarios();
                	break;
                case 8:
                    // Cerrar sesión
                	cerrarSesion();
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
	
    private void registrarPieza()
    {
    	String codigo = this.pedirCadenaAlUsuario("Codigo");
        if(this.laGaleria.VerificarCodigoPieza(codigo)) {
        	if(this.accionesPieza(this.laGaleria.ConsultarPieza(codigo))) {
        		this.laGaleria.ConsultarPieza(codigo).setEstado(Estado.BODEGA);
        		System.out.println("Pieza registrada exitosamente.");
        	}
        	else {
        		System.out.println("No se pudo registrar la pieza.");
        	}
        	
        }
        else {
	        boolean continuar = true;
	        while (continuar)
	            {
		    	int opcion = mostrarMenu("Tipo de Pieza", new String[] {
		    			"Pintura",
		    			"Escultura",
		    			"Video", 
		    			"Fotografia",
		    			"Impresion",
		    			"Otro",
		    			"Volver al menu anterior"
		            });
		    	
		    	switch (opcion)
		        {
		            case 1:
		                registrarPintura(codigo);
		                break;
		            case 2:
		            	registrarEscultura(codigo);
		            	break;
		            case 3:
		            	registrarVideo(codigo);
		            	break;
		            case 4:
		            	registrarFotografia(codigo);
		            	break;
		            case 5:
		            	registrarImpresion(codigo);
		            	break;
		            case 6:
		            	registrarOtro(codigo);
		            	break;
		            case 7:
		            	continuar = false;
		            	break;
		            default:
		                System.out.println("Opción no válida.");
		        }
            }
        }
    }
    
    private void informacionPieza(Pieza pieza)
    {
    	pieza.setTitulo(this.pedirCadenaAlUsuario("Titulo"));
    	pieza.setYearCreacion(this.pedirCadenaAlUsuario("Año de Creacion"));
    	pieza.setLugarCreacion(this.pedirCadenaAlUsuario("Lugar de Creacion"));
    	List<String> autores = new ArrayList<String>( );
    	int cantidad = this.pedirEnteroAlUsuario("Cantidad de Autores");
    	for(int i=0; i<cantidad; i++) {
    		autores.addLast(this.pedirCadenaAlUsuario("Autor"));
    	}
    	pieza.setAutor(autores);
    	pieza.setEstado(Estado.BODEGA);
    	pieza.setAlto(this.pedirDobleAlUsuario("Alto"));
    	pieza.setAncho(this.pedirDobleAlUsuario("Ancho"));
    	
    	if(this.accionesPieza(pieza)) {
    		this.laGaleria.getInventario().add(pieza);
    		System.out.println("Pieza registrada exitosamente.");
    	}
    	else {
    		System.out.println("No se pudo registrar la pieza.");
    	}
    }
    
    private boolean accionesPieza(Pieza pieza) {
    	pieza.setDisponibilidadVentaDirecta(pedirConfirmacionAlUsuario("La pieza se encuentra disponible para la venta"));
    	if (pieza.isDisponibilidadVentaDirecta()) {
    		pieza.setValorFijoVentaDirecta(this.pedirDobleAlUsuario("Valor de la pieza para venta directa"));
    	}
    	
    	pieza.setEsConsignacion(this.pedirConfirmacionAlUsuario("La pieza fue adquirida en consignacion"));
    	if(pieza.isEsConsignacion()) {
    		pieza.setFechaInicioConsignacion(this.pedirFechaAlUsuario("Fecha de inicio consignacion"));
    		pieza.setFechaFinConsignacion(this.pedirFechaAlUsuario("Fecha final de la consignacion"));
    		String loginPropietario = this.pedirCadenaAlUsuario("Usuario del propietario de la consignacion");
    		if (this.laGaleria.ComprobarUsuario(loginPropietario)) {
    			pieza.setPropietario(this.laGaleria.ConsultarUsuario(loginPropietario));
    		}
    		else {
    			System.out.println("Usuario invalido.");
    			return false;
    		}
    	}
    	return true;
    }
    
    private void registrarPintura(String codigo)
    {
        Pintura pintura = new Pintura();
        pintura.setCodigo(codigo);
        pintura.setTipo(Tipo.PINTURA);
        this.informacionPieza(pintura);
        
        List<String> tecnicas = new ArrayList<String>( );
    	int cantidad = this.pedirEnteroAlUsuario("Cantidad de Tecnicas");
    	for(int i=0; i<cantidad; i++) {
    		tecnicas.addLast(this.pedirCadenaAlUsuario("Tecnica"));
    	}
    	pintura.setTecnicas(tecnicas);
    	pintura.setPeso(this.pedirDobleAlUsuario("Peso"));
    }
    
    private void registrarEscultura(String codigo)
    {
    	Escultura escultura = new Escultura();
    	escultura.setCodigo(codigo);
    	escultura.setTipo(Tipo.ESCULTURA);
        this.informacionPieza(escultura);
        
        escultura.setProfundidad(this.pedirDobleAlUsuario("Profundidad"));
        List<String> materiales = new ArrayList<String>( );
    	int cantidad = this.pedirEnteroAlUsuario("Cantidad de Materiales");
    	for(int i=0; i<cantidad; i++) {
    		materiales.addLast(this.pedirCadenaAlUsuario("Material"));
    	}
    	escultura.setMateriales(materiales);
        escultura.setPeso(this.pedirDobleAlUsuario("Peso"));
        escultura.setElectricidad(this.pedirConfirmacionAlUsuario("Requiere electricidad"));
        List<String> detalles = new ArrayList<String>( );
    	int cantidadD = this.pedirEnteroAlUsuario("Cantidad de detalles de instalacion");
    	for(int i=0; i<cantidadD; i++) {
    		detalles.addLast(this.pedirCadenaAlUsuario("Detalle"));
    	}
    	escultura.setDetallesInstalacion(detalles);
    }
    
    private void registrarVideo(String codigo)
    {
    	Video video = new Video();
    	video.setCodigo(codigo);
    	video.setTipo(Tipo.VIDEO);
        this.informacionPieza(video);
        
        video.setDuracion(this.pedirEnteroAlUsuario("Duracion"));
        video.setResolucion(this.pedirCadenaAlUsuario("Resolucion"));
        video.setTamano(this.pedirEnteroAlUsuario("Tamaño"));
        video.setFormato(this.pedirCadenaAlUsuario("Formato"));
    }
    
    private void registrarFotografia(String codigo)
    {
    	Fotografia fotografia = new Fotografia();
    	fotografia.setCodigo(codigo);
    	fotografia.setTipo(Tipo.FOTOGRAFIA);
        this.informacionPieza(fotografia);
        
        fotografia.setResolucion(this.pedirCadenaAlUsuario("Resolucion"));
        fotografia.setTamano(this.pedirEnteroAlUsuario("Tamaño"));
        fotografia.setFormato(this.pedirCadenaAlUsuario("Formato"));
    }
    
    private void registrarImpresion(String codigo)
    {
    	Impresion impresion = new Impresion();
    	impresion.setCodigo(codigo);
    	impresion.setTipo(Tipo.IMPRESION);
        this.informacionPieza(impresion);
        
        impresion.setMaterial(this.pedirCadenaAlUsuario("Material"));
        impresion.setTipoImpresion(this.pedirCadenaAlUsuario("Tipo de impresion"));
    }
    
    private void registrarOtro(String codigo)
    {
    	Otro otro = new Otro();
    	otro.setCodigo(codigo);
    	otro.setTipo(Tipo.OTRO);
        this.informacionPieza(otro);
    }
    
    private void registrarOperador()
    {
    	Empleado operador = new Empleado(Rol.OPERADOR, this.pedirCadenaAlUsuario("Login"), this.pedirCadenaAlUsuario("Password"), this.pedirCadenaAlUsuario("Nombre"));
    	this.laGaleria.getUsuarios().add(operador);
    }
    private void solicitudesVenta()
    {
    	System.out.println( "\n---------------------" );
        System.out.println("Piezas en proceso de venta: ");
        System.out.println( "---------------------" );
        for(Pieza pieza : this.laGaleria.ConsultarInventario(Estado.BLOQUEADO)){
        	imprimirPieza(pieza);
        }
    	int opcion = mostrarMenu("Confirmar venta de:", new String[] {
    			"Todas las piezas bloqueadas",
    			"Seleccionar pieza",
    			"Volver al menu anterior"
            });
    	
    	switch (opcion)
        {
            case 1:
            	for (Venta venta : this.laGaleria.getVentas()) {
            		if(venta.getPiezaVenta().getEstado().equals(Estado.BLOQUEADO)) {
            			System.out.println("Procesando venta de la pieza: "+ venta.getPiezaVenta().getCodigo());
            			if(venta.efectuarVenta(venta)) {
            				System.out.println("Venta exitosa");
            				this.laGaleria.RegistrarVenta(venta);
            			}
            			else {
            				System.out.println("No se ha podido completar la venta, debido a que el comprador no cuenta con los fondos suficientes.");
            			}	
            		}
            	}
                break;
            case 2:
            	String codigoPieza = this.pedirCadenaAlUsuario("Codigo Pieza");
            	
            	for (Venta venta : this.laGaleria.getVentas()) {
            		if(venta.getPiezaVenta().getEstado().equals(Estado.BLOQUEADO) && venta.getPiezaVenta().equals(this.laGaleria.ConsultarPieza(codigoPieza))) {
            			if(venta.efectuarVenta(venta)) {
            				System.out.println("Venta exitosa");
            				this.laGaleria.RegistrarVenta(venta);
            			}
            			else {
            				System.out.println("No se ha podido completar la venta, debido a que el comprador no cuenta con los fondos suficientes.");
            			}	
            		}
            	}
            	break;
            case 3:
                break;
            default:
                System.out.println("Opción no válida.");
        }
    	
    }
    private void confirmarDevoluciones()
    {
    	System.out.println( "\n---------------------" );
        System.out.println("Piezas en Consignacion: ");
        System.out.println( "---------------------" );
        for(Pieza pieza : this.laGaleria.piezasEnConsignacion()){
        	imprimirPieza(pieza);
        }
       
    	int opcion = mostrarMenu("Confirmar la devolucion de:", new String[] {
    			"Todas las piezas que culminaron su periodo de consignacion",
    			"Seleccionar pieza",
    			"Volver al menu anterior"
            });
    	
    	switch (opcion)
        {
            case 1:
                this.laGaleria.ActualizarEstadosPiezasConsignacion();
                System.out.println("Se devolvieron correctamente las piezas.");
                break;
            case 2:
            	String codigoPieza = this.pedirCadenaAlUsuario("Codigo Pieza");
            	Pieza obra = this.laGaleria.ConsultarPieza(codigoPieza);
            	obra.Devolucion(obra);
				obra.setEsConsignacion(false);
				obra.setFechaFinConsignacion(null);
				obra.setFechaInicioConsignacion(null);
				System.out.println("Se devolvio correctamente la pieza.");
                break;
            case 3:
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }
    private void verificarUsuarios()
    {
    	System.out.println( "\n---------------------" );
        System.out.println("Usuarios pendientes por verificar: ");
        System.out.println( "---------------------" );
        for (Usuario usuario : this.laGaleria.getUsuarios()) {
    		if (usuario instanceof Cliente) {
    			Cliente cliente = (Cliente) usuario;
    			if(cliente.isVerificado() == false) {
    				this.imprimirCliente(cliente);
    			}
    		}
        }
        int opcion = mostrarMenu("Verificar usuarios:", new String[] {
    			"Todos los usuarios pendientes por verificar",
    			"Seleccionar usuario",
    			"Volver al menu anterior"
            });
    	
    	switch (opcion)
        {
            case 1:
            	for (Usuario usuario : this.laGaleria.getUsuarios()) {
            		if (usuario instanceof Cliente) {
            			Cliente cliente = (Cliente) usuario;
            			if(cliente.isVerificado() == false) {
            				this.laGaleria.VerificarCliente(cliente);
            			}
            		}
                }
            	System.out.println("Se verificaron todos los usuarios.");
                break;
            case 2:
            	String loginCliente = this.pedirCadenaAlUsuario("Login Cliente:");
            	Cliente cliente = (Cliente) this.laGaleria.ConsultarUsuario(loginCliente);
            	this.laGaleria.VerificarCliente(cliente);
            	System.out.println("Se realizo la verificacion del usuario.");
                break;
            case 3:
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }
}
