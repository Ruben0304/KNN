package ruben.eduardo.knn.interfaces;

import ruben.eduardo.knn.models.Accion;
import ruben.eduardo.knn.models.Indicador;

import java.util.List;

public interface Cargador {
    List<Indicador> cargarIndicadores();

    List<Accion> cargarAcciones();
}
