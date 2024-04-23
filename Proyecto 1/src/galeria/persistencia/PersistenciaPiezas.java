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
        	if (pieza instanceof Pintura) {
				Pintura pintura = (Pintura) pieza;
				
				String estado = pintura.getEstado().toString();
				
        		writer.println( "pintura:" + pintura.getCodigo() + ":" + estado + ":" + pintura.getTitulo() 
        			+ ":" + pintura.getYearCreacion() + ":" + pintura.getLugarCreacion() + ":" + pintura.getAutor() + ":" 
        			+ pintura.getAlto() + ":" + pintura.getAncho() + ":" + pintura.getTecnicas() + ":" + pintura.getPeso());
        	}
        	else if (pieza instanceof Escultura){
        		Escultura escultura = (Escultura) pieza;
        		writer.println( "escultura:" + escultura.getCodigo() + ":" + escultura.getEstado() + ":" + escultura.getTitulo() 
        			+ ":" + escultura.getYearCreacion() + ":" + escultura.getLugarCreacion() + ":" + escultura.getAutor() + ":" 
        			+ escultura.getAlto() + ":" + escultura.getAncho() + ":" + escultura.getProfundidad() + ":" + escultura.getMateriales() 
        			+ ":" + escultura.getPeso() + ":" + escultura.getElectricidad() + ":" + escultura.getDetallesInstalacion());        	
        	}
        }

        writer.close( );
    }
}