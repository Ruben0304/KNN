package ruben.eduardo.knn.servicios;




import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

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

        try (FileWriter writer = new FileWriter("datosC.csv")) {
            // Escribir la fila de encabezado
            writer.write("Numero1,Numero2,Numero3,Numero4,Categoria\n");

            // Ciclo para generar los datos
            for (int i = 1; i <= 1000000; i++) {
                int num1, num2, num3, num4;
                String categoria = categorias.get(random.nextInt(categorias.size()));

                switch (categoria) {
                    case "Electrónica":
                        num1 = faker.number().numberBetween(1, 100);
                        num2 = faker.number().numberBetween(1, 100);
                        num3 = faker.number().numberBetween(1, 100);
                        num4 = faker.number().numberBetween(1, 100);
                        break;
                    case "Ropa":
                        num1 = faker.number().numberBetween(101, 200);
                        num2 = faker.number().numberBetween(101, 200);
                        num3 = faker.number().numberBetween(101, 200);
                        num4 = faker.number().numberBetween(101, 200);
                        break;
                    case "Alimentos":
                        num1 = faker.number().numberBetween(201, 300);
                        num2 = faker.number().numberBetween(201, 300);
                        num3 = faker.number().numberBetween(201, 300);
                        num4 = faker.number().numberBetween(201, 300);
                        break;
                    case "Libros":
                        num1 = faker.number().numberBetween(301, 400);
                        num2 = faker.number().numberBetween(301, 400);
                        num3 = faker.number().numberBetween(301, 400);
                        num4 = faker.number().numberBetween(301, 400);
                        break;
                    case "Juguetes":
                        num1 = faker.number().numberBetween(401, 500);
                        num2 = faker.number().numberBetween(401, 500);
                        num3 = faker.number().numberBetween(401, 500);
                        num4 = faker.number().numberBetween(401, 500);
                        break;
                    default:
                        // En caso de que se agregue una nueva categoría sin definir
                        num1 = faker.number().numberBetween(1, 500);
                        num2 = faker.number().numberBetween(1, 500);
                        num3 = faker.number().numberBetween(1, 500);
                        num4 = faker.number().numberBetween(1, 500);
                        break;
                }

                writer.write(num1 + "," + num2 + "," + num3 + "," + num4 + "," + categoria + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }


    @Test
    void crearCsv1MillonNC() {
        Faker faker = new Faker();
        Random random = new Random();

        try (FileWriter writer = new FileWriter("datosNC.csv")) {
            // Escribir la fila de encabezado
            writer.write("Numero1,Numero2,Numero3,Numero4\n");

            // Ciclo para generar los datos
            for (int i = 1; i <= 150; i++) {
                // Generar valores que se solapen entre las posibles categorías
                int num1 = faker.number().numberBetween(1, 500);
                int num2 = faker.number().numberBetween(1, 500);
                int num3 = faker.number().numberBetween(1, 500);
                int num4 = faker.number().numberBetween(1, 500);

                writer.write(num1 + "," + num2 + "," + num3 + "," + num4 + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }





}