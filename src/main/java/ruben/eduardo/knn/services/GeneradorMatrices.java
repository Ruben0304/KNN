package ruben.eduardo.knn.services;

import ruben.eduardo.knn.interfaces.AnalizadorKNN;
import ruben.eduardo.knn.interfaces.IGeneradorMatrices;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class GeneradorMatrices  {

//    private final AnalizadorKNN analizadorKNN;
//    public GeneradorMatrices(AnalizadorKNN analizadorKNN) {
//        this.analizadorKNN = analizadorKNN;
//    }
//
//    public double[][] generarMatriz() {
//        int n = Bolsa.getIndicadores().size();
//        int n2 = Bolsa.getAcciones().size();
//        double[][] matrizDistancia = new double[n2][n];
//
//        for (int i = 0; i < n2; i++) {
//            for (int j = 0; j < n; j++) {
//                double distancia = analizadorKNN.calcularDistancia(Bolsa.getAcciones().get(i).getIndicador(), Bolsa.getIndicadores().get(j));
//                matrizDistancia[i][j] = distancia;
//            }
//        }
//
//        return matrizDistancia;
//    }
//
//
//    public void escribirMatrizEnFichero(double[][] matriz) {
//        try {
//            FileOutputStream fos = new FileOutputStream("matrizDistancia.txt");
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//
//            // Escribir las dimensiones de la matriz
//            oos.writeInt(matriz.length); // Número de filas
//            oos.writeInt(matriz[0].length); // Número de columnas
//
//            // Escribir los elementos de la matriz
//            for (int i = 0; i < matriz.length; i++) {
//                for (int j = 0; j < matriz[i].length; j++) {
//                    oos.writeDouble(matriz[i][j]);
//                }
//            }
//
//            oos.close();
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }
}
