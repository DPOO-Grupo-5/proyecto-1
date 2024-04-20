package galeria.persistencia;

import galeria.modelo.Galeria;
import galeria.modelo.pieza.Pieza;

public interface IPersistenciaPiezas
{
	void cargarPiezas(String archivo, Galeria galeria);
	
	void salvarPiezas(String archivo, Galeria galeria);
}