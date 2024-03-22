package ruben.eduardo.knn.interfaces;

import java.util.HashMap;
import java.util.LinkedList;

public interface IGeneradorMatrices {
    double[][] generarMatriz(HashMap<LinkedList<Double>,String> elementosC, LinkedList<LinkedList<Double>> elementosNC );

    void escribirMatrizEnFichero(double[][] matriz);

}
