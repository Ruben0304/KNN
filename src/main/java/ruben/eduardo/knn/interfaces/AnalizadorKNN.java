package ruben.eduardo.knn.interfaces;

import java.util.*;

public interface AnalizadorKNN {



   // List<Map.Entry<String, Double>> obtenerVecinos(Accion a);

    ArrayList<Double> calcularRangoDeDatos(Set<LinkedList<Double>> datosClasificados);
    double calcularDistancia(LinkedList<Double> noClasificado, LinkedList<Double> clasificado);




}
