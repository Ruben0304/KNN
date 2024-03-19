package ruben.eduardo.knn.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public interface ILectorFicheros {
    HashMap<LinkedList<Double>, String> leerArchivo(String nombreArchivo, int posicionClasificacion);

    LinkedList<LinkedList<Double>> leerArchivo(String nombreArchivo);

    ArrayList<String> leerEncabezado(String nombreArchivo);
}
