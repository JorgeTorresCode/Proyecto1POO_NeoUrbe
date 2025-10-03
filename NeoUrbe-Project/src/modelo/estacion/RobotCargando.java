package modelo.estacion;

import modelo.robot.RobotAsistente;

import java.util.Objects;

public class RobotCargando {
    private RobotAsistente robot;
    private int numSimulacion;

    public RobotCargando(RobotAsistente robot){
        this.robot = robot;
        this.numSimulacion = 0;
    }

    public void aumentarNumSimulacion() {
        numSimulacion++;
    }

    public RobotAsistente getRobot() { return robot; }
    public int getNumSimulacion() { return numSimulacion; }

    public void setRobot(RobotAsistente robot) { this.robot = robot; }

    @Override
    public String toString() {
        return "RobotCargando{" +
                "robot= " + robot +
                ", simulacion= " + numSimulacion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RobotCargando that = (RobotCargando) o;
        return Objects.equals(robot, that.robot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(robot);
    }
}



