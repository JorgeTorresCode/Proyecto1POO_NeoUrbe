package vista;

import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Administrador");
        ventana.setSize(800, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());

        JLabel etiqueta = new JLabel("Escoja una opción: ", SwingConstants.CENTER);
        ventana.add(etiqueta, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JButton boton0 = new JButton("Edificios");
        JButton boton1 = new JButton("Robots");
        JButton boton2 = new JButton("Drones");
        JButton boton3 = new JButton("Estaciones de Carga");
        JButton boton4 = new JButton("Ciudadanos");
        JCheckBox menu = new JCheckBox("Hola");

        JMenuBar barraMenu = new JMenuBar();

        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemNuevo = new JMenuItem("Nuevo");
        JMenuItem itemSalir = new JMenuItem("Salir");

        JTextField text = new JTextField("HOla como estas");
        panelBotones.add(text);

        JButton[] botones = {boton0, boton1, boton2, boton3, boton4};
        for (JButton boton : botones) {
            panelBotones.add(boton);
        }

        ventana.add(panelBotones, BorderLayout.CENTER);

        boton0.addActionListener(e -> ventanaEdificio(ventana, panelBotones, "Edificios"));
        boton1.addActionListener(e -> ventanaRobots(ventana, panelBotones,"Robots"));
        boton2.addActionListener(e -> ventanaDrones(ventana, panelBotones,"Drones"));
        boton3.addActionListener(e -> ventanaEstacion(ventana, panelBotones,"Estaciones de Carga"));
        boton4.addActionListener(e -> ventanaCiudadano(ventana, panelBotones,"Ciudadanos"));
        ventana.setVisible(true);
    }

    private static void ventanaEdificio(JFrame actual, JPanel panel, String titulo) {
        JPanel panelOriginal = panel;
        panel.removeAll();
        JButton boton0 = new JButton("Agregar");
        JButton boton1 = new JButton("Eliminar");
        JButton boton2 = new JButton("Editar");
        JButton boton3 = new JButton("Consultar");
        JButton boton4 = new JButton("Regresar");
        JButton[] botones = {boton0, boton1, boton2, boton3, boton4};
        for (JButton boton : botones) {
            panel.add(boton);
        }
        panel.repaint();
        panel.revalidate();
        actual.setTitle(titulo);
    }
    private static void ventanaRobots(JFrame actual, JPanel panel, String titulo) {
        JPanel panelOriginal = panel;
        panel.removeAll();
        JButton boton0 = new JButton("Agregar");
        JButton boton1 = new JButton("Eliminar");
        JButton boton2 = new JButton("Editar");
        JButton boton3 = new JButton("Consultar");
        JButton boton4 = new JButton("Regresar");
        JButton[] botones = {boton0, boton1, boton2, boton3, boton4};
        for (JButton boton : botones) {
            panel.add(boton);
        }
        panel.repaint();
        panel.revalidate();
        actual.setTitle(titulo);
    }
    private static void ventanaDrones(JFrame actual, JPanel panel, String titulo) {
        JPanel panelOriginal = panel;
        panel.removeAll();
        JButton boton0 = new JButton("Agregar");
        JButton boton1 = new JButton("Eliminar");
        JButton boton2 = new JButton("Editar");
        JButton boton3 = new JButton("Consultar");
        JButton boton4 = new JButton("Regresar");
        JButton[] botones = {boton0, boton1, boton2, boton3, boton4};
        for (JButton boton : botones) {
            panel.add(boton);
        }
        panel.repaint();
        panel.revalidate();
        actual.setTitle(titulo);
    }
    private static void ventanaEstacion(JFrame actual, JPanel panel, String titulo) {
        JPanel panelOriginal = panel;
        panel.removeAll();
        JButton boton0 = new JButton("Agregar");
        JButton boton1 = new JButton("Eliminar");
        JButton boton2 = new JButton("Editar");
        JButton boton3 = new JButton("Consultar");
        JButton boton4 = new JButton("Regresar");
        JButton[] botones = {boton0, boton1, boton2, boton3, boton4};
        for (JButton boton : botones) {
            panel.add(boton);
        }
        panel.repaint();
        panel.revalidate();
        actual.setTitle(titulo);
    }
    private static void ventanaCiudadano(JFrame actual, JPanel panel, String titulo) {
        JPanel panelOriginal = panel;
        panel.removeAll();
        JButton boton0 = new JButton("Agregar");
        JButton boton1 = new JButton("Eliminar");
        JButton boton2 = new JButton("Editar");
        JButton boton3 = new JButton("Consultar");
        JButton boton4 = new JButton("Regresar");
        JButton[] botones = {boton0, boton1, boton2, boton3, boton4};
        for (JButton boton : botones) {
            panel.add(boton);
        }
        panel.repaint();
        panel.revalidate();
        actual.setTitle(titulo);
    }
}


