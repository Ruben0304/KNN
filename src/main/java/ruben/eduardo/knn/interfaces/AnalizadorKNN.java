package ruben.eduardo.knn.interfaces;

import ruben.eduardo.knn.models.Accion;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface AnalizadorKNN {



    List<Map.Entry<String, Double>> obtenerVecinos(Accion a);

    void normalizarDatos(LinkedList<LinkedList<Double>> datosNoClasificados);
    double calcularDistancia(LinkedList<Double> noClasificado, LinkedList<Double> clasificado);




}
