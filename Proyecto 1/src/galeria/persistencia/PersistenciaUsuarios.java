package galeria.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import galeria.modelo.Galeria;
import galeria.modelo.pieza.Pieza;
import galeria.modelo.usuario.Cliente;
import galeria.modelo.usuario.Empleado;
import galeria.modelo.usuario.Rol;
import galeria.modelo.usuario.Usuario;


public class PersistenciaUsuarios implements IPersistenciaUsuarios
{
	@Override
	public void cargarUsuarios(String archivo, Galeria laGaleria) throws IOException
	{
		Map<String, Cliente> usuarios = new HashMap<String, Cliente>( );
        
        BufferedReader br = new BufferedReader( new FileReader( archivo ) );
        String line = br.readLine( );
        while( line != null )
        {
            String[] partes = line.split( ":" );
            if (partes[0].equals("cliente"))
			{
            	String rol = partes[ 1 ];
            	String login = partes[ 2 ];
	            String password = partes[ 3 ];
	            String nombre = partes[ 4 ];
	            String telefono = partes[ 5 ];
	            String email = partes[ 6 ];
	            boolean verificado = Boolean.parseBoolean(partes[ 7 ]);
	            double capacidadAdquisitiva = Double.parseDouble(partes[ 8 ]);
	            double valorMaximo = Double.parseDouble(partes[ 9 ]);
	            
	            String piezasString = partes[ 10 ];
	            LinkedList<String> piezas = new LinkedList<>();
	
	            String[] piezasSep = piezasString.split(",");
	
	            for (String element : piezasSep) {
	                piezas.add(element.trim());
	            }
	            laGaleria.RegistrarCliente(new Cliente(login, password, nombre, telefono, email, capacidadAdquisitiva, piezas));
	            //usuarios.put( login, new Cliente(login, password, nombre, telefono, email, capacidadAdquisitiva, piezas));
			}
	        else if (partes[0].equals("empleado"))
	        {
	        	Rol rol = Rol.valueOf(partes[ 1 ].toUpperCase());
	        	String login = partes[ 2 ];
	            String password = partes[ 3 ];
	            String nombre = partes[ 4 ];
	            
	            laGaleria.RegistrarEmpleado(new Empleado(rol, login, password, nombre));
	            //laGaleria.RegistrarEmpleado(new Empleado(rol, login, password, nombre));
	        }
	            
            line = br.readLine( );
        }
        br.close( );
	}
	
	@Override
	public void salvarUsuarios(String archivo, Galeria laGaleria) throws IOException
	{
		PrintWriter writer = new PrintWriter( archivo );

        // Guardar la información de los tipos de gasolina
        for( Usuario usuario : laGaleria.getUsuarios() )
        {
        	if (usuario instanceof Cliente) {
				Cliente cliente = (Cliente) usuario;
        		writer.println( "cliente:" + cliente.getLogin( ) + ":" + cliente.getPassword( ) + ":" + cliente.getNombre() + ":" + cliente.getTelefono( ) );
        	}
        	else
        	{
        		writer.println( "empleado:" + usuario.getLogin( ) + ":" + usuario.getPassword( ) + ":" + usuario.getNombre());
        	}
        }

        writer.close( );
    }

}