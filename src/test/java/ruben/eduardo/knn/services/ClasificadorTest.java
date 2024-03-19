package ruben.eduardo.knn.services;

import org.junit.jupiter.api.Test;
import ruben.eduardo.knn.interfaces.ILectorFicheros;
import ruben.eduardo.knn.interfaces.IRegistroClasificados;
import ruben.eduardo.knn.interfaces.IRegistroNoClasificados;
import ruben.eduardo.knn.models.DatosAClasificar;
import ruben.eduardo.knn.models.DatosEntrenamiento;

import static org.junit.jupiter.api.Assertions.*;

class ClasificadorTest {

    private final ILectorFicheros c = new LectorFicheros();

    private final IRegistroNoClasificados registroNoClasificados = new DatosAClasificar(c.leerArchivo("C:/Users/Usuario/IdeaProjects/KNN/src/main/resources/ruben/eduardo/knn/Data/acciones.csv"));

    private final IRegistroClasificados registro = new DatosEntrenamiento(c.leerArchivo("C:/Users/Usuario/IdeaProjects/KNN/src/main/resources/ruben/eduardo/knn/Data/indicadores.csv", 3));

    private final Clasificador clasificador = new Clasificador(registro);

    @Test
    void calcularRangoDeDatos() {

        clasificador.calcularRangoDeDatos(registro.getElementos().keySet()).forEach(System.out::println);
        assertTrue(true);
    }

    @Test
    void calcularDistancia() {
        assertEquals(0.42629288198051535, clasificador.calcularDistancia(registro.getElementos().keySet().iterator().next(), registroNoClasificados.getElementos().getFirst()));

    }

    @Test
    void clasificar() {
        assertEquals("Mantener",clasificador.clasificar(registroNoClasificados.getElementos().getFirst()));
    }

    @Test
    void clasificarConjunto() {

       System.out.println( clasificador.clasificar(registroNoClasificados));
        assertTrue(true);
    }
}