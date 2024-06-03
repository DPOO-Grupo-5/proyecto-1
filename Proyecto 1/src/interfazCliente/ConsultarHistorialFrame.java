package interfazCliente;

import javax.swing.*;
import java.awt.*;

public class ConsultarHistorialFrame extends JFrame {

    public ConsultarHistorialFrame() {
        setTitle("Consultar Historial de Pieza");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 1));

        JLabel historialLabel = new JLabel("Historial de la Pieza:");
        historialLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16)); // Cambio de fuente

        JTextArea historialTextArea = new JTextArea();
        historialTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historialTextArea);

        panel.add(historialLabel);
        panel.add(scrollPane);

        add(panel);

        // Lógica para cargar y mostrar el historial de la pieza

        setVisible(true);
    }
}
