package ruben.eduardo.knn.interfaces;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public interface AnalizadorKNN {



    Object obtenerVecinos(HashMap<LinkedList<Double>,String> clasificados, LinkedList<Double> noClasificados);

    LinkedList<Double> calcularRangoDeDatos(Set<LinkedList<Double>> datosClasificados);
    double calcularDistancia(@NotNull LinkedList<Double> noClasificado, @NotNull LinkedList<Double> clasificado);




}
