package ruben.eduardo.knn.models;

import ruben.eduardo.knn.interfaces.AnalizadorKNN;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class AnalistaFinanciero implements AnalizadorKNN {

    private double momentumRange;
    private double rsiRange;
    private double macdRange;
    private static int k = 5;

    public AnalistaFinanciero(){
       normalizarDatos();
    }


    public static void setK(int k) {
        if (k % 2 != 0 && k < 10) AnalistaFinanciero.k = k;
        else throw new IllegalArgumentException("K debe ser menor que 10 e impar");

    }

    @Override
    public double calcularDistancia(Indicador noClasificado, Indicador clasificado) {

        double normalizedMomentumDiff = (clasificado.Momentum - noClasificado.Momentum) / momentumRange;
        double normalizedRSIDiff = (clasificado.RSI - noClasificado.RSI) / rsiRange;
        double normalizedMACDDiff = (clasificado.MACD - noClasificado.MACD) / macdRange;

        //distancia euclidiana
        return Math.sqrt(
                Math.pow(normalizedMomentumDiff, 2) +
                        Math.pow(normalizedRSIDiff, 2) +
                        Math.pow(normalizedMACDDiff, 2)
        );

    }

    @Override
    public List<Map.Entry<Clasificacion, Double>> obtenerVecinos(Accion a) {

        List<Map.Entry<Clasificacion, Double>> listaVecinos = new ArrayList<>();
        Iterator<Indicador> iter = Bolsa.getIndicadores().iterator();
        Indicador i;

        while (iter.hasNext()){

            i = iter.next();
            double distancia = calcularDistancia(a.getIndicador(),i);
            listaVecinos.add(Map.entry(i.getClasificacion(),distancia));

        }

        return listaVecinos;

    }
    @Override
    public Clasificacion clasificarAccion(Accion a) {

        List<Map.Entry<Clasificacion, Double>> kVecinos = obtenerVecinos(a);
        kVecinos.sort(Map.Entry.comparingByValue());

        Map<Clasificacion, Integer> frecuencia = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            frecuencia.put(kVecinos.get(i).getKey(), frecuencia.getOrDefault(kVecinos.get(i).getKey(), 0) + 1);
        }

        Clasificacion clasificacionQueMasSeRepite = Collections.max(frecuencia.entrySet(), Map.Entry.comparingByValue()).getKey();
        return clasificacionQueMasSeRepite;
    }



    private void normalizarDatos() {
       double maxMomentum = Double.MIN_VALUE;
       double minMomentum = Double.MAX_VALUE;
       double maxRSI = Double.MIN_VALUE;
       double minRSI = Double.MAX_VALUE;
       double maxMACD = Double.MIN_VALUE;
       double minMACD = Double.MAX_VALUE;

        for (Indicador indicador : Bolsa.getIndicadores()) {
            if (indicador.Momentum > maxMomentum) maxMomentum = indicador.Momentum;
            if (indicador.Momentum < minMomentum) minMomentum = indicador.Momentum;
            if (indicador.RSI > maxRSI) maxRSI = indicador.RSI;
            if (indicador.RSI < minRSI) minRSI = indicador.RSI;
            if (indicador.MACD > maxMACD) maxMACD = indicador.MACD;
            if (indicador.MACD < minMACD) minMACD = indicador.MACD;
        }

        momentumRange = maxMomentum - minMomentum;
        rsiRange = maxRSI - minRSI;
        macdRange = maxMACD - minMACD;
    }


    public double[][] generarMatrizDistancia() {
        int n = Bolsa.getIndicadores().size();
        double[][] matrizDistancia = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                double distancia = calcularDistancia(Bolsa.getIndicadores().get(i), Bolsa.getIndicadores().get(j));
                matrizDistancia[i][j] = distancia;
                matrizDistancia[j][i] = distancia;
            }
        }

        return matrizDistancia;
    }

    public static void escribirMatrizEnFichero(double[][] matriz, String nombreFichero)
            throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreFichero))) {
            oos.writeObject(matriz);
        }
    }

}


