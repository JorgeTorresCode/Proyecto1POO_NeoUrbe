package modelo.dron;

import modelo.EdificioInteligente;
import modelo.enumerates.Anomalia;
import java.time.LocalDateTime;
import java.util.Objects;

public class ReporteDron {
    private LocalDateTime fechaHora;
    private Anomalia anomalia;
    private DronVigilancia dron;

    public ReporteDron(LocalDateTime fechaHora, Anomalia anomalia, DronVigilancia dron) {
        this.fechaHora = fechaHora;
        this.anomalia = anomalia;
        this.dron = dron;
    }

    @Override
    public String toString() {
        return "ReporteDron{" +
                "fecha=" + fechaHora.toLocalDate()+
                "hora= " + fechaHora.toLocalTime() +
                "anomalia= " + anomalia + "\n" +
                "dron= " + dron + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReporteDron that = (ReporteDron) o;
        return Objects.equals(fechaHora, that.fechaHora) &&
                Objects.equals(anomalia, that.anomalia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaHora, anomalia);
    }
}
