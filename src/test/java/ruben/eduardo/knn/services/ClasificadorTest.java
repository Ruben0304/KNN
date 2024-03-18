package ruben.eduardo.knn.services;

import org.junit.jupiter.api.Test;
import ruben.eduardo.knn.interfaces.ILectorFicheros;
import ruben.eduardo.knn.interfaces.IRegistro;
import ruben.eduardo.knn.models.DatosEntrenamiento;

import static org.junit.jupiter.api.Assertions.*;

class ClasificadorTest {

    private final ILectorFicheros c = new LectorFicheros();

    private final IRegistro registro = new DatosEntrenamiento(c.leerArchivo("D:/Proyectos/Java/Escuela/KNN/src/main/resources/ruben/eduardo/knn/Data/indicadores.csv",3));
    private final Clasificador clasificador = new Clasificador(registro);
    @Test
    void calcularRangoDeDatos() {

        clasificador.calcularRangoDeDatos(registro.getElementos().keySet()).forEach(System.out::println);
        assertTrue(true);
    }

    @Test
    void calcularDistancia() {

    assertEquals(0.0,clasificador.calcularDistancia(registro.getElementos().keySet().iterator().next(),registro.getElementos().keySet().iterator().next(),clasificador.calcularRangoDeDatos(registro.getElementos().keySet())));


    }
}