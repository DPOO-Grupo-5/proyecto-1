package galeria.persistencia;

import java.io.File;

import galeria.modelo.Galeria;

public interface IPersistenciaUsuarios
{
	void cargarUsuarios(String archivo, Galeria galeria);
	
	void salvarUsuarios(String archivo, Galeria galeria);
}