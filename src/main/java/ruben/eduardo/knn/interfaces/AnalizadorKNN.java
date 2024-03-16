package ruben.eduardo.knn.interfaces;

import ruben.eduardo.knn.models.Accion;
import ruben.eduardo.knn.models.Clasificacion;
import ruben.eduardo.knn.models.Indicador;

import java.util.List;
import java.util.Map;

public interface AnalizadorKNN {

    double calcularDistancia(Indicador noClasificado, Indicador Clasificado);

    List<Map.Entry<Clasificacion, Double>> obtenerVecinos(Accion a);
    Clasificacion clasificarAccion(Accion a);

}
