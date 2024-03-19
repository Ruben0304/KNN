package ruben.eduardo.knn.services;

import org.jetbrains.annotations.NotNull;
import ruben.eduardo.knn.interfaces.AnalizadorKNN;
import ruben.eduardo.knn.interfaces.IClasificador;
import ruben.eduardo.knn.interfaces.IRegistroClasificados;
import ruben.eduardo.knn.interfaces.IRegistroNoClasificados;

import java.util.*;

public class Clasificador implements AnalizadorKNN, IClasificador {

  protected ArrayList<Double> rangoDeDatos;

  protected final IRegistroClasificados registroClasificados;

    public Clasificador(IRegistroClasificados registroClasificados) {
        this.registroClasificados = registroClasificados;
        rangoDeDatos = calcularRangoDeDatos(registroClasificados.getElementos().keySet());
    }

    @Override
    public ArrayList<Map.Entry<String, Double>> obtenerVecinos(HashMap<LinkedList<Double>,String> clasificados, LinkedList<Double> noClasificados) {

        ArrayList<Map.Entry<String, Double>> listaVecinos = new ArrayList<>();
        for (Map.Entry<LinkedList<Double>,String> c : clasificados.entrySet())
            listaVecinos.add(Map.entry(c.getValue(),calcularDistancia(noClasificados,c.getKey())));

         return listaVecinos;

    }

    @Override
    public ArrayList<Double> calcularRangoDeDatos(@NotNull Set<LinkedList<Double>> datosClasificados) {


            // guardar rango de cada columna, para normailizar los datos
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
    public double calcularDistancia(@NotNull LinkedList<Double> noClasificado, @NotNull LinkedList<Double> clasificado) {

        int size1 = noClasificado.size();
        double distancia = 0;

        if (size1 == clasificado.size() && size1==rangoDeDatos.size())
            for (int i = 0; i < size1; i++)
                distancia += Math.pow((clasificado.get(i) - noClasificado.get(i)) / rangoDeDatos.get(i),2);
        else
            throw new IllegalArgumentException("No coinciden los tamaños de las listas");

        return Math.sqrt(distancia);
    }

    @Override
    public String clasificar(LinkedList<Double> elementoNoClasificado) {
        List<Map.Entry<String, Double>> kVecinos = obtenerVecinos(registroClasificados.getElementos(),elementoNoClasificado);
        kVecinos.sort(Map.Entry.comparingByValue());

        Map<String, Integer> frecuencia = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            frecuencia.put(kVecinos.get(i).getKey(), frecuencia.getOrDefault(kVecinos.get(i).getKey(), 0) + 1);
        }

        String clasificacionQueMasSeRepite = Collections.max(frecuencia.entrySet(), Map.Entry.comparingByValue()).getKey();
        return clasificacionQueMasSeRepite;
    }

    @Override
    public HashMap<LinkedList<Double>, String> clasificar(@NotNull IRegistroNoClasificados noClasificados) {

        HashMap<LinkedList<Double>, String> clasificaciones = new HashMap<>();
        for (LinkedList<Double> c : noClasificados.getElementos())
            clasificaciones.put(c,clasificar(c));

        return clasificaciones;
    }
}
