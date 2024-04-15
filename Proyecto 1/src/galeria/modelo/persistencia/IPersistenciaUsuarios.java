package galeria.persistencia;

import java.io.File;

import galeria.modelo.Galeria;

public interface IPersistenciaUsuarios
{
	void cargarUsuario(File archivo, Galeria galeria);
	
	void salvarUsuario(File archivo, Galeria galeria);
}