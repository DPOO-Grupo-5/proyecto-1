package gui;

import pasarelasdepago.PasarelaDePago;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfiguracionPasarelas {
    public static List<PasarelaDePago> cargarPasarelas() {
        List<PasarelaDePago> pasarelas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("configuracionPasarelas.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Class<?> pasarelaClass = Class.forName(linea);
                PasarelaDePago pasarela = (PasarelaDePago) pasarelaClass.getDeclaredConstructor().newInstance();
                pasarelas.add(pasarela);
            }
        } catch (IOException | ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return pasarelas;
    }
}