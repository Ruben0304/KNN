package ruben.eduardo.knn.interfaces;

import java.util.LinkedList;

public interface IClasificador {
    String clasificar(LinkedList<LinkedList<Double>> datosNoClasificados);
}
