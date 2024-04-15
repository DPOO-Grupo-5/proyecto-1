package galeria.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import galeria.modelo.Galeria;
import galeria.modelo.pieza.Pieza;
import galeria.modelo.usuario.Cliente;
import galeria.modelo.usuario.Rol;
import galeria.modelo.usuario.Usuario;


public class PersistenciaUsuarios implements IPersistenciaUsuarios
{
	@Override
	public void cargarUsuario(File archivo, Galeria galeria)
	{
		Map<String, Cliente> usuarios = new HashMap<String, Cliente>( );
        
        BufferedReader br = new BufferedReader( new FileReader( archivo ) );
        String line = br.readLine( );
        while( line != null )
        {
            String[] partes = line.split( ":" );

            String login = partes[ 1 ];
            String password = partes[ 2 ];
            String nombre = partes[ 3 ];
            String rol = partes[ 4 ];
            String telefono = partes[ 5 ];
            String email = partes[ 6 ];
            boolean verificado = Boolean.parseBoolean(partes[ 7 ]);
            double capacidadAdquisitiva = Double.parseDouble(partes[ 8 ]);
            double valorMaximo = Double.parseDouble(partes[ 9 ]);
            
            String piezasString = partes[ 10 ];
            LinkedList<String> piezas = new LinkedList<>();

            String[] piezasSep = piezasString.split(",");

            for (String element : piezasSep) {
                piezas.add(Pieza.ConsultarPieza(element.trim()));
            }
            
            usuarios.put( login, new Cliente(rol, login, password, nombre, valorMaximo, verificado, telefono, email, capacidadAdquisitiva, piezas) );

            line = br.readLine( );
        }
        br.close( );

	}
	
	@Override
	public void salvarUsuario(File archivo, Galeria galeria)
	{
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Usuario usuario : galeria.getUsuarios()) {
                bw.write(usuario.getId() + ":" + usuario.getNombre() + "\n");
            }
        }
    }
}