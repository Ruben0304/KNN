package ruben.eduardo.knn.models;

import ruben.eduardo.knn.interfaces.AnalizadorKNN;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SistemaBolsa implements AnalizadorKNN {


    LinkedList<HashMap<Accion,Double>> redAcciones;

    public SistemaBolsa() {
        this.redAcciones = new LinkedList<HashMap<Accion,Double>>();
    }

    public LinkedList<HashMap<Accion, Double>> getRedAcciones() {
        return redAcciones;
    }

    @Override
    public double calcularKNN (Accion a, int valor ){
        return 0.0;
    }


}
