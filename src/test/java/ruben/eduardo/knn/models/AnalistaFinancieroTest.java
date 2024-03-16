package ruben.eduardo.knn.models;

import org.junit.jupiter.api.Test;
import ruben.eduardo.knn.CargarDatos;

import static org.junit.jupiter.api.Assertions.*;

class AnalistaFinancieroTest {

    private Bolsa bolsa = new Bolsa(new CargarDatos());
    private  AnalistaFinanciero analistaFinanciero = new AnalistaFinanciero();

    @Test
    void calcularDistancia() {


        Indicador i1 = new Indicador(5,0,-1,null);
        Indicador i2 = new Indicador(2,0,3,null);
        Indicador i3 = new Indicador(10,2,9,null);
        assertTrue(analistaFinanciero.calcularDistancia(i1,i2) < analistaFinanciero.calcularDistancia(i1,i3));

    }

    @Test
    void clasificarAccion() {
        Indicador i = new Indicador(1.5, 1.1567879, 1.0,null);
        Accion a = new Accion("Debe dar comprar", 1.0, i);
        assertEquals(Clasificacion.Comprar,analistaFinanciero.clasificarAccion(a));


    }
}