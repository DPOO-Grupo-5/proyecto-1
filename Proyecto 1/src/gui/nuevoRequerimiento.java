package gui;

import pasarelasdepago.PasarelaDePago;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@SuppressWarnings("serial")
public class nuevoRequerimiento extends JFrame {
    private JComboBox<String> comboPasarelas;
    private JTextField campoTarjeta;
    private JTextField campoMonto;
    private JTextField campoNumeroCuenta;
    private JTextField campoNumeroTransaccion;
    private List<PasarelaDePago> pasarelas;

    public nuevoRequerimiento() {
        pasarelas = ConfiguracionPasarelas.cargarPasarelas();

        setTitle("Sistema de Pagos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] lista = {"PayU", "PayPal", "Sire"};

        comboPasarelas = new JComboBox<>(lista);
        for (PasarelaDePago pasarela : pasarelas) {
            comboPasarelas.addItem(pasarela.getClass().getSimpleName());
        }

        campoTarjeta = new JTextField(20);
        campoMonto = new JTextField(20);
        campoNumeroCuenta = new JTextField(20);
        campoNumeroTransaccion = new JTextField(20);
        JButton botonPagar = new JButton("Pagar");

        botonPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarPago();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Agregamos un espacio alrededor del panel
        panel.setBackground(new Color(250, 200, 100)); // Fondo amarillo claro

        JPanel formularioPanel = new JPanel(new GridLayout(6, 2));
        formularioPanel.setBackground(new Color(250, 200, 100)); // Fondo amarillo claro
        formularioPanel.add(new JLabel("Pasarela de Pago:"));
        formularioPanel.add(comboPasarelas);
        formularioPanel.add(new JLabel("Información Tarjeta:"));
        formularioPanel.add(campoTarjeta);
        formularioPanel.add(new JLabel("Monto:"));
        formularioPanel.add(campoMonto);
        formularioPanel.add(new JLabel("Número de Cuenta:"));
        formularioPanel.add(campoNumeroCuenta);
        formularioPanel.add(new JLabel("Número de Transacción:"));
        formularioPanel.add(campoNumeroTransaccion);

        panel.add(formularioPanel);

        JPanel botonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botonPanel.setBackground(new Color(250, 200, 100)); // Fondo amarillo claro
        botonPanel.add(botonPagar);

        panel.add(botonPanel);

        add(panel);

        // Cambio de estilo de letra a Times New Roman
        Font timesNewRoman = new Font("Times New Roman", Font.PLAIN, 14);
        for (Component component : formularioPanel.getComponents()) {
            if (component instanceof JLabel || component instanceof JComboBox || component instanceof JTextField) {
                component.setFont(timesNewRoman);
            }
        }
        botonPagar.setFont(timesNewRoman);
    }

    private void realizarPago() {
        String nombrePasarela = (String) comboPasarelas.getSelectedItem();
        PasarelaDePago pasarelaSeleccionada = pasarelas.stream()
                .filter(p -> p.getClass().getSimpleName().equals(nombrePasarela))
                .findFirst()
                .orElse(null);

        if (pasarelaSeleccionada != null) {
            String informacionTarjeta = campoTarjeta.getText();
            float monto = Float.parseFloat(campoMonto.getText());
            String numeroCuenta = campoNumeroCuenta.getText();
            String numeroTransaccion = campoNumeroTransaccion.getText();

            boolean exito = pasarelaSeleccionada.realizarPago(informacionTarjeta, monto, numeroCuenta, numeroTransaccion);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Pago realizado con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al realizar el pago.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new nuevoRequerimiento().setVisible(true);
            }
        });
    }
}
