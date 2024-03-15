package ruben.eduardo.knn.models;

import ruben.eduardo.knn.interfaces.Cargador;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Bolsa {

    private static ArrayList<Accion> acciones;

    private static LinkedList<Indicador> indicadores;

    public Bolsa(Cargador cargador) {
        acciones = cargador.cargarAcciones();
        indicadores = cargador .cargarIndicadores();
    }

    public static List<Accion> getAcciones() {
        return acciones;
    }

    public static List<Indicador> getIndicadores() {
        return indicadores;
    }

}
