package modelo;

import modelo.robot.RobotAsistente;
import java.util.ArrayList;
import java.util.Objects;

public class Ciudadano {
    private String id;
    private EdificioInteligente edificioInteligente;
    private ArrayList<RobotAsistente> robots;
    private static int ultimoId = 0;

    public Ciudadano(EdificioInteligente edificio) {
        this.id = String.format("C-%03d", ultimoId++);
        this.edificioInteligente = edificio;
        this.robots = new ArrayList<>();
    }

    public boolean asignarRobot(RobotAsistente unRobot) {
        for (RobotAsistente r : robots) {
            if (r.equals(unRobot)) {
                return false;
            }
        }
        return robots.add(unRobot);
    }

    public void mudarse(EdificioInteligente edificioInteligente) {
        this.edificioInteligente = edificioInteligente;
    }

    public String getId() { return id; }
    public EdificioInteligente getEdificioInteligente() { return edificioInteligente; }
    public ArrayList<RobotAsistente> getRobots() { return robots; }

    @Override
    public String toString() {
        return "Ciudadano{" +
                "id=" + id +
                ", edificioInteligente=" + edificioInteligente + ",\n" +
                "Robots=" + robots + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ciudadano that = (Ciudadano) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
