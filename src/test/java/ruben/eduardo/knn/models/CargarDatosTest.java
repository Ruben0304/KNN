package ruben.eduardo.knn.models;

import org.junit.jupiter.api.Test;
import ruben.eduardo.knn.CargarDatos;
import ruben.eduardo.knn.interfaces.Cargador;

import static org.junit.jupiter.api.Assertions.*;

class CargarDatosTest {

    private final Cargador c = new CargarDatos();
    @Test
    void cargarIndicadores() {
        assertEquals(45,c.cargarIndicadores().size());
    }

    @Test
    void cargarAcciones() {
        assertEquals(5, c.cargarAcciones().size());
    }
}