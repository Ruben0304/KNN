package ruben.eduardo.knn.models;

import ruben.eduardo.knn.interfaces.IRegistro;

import java.util.HashMap;
import java.util.LinkedList;

public class DatosEntrenamiento implements IRegistro {


    protected HashMap<LinkedList<Double>,String> elementos;


    public DatosEntrenamiento(HashMap<LinkedList<Double>,String> elementos){
          this.elementos = elementos;
    }

    @Override
    public HashMap<LinkedList<Double>,String> getElementos() {
        return elementos;
    }



}
