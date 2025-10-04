package logica.administradores;

import modelo.EdificioInteligente;

import java.util.ArrayList;

public class AdmEdificios {
    private ArrayList<EdificioInteligente> listaEdificios;

    public AdmEdificios() {
        listaEdificios = new ArrayList<>();
    }

    public boolean crearEdificio(String nombre, String ubicacion, int capacidadMax) {
        EdificioInteligente nuevoEdificio = new EdificioInteligente(nombre, ubicacion, capacidadMax);
        for (EdificioInteligente e : listaEdificios) {
            if (e.equals(nuevoEdificio))
                return false;
        }
        return listaEdificios.add(nuevoEdificio);
    }

    public boolean eliminarEdificio(String idEdificio) {
        for (int i = 0; i < listaEdificios.size(); i++) {
            EdificioInteligente e = listaEdificios.get(i);
            if (idEdificio.equals(e.getId())) {
                listaEdificios.remove(i);
                return true;
            }
        }
        return false;
    }

    public EdificioInteligente localizarEdificio(String idEdificio) {
        for (EdificioInteligente e : listaEdificios) {
            if (e.getId().equals(idEdificio))
                return e;
        }
        return null;
    }


    // +establecerAnomalias(anomalias: Collection<Anomalia>): boolean
    // +agregarCiudadanoAEdificio(ciudadano: Ciudadano, idEdificio: String): boolean
    // +agregarCiudadano(idEdificio: String): boolean
}
