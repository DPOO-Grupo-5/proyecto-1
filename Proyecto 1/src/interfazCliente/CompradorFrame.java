package interfazCliente;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class CompradorFrame extends JFrame {

    public CompradorFrame() {
        setTitle("Menú Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JButton realizarOfertaButton = new JButton("Realizar Oferta");
        realizarOfertaButton.setFont(new Font("Times New Roman", Font.BOLD, 14)); // Cambio de estilo de letra
        JButton calcularValorButton = new JButton("Ver Obras");
        calcularValorButton.setFont(new Font("Times New Roman", Font.BOLD, 14)); // Cambio de estilo de letra
        JButton consultarAutorButton = new JButton("Comprar pieza");
        consultarAutorButton.setFont(new Font("Times New Roman", Font.BOLD, 14)); // Cambio de estilo de letra
        JButton consultarHistorialButton = new JButton("Consultar Historial de Pieza");
        consultarHistorialButton.setFont(new Font("Times New Roman", Font.BOLD, 14)); // Cambio de estilo de letra
        JButton salirButton = new JButton("Cerrar Sesión");
        salirButton.setFont(new Font("Times New Roman", Font.BOLD, 14)); // Cambio de estilo de letra

        panel.add(realizarOfertaButton);
        panel.add(calcularValorButton);
        panel.add(consultarAutorButton);
        panel.add(consultarHistorialButton);
        panel.add(salirButton);

        add(panel);

        realizarOfertaButton.addActionListener(e -> new RealizarOfertaFrame());
        calcularValorButton.addActionListener(e -> new CalcularValorColeccionFrame());
        consultarAutorButton.addActionListener(e -> new ConsultarAutorFrame());
        consultarHistorialButton.addActionListener(e -> new ConsultarHistorialFrame());
        salirButton.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CompradorFrame frame = new CompradorFrame();
            frame.setVisible(true);
        });
    }
}
