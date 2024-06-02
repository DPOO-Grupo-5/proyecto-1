package galeria.modelo.ventas.medios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import galeria.modelo.usuario.Cliente;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Sire implements MedioPago {

    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public boolean procesarPago(Cliente comprador, double valor, LocalDate fecha, String numeroCuenta, String numeroTransaccion) {
        String nombre = comprador.getNombre();
        String email = comprador.getEmail();
        String fechaString = fecha.format(formatoFecha);
        String valorString = Double.toString(valor);
        registrarTransaccion("./datos/PayPayu.json", nombre, email, valorString, fechaString, numeroCuenta, numeroTransaccion);
        return true;
    }

    public void registrarTransaccion(String archivo, String nombre, String email, String valor, String fecha, String numeroCuenta, String numeroTransaccion) {
        try {
            JSONObject json = new JSONObject();
            json.put("numeroTransaccion", numeroTransaccion);
            json.put("fecha", fecha);
            json.put("nombre", nombre);
            json.put("email", email);
            json.put("numeroTarjeta", numeroCuenta);
            json.put("valor", valor);

            FileWriter file = new FileWriter(archivo);
            file.write(json.toString(2));
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

