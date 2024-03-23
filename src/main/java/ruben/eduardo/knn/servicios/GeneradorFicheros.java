package ruben.eduardo.knn.servicios;

import ruben.eduardo.knn.interfaces.IGeneradorFicheros;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GeneradorFicheros implements IGeneradorFicheros {
    public void exportarClasificacionesACsv(Map<LinkedList<Double>, String> clasificaciones, String nombreArchivo) {

        StringBuilder csvBuilder = new StringBuilder();
        // Definir el encabezado del CSV
        try (Stream<String> lines = Files.lines(Paths.get("clasificado.csv"))) {
            String header = lines.findFirst().orElse("");
            csvBuilder.append(header).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Iterar sobre el ConcurrentHashMap y construir el contenido del CSV
        for (Map.Entry<LinkedList<Double>, String> entrada : clasificaciones.entrySet()) {
            // Convertir la clave (LinkedList) a String
            String claveComoCadena = entrada.getKey().stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(","));
            // Agregar la clave y el valor al StringBuilder
            csvBuilder.append(claveComoCadena)
                    .append(",")
                    .append(entrada.getValue())
                    .append("\n");
        }

        // Escribir el contenido del StringBuilder en un archivo CSV
        try (PrintWriter out = new PrintWriter("clasificado.csv")) {
            out.println(csvBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void exportarMatrizArchivoBinario(double[][] matriz, String nombreArchivo) {
        try {
            FileOutputStream fos = new FileOutputStream(nombreArchivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // Escribir las dimensiones de la matriz
            oos.writeInt(matriz.length); // Número de filas
            oos.writeInt(matriz[0].length); // Número de columnas

            // Escribir los elementos de la matriz
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    oos.writeDouble(matriz[i][j]);
                }
            }

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
