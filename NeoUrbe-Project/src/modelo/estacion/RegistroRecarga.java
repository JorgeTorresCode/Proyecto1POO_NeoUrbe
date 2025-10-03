package modelo.estacion;

import java.time.LocalDateTime;
import java.util.Objects;

public class RegistroRecarga {
    private LocalDateTime fechaHora;
    private String description;

    public RegistroRecarga(LocalDateTime fechaHora, String description) {
        this.fechaHora = fechaHora;
        this.description = description;
    }

    public String getDescription() { return description; }
    public LocalDateTime getFechaHora() { return fechaHora; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistroRecarga that = (RegistroRecarga) o;
        return Objects.equals(fechaHora, that.fechaHora) &&
                Objects.equals(description, that.description);
    }

    @Override
    public String toString() {
        return "RegistroRecarga{" +
                "fecha= " + fechaHora.toLocalDate() +
                ", hora= " + fechaHora.toLocalTime() +
                ", description= " + description + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaHora, description);
    }
}
