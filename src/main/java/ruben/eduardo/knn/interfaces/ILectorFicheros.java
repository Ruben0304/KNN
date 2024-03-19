package ruben.eduardo.knn.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public interface ILectorFicheros {
    HashMap<LinkedList<Double>, String> leerArchivo(int posicionClasificacion);

    LinkedList<LinkedList<Double>> leerArchivo();

    ArrayList<String> leerEncabezado();
}
