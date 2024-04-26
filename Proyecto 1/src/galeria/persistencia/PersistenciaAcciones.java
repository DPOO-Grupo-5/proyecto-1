package galeria.persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import galeria.modelo.Galeria;
import galeria.modelo.pieza.Pieza;
import galeria.modelo.usuario.Cliente;
import galeria.modelo.usuario.Usuario;
import galeria.modelo.ventas.SubastaPieza;
import galeria.modelo.ventas.Venta;


public class PersistenciaAcciones implements IPersistenciaAcciones
{
	
	DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
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
	            LocalDate fechaInicial = LocalDate.parse(partes[ 6 ], formatoFecha);
	            LocalDate fechaFinal = LocalDate.parse(partes[ 7 ], formatoFecha);
	            
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