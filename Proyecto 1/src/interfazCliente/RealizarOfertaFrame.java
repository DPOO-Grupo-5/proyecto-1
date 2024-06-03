package interfazCliente;

import javax.swing.*;
import java.awt.*;

public class RealizarOfertaFrame extends JFrame {

    public RealizarOfertaFrame() {
        setTitle("Realizar Oferta");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        panel.add(new JLabel("Nombre de la Obra:"));
        JTextField nombreField = new JTextField();
        panel.add(nombreField);

        panel.add(new JLabel("Valor a Ofertar:"));
        JTextField valorField = new JTextField();
        panel.add(valorField);

        JButton cancelarButton = new JButton("Cancelar");
        JButton aceptarButton = new JButton("Aceptar");

        panel.add(cancelarButton);
        panel.add(aceptarButton);

        add(panel);

        cancelarButton.addActionListener(e -> dispose());
        aceptarButton.addActionListener(e -> {
            // Lógica para realizar la oferta
            dispose();
        });

        // Cambio de fuente a Times New Roman
        Font timesNewRoman = new Font("Times New Roman", Font.PLAIN, 14);
        for (Component component : panel.getComponents()) {
            if (component instanceof JLabel || component instanceof JButton) {
                component.setFont(timesNewRoman);
            }
        }

        setVisible(true);
    }
}
