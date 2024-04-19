package galeria.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import galeria.modelo.usuario.Cliente;
import galeria.modelo.usuario.Usuario;


public class PersistenciaAcciones implements IPersistenciaAcciones
{
	public static Usuario cargarUsuario(File archivo)
	{
        List<Surtidor> acciones = new LinkedList<Surtidor>( );

        BufferedReader br = new BufferedReader( new FileReader( archivo ) );
        String line = br.readLine( );
        while( line != null )
        {
            String[] partes = line.split( ":" );
            if( partes[ 0 ].equals( "cliente" ) )
            {
                String login = partes[ 1 ];
                String password = partes[ 2 ];
                String nombre = partes[ 3 ];
                String rol = partes[ 4 ];
                usuarios.put( login, new Cliente( rol, login, password, nombre ) );
            }
            else if( partes[ 0 ].equals( "surtidor" ) )
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