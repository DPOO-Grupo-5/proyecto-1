package galeria.persistencia;

import galeria.modelo.Galeria;

public interface IPersistenciaAcciones
{
	void cargarAcciones(String archivo, Galeria galeria);
	
	void salvarAcciones(String archivo, Galeria galeria);
}