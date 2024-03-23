package ruben.eduardo.knn.servicios;

import ruben.eduardo.knn.interfaces.AnalizadorKNN;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class GeneradorMatrices  {

    private final AnalizadorKNN analizadorKNN;
    public GeneradorMatrices(AnalizadorKNN analizadorKNN) {
        this.analizadorKNN = analizadorKNN;
    }

    public double[][] generarMatriz(HashMap<LinkedList<Double>,String> elementosC, LinkedList<LinkedList<Double>> elementosNC) {
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
//    }
}
