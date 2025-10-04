package vista;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private DefaultListModel<String> listaEdificiosModel = new DefaultListModel<>();
    private DefaultListModel<String> listaEstacionesModel = new DefaultListModel<>();

    private int edificioCounter = 1;
    private int estacionCounter = 1;

    private String[][] matrizEdificios;
    private String[][] matrizEstaciones;
    private String[][] matrizAnomalias;
    private int robotsRegla = 5;
    private int dronesRegla = 5;

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
        JButton btnTerminar = new JButton("Terminar inicialización");

        btnTerminar.addActionListener(e -> {
            if (listaEdificiosModel.size() < 3) {
                JOptionPane.showMessageDialog(this, "Debe crear al menos 3 edificios antes de terminar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (listaEstacionesModel.size() < 5) {
                JOptionPane.showMessageDialog(this, "Debe crear al menos 5 estaciones antes de terminar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            matrizEdificios = new String[listaEdificiosModel.size()][3];
            for (int i = 0; i < listaEdificiosModel.size(); i++) {
                String item = listaEdificiosModel.get(i);
                String[] partes = item.split(" - ");
                matrizEdificios[i][0] = partes[0].trim();
                String[] subPartes = partes[1].split(" Ubicación | cap:");
                matrizEdificios[i][1] = subPartes[0].trim();
                matrizEdificios[i][2] = subPartes[1].trim();
            }

            matrizEstaciones = new String[listaEstacionesModel.size()][4];
            for (int i = 0; i < listaEstacionesModel.size(); i++) {
                String item = listaEstacionesModel.get(i);
                String[] partes = item.split(" - ");
                matrizEstaciones[i][0] = partes[0].trim();
                String resto = partes[1];
                String descripcion = resto.split(", Ubicación")[0].trim();
                String ubicacion = resto.split("Ubicación\\(")[1].split("\\)")[0].trim();
                String cap = resto.split("cap:")[1].split(" ")[0].trim();
                String estado = resto.substring(resto.indexOf("[") + 1, resto.indexOf("]")).trim();
                matrizEstaciones[i][1] = descripcion;
                matrizEstaciones[i][2] = ubicacion;
                matrizEstaciones[i][3] = cap;
                matrizEstaciones[i][4] = estado;
            }

            abrirVentanaOperador();
            dispose();
        });

        toolBar.add(btnEdificios);
        toolBar.add(btnEstaciones);
        toolBar.add(btnAnomalias);
        toolBar.add(btnReglas);
        toolBar.add(btnTerminar);
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

    private void abrirVentanaOperador() {
        VentanaOperador op = new VentanaOperador(listaEdificiosModel);
        op.setVisible(true);
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
                    form.add(new JLabel("Nombre:"));
                    form.add(nombre);
                    form.add(new JLabel("Ubicación:"));
                    form.add(ubicacion);
                    form.add(new JLabel("Capacidad:"));
                    form.add(capacidad);

                    JOptionPane.showOptionDialog(this, form, "Ingrese datos",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                            null, new Object[]{"Aceptar"}, "Aceptar");

                    try {
                        int cap = Integer.parseInt(capacidad.getText().trim());
                        if (nombre.getText().trim().isEmpty() || ubicacion.getText().trim().isEmpty())
                            throw new Exception();
                        listaEdificiosModel.addElement("Id" + (edificioCounter++) + " - " +
                                nombre.getText().trim() + " Ubicación " + ubicacion.getText().trim() + " cap:" + cap);
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
                    form.add(new JLabel("Descripción:"));
                    form.add(descripcion);
                    form.add(new JLabel("Ubicación:"));
                    form.add(ubicacion);
                    form.add(new JLabel("Capacidad:"));
                    form.add(capacidad);
                    form.add(new JLabel("Estado:"));
                    form.add(estado);

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

        java.util.List<JButton> botones = new ArrayList<>();
        Map<JButton, java.util.List<JCheckBox>> mapaSoluciones = new HashMap<>();

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

            java.util.List<JCheckBox> soluciones = new ArrayList<>();
            soluciones.add(sol1);
            soluciones.add(sol2);
            soluciones.add(sol3);
            soluciones.add(sol4);

            for (JCheckBox j : soluciones) {
                checkPanel.add(j);
            }

            mapaSoluciones.put(btn, soluciones);

            btn.addActionListener(e -> {
                boolean seleccionando = btn.getBackground().equals(Color.RED);
                btn.setBackground(seleccionando ? Color.GREEN : Color.RED);
                checkPanel.setVisible(seleccionando);
                revalidate();
                repaint();
            });

            panel.add(Box.createRigidArea(new Dimension(0, 5)));
            panel.add(btn);
            panel.add(checkPanel);

            botones.add(btn);
        }

        JButton aceptar = new JButton("Aceptar");
        aceptar.setAlignmentX(Component.CENTER_ALIGNMENT);
        aceptar.addActionListener(e -> {
            boolean hayVerde = false;
            for (JButton b : botones) {
                if (b.getBackground().equals(Color.GREEN)) {
                    hayVerde = true;
                    boolean tieneSolucion = false;
                    for (JCheckBox sol : mapaSoluciones.get(b)) {
                        if (sol.isSelected()) {
                            tieneSolucion = true;
                            break;
                        }
                    }
                    if (!tieneSolucion) {
                        JOptionPane.showMessageDialog(this, "La anomalía '" + b.getText() + "' debe tener al menos una solución marcada.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
            if (!hayVerde) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar al menos una anomalía en verde.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (JButton b : botones) {
                b.setEnabled(false);
                for (JCheckBox sol : mapaSoluciones.get(b)) {
                    sol.setEnabled(false);
                }
            }
            aceptar.setEnabled(false);

            matrizAnomalias = new String[botones.size()][5];
            for (int i = 0; i < botones.size(); i++) {
                JButton b = botones.get(i);
                matrizAnomalias[i][0] = b.getText();
                int col = 1;
                for (JCheckBox sol : mapaSoluciones.get(b)) {
                    matrizAnomalias[i][col++] = sol.isSelected() ? "true" : "false";
                }
            }

            JOptionPane.showMessageDialog(this, "Anomalías guardadas correctamente.");
        });

        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(aceptar);

        return new JScrollPane(panel);
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
        JSlider bateriaRobots = new JSlider(0, 100, 5);
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
        JSlider bateriaDrones = new JSlider(0, 100, 5);
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
            robotsRegla = Math.max(5, bateriaRobots.getValue());
            dronesRegla = Math.max(5, bateriaDrones.getValue());

            panel.removeAll();
            panel.setLayout(new GridLayout(2, 1, 10, 10));
            JLabel infoRobots = new JLabel("Batería mínima Robots: " + robotsRegla + "%", JLabel.CENTER);
            infoRobots.setFont(new Font("Arial", Font.BOLD, 16));
            JLabel infoDrones = new JLabel("Batería mínima Drones: " + dronesRegla + "%", JLabel.CENTER);
            infoDrones.setFont(new Font("Arial", Font.BOLD, 16));
            panel.add(infoRobots);
            panel.add(infoDrones);

            panel.revalidate();
            panel.repaint();
        });

        return panel;
    }

    public class VentanaOperador extends JFrame {
        private CardLayout cardLayout;
        private JPanel mainPanel;

        private DefaultListModel<String> listaEdificiosModel;
        private DefaultListModel<String> listaCiudadanosModel = new DefaultListModel<>();
        private DefaultListModel<String> listaRobotsModel = new DefaultListModel<>();
        private DefaultListModel<String> listaDronesModel = new DefaultListModel<>();

        private int ciudadanoCounter = 1;
        private int robotCounter = 1;
        private int dronCounter = 1;

        public VentanaOperador(DefaultListModel<String> edificios) {
            this.listaEdificiosModel = edificios;

            setTitle("Operador");
            setSize(900, 650);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JToolBar toolBar = new JToolBar();
            JButton btnRobots = new JButton("Robots");
            JButton btnCiudadanos = new JButton("Ciudadanos");
            JButton btnDrones = new JButton("Drones");
            JButton btnSimulacion = new JButton("Simulación");
            JButton btnCambiarPerfil = new JButton("Cambiar perfil");

            toolBar.add(btnRobots);
            toolBar.add(btnCiudadanos);
            toolBar.add(btnDrones);
            toolBar.add(btnSimulacion);
            toolBar.add(btnCambiarPerfil);
            add(toolBar, BorderLayout.NORTH);

            cardLayout = new CardLayout();
            mainPanel = new JPanel(cardLayout);

            mainPanel.add(crearPanelRobots(), "Robots");
            mainPanel.add(crearPanelCiudadanos(), "Ciudadanos");
            mainPanel.add(crearPanelDrones(), "Drones");
            mainPanel.add(crearPanelSimulacion(), "Simulacion");

            add(mainPanel, BorderLayout.CENTER);

            btnRobots.addActionListener(e -> cardLayout.show(mainPanel, "Robots"));
            btnCiudadanos.addActionListener(e -> cardLayout.show(mainPanel, "Ciudadanos"));
            btnDrones.addActionListener(e -> cardLayout.show(mainPanel, "Drones"));
            btnSimulacion.addActionListener(e -> cardLayout.show(mainPanel, "Simulacion"));

            btnCambiarPerfil.addActionListener(e -> {
                int res = JOptionPane.showConfirmDialog(
                        this,
                        "¿Desea cambiar a General?",
                        "Confirmación",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (res == JOptionPane.OK_OPTION) {
                    JFrame ventanaGeneral = new JFrame("General");
                    ventanaGeneral.setSize(800, 600);
                    ventanaGeneral.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    ventanaGeneral.setLocationRelativeTo(null);
                    JLabel label = new JLabel("Bienvenido al perfil General", JLabel.CENTER);
                    label.setFont(new Font("Arial", Font.BOLD, 20));
                    ventanaGeneral.add(label);
                    ventanaGeneral.setVisible(true);

                    this.dispose();
                }
            });
        }

        private String extractBuildingId(String raw) {
            if (raw == null) return "";
            if (raw.contains(" - ")) return raw.split(" - ")[0].trim();
            String[] p = raw.split("[,\\s]+");
            return p.length > 0 ? p[0].trim() : raw;
        }

        private JPanel crearPanelRobots() {
            JPanel panel = new JPanel(new BorderLayout());
            JButton btnCrear = new JButton("Crear Robot");
            JButton btnEliminar = new JButton("Eliminar Robot");
            JList<String> lista = new JList<>(listaRobotsModel);

            btnEliminar.setEnabled(false);

            btnCrear.addActionListener(e -> {
                JTextField procesador = new JTextField();
                JSlider bateria = new JSlider(0, 100, 50);
                bateria.setPaintTicks(true);
                bateria.setPaintLabels(true);
                bateria.setMajorTickSpacing(10);
                JLabel labelBateria = new JLabel(bateria.getValue() + "%");
                bateria.addChangeListener(ev -> labelBateria.setText(bateria.getValue() + "%"));

                JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));
                form.add(new JLabel("Procesador:"));
                form.add(procesador);
                form.add(new JLabel("Batería:"));
                form.add(bateria);
                form.add(new JLabel("Nivel actual:"));
                form.add(labelBateria);

                int res = JOptionPane.showConfirmDialog(this, form, "Crear Robot", JOptionPane.OK_CANCEL_OPTION);
                if (res == JOptionPane.OK_OPTION) {
                    String id = "R" + robotCounter++;
                    listaRobotsModel.addElement(id + "," + procesador.getText().trim() + "," + bateria.getValue() + "%");
                    btnEliminar.setEnabled(!listaRobotsModel.isEmpty());
                }
            });

            btnEliminar.addActionListener(e -> {
                if (listaRobotsModel.isEmpty()) return;
                JComboBox<String> combo = new JComboBox<>();
                for (int i = 0; i < listaRobotsModel.size(); i++) {
                    String[] datos = listaRobotsModel.get(i).split(",", 2);
                    combo.addItem(datos[0]);
                }
                int res = JOptionPane.showConfirmDialog(this, combo, "Eliminar Robot", JOptionPane.OK_CANCEL_OPTION);
                if (res == JOptionPane.OK_OPTION && combo.getSelectedItem() != null) {
                    String id = (String) combo.getSelectedItem();
                    for (int i = 0; i < listaRobotsModel.size(); i++) {
                        if (listaRobotsModel.get(i).startsWith(id + ",")) {
                            listaRobotsModel.remove(i);
                            break;
                        }
                    }
                }
                btnEliminar.setEnabled(!listaRobotsModel.isEmpty());
            });

            JPanel botones = new JPanel();
            botones.add(btnCrear);
            botones.add(btnEliminar);

            panel.add(botones, BorderLayout.NORTH);
            panel.add(new JScrollPane(lista), BorderLayout.CENTER);
            return panel;
        }

        private JPanel crearPanelCiudadanos() {
            JPanel panel = new JPanel(new BorderLayout());
            JButton btnCrear = new JButton("Crear Ciudadano");
            JButton btnEliminar = new JButton("Eliminar Ciudadano");
            JButton btnMudar = new JButton("Mudar Ciudadano");
            JButton btnConsultarEdificios = new JButton("Consultar Edificios");
            JList<String> lista = new JList<>(listaCiudadanosModel);

            btnEliminar.setEnabled(false);
            btnMudar.setEnabled(false);

            java.util.function.Function<String, Integer> contarCiudadanos = (edificioId) -> {
                int count = 0;
                for (int i = 0; i < listaCiudadanosModel.size(); i++) {
                    String[] parts = listaCiudadanosModel.get(i).split(",", 2);
                    if (parts.length > 1 && parts[1].equals(edificioId)) count++;
                }
                return count;
            };

            java.util.function.Function<String, Integer> capacidadEdificio = (edificioId) -> {
                for (int i = 0; i < listaEdificiosModel.size(); i++) {
                    String raw = listaEdificiosModel.get(i);
                    String id = extractBuildingId(raw);
                    if (id.equals(edificioId)) {
                        String capStr = raw.substring(raw.indexOf("cap:") + 4).trim();
                        return Integer.parseInt(capStr);
                    }
                }
                return Integer.MAX_VALUE;
            };

            btnCrear.addActionListener(e -> {
                if (listaEdificiosModel.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No hay edificios disponibles", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JComboBox<String> comboEdificios = new JComboBox<>();
                for (int i = 0; i < listaEdificiosModel.size(); i++) {
                    String id = extractBuildingId(listaEdificiosModel.get(i));
                    int capacidad = capacidadEdificio.apply(id);
                    int ocupados = contarCiudadanos.apply(id);
                    if (ocupados < capacidad) comboEdificios.addItem(id);
                }

                if (comboEdificios.getItemCount() == 0) {
                    JOptionPane.showMessageDialog(this, "No hay edificios disponibles con capacidad", "Información", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                int res = JOptionPane.showConfirmDialog(this, comboEdificios, "Seleccionar Edificio", JOptionPane.OK_CANCEL_OPTION);
                if (res == JOptionPane.OK_OPTION && comboEdificios.getSelectedItem() != null) {
                    String edificioId = (String) comboEdificios.getSelectedItem();
                    String id = "C" + ciudadanoCounter++;
                    listaCiudadanosModel.addElement(id + "," + edificioId);
                    btnEliminar.setEnabled(!listaCiudadanosModel.isEmpty());
                    btnMudar.setEnabled(!listaCiudadanosModel.isEmpty());
                }
            });

            btnMudar.addActionListener(e -> {
                int index = lista.getSelectedIndex();
                if (index == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona primero un ciudadano de la lista", "Atención", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                String datosCiudadano = listaCiudadanosModel.get(index);
                String[] parts = datosCiudadano.split(",", 2);
                String idCiudadano = parts[0];
                String edificioActual = parts.length > 1 ? parts[1] : "";

                JComboBox<String> comboEdificios = new JComboBox<>();
                for (int i = 0; i < listaEdificiosModel.size(); i++) {
                    String id = extractBuildingId(listaEdificiosModel.get(i));
                    int capacidad = capacidadEdificio.apply(id);
                    int ocupados = contarCiudadanos.apply(id);
                    if (!id.equals(edificioActual) && ocupados < capacidad) comboEdificios.addItem(id);
                }

                if (comboEdificios.getItemCount() == 0) {
                    JOptionPane.showMessageDialog(this, "No hay otros edificios con capacidad disponibles", "Información", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                JPanel form = new JPanel(new BorderLayout());
                form.add(new JLabel("Edificio a mudarse:"), BorderLayout.NORTH);
                form.add(comboEdificios, BorderLayout.CENTER);

                int res = JOptionPane.showConfirmDialog(this, form, "Mudar Ciudadano", JOptionPane.OK_CANCEL_OPTION);
                if (res == JOptionPane.OK_OPTION && comboEdificios.getSelectedItem() != null) {
                    String nuevoEdificio = (String) comboEdificios.getSelectedItem();
                    listaCiudadanosModel.set(index, idCiudadano + "," + nuevoEdificio);
                }
            });

            btnEliminar.addActionListener(e -> {
                if (listaCiudadanosModel.isEmpty()) return;
                JComboBox<String> combo = new JComboBox<>();
                for (int i = 0; i < listaCiudadanosModel.size(); i++) {
                    String[] datos = listaCiudadanosModel.get(i).split(",", 2);
                    combo.addItem(datos[0]);
                }
                int res = JOptionPane.showConfirmDialog(this, combo, "Eliminar Ciudadano", JOptionPane.OK_CANCEL_OPTION);
                if (res == JOptionPane.OK_OPTION && combo.getSelectedItem() != null) {
                    String id = (String) combo.getSelectedItem();
                    for (int i = 0; i < listaCiudadanosModel.size(); i++) {
                        if (listaCiudadanosModel.get(i).startsWith(id + ",")) {
                            listaCiudadanosModel.remove(i);
                            break;
                        }
                    }
                }
                btnEliminar.setEnabled(!listaCiudadanosModel.isEmpty());
                btnMudar.setEnabled(!listaCiudadanosModel.isEmpty());
            });

            btnConsultarEdificios.addActionListener(e -> {
                if (listaEdificiosModel.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No hay edificios registrados");
                    return;
                }
                StringBuilder info = new StringBuilder();
                for (int i = 0; i < listaEdificiosModel.size(); i++) {
                    info.append(listaEdificiosModel.get(i)).append("\n");
                }
                JOptionPane.showMessageDialog(this, info.toString(), "Consultar Edificios", JOptionPane.INFORMATION_MESSAGE);
            });

            JPanel botones = new JPanel();
            botones.add(btnCrear);
            botones.add(btnEliminar);
            botones.add(btnMudar);
            botones.add(btnConsultarEdificios);

            panel.add(botones, BorderLayout.NORTH);
            panel.add(new JScrollPane(lista), BorderLayout.CENTER);
            return panel;
        }

        private JPanel crearPanelDrones() {
            JPanel panel = new JPanel(new BorderLayout());
            JButton btnCrear = new JButton("Crear Dron");
            JList<String> lista = new JList<>(listaDronesModel);

            btnCrear.addActionListener(e -> {
                JTextField procesador = new JTextField();
                JSlider bateria = new JSlider(0, 100, 50);
                bateria.setPaintTicks(true);
                bateria.setPaintLabels(true);
                bateria.setMajorTickSpacing(10);
                JLabel labelBateria = new JLabel(bateria.getValue() + "%");
                bateria.addChangeListener(ev -> labelBateria.setText(bateria.getValue() + "%"));

                JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));
                form.add(new JLabel("Procesador:"));
                form.add(procesador);
                form.add(new JLabel("Batería:"));
                form.add(bateria);
                form.add(new JLabel("Nivel actual:"));
                form.add(labelBateria);

                int res = JOptionPane.showConfirmDialog(this, form, "Crear Dron", JOptionPane.OK_CANCEL_OPTION);
                if (res == JOptionPane.OK_OPTION) {
                    String id = "D" + dronCounter++;
                    listaDronesModel.addElement(id + "," + procesador.getText().trim() + "," + bateria.getValue() + "%");
                }
            });

            JPanel botones = new JPanel();
            botones.add(btnCrear);

            panel.add(botones, BorderLayout.NORTH);
            panel.add(new JScrollPane(lista), BorderLayout.CENTER);
            return panel;
        }

        private JPanel crearPanelSimulacion() {
            JPanel panel = new JPanel(new BorderLayout());
            JLabel label = new JLabel("Simulación iniciada", JLabel.CENTER);
            panel.add(label, BorderLayout.CENTER);
            return panel;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
