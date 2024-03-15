package ruben.eduardo.knn.interfaces;

import ruben.eduardo.knn.models.Accion;
import ruben.eduardo.knn.models.Indicador;

import java.util.ArrayList;
import java.util.LinkedList;

public interface Cargador {
    LinkedList<Indicador> cargarIndicadores();

    ArrayList<Accion> cargarAcciones();
}
