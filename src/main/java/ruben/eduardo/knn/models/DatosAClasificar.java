package ruben.eduardo.knn.models;

import ruben.eduardo.knn.interfaces.IRegistroNoClasificados;

import java.util.LinkedList;

public class DatosAClasificar implements IRegistroNoClasificados {

    protected LinkedList<LinkedList<Double>> elementos;

    public DatosAClasificar(LinkedList<LinkedList<Double>> elementos) {
        this.elementos = elementos;

    }

    @Override
    public LinkedList<LinkedList<Double>> getElementos() {
        return elementos;
    }
}
