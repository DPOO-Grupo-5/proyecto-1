package galeria.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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


public class PersistenciaPiezas implements IPersistenciaPiezas
{
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	
	@Override
	public void cargarPiezas(String archivo, Galeria laGaleria) throws IOException, ParseException
	{
        Map<String, Pieza> piezas = new HashMap<String, Pieza>( );

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
            if( partes[ 0 ].equals( "pintura" ) )
            {                
                String tecnicasString = partes[ 9 ];
                LinkedList<String> tecnicas = new LinkedList<>();

                String[] tecnicasSep = tecnicasString.split(",");

                for (String element : tecnicasSep) {
                    tecnicas.add(element.trim());
                }
                
                double peso = Double.parseDouble( partes[ 10 ] );
                
                laGaleria.RegistrarPieza(new Pintura(codigo, titulo, yearCreacion, lugarCreacion, autor,
            			estado, alto, ancho, tecnicas, peso));
            }
            else if( partes[ 0 ].equals( "escultura" ) )
            {
                double profundidad = Double.parseDouble( partes[ 9 ] );
                
                String materialesString = partes[ 10 ];
                LinkedList<String> materiales = new LinkedList<>();

                String[] materialesSep = materialesString.split(",");

                for (String element : materialesSep) {
                    materiales.add(element.trim());
                }
                
                double peso = Double.parseDouble( partes[ 11 ] );
                boolean electricidad = Boolean.parseBoolean(partes[ 12 ]);
                
                String detallesString = partes[ 13 ];
                LinkedList<String> detalles = new LinkedList<>();

                String[] detallesSep = detallesString.split(",");

                for (String element : detallesSep) {
                    detalles.add(element.trim());
                }
                
                laGaleria.RegistrarPieza(new Escultura(codigo, titulo, yearCreacion, lugarCreacion, autor,
            			estado, alto, ancho, profundidad, materiales, peso, electricidad, detalles));
            }
            else if( partes[ 0 ].equals( "video" ) )
            {
                int duracion = Integer.parseInt( partes[ 9 ] );
                String resolucion = partes[ 10 ];
                int size = Integer.parseInt( partes[ 11 ] );
                String formato = partes[ 12 ];
                
                laGaleria.RegistrarPieza(new Video(codigo, titulo, yearCreacion, lugarCreacion, autor,
            			estado, alto, ancho, duracion, resolucion, size, formato));
            }
            else if( partes[ 0 ].equals( "fotografia" ) )
            {
                String resolucion = partes[ 9 ];
                int size = Integer.parseInt( partes[ 10 ] );
                String formato = partes[ 11 ];
                
                laGaleria.RegistrarPieza(new Fotografia(codigo, titulo, yearCreacion, lugarCreacion, autor,
            			estado, alto, ancho, resolucion, size, formato));
            }
            else if( partes[ 0 ].equals( "impresion" ) )
            {
                String material = partes[ 9 ];
                String tipoImpresion = partes[ 10 ];
                
                laGaleria.RegistrarPieza(new Impresion(codigo, titulo, yearCreacion, lugarCreacion, autor,
            			estado, alto, ancho, material, tipoImpresion));
            }
            else
            {   
                laGaleria.RegistrarPieza(new Otro(codigo, titulo, yearCreacion, lugarCreacion, autor,
            			estado, alto, ancho));
            }

            line = br.readLine( );
		}
        br.close( );
	}
	
	@Override
	public void salvarPiezas(String archivo, Galeria laGaleria) throws IOException
	{
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
			
        	if (pieza instanceof Pintura) {
				Pintura pintura = (Pintura) pieza;
				
				String tecnicasf = "";
				for (String tecnicaf : pintura.getTecnicas()) {
					tecnicasf += tecnicaf + ",";
				}
				String tecnicas = tecnicasf.substring(0, tecnicasf.length() - 1);
				String peso = Double.toString(pintura.getPeso());
				
        		writer.println( "pintura:" + pintura.getCodigo() + ":" + estado + ":" + pintura.getTitulo() 
        			+ ":" + pintura.getYearCreacion() + ":" + pintura.getLugarCreacion() + ":" + autor + ":" 
        			+ alto + ":" + ancho + ":" + tecnicas + ":" + peso);
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
        			+ ancho + ":" + profundidad + ":" + materiales + ":" + peso + ":" + electricidad + ":" + detallesInstalacion);   
        		
        	} else if (pieza instanceof Video){
        		Video video = (Video) pieza;
        		
        		String duracion = Integer.toString(video.getDuracion());
        		String size = Integer.toString(video.getTamano());
        		
        		writer.println( "video:" + video.getCodigo() + ":" + estado + ":" + video.getTitulo() 
        			+ ":" + video.getYearCreacion() + ":" + video.getLugarCreacion() + ":" + autor + ":" + alto + ":" 
        			+ ancho + ":" + duracion + ":" + video.getResolucion() + ":" + size + ":" + video.getFormato());        	
        	} else if (pieza instanceof Fotografia){
        		Fotografia fotografia = (Fotografia) pieza;
        		
        		String size = Integer.toString(fotografia.getTamano());
        		
        		writer.println( "fotografia:" + fotografia.getCodigo() + ":" + estado + ":" + fotografia.getTitulo() 
        			+ ":" + fotografia.getYearCreacion() + ":" + fotografia.getLugarCreacion() + ":" + autor + ":" + alto + ":" 
        			+ ancho + ":" + fotografia.getResolucion() + ":" + size + ":" + fotografia.getFormato());        	
        	} else if (pieza instanceof Impresion){
        		Impresion impresion = (Impresion) pieza;
        		
        		writer.println( "impresion:" + impresion.getCodigo() + ":" + estado + ":" + impresion.getTitulo() 
        			+ ":" + impresion.getYearCreacion() + ":" + impresion.getLugarCreacion() + ":" + autor + ":" + alto + ":" 
        			+ ancho + ":" + impresion.getMaterial() + ":" + impresion.getTipoImpresion());        	
        	} else {
        		Otro otro = (Otro) pieza;
        		
        		writer.println( "otro:" + otro.getCodigo() + ":" + estado + ":" + otro.getTitulo() 
        			+ ":" + otro.getYearCreacion() + ":" + otro.getLugarCreacion() + ":" + autor + ":" + alto + ":" 
        			+ ancho);  
        	}
        }

        writer.close( );
    }
}