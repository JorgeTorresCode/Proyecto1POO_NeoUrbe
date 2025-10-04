package logica;

import logica.administradores.*;
import modelo.enumerates.*;
import modelo.robot.*;
import modelo.dron.*;
import modelo.estacion.*;
import modelo.consejo.*;
import modelo.*;

import java.util.ArrayList;

public class Control {
    private AdmCiudadanos admCiudadanos;
    private AdmEdificios admEdificios;
    private AdmRobots admRobots;
    private AdmDrones admDrones;
    private AdmEstaciones admEstaciones;
    private ConsejoInteligencia consejoInteligencia;

    // ===========================
    // \\Perfil Administrador
    // ===========================

    public boolean crearEdificio(String nombre, String ubicacion, int capacidadMax) {
        return admEdificios.crearEdificio(nombre, ubicacion, capacidadMax);
    }

    public boolean crearEstacionRecarga(String descripcion, String ubicacion, int capacidadMax, EstadoEstacion estado) {
        return false;
    }

    public boolean establecerConjuntoAnomalias(ArrayList<Anomalia> anomalias) {
        return false;
    }

    public boolean establecerSolucionesAnomalias(Anomalia anomalia, ArrayList<Accion> acciones) {
        return false;
    }

    public boolean establecerReglaDron(int minimo) {
        return false;
    }

    public boolean establecerReglaRobot(int minimo) {
        return false;
    }

    // ===========================
    // \\Perfil Operador
    // ===========================

    public EdificioInteligente buscarEdificio(String idEdificio) {
        return null;
    }

    public Ciudadano buscarCiudadano(String id) {
        return null;
    }

    public boolean crearCiudadano(EdificioInteligente elEdificio) {
        return false;
    }

    public boolean ponerCiudadanoEnEdificio(String id, Ciudadano inquilino) {
        return false;
    }

    public boolean eliminarCiudadano(String idCiudadano) {
        return false;
    }

    public boolean mudarCiudadano(String idCiudadano, String idEdificioDeseado) {
        return false;
    }

    public boolean crearRobot(int bateria) {
        return false;
    }

    public boolean eliminarRobot(String procesador) {
        return false;
    }

    public boolean asignarRobotACiudadano(String idCiudadano, String idRobot) {
        return false;
    }

    public boolean generarDrones(int numDrones) {
        return false;
    }

    // ===========================
    // \\Simulación
    // ===========================

    public boolean aumentarCiclo() {
        return false;
    }

    public boolean mandarARobotAzar() {
        return false;
    }

    private ArrayList<RobotAsistente> ubicarRobotsAlerta() {
        return null;
    }

    private ArrayList<DronVigilancia> ubicarDronesAlerta() {
        return null;
    }

    public ArrayList<ReporteDron> enviarPatrullajeDrones() {
        return null;
    }

    public boolean enviarDronesYRobotsACargar(ArrayList<DronVigilancia> drones, ArrayList<RobotAsistente> robots) {
        return false;
    }

    public boolean enviarReporteDronAConsejo(ArrayList<ReporteDron> reportes) {
        return false;
    }

    // ===========================
    // \\Perfil General
    // ===========================

    public String getRobotsAlerta() {
        return null;
    }

    public String getDronesAlerta() {
        return null;
    }

    public String getDetallesRobotsEdificios() {
        return null;
    }

    public String getTasaRechazoTareasRobot() {
        return null;
    }
}