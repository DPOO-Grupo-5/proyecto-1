package galeria.persistencia;

import java.io.IOException;
import java.text.ParseException;

import galeria.modelo.Galeria;

public interface IPersistenciaAcciones
{
	void cargarAcciones(String archivo, Galeria laGaleria) throws IOException, ParseException;
	
	void salvarAcciones(String archivo, Galeria laGaleria) throws IOException;
}