package ruben.eduardo.knn.models;

import ruben.eduardo.knn.interfaces.IAlmacenador;
import ruben.eduardo.knn.interfaces.ILectorFicheros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class DatosEntrenamiento implements IAlmacenador{


    protected HashMap<LinkedList<Double>,String> elementos;
    protected ArrayList<String> encabezados;
    protected final ILectorFicheros servicioArchivos;
    protected final String nombreAchivo;


    public DatosEntrenamiento(ILectorFicheros servicioArchivos, String nombreArchivo){
          encabezados = new ArrayList<>();
          elementos = new HashMap<>();
          this.nombreAchivo = nombreArchivo;
          this.servicioArchivos = servicioArchivos;
          cargarEncabezados();
    }

    @Override
    public HashMap<LinkedList<Double>,String> getElementos() {
        return elementos;
    }

    @Override
    public  ArrayList<String>  getEncabezados() {
        return encabezados;
    }

    @Override
    public void cargarElementos(int posicionClasificacion) {
       elementos = servicioArchivos.leerArchivo(nombreAchivo, posicionClasificacion);
    }

    @Override
    public void cargarEncabezados() {
       encabezados = servicioArchivos.leerEncabezado(nombreAchivo);
    }
}
