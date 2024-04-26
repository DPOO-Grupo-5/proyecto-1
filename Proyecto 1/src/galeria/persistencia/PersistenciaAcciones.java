package galeria.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import galeria.modelo.Galeria;
import galeria.modelo.pieza.Pieza;
import galeria.modelo.usuario.Cliente;
import galeria.modelo.usuario.Usuario;
import galeria.modelo.ventas.SubastaPieza;
import galeria.modelo.ventas.Venta;


public class PersistenciaAcciones implements IPersistenciaAcciones
{
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	
	public void cargarAcciones(String archivo, Galeria laGaleria) throws IOException, ParseException
	{
		BufferedReader br = new BufferedReader( new FileReader( archivo ) );
        String line = br.readLine( );
        while( line != null )
        {
            String[] partes = line.split( ":" );
            if (partes[0].equals("subasta"))
			{
	            double valorInicial = Double.parseDouble(partes[ 1 ]);
	            double valorMinimo = Double.parseDouble(partes[ 2 ]);
	            double valorOfertado = Double.parseDouble(partes[ 3 ]);
	            double valorActual = Double.parseDouble(partes[ 4 ]);
	            String piezaString = partes[ 5 ];
	            Pieza piezaSubastada = laGaleria.ConsultarPieza(piezaString);
	            String fechaInicialString = partes[ 6 ];
	            Date fechaInicial = dateFormat.parse(fechaInicialString);
	            String fechaFinalString = partes[ 7 ];
	            Date fechaFinal = dateFormat.parse(fechaFinalString);
	            
	            laGaleria.RegistrarSubasta(new SubastaPieza(valorInicial, valorMinimo, valorOfertado, valorActual, piezaSubastada, fechaInicial, fechaFinal));
			}
	        else if (partes[0].equals("venta"))
	        {
	        	double valor = Double.parseDouble(partes[ 1 ]);
	        	String compradorString = partes[ 5 ];
	            Usuario usuario = laGaleria.ConsultarUsuario(compradorString);
	            Cliente comprador = (Cliente) usuario;
	            String piezaString = partes[ 5 ];
	            Pieza piezaVenta = laGaleria.ConsultarPieza(piezaString);

	            laGaleria.RegistrarVenta(new Venta(valor, comprador, piezaVenta));
	        }
	            
            line = br.readLine( );
        }
        br.close( );
	}
	
	public void salvarAcciones(String archivo, Galeria laGaleria) throws IOException {
		
	}
}