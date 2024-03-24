package ruben.eduardo.knn.servicios.clasificadores;

import ruben.eduardo.knn.interfaces.IRegistroClasificados;

import java.util.*;

public class ClasificadorQeue extends Clasificador{

    public ClasificadorQeue(IRegistroClasificados registroClasificados) {
        super(registroClasificados);
    }

    public PriorityQueue<Map.Entry<String, Double>> obtenerVecinos(HashMap<LinkedList<Double>, String> clasificados, LinkedList<Double> noClasificados) {
        // Crear la PriorityQueue con un comparador que ordene las entradas por distancia
        PriorityQueue<Map.Entry<String, Double>> colaPrioridad = new PriorityQueue<>(
                Comparator.comparingDouble(Map.Entry<String, Double>::getValue)
        );


        // Iterar sobre el conjunto de datos clasificados para calcular las distancias
        for (Map.Entry<LinkedList<Double>, String> c : clasificados.entrySet()) {
            // Calcular la distancia
            double distancia = calcularDistancia(noClasificados, c.getKey());
            // Crear una entrada para la clasificación y la distancia
            Map.Entry<String, Double> entrada = new AbstractMap.SimpleEntry<>(c.getValue(), distancia);
            // Añadir la entrada a la cola de prioridad
            colaPrioridad.offer(entrada);
        }

        return colaPrioridad;
    }


    public String clasificar(LinkedList<Double> elementoNoClasificado) {
        // Obtener la PriorityQueue con los vecinos y sus distancias
        PriorityQueue<Map.Entry<String, Double>> kVecinos = obtenerVecinos(registroClasificados.getElementos(), elementoNoClasificado);

        Map<String, Integer> frecuencia = new HashMap<>();

        // Asegurarse de que hay al menos K vecinos
        int k = Math.min(kVecinos.size(), this.k);
        for (int i = 0; i < k; i++) {
            Map.Entry<String, Double> vecino = kVecinos.poll();
            if (vecino != null) {
                String clase = vecino.getKey();
                frecuencia.put(clase, frecuencia.getOrDefault(clase, 0) + 1);
            }
        }

        // Encontrar la clase con la máxima frecuencia entre los K vecinos más cercanos
        return Collections.max(frecuencia.entrySet(), Map.Entry.comparingByValue()).getKey();
    }





}
