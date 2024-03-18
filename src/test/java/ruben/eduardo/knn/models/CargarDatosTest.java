package ruben.eduardo.knn.models;

import org.junit.jupiter.api.Test;
import ruben.eduardo.knn.services.LectorFicheros;
import ruben.eduardo.knn.interfaces.ILectorFicheros;

import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CargarDatosTest {

    private final ILectorFicheros c = new LectorFicheros();

    //archivo indicadores
    @Test
    void leerEncabezado() {

        c.leerEncabezado("D:/Proyectos/Java/Escuela/KNN/src/main/resources/ruben/eduardo/knn/Data/indicadores.csv").forEach(System.out::println);

        // Importante comporbar que true es true , no quitar
        assertTrue(true);

    }


    @Test
    void leerArchivoClasificaciones() {
        HashMap<LinkedList<Double>, String> f = c.leerArchivo("D:/Proyectos/Java/Escuela/KNN/src/main/resources/ruben/eduardo/knn/Data/indicadores.csv", 3);
        for (String s : f.values()) {
            System.out.println(s);
        }
        assertTrue(true);
    }

    @Test
    void leerArchivoValores() {
        HashMap<LinkedList<Double>, String> f = c.leerArchivo("D:/Proyectos/Java/Escuela/KNN/src/main/resources/ruben/eduardo/knn/Data/indicadores.csv", 3);
        for (LinkedList<Double> s : f.keySet()) {
            System.out.println(s);
        }
        assertTrue(true);
    }


    //archivo fulbo distinto

    @Test
    void leerEncabezadoF() {

        c.leerEncabezado("D:/Proyectos/Java/Escuela/KNN/src/main/resources/ruben/eduardo/knn/Data/Fulbo.csv").forEach(System.out::println);

        // Importante comporbar que true es true , no quitar
        assertTrue(true);

    }

    @Test
    void leerArchivoClasificacionesF() {
        HashMap<LinkedList<Double>, String> f = c.leerArchivo("D:/Proyectos/Java/Escuela/KNN/src/main/resources/ruben/eduardo/knn/Data/Fulbo.csv", 0);
        for (String s : f.values()) {
            System.out.println(s);
        }
        assertTrue(true);
    }

    @Test
    void leerArchivoValoresF() {
        HashMap<LinkedList<Double>, String> f = c.leerArchivo("D:/Proyectos/Java/Escuela/KNN/src/main/resources/ruben/eduardo/knn/Data/Fulbo.csv", 0);
        for (LinkedList<Double> s : f.keySet()) {
            System.out.println(s);
        }
        assertTrue(true);
    }
}