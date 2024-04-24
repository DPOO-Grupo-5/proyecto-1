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
            	String login = partes[ 1 ];
	            String password = partes[ 2 ];
	            String nombre = partes[ 3 ];
	            String telefono = partes[ 4 ];
	            String email = partes[ 5 ];
	            double capacidadAdquisitiva = Double.parseDouble(partes[ 6 ]);
	            double valorMaximo = Double.parseDouble(partes[ 7 ]);
	            boolean verificado = Boolean.parseBoolean(partes[ 8 ]);
	            String piezasString = partes[ 9 ];
	            LinkedList<String> piezas = new LinkedList<>();
	
	            String[] piezasSep = piezasString.split(",");
	
	            for (String element : piezasSep) {
	                piezas.add(element.trim());
	            }
	            laGaleria.RegistrarCliente(new Cliente(login, password, nombre, telefono, email, capacidadAdquisitiva, valorMaximo, verificado, piezas));
			}
	        else if (partes[0].equals("empleado"))
	        {
	        	Rol rol = Rol.valueOf(partes[ 1 ].toUpperCase());
	        	String login = partes[ 2 ];
	            String password = partes[ 3 ];
	            String nombre = partes[ 4 ];
	            
	            laGaleria.RegistrarEmpleado(new Empleado(rol, login, password, nombre));
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
				
				String capacidadAdquisitiva = Double.toString(cliente.getCapacidadAdquisitiva());
				String valorMaximo = Double.toString(cliente.getValorMaximo());
				String verificado = Boolean.toString(cliente.isVerificado());
				String piezasf = "";
				for (String codigo : cliente.getPiezas()) {
					piezasf += codigo + ",";
				}
				String piezas = piezasf.substring(0, piezasf.length() - 1);
				 
        		writer.println( "cliente:" + cliente.getLogin( ) + ":" + cliente.getPassword( ) + ":" + cliente.getNombre() + ":" + cliente.getTelefono() + ":" + cliente.getEmail() + ":" + capacidadAdquisitiva + ":" + valorMaximo + ":" + verificado + ":" + piezas);
        	}
        	else if (usuario instanceof Cliente){
        		Empleado empleado = (Empleado) usuario;
        		
        		String rol = empleado.getRol().toString();
        		writer.println( "empleado:" + rol + ":" + empleado.getLogin( ) + ":" + empleado.getPassword( ) + ":" + empleado.getNombre());
        	}
        }

        writer.close( );
    }

}