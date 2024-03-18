package ruben.eduardo.knn.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public interface IAlmacenador {

    HashMap<LinkedList<Double>,String> getElementos();

    ArrayList<String> getEncabezados();

    void cargarElementos(int posicionClasificacion);

    void cargarEncabezados();


}
