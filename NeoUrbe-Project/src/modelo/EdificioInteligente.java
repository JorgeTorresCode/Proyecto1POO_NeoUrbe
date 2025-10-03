package modelo;

import modelo.dron.DronVigilancia;
import modelo.enumerates.Anomalia;

import java.util.ArrayList;
import java.util.Objects;

public class EdificioInteligente {
    private String id;
    private String nombre;
    private String ubicacion;
    private int capacidadMax;
    private ArrayList<Ciudadano> ciudadanos;
    private ArrayList<DronVigilancia> drones;
    private static ArrayList<Anomalia> anomalias = new ArrayList<>();
    private static int ultimoId = 0;

    EdificioInteligente(String nombre, String ubicacion, int capacidadMax) {
        this.id = String.format("EI-%03d", ultimoId++);
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.capacidadMax = capacidadMax;
        this.ciudadanos = new ArrayList<>();
        this.drones = new ArrayList<>();
    }

    boolean agregarCiudadano(Ciudadano unCiudadano) {
        for (Ciudadano c : ciudadanos) {
            if (c.equals(unCiudadano)) {
                return false;
            }
        }
        return ciudadanos.add(unCiudadano);
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getUbicacion() { return ubicacion; }
    public int getCapacidadMax() { return capacidadMax; }
    public ArrayList<Ciudadano> getCiudadanos() { return ciudadanos; }
    public ArrayList<DronVigilancia> getDrones() { return drones; }
    public static ArrayList<Anomalia> getAnomalias() { return anomalias; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public void setCapacidadMax(int capacidadMax) { this.capacidadMax = capacidadMax; }


    @Override
    public String toString() {
        return "EdificioInteligente{" +
                "id=" + id +
                ", nombre= " + nombre +
                ", ubicacion= " + ubicacion +
                ", capacidadMax= " + capacidadMax + ",\n" +
                "Ciudadanos= " + ciudadanos + ",\n" +
                "Drones= " + drones + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdificioInteligente that = (EdificioInteligente) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
