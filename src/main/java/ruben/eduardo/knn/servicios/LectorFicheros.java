package ruben.eduardo.knn.servicios;

import ruben.eduardo.knn.interfaces.ILectorFicheros;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LectorFicheros implements ILectorFicheros {

    protected String nombreArchivo;

    public LectorFicheros(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public HashMap<LinkedList<Double>, String> leerArchivo(int posicionClasificacion) {

        HashMap<LinkedList<Double>, String> listaValores = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {
                LinkedList<Double> valoresTemp = new LinkedList<>();
                String[] valores = linea.split(",");

                for (int i = 0; i < valores.length; i++) {
                    if (i != posicionClasificacion)
                        valoresTemp.addLast(Double.parseDouble(valores[i]));
                }
                listaValores.put(valoresTemp, valores[posicionClasificacion]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaValores;
    }

    @Override
    public LinkedList<LinkedList<Double>> leerArchivo() {

        LinkedList<LinkedList<Double>> listaValores = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {
                LinkedList<Double> valoresTemp = new LinkedList<>();
                String[] valores = linea.split(",");

                for (int i = 0; i < valores.length; i++) {
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
    public ArrayList<String> leerEncabezado() {
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

    @Override
    public int obtenerPosicionClasificacion() {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
                    br.readLine(); // 1ra
            linea = br.readLine(); // 2da
            String[] valores = linea.split(",");

            for (int i = 0; i < valores.length; i++)
                if (!valores[i].matches("-?\\d+(\\.\\d+)?"))
                    return i;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void mostrarClasificacionesComoCsv() {

        try {
            Desktop.getDesktop().open(new File("clasificado.csv"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
