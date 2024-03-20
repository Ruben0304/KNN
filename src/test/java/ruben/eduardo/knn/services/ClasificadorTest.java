package ruben.eduardo.knn.services;

import org.junit.jupiter.api.Test;
import ruben.eduardo.knn.avl.AvlTree;
import ruben.eduardo.knn.interfaces.ILectorFicheros;
import ruben.eduardo.knn.interfaces.IRegistroClasificados;
import ruben.eduardo.knn.interfaces.IRegistroNoClasificados;
import ruben.eduardo.knn.models.DatosAClasificar;
import ruben.eduardo.knn.models.DatosEntrenamiento;

import static org.junit.jupiter.api.Assertions.*;

class ClasificadorTest {

    private final ILectorFicheros c = new LectorFicheros("D:/Proyectos/Java/Escuela/KNN/src/main/resources/ruben/eduardo/knn/Data/datos.csv");

    private final ILectorFicheros cnc = new LectorFicheros("D:/Proyectos/Java/Escuela/KNN/src/main/resources/ruben/eduardo/knn/Data/datosNC.csv");
    private final IRegistroNoClasificados registroNoClasificados = new DatosAClasificar(cnc.leerArchivo());

    private final IRegistroClasificados registro = new DatosEntrenamiento(c.leerArchivo( 4));

    private final Clasificador clasificador = new Clasificador(registro);

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

    @Test
    void clasificar() {

        clasificador.clasificarParalelo(registroNoClasificados);
        assertTrue(true);
    }




    @Test
    void clasificarAvlProfile() {
     //   AvlTree<String> a = new AvlTree<>();

       // registroNoClasificados.getElementos().forEach(clasificador::clasificarAvl);
        clasificador.clasificarParaleloAvl(registroNoClasificados);
        assertTrue(true);
    }

    @Test
    void clasificarAvl() {
        assertEquals(clasificador.clasificarParaleloAvl(registroNoClasificados), clasificador.clasificarParalelo(registroNoClasificados));
    }
//
//
//
//    @Test
//    void clasificarConjunto() {
//
//        System.out.println( ((Clasificador)clasificador).clasificarParalelo(registroNoClasificados));
//        assertTrue(true);
//    }

}