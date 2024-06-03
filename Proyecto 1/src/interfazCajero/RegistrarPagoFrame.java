package interfazCajero;

import javax.swing.*;
import java.awt.*;

public class RegistrarPagoFrame extends JFrame {

    public RegistrarPagoFrame() {
        setTitle("Registrar Pago");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        Font timesNewRoman = new Font("Times New Roman", Font.PLAIN, 12); // Crear una instancia de la fuente Times New Roman

        panel.add(new JLabel("Valor Facturado:")).setFont(timesNewRoman);
        JTextField facturadoField = new JTextField();
        panel.add(facturadoField);

        panel.add(new JLabel("Valor de la Pieza:")).setFont(timesNewRoman);
        JTextField piezaField = new JTextField();
        panel.add(piezaField);

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setFont(timesNewRoman); // Establecer la fuente para el botón Cancelar
        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.setFont(timesNewRoman); // Establecer la fuente para el botón Aceptar

        panel.add(cancelarButton);
        panel.add(aceptarButton);

        add(panel);

        cancelarButton.addActionListener(e -> dispose());
        aceptarButton.addActionListener(e -> {
            // Lógica para registrar el pago
            dispose();
        });

        setVisible(true);
    }
}
