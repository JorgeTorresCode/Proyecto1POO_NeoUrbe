package modelo.robot;

import modelo.enumerates.TTarea;
import modelo.estacion.RegistroRecarga;

import java.time.LocalDateTime;
import java.util.Objects;

public class RegistroTarea {
    private LocalDateTime fechaHora;
    private TTarea tarea;

    public RegistroTarea(LocalDateTime fechaHora, TTarea tarea){
        this.fechaHora = fechaHora;
        this.tarea = tarea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistroTarea that = (RegistroTarea) o;
        return Objects.equals(fechaHora, that.fechaHora) &&
                Objects.equals(tarea, that.tarea);
    }

    @Override
    public String toString() {
        return "RegistroRecarga{" +
                "fecha= " + fechaHora.toLocalDate() +
                ", hora= " + fechaHora.toLocalTime() +
                ", tarea= " + tarea + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaHora, tarea);
    }
}
