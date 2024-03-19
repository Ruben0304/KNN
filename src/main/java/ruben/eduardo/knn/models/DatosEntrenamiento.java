package ruben.eduardo.knn.models;

import ruben.eduardo.knn.interfaces.IRegistroClasificados;

import java.util.HashMap;
import java.util.LinkedList;

public class DatosEntrenamiento implements IRegistroClasificados {


    protected HashMap<LinkedList<Double>,String> elementos;


    public DatosEntrenamiento(HashMap<LinkedList<Double>,String> elementos){
          this.elementos = elementos;
    }

    @Override
    public HashMap<LinkedList<Double>,String> getElementos() {
        return elementos;
    }



}
