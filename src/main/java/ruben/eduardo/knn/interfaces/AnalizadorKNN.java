package ruben.eduardo.knn.interfaces;

import ruben.eduardo.knn.models.Accion;

public interface AnalizadorKNN {

    double calcularKNN(Accion a, int valor);
}
