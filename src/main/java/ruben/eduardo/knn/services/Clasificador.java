package ruben.eduardo.knn.services;

import org.jetbrains.annotations.NotNull;
import ruben.eduardo.knn.interfaces.AnalizadorKNN;
import ruben.eduardo.knn.interfaces.IClasificador;
import ruben.eduardo.knn.interfaces.IRegistro;

import java.util.*;

public class Clasificador implements AnalizadorKNN, IClasificador {

    private final IRegistro registro;

    public Clasificador(IRegistro registro) {
        this.registro = registro;
    }

//    @Override
//    public List<Map.Entry<String, Double>> obtenerVecinos(Accion a) {
//        return null;
//    }

    @Override
    public ArrayList<Double> calcularRangoDeDatos(@NotNull Set<LinkedList<Double>> datosClasificados) {
        // Lista para almacenar el rango de cada columna
        ArrayList<Double> rangos = new ArrayList<>();


        int numColumnas = datosClasificados.iterator().next().size();

        // 🥵🥵🥵

        for (int i = 0; i < numColumnas; i++) {
            int columna = i;

            double max = datosClasificados.stream()
                    .mapToDouble(lista -> lista.get(columna))
                    .max()
                    .orElseThrow(NoSuchElementException::new);

            double min = datosClasificados.stream()
                    .mapToDouble(lista -> lista.get(columna))
                    .min()
                    .orElseThrow(NoSuchElementException::new);

            rangos.add(max - min);
        }


        return rangos;
    }

    @Override
    public double calcularDistancia(@NotNull LinkedList<Double> noClasificado, @NotNull LinkedList<Double> clasificado,ArrayList<Double> rangos) {

        int size1 = noClasificado.size();
        double distancia = 0;

        if (size1 == clasificado.size() && size1==rangos.size())
            for (int i = 0; i < size1; i++)
                distancia += Math.pow((clasificado.get(i) - noClasificado.get(i)) / rangos.get(i),2);
        else
            throw new IllegalArgumentException("No coinciden los tamaños de las listas");

        return Math.sqrt(distancia);
    }


    @Override
    public String clasificar(LinkedList<LinkedList<Double>> datosNoClasificados) {
        return null;
    }
}
