package vista;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private DefaultListModel<String> listaEdificiosModel = new DefaultListModel<>();
    private DefaultListModel<String> listaEstacionesModel = new DefaultListModel<>();

    private int edificioCounter = 1;
    private int estacionCounter = 1;

    public Main() {
        setTitle("Administrador");
        setSize(900, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JToolBar toolBar = new JToolBar();
        JButton btnEdificios = new JButton("Edificios");
        JButton btnEstaciones = new JButton("Estaciones");
        JButton btnAnomalias = new JButton("Anomalias");
        JButton btnReglas = new JButton("Reglas");

        JButton btnPerfil = new JButton("Cambiar de perfil");
        JPopupMenu menuPerfil = new JPopupMenu();
        JButton operadorBtn = new JButton("Operador");
        JButton generalBtn = new JButton("General");

        operadorBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Perfil cambiado a OPERADOR"));
        generalBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Perfil cambiado a GENERAL"));

        menuPerfil.add(operadorBtn);
        menuPerfil.add(generalBtn);
        btnPerfil.addActionListener(e -> menuPerfil.show(btnPerfil, 0, btnPerfil.getHeight()));

        toolBar.add(btnEdificios);
        toolBar.add(btnEstaciones);
        toolBar.add(btnAnomalias);
        toolBar.add(btnReglas);
        toolBar.add(btnPerfil);
        add(toolBar, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(crearPanelEdificios(), "Edificios");
        mainPanel.add(crearPanelEstaciones(), "Estaciones");
        mainPanel.add(crearPanelAnomalias(), "Anomalias");
        mainPanel.add(crearPanelReglas(), "Reglas");

        add(mainPanel, BorderLayout.CENTER);

        btnEdificios.addActionListener(e -> cardLayout.show(mainPanel, "Edificios"));
        btnEstaciones.addActionListener(e -> cardLayout.show(mainPanel, "Estaciones"));
        btnAnomalias.addActionListener(e -> cardLayout.show(mainPanel, "Anomalias"));
        btnReglas.addActionListener(e -> cardLayout.show(mainPanel, "Reglas"));
    }

    private JPanel crearPanelEdificios() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton btnCrear = new JButton("Crear Edificios");
        JList<String> lista = new JList<>(listaEdificiosModel);

        btnCrear.addActionListener(e -> {
            int cantidad = 0;
            while (true) {
                String input = JOptionPane.showInputDialog(this, "¿Cuántos edificios desea crear? (3-10)");
                if (input == null) continue;
                try {
                    cantidad = Integer.parseInt(input.trim());
                    if (cantidad < 3 || cantidad > 10) throw new Exception();
                    break;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un número válido entre 3 y 10", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            for (int i = 0; i < cantidad; i++) {
                boolean correcto = false;
                while (!correcto) {
                    JTextField nombre = new JTextField();
                    JTextField ubicacion = new JTextField();
                    JTextField capacidad = new JTextField();

                    JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));
                    form.setBorder(BorderFactory.createTitledBorder("Edificio " + edificioCounter));
                    form.add(new JLabel("Nombre:")); form.add(nombre);
                    form.add(new JLabel("Ubicación:")); form.add(ubicacion);
                    form.add(new JLabel("Capacidad:")); form.add(capacidad);

                    JOptionPane.showOptionDialog(this, form, "Ingrese datos",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                            null, new Object[]{"Aceptar"}, "Aceptar");

                    try {
                        int cap = Integer.parseInt(capacidad.getText().trim());
                        if (nombre.getText().trim().isEmpty() || ubicacion.getText().trim().isEmpty())
                            throw new Exception();
                        listaEdificiosModel.addElement("Id" + (edificioCounter++) + " - " +
                                nombre.getText().trim() + ", Ubicación (" + ubicacion.getText().trim() + ") cap:" + cap);
                        correcto = true;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Todos los campos deben estar correctos y capacidad numérica", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            btnCrear.setEnabled(false);
        });

        panel.add(btnCrear, BorderLayout.NORTH);
        panel.add(new JScrollPane(lista), BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelEstaciones() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton btnCrear = new JButton("Crear Estaciones");
        JList<String> lista = new JList<>(listaEstacionesModel);

        btnCrear.addActionListener(e -> {
            int cantidad = 0;
            while (true) {
                String input = JOptionPane.showInputDialog(this, "¿Cuántas estaciones desea crear? (5-8)");
                if (input == null) continue;
                try {
                    cantidad = Integer.parseInt(input.trim());
                    if (cantidad < 5 || cantidad > 8) throw new Exception();
                    break;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un número válido entre 5 y 8", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            for (int i = 0; i < cantidad; i++) {
                boolean correcto = false;
                while (!correcto) {
                    JTextField descripcion = new JTextField();
                    JTextField ubicacion = new JTextField();
                    JTextField capacidad = new JTextField();
                    JComboBox<String> estado = new JComboBox<>(new String[]{"DISPONIBLE", "MAL_ESTADO", "MANTENIMIENTO"});

                    JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));
                    form.setBorder(BorderFactory.createTitledBorder("Estación " + estacionCounter));
                    form.add(new JLabel("Descripción:")); form.add(descripcion);
                    form.add(new JLabel("Ubicación:")); form.add(ubicacion);
                    form.add(new JLabel("Capacidad:")); form.add(capacidad);
                    form.add(new JLabel("Estado:")); form.add(estado);

                    JOptionPane.showOptionDialog(this, form, "Ingrese datos",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                            null, new Object[]{"Aceptar"}, "Aceptar");

                    try {
                        int cap = Integer.parseInt(capacidad.getText().trim());
                        if (descripcion.getText().trim().isEmpty() || ubicacion.getText().trim().isEmpty())
                            throw new Exception();
                        listaEstacionesModel.addElement("Id" + (estacionCounter++) + " - " +
                                descripcion.getText().trim() + ", Ubicación(" + ubicacion.getText().trim() + ") cap:" + cap + " [" + estado.getSelectedItem() + "]");
                        correcto = true;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Todos los campos deben estar correctos y capacidad numérica", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            btnCrear.setEnabled(false);
        });

        panel.add(btnCrear, BorderLayout.NORTH);
        panel.add(new JScrollPane(lista), BorderLayout.CENTER);
        return panel;
    }

    private JScrollPane crearPanelAnomalias() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        String[] anomalias = {
                "Colisión vehicular",
                "Congestión vehicular",
                "Desarrollo de obra pública",
                "Derrames de sustancias peligrosas en carretera",
                "Incendio",
                "Presencia de humo",
                "Presencia de gases",
                "Accidente grave",
                "Presencia de ambulancias en estado de emergencia"
        };

        for (String anomalia : anomalias) {
            JButton btn = new JButton(anomalia);
            btn.setBackground(Color.RED);
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);

            JPanel checkPanel = new JPanel();
            checkPanel.setLayout(new BoxLayout(checkPanel, BoxLayout.Y_AXIS));
            checkPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 10));
            checkPanel.setVisible(false);
            checkPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

            JCheckBox sol1 = new JCheckBox("Contactar a bomberos");
            JCheckBox sol2 = new JCheckBox("Contactar a oficiales de tránsito");
            JCheckBox sol3 = new JCheckBox("Llamar al 911");
            JCheckBox sol4 = new JCheckBox("Convocar ambulancias");

            checkPanel.add(sol1);
            checkPanel.add(sol2);
            checkPanel.add(sol3);
            checkPanel.add(sol4);

            btn.addActionListener(e -> {
                boolean seleccionando = btn.getBackground().equals(Color.RED);
                btn.setBackground(seleccionando ? Color.GREEN : Color.RED);
                checkPanel.setVisible(seleccionando);
                revalidate();
                repaint();
            });

            panel.add(Box.createRigidArea(new Dimension(0,5)));
            panel.add(btn);
            panel.add(checkPanel);
        }

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        return scroll;
    }

    private JPanel crearPanelReglas() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JPanel panelRobots = new JPanel(new BorderLayout());
        panelRobots.setBorder(BorderFactory.createTitledBorder("Batería mínima Robots (%)"));
        JSlider bateriaRobots = new JSlider(5, 75, 5);
        bateriaRobots.setMajorTickSpacing(10);
        bateriaRobots.setPaintTicks(true);
        bateriaRobots.setPaintLabels(true);
        JLabel valorRobots = new JLabel("5%", JLabel.CENTER);
        bateriaRobots.addChangeListener(e -> valorRobots.setText(bateriaRobots.getValue() + "%"));
        panelRobots.add(bateriaRobots, BorderLayout.CENTER);
        panelRobots.add(valorRobots, BorderLayout.SOUTH);

        panel.add(panelRobots, gbc);

        gbc.gridy++;
        JPanel panelDrones = new JPanel(new BorderLayout());
        panelDrones.setBorder(BorderFactory.createTitledBorder("Batería mínima Drones (%)"));
        JSlider bateriaDrones = new JSlider(5, 75, 5);
        bateriaDrones.setMajorTickSpacing(10);
        bateriaDrones.setPaintTicks(true);
        bateriaDrones.setPaintLabels(true);
        JLabel valorDrones = new JLabel("5%", JLabel.CENTER);
        bateriaDrones.addChangeListener(e -> valorDrones.setText(bateriaDrones.getValue() + "%"));
        panelDrones.add(bateriaDrones, BorderLayout.CENTER);
        panelDrones.add(valorDrones, BorderLayout.SOUTH);

        panel.add(panelDrones, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton aceptar = new JButton("Aceptar");
        panel.add(aceptar, gbc);

        aceptar.addActionListener(e -> {
            int robotVal = bateriaRobots.getValue();
            int droneVal = bateriaDrones.getValue();

            panel.removeAll();
            panel.setLayout(new GridLayout(2, 1, 10, 10));
            JLabel infoRobots = new JLabel("Batería mínima Robots: " + robotVal + "%", JLabel.CENTER);
            infoRobots.setFont(new Font("Arial", Font.BOLD, 16));
            JLabel infoDrones = new JLabel("Batería mínima Drones: " + droneVal + "%", JLabel.CENTER);
            infoDrones.setFont(new Font("Arial", Font.BOLD, 16));
            panel.add(infoRobots);
            panel.add(infoDrones);

            panel.revalidate();
            panel.repaint();
        });

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
