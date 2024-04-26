package galeria.persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.LinkedList;

import galeria.modelo.Galeria;
import galeria.modelo.pieza.Escultura;
import galeria.modelo.pieza.Estado;
import galeria.modelo.pieza.Fotografia;
import galeria.modelo.pieza.Impresion;
import galeria.modelo.pieza.Otro;
import galeria.modelo.pieza.Pieza;
import galeria.modelo.pieza.Pintura;
import galeria.modelo.pieza.Video;
import galeria.modelo.usuario.Usuario;


public class PersistenciaPiezas implements IPersistenciaPiezas
{	
	@Override
	public void cargarPiezas(String archivo, Galeria laGaleria) throws IOException, ParseException
	{
		
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
        BufferedReader br = new BufferedReader( new FileReader( archivo ) );
        String line = br.readLine( );
        while( line != null )
        {
            String[] partes = line.split( ":" );
            String codigo = partes[ 1 ];
            Estado estado = Estado.valueOf(partes[ 2 ].toUpperCase());
            String titulo = partes[ 3 ];
            String yearCreacion = partes[ 4 ];
            String lugarCreacion = partes[ 5 ];
            
            String autorString = partes[ 6 ];
            LinkedList<String> autor = new LinkedList<>();

            String[] autorSep = autorString.split(",");

            for (String element : autorSep) {
                autor.add(element.trim());
            }
            double alto = Double.parseDouble( partes[ 7 ] );
            double ancho = Double.parseDouble( partes[ 8 ] );
            boolean disponibilidadVentaDirecta = Boolean.parseBoolean(partes[ 9 ]);
            double valorFijoVentaDirecta = Double.parseDouble(partes[ 10 ]);
            boolean esConsignacion = Boolean.parseBoolean(partes[ 11 ]);;
            String fechaInicioConsignacionString = partes[ 12 ];
            String fechaFinConsignacionString = partes[ 13 ];
            String propietarioString = partes[ 14 ];
            
            
            if( partes[ 0 ].equals( "pintura" ) )
            {                
                String tecnicasString = partes[ 15 ];
                LinkedList<String> tecnicas = new LinkedList<>();

                String[] tecnicasSep = tecnicasString.split(",");

                for (String element : tecnicasSep) {
                    tecnicas.add(element.trim());
                }
                 
                double peso = Double.parseDouble( partes[ 16 ] );
                
                if (!disponibilidadVentaDirecta) {
                	
                	laGaleria.RegistrarPieza(new Pintura(codigo, titulo, yearCreacion, lugarCreacion, autor,
                			estado, alto, ancho, tecnicas, peso));
                	
                } else if (!esConsignacion) {
                	
                	laGaleria.RegistrarPieza(new Pintura(codigo, titulo, yearCreacion, lugarCreacion, autor,
                			estado, alto, ancho, disponibilidadVentaDirecta, valorFijoVentaDirecta, tecnicas, peso));
                } else {
                	
                	LocalDate fechaInicioConsignacion = LocalDate.parse(fechaInicioConsignacionString, formatoFecha);
                	LocalDate fechaFinConsignacion = LocalDate.parse(fechaFinConsignacionString, formatoFecha);
                    Usuario propietario = laGaleria.ConsultarUsuario(propietarioString);
                	
                	laGaleria.RegistrarPieza(new Pintura(codigo, titulo, yearCreacion, lugarCreacion, autor,
                			estado, alto, ancho, disponibilidadVentaDirecta, valorFijoVentaDirecta, 
                			esConsignacion, fechaInicioConsignacion, fechaFinConsignacion, propietario, tecnicas, peso));
                }
                
            }
            else if( partes[ 0 ].equals( "escultura" ) )
            {
                double profundidad = Double.parseDouble( partes[ 15 ] );
                
                String materialesString = partes[ 16 ];
                LinkedList<String> materiales = new LinkedList<>();

                String[] materialesSep = materialesString.split(",");

                for (String element : materialesSep) {
                    materiales.add(element.trim());
                }
                
                double peso = Double.parseDouble( partes[ 17 ] );
                boolean electricidad = Boolean.parseBoolean(partes[ 18 ]);
                
                String detallesString = partes[ 19 ];
                LinkedList<String> detalles = new LinkedList<>();

                String[] detallesSep = detallesString.split(",");

                for (String element : detallesSep) {
                    detalles.add(element.trim());
                }

                if (!disponibilidadVentaDirecta) {
                	
                	laGaleria.RegistrarPieza(new Escultura(codigo, titulo, yearCreacion, lugarCreacion, autor,
                			estado, alto, ancho, profundidad, materiales, peso, electricidad, detalles));
                	
                } else if (!esConsignacion) {
                	                	
                	laGaleria.RegistrarPieza(new Escultura(codigo, titulo, yearCreacion, lugarCreacion, autor,
                			estado, alto, ancho, disponibilidadVentaDirecta, valorFijoVentaDirecta, profundidad, 
                			materiales, peso, electricidad, detalles));
                } else {
                	
                	LocalDate fechaInicioConsignacion = LocalDate.parse(fechaInicioConsignacionString, formatoFecha);
                	LocalDate fechaFinConsignacion = LocalDate.parse(fechaFinConsignacionString, formatoFecha);
                    Usuario propietario = laGaleria.ConsultarUsuario(propietarioString);
                	
                	laGaleria.RegistrarPieza(new Escultura(codigo, titulo, yearCreacion, lugarCreacion, autor,
                			estado, alto, ancho, disponibilidadVentaDirecta, valorFijoVentaDirecta, 
                			esConsignacion, fechaInicioConsignacion, fechaFinConsignacion, propietario, 
                			profundidad, materiales, peso, electricidad, detalles));
                }
            }
            else if( partes[ 0 ].equals( "video" ) )
            {
                int duracion = Integer.parseInt( partes[ 15 ] );
                String resolucion = partes[ 16 ];
                int size = Integer.parseInt( partes[ 17 ] );
                String formato = partes[ 18 ];
                
                if (!disponibilidadVentaDirecta) {
                	
                	laGaleria.RegistrarPieza(new Video(codigo, titulo, yearCreacion, lugarCreacion, autor,
                			estado, alto, ancho, duracion, resolucion, size, formato));
                	
                } else if (!esConsignacion) {
                	                	
                	laGaleria.RegistrarPieza(new Video(codigo, titulo, yearCreacion, lugarCreacion, autor,
                			estado, alto, ancho, disponibilidadVentaDirecta, valorFijoVentaDirecta, duracion, resolucion, size, formato));
                } else {
                	
                	LocalDate fechaInicioConsignacion = LocalDate.parse(fechaInicioConsignacionString, formatoFecha);
                	LocalDate fechaFinConsignacion = LocalDate.parse(fechaFinConsignacionString, formatoFecha);
                    Usuario propietario = laGaleria.ConsultarUsuario(propietarioString);
                	
                	laGaleria.RegistrarPieza(new Video(codigo, titulo, yearCreacion, lugarCreacion, autor,
                			estado, alto, ancho, disponibilidadVentaDirecta, valorFijoVentaDirecta, 
                			esConsignacion, fechaInicioConsignacion, fechaFinConsignacion, propietario, duracion, resolucion, size, formato));
                }
                
            }
            else if( partes[ 0 ].equals( "fotografia" ) )
            {
                String resolucion = partes[ 15 ];
                int size = Integer.parseInt( partes[ 16 ] );
                String formato = partes[ 17 ];
                
                if (!disponibilidadVentaDirecta) {
                	
                	laGaleria.RegistrarPieza(new Fotografia(codigo, titulo, yearCreacion, lugarCreacion, autor,
                			estado, alto, ancho, resolucion, size, formato));
                	
                } else if (!esConsignacion) {
                	                	
                	laGaleria.RegistrarPieza(new Fotografia(codigo, titulo, yearCreacion, lugarCreacion, autor,
                			estado, alto, ancho, disponibilidadVentaDirecta, valorFijoVentaDirecta, resolucion, size, formato));
                } else {
                	
                	LocalDate fechaInicioConsignacion = LocalDate.parse(fechaInicioConsignacionString, formatoFecha);
                	LocalDate fechaFinConsignacion = LocalDate.parse(fechaFinConsignacionString, formatoFecha);
                    Usuario propietario = laGaleria.ConsultarUsuario(propietarioString);
                	
                	laGaleria.RegistrarPieza(new Fotografia(codigo, titulo, yearCreacion, lugarCreacion, autor,
                			estado, alto, ancho, disponibilidadVentaDirecta, valorFijoVentaDirecta, 
                			esConsignacion, fechaInicioConsignacion, fechaFinConsignacion, propietario, resolucion, size, formato));
                }
            }
            else if( partes[ 0 ].equals( "impresion" ) )
            {
                String material = partes[ 15 ];
                String tipoImpresion = partes[ 16 ];
                
                if (!disponibilidadVentaDirecta) {
                	
                	laGaleria.RegistrarPieza(new Impresion(codigo, titulo, yearCreacion, lugarCreacion, autor,
                			estado, alto, ancho, material, tipoImpresion));
                	
                } else if (!esConsignacion) {
                	                	
                	laGaleria.RegistrarPieza(new Impresion(codigo, titulo, yearCreacion, lugarCreacion, autor,
                			estado, alto, ancho, disponibilidadVentaDirecta, valorFijoVentaDirecta, material, tipoImpresion));
                } else {
                	
                	LocalDate fechaInicioConsignacion = LocalDate.parse(fechaInicioConsignacionString, formatoFecha);
                	LocalDate fechaFinConsignacion = LocalDate.parse(fechaFinConsignacionString, formatoFecha);
                    Usuario propietario = laGaleria.ConsultarUsuario(propietarioString);
                	
                	laGaleria.RegistrarPieza(new Impresion(codigo, titulo, yearCreacion, lugarCreacion, autor,
                			estado, alto, ancho, disponibilidadVentaDirecta, valorFijoVentaDirecta, 
                			esConsignacion, fechaInicioConsignacion, fechaFinConsignacion, propietario, material, tipoImpresion));
                }
            }
            else
            {   
                if (!disponibilidadVentaDirecta) {
                	
                	laGaleria.RegistrarPieza(new Otro(codigo, titulo, yearCreacion, lugarCreacion, autor,
                			estado, alto, ancho));
                	
                } else if (!esConsignacion) {
                	                	
                	laGaleria.RegistrarPieza(new Otro(codigo, titulo, yearCreacion, lugarCreacion, autor,
                			estado, alto, ancho, disponibilidadVentaDirecta, valorFijoVentaDirecta));
                } else {
                	
                	LocalDate fechaInicioConsignacion = LocalDate.parse(fechaInicioConsignacionString, formatoFecha);
                	LocalDate fechaFinConsignacion = LocalDate.parse(fechaFinConsignacionString, formatoFecha);
                    Usuario propietario = laGaleria.ConsultarUsuario(propietarioString);
                	
                	laGaleria.RegistrarPieza(new Otro(codigo, titulo, yearCreacion, lugarCreacion, autor,
                			estado, alto, ancho, disponibilidadVentaDirecta, valorFijoVentaDirecta, 
                			esConsignacion, fechaInicioConsignacion, fechaFinConsignacion, propietario));
                }
            }

            line = br.readLine( );
		}
        br.close( );
	}
	
	@Override
	public void salvarPiezas(String archivo, Galeria laGaleria) throws IOException
	{
		
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		PrintWriter writer = new PrintWriter( archivo );

        // Guardar la información de los tipos de gasolina
        for( Pieza pieza : laGaleria.getInventario() )
        {
        	String estado = pieza.getEstado().toString();
			String autoresf = "";
			for (String autorf : pieza.getAutor()) {
				autoresf += autorf + ",";
			}
			String autor = autoresf.substring(0, autoresf.length() - 1);
			String alto = Double.toString(pieza.getAlto());
			String ancho = Double.toString(pieza.getAncho());
			String disponibilidadVentaDirecta = Boolean.toString(pieza.isDisponibilidadVentaDirecta()); 
            String valorFijoVentaDirecta = Double.toString(pieza.getValorFijoVentaDirecta());
            String esConsignacion = Boolean.toString(pieza.isEsConsignacion()); 
            LocalDate fechaInicioConsignacionDate = pieza.getFechaInicioConsignacion();
            String fechaInicioConsignacion;
            if (fechaInicioConsignacionDate != null) {
            	fechaInicioConsignacion = fechaInicioConsignacionDate.format(formatoFecha);
            } else {
            	fechaInicioConsignacion = "null";
            }
            LocalDate fechaFinConsignacionDate = pieza.getFechaFinConsignacion();
            String fechaFinConsignacion;
            if (fechaFinConsignacionDate != null) {
            	fechaFinConsignacion = fechaFinConsignacionDate.format(formatoFecha);
            } else {
            	fechaFinConsignacion = "null";
            }
            String propietario = pieza.getPropietario().getLogin();
			
        	if (pieza instanceof Pintura) {
				Pintura pintura = (Pintura) pieza;
				
				String tecnicasf = "";
				for (String tecnicaf : pintura.getTecnicas()) {
					tecnicasf += tecnicaf + ",";
				}
				String tecnicas = tecnicasf.substring(0, tecnicasf.length() - 1);
				String peso = Double.toString(pintura.getPeso());
				
        		writer.println( "pintura:" + pintura.getCodigo() + ":" + estado + ":" + pintura.getTitulo() 
        			+ ":" + pintura.getYearCreacion() + ":" + pintura.getLugarCreacion() + ":" + autor + ":" + alto + ":" 
        			+ ancho + ":" + disponibilidadVentaDirecta + ":" + valorFijoVentaDirecta + ":" + esConsignacion + ":" 
        			+ fechaInicioConsignacion + ":" + fechaFinConsignacion + ":" + propietario + ":" + tecnicas + ":" + peso);
        		
        	} else if (pieza instanceof Escultura){
        		Escultura escultura = (Escultura) pieza;
        		
        		String profundidad = Double.toString(escultura.getProfundidad());
        		String materialesf = "";
				for (String materialf : escultura.getMateriales()) {
					materialesf += materialf + ",";
				}
				String materiales = materialesf.substring(0, materialesf.length() - 1);
        		String peso = Double.toString(escultura.getPeso());
        		String electricidad = Boolean.toString(escultura.isElectricidad());
        		String detalles = "";
				for (String detalle : escultura.getDetallesInstalacion()) {
					detalles += detalle + ",";
				}
				String detallesInstalacion = detalles.substring(0, detalles.length() - 1);
        		
        		writer.println( "escultura:" + escultura.getCodigo() + ":" + estado + ":" + escultura.getTitulo() 
        			+ ":" + escultura.getYearCreacion() + ":" + escultura.getLugarCreacion() + ":" + autor + ":" + alto + ":" 
        			+ ancho + ":" + disponibilidadVentaDirecta + ":" + valorFijoVentaDirecta + ":" + esConsignacion + ":" 
        			+ fechaInicioConsignacion + ":" + fechaFinConsignacion + ":" + propietario + ":" + profundidad + ":" 
        			+ materiales + ":" + peso + ":" + electricidad + ":" + detallesInstalacion);   
        		
        	} else if (pieza instanceof Video){
        		Video video = (Video) pieza;
        		
        		String duracion = Integer.toString(video.getDuracion());
        		String size = Integer.toString(video.getTamano());
        		
        		writer.println( "video:" + video.getCodigo() + ":" + estado + ":" + video.getTitulo() 
        			+ ":" + video.getYearCreacion() + ":" + video.getLugarCreacion() + ":" + autor + ":" + alto + ":" 
        			+ ancho + ":" + disponibilidadVentaDirecta + ":" + valorFijoVentaDirecta + ":" + esConsignacion + ":" 
        			+ fechaInicioConsignacion + ":" + fechaFinConsignacion + ":" + propietario + ":" + duracion + ":" 
        			+ video.getResolucion() + ":" + size + ":" + video.getFormato());     
        		
        	} else if (pieza instanceof Fotografia){
        		Fotografia fotografia = (Fotografia) pieza;
        		
        		String size = Integer.toString(fotografia.getTamano());
        		
        		writer.println( "fotografia:" + fotografia.getCodigo() + ":" + estado + ":" + fotografia.getTitulo() 
        			+ ":" + fotografia.getYearCreacion() + ":" + fotografia.getLugarCreacion() + ":" + autor + ":" + alto + ":" 
        			+ ancho + ":" + disponibilidadVentaDirecta + ":" + valorFijoVentaDirecta + ":" + esConsignacion + ":" 
        			+ fechaInicioConsignacion + ":" + fechaFinConsignacion + ":" + propietario + ":" + fotografia.getResolucion() 
        			+ ":" + size + ":" + fotografia.getFormato());        	
        		
        	} else if (pieza instanceof Impresion){
        		Impresion impresion = (Impresion) pieza;
        		
        		writer.println( "impresion:" + impresion.getCodigo() + ":" + estado + ":" + impresion.getTitulo() 
        			+ ":" + impresion.getYearCreacion() + ":" + impresion.getLugarCreacion() + ":" + autor + ":" + alto + ":" 
        			+ ancho + ":" + disponibilidadVentaDirecta + ":" + valorFijoVentaDirecta + ":" + esConsignacion + ":" 
        			+ fechaInicioConsignacion + ":" + fechaFinConsignacion + ":" + propietario + ":" + impresion.getMaterial() 
        			+ ":" + impresion.getTipoImpresion());
        		
        	} else {
        		Otro otro = (Otro) pieza;
        		
        		writer.println( "otro:" + otro.getCodigo() + ":" + estado + ":" + otro.getTitulo() 
        			+ ":" + otro.getYearCreacion() + ":" + otro.getLugarCreacion() + ":" + autor + ":" + alto + ":" 
        			+ ancho + ":" + disponibilidadVentaDirecta + ":" + valorFijoVentaDirecta + ":" + esConsignacion + ":" 
        			+ fechaInicioConsignacion + ":" + fechaFinConsignacion + ":" + propietario);  
        	}
        }

        writer.close( );
    }
}