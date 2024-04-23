package galeria.persistencia;

import java.io.IOException;
import java.text.ParseException;

import galeria.modelo.Galeria;
import galeria.modelo.pieza.Pieza;

public interface IPersistenciaPiezas
{
	void cargarPiezas(String archivo, Galeria laGaleria) throws IOException, ParseException;
	
	void salvarPiezas(String archivo, Galeria laGaleria) throws IOException, ParseException;
}