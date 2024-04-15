package galeria.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import galeria.modelo.pieza.Pieza;
import galeria.modelo.usuario.Cliente;
import galeria.modelo.usuario.Usuario;


public class PersistenciaPiezas
{
	public static Usuario cargarUsuario(File archivo)
	{
        Map<String, Pieza> piezas = new HashMap<String, Pieza>( );

        BufferedReader br = new BufferedReader( new FileReader( archivo ) );
        String line = br.readLine( );
        while( line != null )
        {
            String[] partes = line.split( ":" );
            if( partes[ 0 ].equals( "pintura" ) )
            {
                String codigo = partes[ 1 ];
                String estado = partes[ 2 ];
                String titulo = partes[ 3 ];
                String yearCreacion = partes[ 4 ];
                String lugarCreacion = partes[ 5 ];
                String autor = partes[ 6 ];
                double alto = Double.parseDouble( partes[ 7 ] );
                double ancho = Double.parseDouble( partes[ 8 ] );
                double precio = Double.parseDouble( partes[ 9 ] );
                
                String tecnicasString = partes[ 10 ];
                LinkedList<String> tecnicas = new LinkedList<>();

                String[] tecnicasSep = tecnicasString.split(",");

                for (String element : tecnicasSep) {
                    tecnicas.add(element.trim());
                }
                
                double peso = Double.parseDouble( partes[ 11 ] );
                piezas.put( codigo, new Pintura( rol, login, password, nombre ) );
            }
            else if( partes[ 0 ].equals( "escultura" ) )
            {
                String nombreEmpleado = partes[ 1 ];
                if( !empleados.containsKey( nombreEmpleado ) )
                {
                    empleados.put( nombreEmpleado, new Empleado( nombreEmpleado ) );
                }
                Empleado empleadoAsignado = empleados.get( nombreEmpleado );
                Surtidor nuevoSurtidor = new Surtidor( tipos, empleadoAsignado );
                for( int pos = 2; pos < partes.length; pos += 2 )
                {
                    String tipo = partes[ pos ];
                    double cantidad = Double.parseDouble( partes[ pos + 1 ] );
                    nuevoSurtidor.cambiarGalonesVendidos( tipo, cantidad );
                }
                surtidores.add( nuevoSurtidor );
            }
            else if( partes[ 0 ].equals( "empleado" ) )
            {
                String nombreEmpleado = partes[ 1 ];
                int dinero = Integer.parseInt( partes[ 2 ] );
                if( !empleados.containsKey( nombreEmpleado ) )
                {
                    empleados.put( nombreEmpleado, new Empleado( nombreEmpleado ) );
                }
                Empleado nuevoEmpleado = empleados.get( nombreEmpleado );
                nuevoEmpleado.agregarDinero( dinero );
            }

            line = br.readLine( );
        }
        br.close( );

        Gasolinera nuevaGasolinera = new Gasolinera( surtidores, tipos.values( ), empleados.values( ) );
        return nuevaGasolinera;
	}
}