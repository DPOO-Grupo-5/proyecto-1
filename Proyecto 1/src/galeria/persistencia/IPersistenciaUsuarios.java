package galeria.persistencia;

import java.io.IOException;

import galeria.modelo.Galeria;

public interface IPersistenciaUsuarios
{
	void cargarUsuarios(String archivo, Galeria laGaleria) throws IOException;
	
	void salvarUsuarios(String archivo, Galeria laGaleria) throws IOException;
}