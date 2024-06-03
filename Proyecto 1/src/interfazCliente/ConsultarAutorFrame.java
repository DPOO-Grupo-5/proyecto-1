package interfazCliente;

import javax.swing.*;
import java.awt.*;

public class ConsultarAutorFrame extends JFrame {

    public ConsultarAutorFrame() {
        setTitle("Consultar Artista");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 1));

        JLabel autorLabel = new JLabel("Nombre del Artista:");
        autorLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16)); // Cambio de fuente

        JTextField autorField = new JTextField();
        autorField.setEditable(false);

        panel.add(autorLabel);
        panel.add(autorField);

        add(panel);

        // Lógica para cargar y mostrar el nombre del artista

        setVisible(true);
    }
}

