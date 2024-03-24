package ruben.eduardo.knn.servicios;

import org.junit.jupiter.api.Test;
import ruben.eduardo.knn.servicios.clasificadores.ClasificadorAVL;
import ruben.eduardo.knn.servicios.clasificadores.ClasificadorLinked;
import ruben.eduardo.knn.servicios.clasificadores.ClasificadorLista;
import ruben.eduardo.knn.servicios.clasificadores.ClasificadorQeue;
import ruben.eduardo.knn.interfaces.*;
import ruben.eduardo.knn.modelos.DatosAClasificar;
import ruben.eduardo.knn.modelos.DatosEntrenamiento;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

class AverQuienLoHAceMasRapidoTest {

    private final ILectorFicheros c = new LectorFicheros("datosC.csv");

    private final ILectorFicheros cnc = new LectorFicheros("datosNC.csv");
    private final IRegistroNoClasificados registroNoClasificados = new DatosAClasificar(cnc.leerArchivo());

    private final IRegistroClasificados registro = new DatosEntrenamiento(c.leerArchivo(4));

    private IClasificador clasificador;



    @Test
    void clasificarNormalSinParalelo() {

        clasificador = new ClasificadorLista(registro);
        clasificador.clasificarNoParalelo(registroNoClasificados);
        assertTrue(true);
    }

    @Test
    void clasificarParalelo() {

        clasificador = new ClasificadorLista(registro);
        clasificador.clasificarConjunto(registroNoClasificados);
        assertTrue(true);
    }

    @Test
    void clasificarParaleloLinked() {

        clasificador = new ClasificadorLinked(registro);
        clasificador.clasificarConjunto(registroNoClasificados);
        assertTrue(true);
    }

    @Test
    void clasificarParaleloQueue() {

        clasificador = new ClasificadorQeue(registro);
        clasificador.clasificarConjunto(registroNoClasificados);
        assertTrue(true);
    }

    @Test
    void clasificarParaleloAvl() {

        clasificador = new ClasificadorAVL(registro);
        clasificador.clasificarConjunto(registroNoClasificados);
        assertTrue(true);
    }

    @Test
    void comprobarQueDanloMismo() {


        clasificador = new ClasificadorLista(registro);
        ConcurrentHashMap
                <LinkedList<Double>,
                        String> resultadoParaleloLista = (ConcurrentHashMap<LinkedList<Double>, String>) clasificador.clasificarConjunto(registroNoClasificados);


        clasificador = new ClasificadorQeue(registro);
        ConcurrentHashMap
                <LinkedList<Double>,
                        String> resultadoParaleloQueue = (ConcurrentHashMap<LinkedList<Double>, String>) clasificador.clasificarConjunto(registroNoClasificados);


        clasificador = new ClasificadorAVL(registro);
        ConcurrentHashMap
                <LinkedList<Double>,
                        String> resultadoParaleloAvl = (ConcurrentHashMap<LinkedList<Double>, String>) clasificador.clasificarConjunto(registroNoClasificados);


        clasificador = new ClasificadorLinked(registro);
        ConcurrentHashMap
                <LinkedList<Double>,
                        String> resultadoParaleloLinked = (ConcurrentHashMap<LinkedList<Double>, String>) clasificador.clasificarConjunto(registroNoClasificados);


        boolean sonIguales = resultadoParaleloLista.equals(resultadoParaleloAvl)
                && resultadoParaleloAvl.equals(resultadoParaleloQueue) && resultadoParaleloLista.equals(resultadoParaleloLinked);


        assertTrue(sonIguales, "Los métodos de clasificación deberían devolver el mismo resultado.");
    }




}