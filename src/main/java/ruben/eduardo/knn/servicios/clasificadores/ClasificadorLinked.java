package ruben.eduardo.knn.servicios.clasificadores;

import ruben.eduardo.knn.interfaces.IRegistroClasificados;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ClasificadorLinked extends Clasificador{

    public ClasificadorLinked(IRegistroClasificados registroClasificados) {
        super(registroClasificados);
    }

    @Override
    public LinkedList<Map.Entry<String, Double>> obtenerVecinos(HashMap<LinkedList<Double>,String> clasificados, LinkedList<Double> noClasificados) {

        LinkedList<Map.Entry<String, Double>> listaVecinos = new LinkedList<>();
        for (Map.Entry<LinkedList<Double>,String> c : clasificados.entrySet())
            listaVecinos.addLast(Map.entry(c.getValue(),calcularDistancia(noClasificados,c.getKey())));

        return listaVecinos;

    }

    @Override
    public String clasificar(LinkedList<Double> elementoNoClasificado) {
        LinkedList<Map.Entry<String, Double>> kVecinos = obtenerVecinos(registroClasificados.getElementos(),elementoNoClasificado);
        kVecinos.sort(Map.Entry.comparingByValue());

        Map<String, Integer> frecuencia = new HashMap<>();

        for (int i = 0; i < this.k; i++)
            frecuencia.put(kVecinos.get(i).getKey(), frecuencia.getOrDefault(kVecinos.get(i).getKey(), 0) + 1);

        return Collections.max(frecuencia.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

}
