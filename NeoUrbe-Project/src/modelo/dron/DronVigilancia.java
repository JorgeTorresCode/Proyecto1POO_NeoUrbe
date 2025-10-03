package modelo.dron;

import modelo.EdificioInteligente;
import modelo.enumerates.Anomalia;

import java.time.LocalDateTime;

public class DronVigilancia {
    private String procesador;
    private int porcentajeBateria;
    private EdificioInteligente edificioPatrullado;
    private boolean alerta;
    private static int bateriaMin;
    private static int ultimoId = 0;

    public DronVigilancia(EdificioInteligente edificio) {
        this.edificioPatrullado =edificio;
        int randomInt = (int) (Math.random() * 3) + 1;
        this.porcentajeBateria = 25*randomInt;
        this.procesador = String.format("DV-%03d", ultimoId++);
        this.alerta = false;
    }

    public ReporteDron detectarAnomalia(){
        int randomInt = (int) (Math.random() * edificioPatrullado.getAnomalias().size());
        Anomalia randomAno = edificioPatrullado.getAnomalias().get(randomInt);
        ReporteDron reporte = new ReporteDron(LocalDateTime.now(),randomAno, this);
        if (porcentajeBateria-25 <= bateriaMin) {
            alerta = true;
            return null;
        }
        porcentajeBateria -= 25;
        return reporte;
    }

    public void cargar() {
        this.porcentajeBateria = 100;
        this.alerta = false;
    }

    public String getProcesador() { return procesador; }
    public int getPorcentajeBateria() { return porcentajeBateria; }
    public EdificioInteligente getEdificioPatrullado() { return edificioPatrullado; }
    public boolean enAlerta() { return alerta; }
    public static int getBateriaMin() { return bateriaMin; }

    public void setEdificioPatrullado(EdificioInteligente edificioPatrullado) {
        this.edificioPatrullado = edificioPatrullado;
    }
    public static void setBateriaMin(int bateriaMin) {
        DronVigilancia.bateriaMin = bateriaMin;
    }

    @Override
    public String toString() {
        return "DronVigilancia{" +
                "procesador= " + procesador +
                ", porcentajeBateria= " + porcentajeBateria +
                ", edificioPatrullado= " + edificioPatrullado +
                ", alerta= " + alerta +
                ", bateriaMin= " + bateriaMin + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DronVigilancia that = (DronVigilancia) obj;
        return procesador.equals(that.procesador);
    }

    @Override
    public int hashCode() {
        return procesador.hashCode();
    }

}
