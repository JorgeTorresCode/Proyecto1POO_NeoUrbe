package modelo.consejo;

import modelo.dron.ReporteDron;
import modelo.enumerates.Accion;
import java.util.Objects;

public class ReporteConsejo {
    private ReporteDron reporteDron;
    private Accion accion;

    public ReporteConsejo(ReporteDron reporteDron, Accion accion) {
        this.reporteDron = reporteDron;
        this.accion = accion;
    }

    public ReporteDron getReporteDron() { return reporteDron; }
    public Accion getAccion() { return accion; }

    public void setReporteDron(ReporteDron reporteDron) { this.reporteDron = reporteDron; }
    public void setAccion(Accion accion) { this.accion = accion; }

    @Override
    public String toString() {
        return "ReporteConsejo{" +
                "reporteDron= " + reporteDron +
                ", accion= " + accion + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReporteConsejo that = (ReporteConsejo) o;
        return Objects.equals(reporteDron, that.reporteDron) &&
                Objects.equals(accion, that.accion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reporteDron, accion);
    }
}
