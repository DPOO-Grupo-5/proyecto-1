package gui;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Gestión de Subastas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear el menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem menuItemAbrir = new JMenuItem("Abrir");
        JMenuItem menuItemGuardar = new JMenuItem("Guardar");
        JMenuItem menuItemSalir = new JMenuItem("Salir");
        menuArchivo.add(menuItemAbrir);
        menuArchivo.add(menuItemGuardar);
        menuArchivo.add(menuItemSalir);

        JMenu menuGestion = new JMenu("Gestión");
        JMenuItem menuItemSubastas = new JMenuItem("Subastas");
        JMenuItem menuItemUsuarios = new JMenuItem("Usuarios");
        JMenuItem menuItemInventario = new JMenuItem("Inventario");
        menuGestion.add(menuItemSubastas);
        menuGestion.add(menuItemUsuarios);
        menuGestion.add(menuItemInventario);

        menuBar.add(menuArchivo);
        menuBar.add(menuGestion);
        setJMenuBar(menuBar);

        // Crear el panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Crear el panel de estadísticas (placeholder)
        JPanel panelEstadisticas = new JPanel();
        panelEstadisticas.setBorder(BorderFactory.createTitledBorder("Posibles compradores"));
        //panelEstadisticas.add(new JLabel("Gráfico de ventas aquí"));
        ImageIcon imagen = new ImageIcon("Galeria/data/grafica1.png");
        JLabel imagen1 = new JLabel(imagen);
        panelEstadisticas.add(imagen1);

        // Crear el panel de subastas activas (placeholder)
        JPanel panelSubastas = new JPanel();
        panelSubastas.setBorder(BorderFactory.createTitledBorder("Subastas Activas"));
        panelSubastas.add(new JLabel("Listado de subastas"));

        panelPrincipal.add(panelEstadisticas, BorderLayout.NORTH);
        panelPrincipal.add(panelSubastas, BorderLayout.CENTER);

        add(panelPrincipal, BorderLayout.CENTER);

        // Agregar listeners a los ítems de menú
        menuItemSalir.addActionListener(e -> System.exit(0));
        menuItemSubastas.addActionListener(e -> abrirVentanaSubastas());
        menuItemUsuarios.addActionListener(e -> abrirVentanaUsuarios());
        menuItemInventario.addActionListener(e -> abrirVentanaInventario());

        // Cambiar el estilo de letra a Times New Roman
        Font timesNewRoman = new Font("Times New Roman", Font.PLAIN, 14);
        setFontAndComponents(menuBar, timesNewRoman);
        setFontAndComponents(panelPrincipal, timesNewRoman);
        setFontAndComponents(panelEstadisticas, timesNewRoman);
        setFontAndComponents(panelSubastas, timesNewRoman);
    }

    private void setFontAndComponents(Container container, Font font) {
        for (Component component : container.getComponents()) {
            if (component instanceof JMenu || component instanceof JMenuItem || component instanceof JLabel || component instanceof JButton) {
                component.setFont(font);
            }
            if (component instanceof Container) {
                setFontAndComponents((Container) component, font);
            }
        }
    }

    private void abrirVentanaSubastas() {
        JFrame frameSubastas = new JFrame("Gestión de Subastas");
        frameSubastas.setSize(600, 400);
        frameSubastas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameSubastas.setVisible(true);
    }

    private void abrirVentanaUsuarios() {
        // Creamos una lista de opciones
        String[] opciones = {"Agregar Usuario", "Eliminar Usuario", "Editar Usuario"};

        // Mostramos el diálogo de selección múltiple
        int opcionSeleccionada = JOptionPane.showOptionDialog(
                this,
                "Seleccione una acción:",
                "Gestión de Usuarios",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        // Dependiendo de la opción seleccionada, realizamos la acción correspondiente
        switch (opcionSeleccionada) {
            case 0: // Agregar Usuario
                JFrame frameUsuariosAgregar = new JFrame("Agregar Usuario");
                frameUsuariosAgregar.setSize(600, 400);
                frameUsuariosAgregar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameUsuariosAgregar.setVisible(true);
                break;
            case 1: // Eliminar Usuario
                JFrame frameUsuariosEliminar = new JFrame("Eliminar Usuario");
                frameUsuariosEliminar.setSize(600, 400);
                frameUsuariosEliminar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameUsuariosEliminar.setVisible(true);
                break;
            case 2: // Editar Usuario
                JFrame frameUsuariosEditar = new JFrame("Editar Usuario");
                frameUsuariosEditar.setSize(600, 400);
                frameUsuariosEditar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameUsuariosEditar.setVisible(true);
                break;
            default:
                // No hacemos nada si se cancela la selección
                break;
        }
    }

    private void abrirVentanaInventario() {
        JFrame frameInventario = new JFrame("Gestión de Inventario");
        frameInventario.setSize(600, 400);
        frameInventario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameInventario.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}

