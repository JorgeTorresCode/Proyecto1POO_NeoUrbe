package modelo.consejo;

import modelo.enumerates.Accion;
import modelo.enumerates.Anomalia;

import java.util.ArrayList;
import java.util.Objects;

public class SolucionAnomalia {
    private Anomalia anomalia;
    private ArrayList<Accion> acciones;

    public SolucionAnomalia(Anomalia anomalia){
        this.anomalia = anomalia;
        this.acciones = new ArrayList<>();
    }
    public boolean agregarAccion(Accion unaAccion){
        for (Accion a : acciones) {
            if (a == unaAccion) {
                return false;
            }
        }
        return acciones.add(unaAccion);
    }

    public boolean removerAccion(Accion acc) {
        return acciones.remove(acc);
    }

    public Anomalia getAnomalia() { return anomalia; }
    public ArrayList<Accion> getAcciones() { return acciones; }

    public void setAnomalia(Anomalia anomalia) { this.anomalia = anomalia; }

    @Override
    public String toString() {
        return "SolucionAnomalia{" +
                "anomalia= " + anomalia +
                ", acciones= " + acciones + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolucionAnomalia that = (SolucionAnomalia) o;
        return Objects.equals(anomalia, that.anomalia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anomalia, acciones);
    }
}
