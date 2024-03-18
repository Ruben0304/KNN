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

        // Calcular el rango para cada columna
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
    public double calcularDistancia(LinkedList<Double> noClasificado, LinkedList<Double> clasificado) {

        return 0;
    }

    @Override
    public String clasificar(LinkedList<LinkedList<Double>> datosNoClasificados) {
        return null;
    }
}
