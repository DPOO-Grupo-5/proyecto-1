package galeria.persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import galeria.modelo.Galeria;
import galeria.modelo.pieza.Pieza;
import galeria.modelo.usuario.Cliente;
import galeria.modelo.usuario.Empleado;
import galeria.modelo.usuario.Usuario;
import galeria.modelo.ventas.Oferta;
import galeria.modelo.ventas.SubastaPieza;
import galeria.modelo.ventas.Venta;


public class PersistenciaAcciones implements IPersistenciaAcciones
{
	
	DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public void cargarAcciones(String archivo, Galeria laGaleria)
	{
		Map<Integer, SubastaPieza> subastas = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader( archivo ))) {
	        String line = br.readLine( );
	        while( line != null )
	        {
	            String[] partes = line.split( ":" );
	            if (partes[0].equals("subasta"))
				{
	            	int contador = Integer.parseInt(partes[ 1 ]);
	            	double valorInicial = Double.parseDouble(partes[ 2 ]);
		            double valorMinimo = Double.parseDouble(partes[ 3 ]);
		            Pieza piezaSubastada = laGaleria.ConsultarPieza(partes[ 4 ]);
		            LocalDate tiempoFinal = LocalDate.parse(partes[ 5 ], formatoFecha);
		            
		            SubastaPieza subasta = new SubastaPieza(valorInicial, valorMinimo, piezaSubastada, tiempoFinal);
		            laGaleria.RegistrarSubasta(subasta);
		            subastas.put(contador, subasta);
				}
		        else if (partes[0].equals("venta"))
		        {
		        	double valor = Double.parseDouble(partes[ 1 ]);
		        	String compradorString = partes[ 2 ];
		            Usuario usuario = laGaleria.ConsultarUsuario(compradorString);
		            Cliente comprador = (Cliente) usuario;
		            String piezaString = partes[ 3 ];
		            Pieza piezaVenta = laGaleria.ConsultarPieza(piezaString);
		            String medioElegido = partes[ 4 ];
	
		            laGaleria.RegistrarVenta(new Venta(valor, comprador, piezaVenta, medioElegido));
		        }
		        else if (partes[0].equals("oferta"))
		        {
		        	int contador = Integer.parseInt(partes[ 1 ]);
		        	String compradorString = partes[ 2 ];
		            Usuario usuario = laGaleria.ConsultarUsuario(compradorString);
		            Cliente ofertador = (Cliente) usuario;
		            double valor = Double.parseDouble(partes[ 3 ]);
		            
		            SubastaPieza subasta = subastas.get(contador);
		            subasta.RecibirOferta(new Oferta(ofertador, valor));
		        }	            
	            line = br.readLine( );
	        }
	        br.close( );
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void salvarAcciones(String archivo, Galeria laGaleria) throws IOException {
		PrintWriter writer = new PrintWriter( archivo );
        int contador = 1;
        
		for( SubastaPieza subasta : laGaleria.getSubastas() )
	    {
			String contadorString = Integer.toString(contador);
			String valorInicial = Double.toString(subasta.getValorInicial());
			String valorMinimo = Double.toString(subasta.getValorMinimo());
			String piezaSubastada = subasta.getPiezaSubastada().getCodigo();
			String tiempoFinal = subasta.getTiempoFinal().format(formatoFecha);
			 
			writer.println( "subasta:" + ":" + contadorString + ":" + valorInicial + ":" + valorMinimo + ":" + piezaSubastada + ":" + tiempoFinal);
			
			for ( Oferta oferta : subasta.getOfertas() ) 
			{
				String ofertador = oferta.getOfertador().getLogin();
				String valor = Double.toString(oferta.getValor());
				 
				writer.println( "oferta:" + ":" + contadorString + ":" + ofertador + ":" + valor);
			}
	    	
			contador++;
	    }
		for( Venta venta : laGaleria.getVentas() )
	    {
				
			String valor = Double.toString(venta.getValor());
			String comprador = venta.getComprador().getLogin();
			String piezaVenta = venta.getPiezaVenta().getCodigo();
			String medioElegido = venta.getMedioElegido();
			 
			writer.println( "venta:" + valor + ":" + comprador + ":" + piezaVenta + ";" + medioElegido);
	    	
	    }

        writer.close( );
	}
}