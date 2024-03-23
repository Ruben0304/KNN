package ruben.eduardo.knn.clasificadores;

import org.jetbrains.annotations.NotNull;
import ruben.eduardo.knn.interfaces.IRegistroClasificados;
import ruben.eduardo.knn.interfaces.IRegistroNoClasificados;

import java.util.*;

public class ClasificadorLista extends Clasificador {

    public ClasificadorLista(IRegistroClasificados registroClasificados) {
        super(registroClasificados);
    }

    @Override
    public ArrayList<Map.Entry<String, Double>> obtenerVecinos(HashMap<LinkedList<Double>,String> clasificados, LinkedList<Double> noClasificados) {

        ArrayList<Map.Entry<String, Double>> listaVecinos = new ArrayList<>();
        for (Map.Entry<LinkedList<Double>,String> c : clasificados.entrySet())
            listaVecinos.add(Map.entry(c.getValue(),calcularDistancia(noClasificados,c.getKey())));

         return listaVecinos;

    }


    @Override
    public String clasificar(LinkedList<Double> elementoNoClasificado) {
        ArrayList<Map.Entry<String, Double>> kVecinos = obtenerVecinos(registroClasificados.getElementos(),elementoNoClasificado);
        kVecinos.sort(Map.Entry.comparingByValue());

        Map<String, Integer> frecuencia = new HashMap<>();

        for (int i = 0; i < 5; i++)
            frecuencia.put(kVecinos.get(i).getKey(), frecuencia.getOrDefault(kVecinos.get(i).getKey(), 0) + 1);

        return Collections.max(frecuencia.entrySet(), Map.Entry.comparingByValue()).getKey();
    }






}
