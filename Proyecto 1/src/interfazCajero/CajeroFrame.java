package interfazCajero;

import javax.swing.*;
import java.awt.*;

public class CajeroFrame extends JFrame {

    public CajeroFrame() {
        setTitle("Sistema de Cajero");
        setSize(400, 200); // Nuevo tamaño
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel tituloLabel = new JLabel("Bienvenido, Cajero");
        tituloLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton registrarPagoButton = new JButton("Registrar Pago");
        registrarPagoButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        registrarPagoButton.setBackground(new Color(100, 200, 100)); // Color verde claro
        registrarPagoButton.setForeground(Color.WHITE); // Texto blanco

        panel.add(tituloLabel, BorderLayout.NORTH);
        panel.add(registrarPagoButton, BorderLayout.CENTER);

        JButton salirButton = new JButton("Salir");
        salirButton.setFont(new Font("Times New Roman", Font.PLAIN, 16)); // Establecer la fuente para el botón Salir
        panel.add(salirButton, BorderLayout.SOUTH);

        add(panel);

        // Agregar acciones a los botones
        registrarPagoButton.addActionListener(e -> new RegistrarPagoFrame());
        salirButton.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CajeroFrame frame = new CajeroFrame();
            frame.setVisible(true);
        });
    }
}
