package ruben.eduardo.knn.clasificadores;

import org.jetbrains.annotations.NotNull;
import ruben.eduardo.knn.interfaces.AnalizadorKNN;
import ruben.eduardo.knn.interfaces.IClasificador;
import ruben.eduardo.knn.interfaces.IRegistroClasificados;
import ruben.eduardo.knn.interfaces.IRegistroNoClasificados;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;

public abstract class Clasificador implements AnalizadorKNN, IClasificador {

    protected ArrayList<Double> rangoDeDatos;


    protected final IRegistroClasificados registroClasificados;

    public Clasificador(IRegistroClasificados registroClasificados) {
        this.registroClasificados = registroClasificados;
        rangoDeDatos = calcularRangoDeDatos(registroClasificados.getElementos().keySet());
    }

    @Override
    public ArrayList<Double> calcularRangoDeDatos(@NotNull Set<LinkedList<Double>> datosClasificados) {


        // guardar rango de cada columna, para normailizar los datos
        ArrayList<Double> rangos = new ArrayList<>();
        int numColumnas = datosClasificados.iterator().next().size();

        // 🥵🥵🥵

        for (int i = 0; i < numColumnas; i++) {
            int columna = i;

            double max = datosClasificados.parallelStream()
                    .mapToDouble(lista -> lista.get(columna))
                    .max()
                    .orElseThrow(NoSuchElementException::new);

            double min = datosClasificados.parallelStream()
                    .mapToDouble(lista -> lista.get(columna))
                    .min()
                    .orElseThrow(NoSuchElementException::new);

            rangos.add(max - min);
        }


        return rangos;
    }

    @Override
    public double calcularDistancia(@NotNull LinkedList<Double> noClasificado, @NotNull LinkedList<Double> clasificado) {

        double distancia = 0;

        Iterator<Double> nc = noClasificado.iterator();
        Iterator<Double> c = clasificado.iterator();
        Iterator<Double> r = rangoDeDatos.iterator();

        while (c.hasNext())
            distancia += Math.pow((c.next() - nc.next()) / r.next(),2);

        return Math.sqrt(distancia);
    }


    @Override
    public ConcurrentHashMap<LinkedList<Double>, String> clasificarConjunto(IRegistroNoClasificados noClasificados) {
        ConcurrentHashMap<LinkedList<Double>, String> clasificaciones = new ConcurrentHashMap<>();
        ForkJoinPool customThreadPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        customThreadPool.submit(() ->
                noClasificados.getElementos().parallelStream().forEach(c -> clasificaciones.put(c, clasificar(c)))
        ).join();
        return clasificaciones;
    }

    @Override
    public HashMap<LinkedList<Double>, String> clasificarNoParalelo(@NotNull IRegistroNoClasificados noClasificados) {

        HashMap<LinkedList<Double>, String> clasificaciones = new HashMap<>();
        for (LinkedList<Double> c : noClasificados.getElementos())
            clasificaciones.put(c,clasificar(c));

        return clasificaciones;
    }

}
