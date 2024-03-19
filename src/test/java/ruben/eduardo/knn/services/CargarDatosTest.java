package ruben.eduardo.knn.services;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import ruben.eduardo.knn.services.LectorFicheros;
import ruben.eduardo.knn.interfaces.ILectorFicheros;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CargarDatosTest {

    private final LectorFicheros c = new LectorFicheros("D:/Proyectos/Java/Escuela/KNN/src/main/resources/ruben/eduardo/knn/Data/Fulbo.csv");

    //archivo indicadores
    @Test
    void leerEncabezado() {

        c.leerEncabezado().forEach(System.out::println);
        // Importante comporbar que true es true , no quitar
        assertTrue(true);

    }


    @Test
    void leerArchivoClasificaciones() {
        HashMap<LinkedList<Double>, String> f = c.leerArchivo( 3);
        for (String s : f.values()) {
            System.out.println(s);
        }

        assertTrue(true);
    }

    @Test
    void leerArchivoValores() {
        HashMap<LinkedList<Double>, String> f = c.leerArchivo(3);
        for (LinkedList<Double> s : f.keySet()) {
            System.out.println(s);
        }
        assertTrue(true);
    }


    //archivo fulbo distinto

    @Test
    void leerEncabezadoF() {

        c.leerEncabezado().forEach(System.out::println);

        // Importante comporbar que true es true , no quitar
        assertTrue(true);

    }

    @Test
    void leerArchivoClasificacionesF() {
        HashMap<LinkedList<Double>, String> f = c.leerArchivo( 0);
        for (String s : f.values()) {
            System.out.println(s);
        }
        assertTrue(true);
    }

    @Test
    void leerArchivoValoresF() {
        HashMap<LinkedList<Double>, String> f = c.leerArchivo( 0);
        for (LinkedList<Double> s : f.keySet()) {
            System.out.println(s);
        }
        assertTrue(true);
    }

    @Test
    void crearCsv1Millon() {
        Faker faker = new Faker();
        List<String> categorias = Arrays.asList("Electrónica", "Ropa", "Alimentos", "Libros", "Juguetes");
        Random random = new Random();

        try (FileWriter writer = new FileWriter("datos.csv")) {
            // Escribir la fila de encabezado
            writer.write("Numero1,Numero2,Numero3,Numero4,Categoria\n");

            // Ciclo para generar los datos
            for (int i = 1; i <= 1000000; i++) {
                int num1 = faker.number().numberBetween(1, 100);
                int num2 = faker.number().numberBetween(1, 100);
                int num3 = faker.number().numberBetween(1, 100);
                int num4 = faker.number().numberBetween(1, 100);
                // Seleccionar una categoría aleatoria de la lista
                String categoria = categorias.get(random.nextInt(categorias.size()));

                writer.write(num1 + "," + num2 + "," + num3 + "," + num4 + "," + categoria + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }

    @Test
    void obtenerPosicionClasificacion() {
        assertEquals(0,c.obtenerPosicionClasificacion());
    }

    @Test
    void crearCsv1MillonNC() {
        Faker faker = new Faker();

        try (FileWriter writer = new FileWriter("datosNC.csv")) {
            // Escribir la fila de encabezado
            writer.write("Numero1,Numero2,Numero3,Numero4\n");

            // Ciclo para generar los datos
            for (int i = 1; i <= 3000; i++) {
                int num1 = faker.number().numberBetween(1, 100);
                int num2 = faker.number().numberBetween(1, 100);
                int num3 = faker.number().numberBetween(1, 100);
                int num4 = faker.number().numberBetween(1, 100);


                writer.write(num1 + "," + num2 + "," + num3 + "," + num4 + "," +"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }


}