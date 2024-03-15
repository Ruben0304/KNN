package ruben.eduardo.knn.interfaces;

import ruben.eduardo.knn.models.Accion;
import ruben.eduardo.knn.models.Clasificacion;

public interface AnalizadorKNN {

    double calcularKNN(Accion a, int k);
    Clasificacion clasificarAccion(Accion a, int k);

}
