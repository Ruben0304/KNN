package ruben.eduardo.knn.servicios;

import javafx.event.ActionEvent;
import ruben.eduardo.knn.interfaces.*;
import ruben.eduardo.knn.visual.componentes.Alertas;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class GeneradorMatrices implements IGeneradorMatrices {

    private final AnalizadorKNN analizadorKNN;


    public GeneradorMatrices(AnalizadorKNN analizadorKNN) {
        this.analizadorKNN = analizadorKNN;
    }

    public double[][] generarMatriz(HashMap<LinkedList<Double>, String> elementosC, LinkedList<LinkedList<Double>> elementosNC) {
        int nNOClasif = elementosNC.size();
        Set<LinkedList<Double>> clavesClasif = elementosC.keySet();
        int nClasif = clavesClasif.size();
        double[][] matrizDistancia = new double[nClasif][nNOClasif];

        Iterator<LinkedList<Double>> itClasif = clavesClasif.iterator();
        int i = 0;
        while (itClasif.hasNext()) {
            LinkedList<Double> clasificado = itClasif.next();
            Iterator<LinkedList<Double>> itNOClasif = elementosNC.iterator();
            int j = 0;
            while (itNOClasif.hasNext()) {
                LinkedList<Double> noClasificado = itNOClasif.next();
                double distancia = analizadorKNN.calcularDistancia(noClasificado, clasificado);
                matrizDistancia[i][j] = distancia;
                j++;
            }
            i++;
        }

        return matrizDistancia;
    }




    public void imprimirMatrizEnConsola(double[][] matrizDistancia) {
        for (int i = 0; i < matrizDistancia.length; i++) {

            for (int j = 0; j < matrizDistancia[i].length; j++)
                System.out.printf("%.2f ", matrizDistancia[i][j]);

            System.out.println(" ");
        }
    }
}




