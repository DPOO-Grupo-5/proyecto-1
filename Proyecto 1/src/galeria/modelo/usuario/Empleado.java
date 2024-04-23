package galeria.modelo.usuario;

public class Empleado extends Usuario
{

	public Empleado(String rol, String login, String password, String nombre) 
	{
			super(Rol.ADMINISTRADOR, login, password, nombre);
	}


}
