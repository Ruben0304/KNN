package ruben.eduardo.knn.services;

import org.jetbrains.annotations.NotNull;
import ruben.eduardo.knn.avl.AvlTree;
import ruben.eduardo.knn.interfaces.AnalizadorKNN;
import ruben.eduardo.knn.interfaces.IClasificador;
import ruben.eduardo.knn.interfaces.IRegistroClasificados;
import ruben.eduardo.knn.interfaces.IRegistroNoClasificados;
import ruben.eduardo.knn.models.DistanciaAClasificacion;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;

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

    public LinkedList<Map.Entry<String, Double>> obtenerVecinosLinked(HashMap<LinkedList<Double>,String> clasificados, LinkedList<Double> noClasificados) {

        LinkedList<Map.Entry<String, Double>> listaVecinos = new LinkedList<>();
        for (Map.Entry<LinkedList<Double>,String> c : clasificados.entrySet())
            listaVecinos.addLast(Map.entry(c.getValue(),calcularDistancia(noClasificados,c.getKey())));

        return listaVecinos;

    }

    public AvlTree<DistanciaAClasificacion> obtenerVecinosAvl(HashMap<LinkedList<Double>,String> clasificados, LinkedList<Double> noClasificados) {

        AvlTree<DistanciaAClasificacion> listaVecinos = new AvlTree<>();
        for (Map.Entry<LinkedList<Double>,String> c : clasificados.entrySet())
            listaVecinos.insert(new DistanciaAClasificacion(Map.entry(c.getValue(),calcularDistancia(noClasificados,c.getKey()))));

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

//        int size1 = noClasificado.size();
        double distancia = 0;

        Iterator<Double> nc = noClasificado.iterator();
        Iterator<Double> c = clasificado.iterator();
        Iterator<Double> r = rangoDeDatos.iterator();

        distancia += Math.pow(c.next() - nc.next() / r.next(),2);

//        if (size1 == clasificado.size() && size1==rangoDeDatos.size())
//            for (int i = 0; i < size1; i++)
//                distancia += Math.pow((clasificado.get(i) - noClasificado.get(i)) / rangoDeDatos.get(i),2);
//        else
//            throw new IllegalArgumentException("No coinciden los tamaños de las listas");

        return Math.sqrt(distancia);
    }

    @Override
    public String clasificar(LinkedList<Double> elementoNoClasificado) {
        List<Map.Entry<String, Double>> kVecinos = obtenerVecinos(registroClasificados.getElementos(),elementoNoClasificado);
        kVecinos.sort(Map.Entry.comparingByValue());

        Map<String, Integer> frecuencia = new HashMap<>();

        for (int i = 0; i < 5; i++)
            frecuencia.put(kVecinos.get(i).getKey(), frecuencia.getOrDefault(kVecinos.get(i).getKey(), 0) + 1);

        return Collections.max(frecuencia.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public String clasificarlinked(LinkedList<Double> elementoNoClasificado) {
        LinkedList<Map.Entry<String, Double>> kVecinos = obtenerVecinosLinked(registroClasificados.getElementos(),elementoNoClasificado);
        kVecinos.sort(Map.Entry.comparingByValue());

        Map<String, Integer> frecuencia = new HashMap<>();

        for (int i = 0; i < 5; i++)
            frecuencia.put(kVecinos.get(i).getKey(), frecuencia.getOrDefault(kVecinos.get(i).getKey(), 0) + 1);

        return Collections.max(frecuencia.entrySet(), Map.Entry.comparingByValue()).getKey();
    }


    public String clasificarAvl(LinkedList<Double> elementoNoClasificado) {
        AvlTree<DistanciaAClasificacion> kVecinos = obtenerVecinosAvl(registroClasificados.getElementos(), elementoNoClasificado);
        ArrayList<DistanciaAClasificacion> distanciaAClasificaciones = kVecinos.findNElements(5, false);

        Map<String, Integer> frecuencia = new HashMap<>();

        // Contar la frecuencia de cada String en la lista
        for (DistanciaAClasificacion clasificacion : distanciaAClasificaciones) {
            String clave = clasificacion.distancia().getKey();
            frecuencia.put(clave, frecuencia.getOrDefault(clave, 0) + 1);
        }

        // Encontrar el String con la mayor frecuencia
        String masFrecuente = Collections.max(frecuencia.entrySet(), Map.Entry.comparingByValue()).getKey();

        return masFrecuente;
    }




    @Override
    public HashMap<LinkedList<Double>, String> clasificar(@NotNull IRegistroNoClasificados noClasificados) {

        HashMap<LinkedList<Double>, String> clasificaciones = new HashMap<>();
        for (LinkedList<Double> c : noClasificados.getElementos())
            clasificaciones.put(c,clasificar(c));

        return clasificaciones;
    }



    public ConcurrentHashMap<LinkedList<Double>, String> clasificarParaleloAvl( IRegistroNoClasificados noClasificados) {
        ConcurrentHashMap<LinkedList<Double>, String> clasificaciones = new ConcurrentHashMap<>();
        ForkJoinPool customThreadPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        customThreadPool.submit(() ->
                noClasificados.getElementos().parallelStream().forEach(c -> clasificaciones.put(c, clasificarAvl(c)))
        ).join();
        return clasificaciones;
    }


    public ConcurrentHashMap<LinkedList<Double>, String> clasificarParalelo(@NotNull IRegistroNoClasificados noClasificados) {
        ConcurrentHashMap<LinkedList<Double>, String> clasificaciones = new ConcurrentHashMap<>();
        ForkJoinPool customThreadPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        customThreadPool.submit(() ->
                noClasificados.getElementos().parallelStream().forEach(c -> clasificaciones.put(c, clasificar(c)))
        ).join();
        return clasificaciones;
    }

    public ConcurrentHashMap<LinkedList<Double>, String> clasificarParaleloLinked(@NotNull IRegistroNoClasificados noClasificados) {
        ConcurrentHashMap<LinkedList<Double>, String> clasificaciones = new ConcurrentHashMap<>();
        ForkJoinPool customThreadPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        customThreadPool.submit(() ->
                noClasificados.getElementos().parallelStream().forEach(c -> clasificaciones.put(c, clasificarlinked(c)))
        ).join();
        return clasificaciones;
    }
}
