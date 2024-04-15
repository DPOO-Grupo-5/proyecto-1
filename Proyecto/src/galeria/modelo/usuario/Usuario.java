package galeria.modelo.usuario;
/**
 * Esta clase modela a los usuarios del sistema de la galeria, tanto clientes como empleados.
 */
public abstract class Usuario {
	
	/**
	 * Esta clase modela a los clientes de la galeria, tanto los propietarios como los compradores
	 */
	private Rol rol;
	/**
	 * Esta clase modela a los clientes de la galeria, tanto los propietarios como los compradores
	 */
	private String login;
	/**
	 * Esta clase modela a los clientes de la galeria, tanto los propietarios como los compradores
	 */
	private String password;
	
	/**
	 * Crea un usuario indicando su rol, login y contraseña
	 */
	public Usuario(Rol rol, String login, String password) {
		this.rol = rol;
		this.login = login;
		this.password = password;
	}
	
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
