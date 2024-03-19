package ruben.eduardo.knn.services;

import org.junit.jupiter.api.Test;
import ruben.eduardo.knn.interfaces.ILectorFicheros;
import ruben.eduardo.knn.interfaces.IRegistroClasificados;
import ruben.eduardo.knn.interfaces.IRegistroNoClasificados;
import ruben.eduardo.knn.models.DatosAClasificar;
import ruben.eduardo.knn.models.DatosEntrenamiento;

import static org.junit.jupiter.api.Assertions.*;

class ClasificadorTest {

    private final ILectorFicheros c = new LectorFicheros("D:/Proyectos/Java/Escuela/KNN/src/main/resources/ruben/eduardo/knn/Data/indicadores.csv");

//    private final IRegistroNoClasificados registroNoClasificados = new DatosAClasificar(c.leerArchivo("D:/Proyectos/Java/Escuela/KNN/src/main/resources/ruben/eduardo/knn/Data/datosNC.csv"));
//
//    private final IRegistroClasificados registro = new DatosEntrenamiento(c.leerArchivo("D:/Proyectos/Java/Escuela/KNN/src/main/resources/ruben/eduardo/knn/Data/datos.csv", 4));
//
//    private final Clasificador clasificador = new Clasificador(registro);
//
//    @Test
//    void calcularRangoDeDatos() {
//
//
//       // registro.getElementos().keySet().forEach(System.out::println);
//        clasificador.calcularRangoDeDatos(registro.getElementos().keySet()).forEach(System.out::println);
//        assertTrue(true);
//    }
//
//    @Test
//    void calcularDistancia() {
//        assertEquals(0.42629288198051535, clasificador.calcularDistancia(registro.getElementos().keySet().iterator().next(), registroNoClasificados.getElementos().getFirst()));
//
//    }
//
//
//    @Test
//    void clasificar() {
//        assertEquals("Juguetes",clasificador.clasificar(registroNoClasificados.getElementos().getFirst()));
//    }
//
//    @Test
//    void clasificarConjunto() {
//
//        System.out.println( ((Clasificador)clasificador).clasificarParalelo(registroNoClasificados));
//        assertTrue(true);
//    }

}