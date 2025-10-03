package modelo.robot;

import modelo.enumerates.TTarea;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class RobotAsistente {
    private String procesador;
    private int bateria;
    private boolean alerta;
    private ArrayList<RegistroTarea> registroTareas;
    private final ArrayList<TTarea> tareas;
    private static int bateriaMin;
    private static int ultimoProcesador;

    public RobotAsistente(int bateria){
        this.bateria = bateria;
        this.procesador = String.format("RA-%03d", ultimoProcesador++);
        ArrayList<TTarea> tareas = new ArrayList<>(Arrays.asList(TTarea.values()));
        Collections.shuffle(tareas);
        int numValoresSeleccionados = (int) (Math.random() * tareas.size()) + 1;
        ArrayList<TTarea> seleccionadas = (ArrayList<TTarea>) tareas.subList(0, numValoresSeleccionados);
        this.tareas = seleccionadas;
        this.alerta = false;
    }

    public void cargar() {
        this.bateria = 100;
        this.alerta = false;
    }
    public boolean realizarTareaAleatoria(){
        ArrayList<TTarea> tareast = new ArrayList<>(Arrays.asList(TTarea.values()));
        int[] baterias = {5, 10, 15, 5, 20, 25};
        int tareaAleatoria = (int) (Math.random() * tareast.size());
        int pos = tareast.indexOf(tareas.get(tareaAleatoria));
        if (bateria - baterias[pos] <= bateriaMin) {
            alerta = true;
            return false;
        }
        bateria -= baterias[pos];

        return true;
    }
    private boolean crearRegistro(LocalDateTime fechaHora, TTarea tarea){
        RegistroTarea registro = new RegistroTarea(fechaHora, tarea);
        return registroTareas.add(registro);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RobotAsistente that = (RobotAsistente) o;
        return Objects.equals(procesador, that.procesador);
    }

    @Override
    public String toString() {
        return "RobotAsistente{" +
                "procesador= " + procesador +
                ", bateria= " + bateria +
                ", alerta= " + alerta + ",\n" +
                "Tareas disponibles= " + tareas + ",\n" +
                "Registro de tareas realizadas= " + registroTareas + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(procesador);
    }
}
