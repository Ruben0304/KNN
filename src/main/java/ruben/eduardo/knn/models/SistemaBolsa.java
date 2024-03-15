package ruben.eduardo.knn.models;

import ruben.eduardo.knn.interfaces.AnalizadorKNN;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SistemaBolsa implements AnalizadorKNN {

    private static int k;

    public static void setK(int k) {
        if (k % 2 != 0 && k<10) {
            SistemaBolsa.k = k;
        } else {
            throw new IllegalArgumentException("Este valor debe ser pequeño e impar");
        }
    }

    @Override
    public double calcularKNN(Accion a) {


    }

    @Override
    public Clasificacion clasificarAccion(Accion a) {
        List<Map.Entry<Clasificacion, Double>> listaVecinos = new LinkedList<>();

        return null;
    }
}


