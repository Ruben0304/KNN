package ruben.eduardo.knn.interfaces;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public interface IClasificador {


    String clasificar(LinkedList<Double> elementoNoClasificado);
    Map<LinkedList<Double>, String> clasificarConjunto(IRegistroNoClasificados noClasificados);

    HashMap<LinkedList<Double>, String> clasificarNoParalelo(@NotNull IRegistroNoClasificados noClasificados);
}
