package modelo.estacion;

import modelo.dron.DronVigilancia;

import java.util.Objects;

public class DronCargando {
    private DronVigilancia dron;
    private int numSimulacion;

    public DronCargando(DronVigilancia dron){
        this.dron = dron;
        this.numSimulacion = 0;
    }

    public void aumentarNumSimulacion() {
        numSimulacion++;
    }

    public DronVigilancia getDron() { return dron; }
    public int getNumSimulacion() { return numSimulacion; }

    public void setDron(DronVigilancia dron) { this.dron = dron; }

    @Override
    public String toString() {
        return "DronCargando{" +
                "dron= " + dron +
                ", simulacion= " + numSimulacion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DronCargando that = (DronCargando) o;
        return Objects.equals(dron, that.dron);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dron);
    }
}



