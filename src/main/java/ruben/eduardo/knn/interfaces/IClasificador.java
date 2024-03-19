package ruben.eduardo.knn.interfaces;

import java.util.HashMap;
import java.util.LinkedList;

public interface IClasificador {


    String clasificar(LinkedList<Double> elementoNoClasificado);
    HashMap<LinkedList<Double>, String> clasificar(IRegistroNoClasificados noClasificados);
}
