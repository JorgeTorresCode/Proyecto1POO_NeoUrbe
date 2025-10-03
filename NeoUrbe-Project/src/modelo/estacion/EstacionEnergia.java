package modelo.estacion;

import modelo.dron.DronVigilancia;
import modelo.enumerates.EstadoEstacion;
import modelo.robot.RobotAsistente;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EstacionEnergia {
    private EstadoEstacion estado;
    private String id;
    private String descripcion;
    private String ubicacion;
    private int capacidadMax;
    private ArrayList<RegistroRecarga> registros;
    private ArrayList<DronCargando> dronesCargando;
    private ArrayList<RobotCargando> robotsCargando;
    private int ultimoId = 0;

    public EstacionEnergia(EstadoEstacion estado, String desc, String ubicacion, int capacidadMax) {
        this.estado = estado;
        this.descripcion = desc;
        this.ubicacion = ubicacion;
        this.capacidadMax = capacidadMax;
        this.id = String.format("EE-%03d", ultimoId++);
        this.registros = new ArrayList<>();
        this.dronesCargando = new ArrayList<>();
        this.robotsCargando = new ArrayList<>();
    }

    public void aumentarNumSimulacion() {
        for (DronCargando d : dronesCargando) {
            d.aumentarNumSimulacion();
        }
        for (RobotCargando r : robotsCargando) {
            r.aumentarNumSimulacion();
        }
    }

    public boolean iniciarCargaDron(DronVigilancia unDron) {
        if (isFull()) return false;
        for (DronCargando d : dronesCargando) {
            if (d.getDron().equals(unDron)) {
                return false;
            }
        }
        DronCargando carga = new DronCargando(unDron);
        agregarRegistro(LocalDateTime.now(), unDron.toString());
        return dronesCargando.add(carga);
    }

    public boolean finalizarCargaDrones() {
        for (DronCargando d : dronesCargando) {
            if (d.getNumSimulacion() == 2) {
                d.getDron().cargar();
                return dronesCargando.remove(d);
            }
        }
        return false;
    }

    public boolean iniciarCargaRobot(RobotAsistente unRobot) {
        if (isFull()) return false;
        for (RobotCargando r : robotsCargando) {
            if (r.getRobot().equals(unRobot)) {
                return false;
            }
        }
        RobotCargando carga = new RobotCargando(unRobot);
        agregarRegistro(LocalDateTime.now(), unRobot.toString());
        return robotsCargando.add(carga);
    }

    public boolean finalizarCargaRobots() {
        for (RobotCargando r : robotsCargando) {
            if (r.getNumSimulacion() == 2) {
                r.getRobot().cargar();
                return robotsCargando.remove(r);
            }
        }
        return false;
    }

    public boolean isFull() {
        return dispositivosCargando() >= capacidadMax;
    }

    private boolean agregarRegistro(LocalDateTime fechaHora, String desc) {
        RegistroRecarga registro = new RegistroRecarga(fechaHora, desc);
        return registros.add(registro);
    }
    
    private int dispositivosCargando() {
        return dronesCargando.size() + robotsCargando.size();
    }
}