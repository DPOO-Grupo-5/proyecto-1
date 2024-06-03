package pasarelasdepago;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Sire implements PasarelaDePago {
    @Override
    public boolean realizarPago(String informacionTarjeta, float monto, String numeroCuenta, String numeroTransaccion) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter("Sire.json", true))) {
            printWriter.println("{");
            printWriter.println("\"Transacción Sire\": {");
            printWriter.println("\"Información Tarjeta\": \"" + informacionTarjeta + "\",");
            printWriter.println("\"Monto\": \"" + monto + "\",");
            printWriter.println("\"Número de Cuenta\": \"" + numeroCuenta + "\",");
            printWriter.println("\"Número de Transacción\": \"" + numeroTransaccion + "\",");
            printWriter.println("\"Resultado\": \"Éxito\"");
            printWriter.println("}");
            printWriter.println("}");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}