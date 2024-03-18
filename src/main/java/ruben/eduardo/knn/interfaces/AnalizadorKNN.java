package ruben.eduardo.knn.interfaces;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public interface AnalizadorKNN {



   // List<Map.Entry<String, Double>> obtenerVecinos(Accion a);

    ArrayList<Double> calcularRangoDeDatos(Set<LinkedList<Double>> datosClasificados);
    double calcularDistancia(@NotNull LinkedList<Double> noClasificado, @NotNull LinkedList<Double> clasificado, ArrayList<Double> rangos);




}
