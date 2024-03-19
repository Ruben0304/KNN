package ruben.eduardo.knn.services;

import ruben.eduardo.knn.interfaces.ILectorFicheros;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class LectorFicheros implements ILectorFicheros {
    @Override
    public HashMap<LinkedList<Double>, String> leerArchivo(String nombreArchivo, int posicionClasificacion) {

        HashMap<LinkedList<Double>, String> listaValores = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {
                LinkedList<Double> valoresTemp = new LinkedList<>();
                String[] valores = linea.split(",");

                for (int i = 0; i < valores.length ; i++) {
                    if (i != posicionClasificacion)
                     valoresTemp.addLast(Double.parseDouble(valores[i]));
                }
                listaValores.put(valoresTemp,valores[posicionClasificacion]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaValores;
    }

    @Override
    public LinkedList<LinkedList<Double>> leerArchivo(String nombreArchivo) {

        LinkedList<LinkedList<Double>> listaValores = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {
                LinkedList<Double> valoresTemp = new LinkedList<>();
                String[] valores = linea.split(",");

                for (int i = 0; i < valores.length ; i++) {
                        valoresTemp.addLast(Double.parseDouble(valores[i]));
                }
                listaValores.addLast(valoresTemp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaValores;
    }

    @Override
    public ArrayList<String> leerEncabezado(String nombreArchivo) {
        ArrayList<String> encabezados = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            linea = br.readLine();
            String[] valores = linea.split(",");
            encabezados.addAll(Arrays.asList(valores));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return encabezados;
    }
}
