package galeria.persistencia;

import java.io.File;

import galeria.modelo.Galeria;

public interface IPersistenciaUsuarios
{
	void cargarUsuarios(File archivo, Galeria galeria);
	
	void salvarUsuarios(File archivo, Galeria galeria);
}