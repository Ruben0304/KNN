package ruben.eduardo.knn.interfaces;

import java.util.LinkedList;
import java.util.Map;

public interface IGeneradorFicheros {
    void exportarClasificacionesACsv(Map<LinkedList<Double>, String> clasificaciones, String nombreArchivo);

    void exportarMatrizArchivoBinario(double[][] matriz, String nombreArchivo);
}
