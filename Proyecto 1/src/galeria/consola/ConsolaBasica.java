package galeria.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Iterator;

import galeria.modelo.Galeria;
import galeria.modelo.pieza.Escultura;
import galeria.modelo.pieza.Estado;
import galeria.modelo.pieza.Fotografia;
import galeria.modelo.pieza.Impresion;
import galeria.modelo.pieza.Pieza;
import galeria.modelo.pieza.Pintura;
import galeria.modelo.pieza.Tipo;
import galeria.modelo.pieza.Video;
import galeria.modelo.usuario.Cliente;
import galeria.modelo.ventas.SubastaPieza;
import galeria.modelo.ventas.Venta;
import galeria.persistencia.CentralPersistencia;
import galeria.persistencia.TipoInvalidoException;

/**
 * Esta es una clase abstracta que implementa métodos útiles para todas las consolas de la aplicación.
 */
public abstract class ConsolaBasica
{
	protected Galeria laGaleria;
	
	public ConsolaBasica(Galeria laGaleria) {
		this.laGaleria = laGaleria;
	}

    public ConsolaBasica() {
	}

	public Galeria getLaGaleria() {
		return laGaleria;
	}

	public void setLaGaleria(Galeria laGaleria) {
		this.laGaleria = laGaleria;
	}

	/**
     * Le pide al usuario que ingrese una cadena de caracteres
     * @param mensaje El mensaje con el que se solicita la información
     * @return La cadena introducida por el usuario
     */
    protected String pedirCadenaAlUsuario( String mensaje )
    {
        try
        {
            System.out.print( mensaje + ": " );
            BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
            String input = reader.readLine( );
            return input;
        }
        catch( IOException e )
        {
            System.out.println( "Error leyendo de la consola" );
        }
        return "error";
    }

    /**
     * Le pide al usuario que ingrese una cadena de caracteres
     * @param mensaje El mensaje con el que se solicita la información
     * @return La cadena introducida por el usuario
     */
    protected LocalDate pedirFechaAlUsuario( String mensaje )
    {
        try
        {
            System.out.print( mensaje + ": " + " (En formato: dd/mm/yyyy)");
            BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
            String fechaString = reader.readLine( );
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(fechaString, formatter);
            return fecha;
        }
        catch( IOException e )
        {
            System.out.println( "Error leyendo de la consola" );
        }
		return null;
    }
    
    /**
     * Le pide confirmación al usuario, indicándole que debe responder 'si' o 'no'.
     * @param mensaje El mensaje con el que se solicita la información
     * @return Retorna true únicamente si el usuario responde 'sí', 'si' o 'si', independientemente de las minúsculas y las mayúsculas. Retorna false en cualquier otro caso.
     */
    protected boolean pedirConfirmacionAlUsuario( String mensaje )
    {
        try
        {
            System.out.print( mensaje + " (Responda 'si' o 'no' ) " );
            BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
            String input = reader.readLine( ).toLowerCase( );
            boolean respuesta = false;
            if( input.equals( "si" ) || input.equals( "sí" ) || input.equals( "s" ) )
                respuesta = true;

            return respuesta;
        }
        catch( IOException e )
        {
            System.out.println( "Error leyendo de la consola" );
        }
        return false;
    }

    /**
     * Le pide al usuario que ingrese un número que no puede tener cifras decimales
     * @param mensaje El mensaje con el que se solicita la información
     * @return El valor introducido por el usuario
     */
    protected int pedirEnteroAlUsuario( String mensaje )
    {
        int valorResultado = Integer.MIN_VALUE;
        while( valorResultado == Integer.MIN_VALUE )
        {
            try
            {
                System.out.print( mensaje + ": " );
                BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
                String input = reader.readLine( );
                int numero = Integer.parseInt( input );
                valorResultado = numero;
            }
            catch( NumberFormatException nfe )
            {
                System.out.println( "El valor digitado no es un entero" );
            }
            catch( IOException e )
            {
                System.out.println( "Error leyendo de la consola" );
            }
        }
        return valorResultado;
    }

    /**
     * Le pide al usuario que ingrese un número que no puede tener cifras decimales
     * @param mensaje El mensaje con el que se solicita la información
     * @return El valor introducido por el usuario
     */
    protected double pedirDobleAlUsuario( String mensaje )
    {
        double valorResultado = Double.MIN_VALUE;
        while( valorResultado == Double.MIN_VALUE )
        {
            try
            {
                System.out.print( mensaje + ": " );
                BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
                String input = reader.readLine( );
                double numero = Double.parseDouble( input );
                valorResultado = numero;
            }
            catch( NumberFormatException nfe )
            {
                System.out.println( "El valor digitado no es un entero" );
            }
            catch( IOException e )
            {
                System.out.println( "Error leyendo de la consola" );
            }
        }
        return valorResultado;
    }
    
    /**
     * Le pide al usuario que ingrese un número que puede tener cifras decimales
     * @param mensaje El mensaje con el que se solicita la información
     * @return El valor introducido por el usuario
     */
    protected double pedirNumeroAlUsuario( String mensaje )
    {
        double valorResultado = Integer.MIN_VALUE;
        while( valorResultado == Integer.MIN_VALUE )
        {
            try
            {
                System.out.print( mensaje + ": " );
                BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
                String input = reader.readLine( );
                double numero = Double.parseDouble( input );
                valorResultado = numero;
            }
            catch( NumberFormatException nfe )
            {
                System.out.println( "El valor digitado no es un entero" );
            }
            catch( IOException e )
            {
                System.out.println( "Error leyendo de la consola" );
            }
        }
        return valorResultado;
    }

    /**
     * Le pide al usuario que seleccione una de las opciones que se le presenten
     * @param coleccionOpciones
     * @return Retorna la opción seleccionada (el valor, no su posición).
     */
    protected String pedirOpcionAlUsuario( Collection<? extends Object> coleccionOpciones )
    {
        String[] opciones = new String[coleccionOpciones.size( )];
        int pos = 0;
        for( Iterator<? extends Object> iterator = coleccionOpciones.iterator( ); iterator.hasNext( ); pos++ )
        {
            opciones[ pos ] = iterator.next( ).toString( );
        }

        System.out.println( "Seleccione una de las siguientes opciones:" );
        for( int i = 1; i <= opciones.length; i++ )
        {
            System.out.println( " " + i + ". " + opciones[ i - 1 ] );
        }

        String opcion = pedirCadenaAlUsuario( "\nEscriba el número que corresponde a la opción deseada" );
        try
        {
            int opcionSeleccionada = Integer.parseInt( opcion );
            if( opcionSeleccionada > 0 && opcionSeleccionada <= opciones.length )
                return opciones[ opcionSeleccionada - 1 ];
            else
            {
                System.out.println( "Esa no es una opción válida. Digite solamente números entre 1 y " + opciones.length );
                return pedirOpcionAlUsuario( coleccionOpciones );
            }
        }
        catch( NumberFormatException nfe )
        {
            System.out.println( "Esa no es una opción válida. Digite solamente números." );
            return pedirOpcionAlUsuario( coleccionOpciones );
        }
    }

    /**
     * Muestra un menú y le pide al usuario que seleccione una opción
     * @param nombreMenu El nombre del menu
     * @param opciones Las opciones que se le presentan al usuario
     * @return El número de la opción seleccionada por el usuario, contando desde 1
     */
    protected int mostrarMenu( String nombreMenu, String[] opciones )
    {
        System.out.println( "\n---------------------" );
        System.out.println( nombreMenu );
        System.out.println( "---------------------" );

        for( int i = 1; i <= opciones.length; i++ )
        {
            System.out.println( " " + i + ". " + opciones[ i - 1 ] );
        }
        String opcion = pedirCadenaAlUsuario( "Escoja la opción deseada" );
        try
        {
            int opcionSeleccionada = Integer.parseInt( opcion );
            if( opcionSeleccionada > 0 && opcionSeleccionada <= opciones.length )
                return opcionSeleccionada;
            else
            {
                System.out.println( "Esa no es una opción válida. Digite solamente números entre 1 y " + opciones.length );
                return mostrarMenu( nombreMenu, opciones );
            }
        }
        catch( NumberFormatException nfe )
        {
            System.out.println( "Esa no es una opción válida. Digite solamente números." );
            return mostrarMenu( nombreMenu, opciones );
        }
    }
    
    protected void consultar()
    {
    	boolean continuar = true;
        while (continuar)
        {
	    	int opcion = mostrarMenu("Consultar Piezas", new String[] {
	    			"Piezas en bodega",
	    			"Piezas en exhibicion",
	    			"Piezas disponibles para la venta",
	    			"Piezas en subasta",
	    			"Piezas de un artista",
	    			"Pieza específica",
	    			"Volver al menu anterior"
	            });
	    	
	    	switch (opcion)
	        {
	            case 1:
	                piezasBodega();
	                break;
	            case 2:
	                piezasExhibicion();
	                break;
	            case 3:
	                piezasDisponiblesVenta();
	                break;
	            case 4:
	            	piezasSubasta();
	            	break;
	            case 5:
	            	piezasArtista();
	            	break;
	            case 6:
	            	piezaEspecifica();
	            	break;
	            case 7:
	            	continuar = false;
                    break;
	            default:
	                System.out.println("Opción no válida.");
	        }
        }
    }
    
    protected void piezasBodega()
    {
        for(Pieza pieza : laGaleria.ConsultarInventario(Estado.BODEGA)){
        	imprimirPieza(pieza);
        }
    }

    protected void piezasExhibicion()
    {
    	for( Pieza pieza: laGaleria.ConsultarInventario(Estado.EXHIBICION)) {
    		imprimirPieza(pieza);
    	}
    }
    
    protected void piezasDisponiblesVenta()
    {
    	for( Pieza pieza: laGaleria.ConsultarPiezasEnVenta()) {
    		imprimirPieza(pieza);
    		System.out.println("Valor: "+ pieza.getValorFijoVentaDirecta());
    	}
    }

    protected void piezasSubasta()
    {
    	for( Pieza pieza: laGaleria.ConsultarInventario(Estado.SUBASTA)) {
    		imprimirPieza(pieza);
    		for(SubastaPieza subasta : this.laGaleria.getSubastas()) {
    			if(subasta.getPiezaSubastada().equals(pieza)) {
    				System.out.println("Valor actual de la pieza en subasta: "+ subasta.getValorActual());
    			}
    		}
    		
    	}
    }

    protected void piezasArtista()
    {
    	String artista = pedirCadenaAlUsuario("Artista");
    	for( Pieza pieza: laGaleria.ConsultarPiezasArtista(artista)) {
    		imprimirPieza(pieza);
    		System.out.println( "\n---------------------" );
            System.out.println("Historial de ventas: ");
            System.out.println( "---------------------" );
        	for(Venta venta: laGaleria.ConsultarVentasPieza(pieza)) {
        		System.out.println("Login comprador: "+ venta.getComprador().getLogin());
        		this.imprimirVenta(venta);
        		System.out.println("");
        	}
        }
    }
    
    protected void piezaEspecifica()
    {
    	String codigo = pedirCadenaAlUsuario("Codigo Pieza");
    	this.imprimirPieza(this.laGaleria.ConsultarPieza(codigo));
    	System.out.println( "\n---------------------" );
        System.out.println("Historial de ventas: ");
        System.out.println( "---------------------" );
    	for(Venta venta: laGaleria.ConsultarVentasPieza(laGaleria.ConsultarPieza(codigo))){
    		System.out.println("Login comprador: "+ venta.getComprador().getLogin());
    		this.imprimirVenta(venta);
    	}
    }

    protected void consultarComprador(String login)
    {
        for (Pieza pieza: laGaleria.ConsultarPiezas(login)) {
        	this.imprimirPieza(pieza);
        	for(Venta venta: laGaleria.ConsultarComprasUsuario(pieza, login)) {
        		this.imprimirVenta(venta);
        	}
        }
        System.out.println("Valor Coleccion: "+ laGaleria.CalcularValorColeccion(login));
    }
    
    protected void imprimirPieza(Pieza pieza)
    {
    	System.out.println( "\n---------------------" );
        System.out.println("Pieza: "+ pieza.getCodigo());
        System.out.println( "---------------------" );
        System.out.println("Titulo: "+ pieza.getTitulo());
        System.out.println("Año de Creacion: "+ pieza.getYearCreacion());
        System.out.println("Lugar de Creacion: "+ pieza.getLugarCreacion());
        System.out.println("Autores: ");
        for(int i =1; i<pieza.getAutor().size(); i++) {
        	System.out.println(pieza.getAutor().get(i));
        }
        System.out.println("Alto: "+ pieza.getAlto());
        System.out.println("Ancho: "+ pieza.getAncho());
        System.out.println("Ancho: "+ pieza.getTipo());
        if (pieza.getTipo().equals(Tipo.PINTURA)) {
        	this.imprimirPintura(pieza);
        }
        else if (pieza.getTipo().equals(Tipo.ESCULTURA)) {
        	this.imprimirEscultura(pieza);
        }
        else if (pieza.getTipo().equals(Tipo.VIDEO)){
        	this.imprimirVideo(pieza);
        }
        else if (pieza.getTipo().equals(Tipo.FOTOGRAFIA)) {
        	this.imprimirFotografia(pieza);
        }
        else if (pieza.getTipo().equals(Tipo.IMPRESION)){
        	this.imprimirImpresion(pieza);
        }
    }
    
    protected void imprimirPintura(Pieza pieza)
    {
    	Pintura piezaPintura = (Pintura) pieza;
    	System.out.println("Tecnicas: ");
    	for(int i =1; i<piezaPintura.getTecnicas().size(); i++) {
        	System.out.println(piezaPintura.getTecnicas().get(i));
        }
    	System.out.println("Peso: "+ piezaPintura.getPeso());
    }
    protected void imprimirEscultura(Pieza pieza)
    {
    	Escultura piezaEscultura = (Escultura) pieza;
    	System.out.println("Peso: "+ piezaEscultura.getProfundidad());
    	System.out.println("Materiales: ");
    	for(int i =1; i<piezaEscultura.getMateriales().size(); i++) {
        	System.out.println(piezaEscultura.getMateriales().get(i));
        }
    	System.out.println("Peso: "+ piezaEscultura.getPeso());
    	if(piezaEscultura.isElectricidad()) {
    		System.out.println("La escultura requiere electricidad.");
    	}
    	else {
    		System.out.println("La escultura no requiere electricidad.");
    	}
    	System.out.println("Detalles de instalacion: ");
    	for(int i =1; i<piezaEscultura.getDetallesInstalacion().size(); i++) {
        	System.out.println(piezaEscultura.getDetallesInstalacion().get(i));
        }
    }
    protected void imprimirVideo(Pieza pieza)
    {
    	Video piezaVideo = (Video) pieza;
    	System.out.println("Duracion: "+ piezaVideo.getDuracion());
    	System.out.println("Resolucion: "+ piezaVideo.getResolucion());
    	System.out.println("Tamaño: "+ piezaVideo.getTamano());
    	System.out.println("Formato: "+ piezaVideo.getFormato());
    }
    protected void imprimirFotografia(Pieza pieza)
    {
    	Fotografia piezaFotografia = (Fotografia) pieza;
    	System.out.println("Resolucion: "+ piezaFotografia.getResolucion());
    	System.out.println("Tamaño: "+ piezaFotografia.getTamano());
    	System.out.println("Formato: "+ piezaFotografia.getFormato());
    }
    protected void imprimirImpresion(Pieza pieza)
    {
    	Impresion piezaImpresion = (Impresion) pieza;
    	System.out.println("Material: "+ piezaImpresion.getMaterial());
    	System.out.println("Tipo de impresion: "+ piezaImpresion.getTipoImpresion());
    }
    protected void imprimirVenta(Venta venta)
    {
    	System.out.println("Valor: "+ venta.getValor());
    	System.out.println("Medio de pago: "+ venta.getMedioPago());
    }
    protected void imprimirCliente(Cliente cliente)
    {
    	System.out.println("Login: "+ cliente.getLogin());
    	System.out.println("Nombre: "+ cliente.getNombre());
    	System.out.println("e-mail: "+ cliente.getEmail());
    	System.out.println("Telefono: "+ cliente.getTelefono());
    }
    
    protected void cerrarSesion() throws TipoInvalidoException, IOException, ParseException {
    	String archivoPiezas = "piezas.txt"; 
        laGaleria.salvarPiezas( "./datos/" + archivoPiezas, CentralPersistencia.PLAIN );
        
        String archivoUsuarios = "usuarios.txt"; 
        laGaleria.salvarUsuarios( "./datos/" + archivoUsuarios, CentralPersistencia.PLAIN );
        
        String archivoAcciones = "acciones.txt"; 
        laGaleria.salvarAcciones( "./datos/" + archivoAcciones, CentralPersistencia.PLAIN );
    }
}

