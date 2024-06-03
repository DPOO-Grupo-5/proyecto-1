package interfazCliente;

import javax.swing.*;
import java.awt.*;

public class CalcularValorColeccionFrame extends JFrame {

    public CalcularValorColeccionFrame() {
        setTitle("Calcular Valor de Colección");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 1));

        JLabel valorLabel = new JLabel("Valor total de la colección:");
        valorLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16)); // Cambio de fuente
        JLabel resultadoLabel = new JLabel("");
        panel.add(valorLabel);
        panel.add(resultadoLabel);

        JPanel buttonPanel = new JPanel();
        JButton calcularButton = new JButton("Calcular");
        calcularButton.setFont(new Font("Times New Roman", Font.PLAIN, 16)); // Cambio de fuente
        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setFont(new Font("Times New Roman", Font.PLAIN, 16)); // Cambio de fuente

        buttonPanel.add(calcularButton);
        buttonPanel.add(cancelarButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        cancelarButton.addActionListener(e -> dispose());
        calcularButton.addActionListener(e -> {
            // Lógica para calcular el valor de la colección
        });

        setVisible(true);
    }
}

