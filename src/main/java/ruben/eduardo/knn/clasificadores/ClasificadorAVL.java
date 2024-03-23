package ruben.eduardo.knn.clasificadores;

import ruben.eduardo.knn.avl.AvlTree;
import ruben.eduardo.knn.interfaces.IRegistroClasificados;
import ruben.eduardo.knn.modelos.DistanciaAClasificacion;

import java.util.*;

public class ClasificadorAVL extends Clasificador{

    public ClasificadorAVL(IRegistroClasificados registroClasificados) {
        super(registroClasificados);
    }

    @Override
    public AvlTree<DistanciaAClasificacion> obtenerVecinos(HashMap<LinkedList<Double>, String> clasificados, LinkedList<Double> noClasificados) {
        AvlTree<DistanciaAClasificacion> listaVecinos = new AvlTree<>();
        for (Map.Entry<LinkedList<Double>, String> c : clasificados.entrySet()) {
            // Crear una nueva instancia de DistanciaAClasificacion con la distancia y la clasificación
            DistanciaAClasificacion distanciaClasificacion = new DistanciaAClasificacion(
                    new AbstractMap.SimpleEntry<>(c.getValue(), calcularDistancia(noClasificados, c.getKey()))
            );
            // Insertar la instancia en el árbol AVL
            listaVecinos.insert(distanciaClasificacion);
        }
        return listaVecinos;
    }

    @Override
    public String clasificar(LinkedList<Double> elementoNoClasificado) {
        AvlTree<DistanciaAClasificacion> kVecinos = obtenerVecinos(registroClasificados.getElementos(), elementoNoClasificado);
        Iterator<DistanciaAClasificacion> iterador = kVecinos.symmetricIterator();
        TreeMap<String, Integer> frecuencia = new TreeMap<>();
        int vecinosContados = 0;

        while (iterador.hasNext() && vecinosContados < this.k) {
            DistanciaAClasificacion nodo = iterador.next();
            String clave = nodo.distancia().getKey();
            int contadorNodo = nodo.contador(); // El contador está en DistanciaAClasificacion

            // Calcular cuántos vecinos más podemos contar sin exceder el límite de k
            int vecinosParaAgregar = Math.min(contadorNodo, this.k - vecinosContados);

            frecuencia.put(clave, frecuencia.getOrDefault(clave, 0) + vecinosParaAgregar);
            vecinosContados += vecinosParaAgregar;

            // Si alcanzamos el límite de vecinos, salimos del bucle
            if (vecinosContados >= this.k) {
                break;
            }
        }

        // Encontrar la clave con la mayor frecuencia
        String masFrecuente = null;
        int maxFrecuencia = 0;
        for (Map.Entry<String, Integer> entry : frecuencia.entrySet()) {
            if (entry.getValue() > maxFrecuencia) {
                maxFrecuencia = entry.getValue();
                masFrecuente = entry.getKey();
            }
        }



        return masFrecuente;
    }

}
