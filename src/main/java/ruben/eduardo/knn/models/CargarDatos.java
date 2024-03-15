package ruben.eduardo.knn.models;

import ruben.eduardo.knn.Main;
import ruben.eduardo.knn.interfaces.Cargador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CargarDatos implements Cargador {
    @Override
    public LinkedList<Indicador> cargarIndicadores() {
        LinkedList<Indicador> listaIndicadores = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("D:/Proyectos/Java/Escuela/KNN/src/main/resources/ruben/eduardo/knn/Data/indicadores.csv"))) {
            String linea;
            br.readLine(); // Esto es para saltar la línea del encabezado
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");
                // Asegúrate de convertir y asignar correctamente los valores a las variables
                double volumen = Double.parseDouble(valores[0]);
                double rsi = Double.parseDouble(valores[1]);
                double macd = Double.parseDouble(valores[2]);
                String clasificacion = valores[3];


                // Crear una nueva instancia de Accion y añadirla a la lista
                Indicador i  = new Indicador(volumen,rsi,macd,Clasificacion.valueOf(clasificacion));
                listaIndicadores.add(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaIndicadores;
    }

    @Override
    public List<Accion> cargarAcciones() {
        List<Accion> listaAcciones = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CargarDatos.class.getResource("Data/acciones.csv").toString()))) {
            String linea;
            br.readLine(); // Esto es para saltar la línea del encabezado
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");
                // Asegúrate de convertir y asignar correctamente los valores a las variables
                String nombre = valores[0];
                double precio = Double.parseDouble(valores[1]);
                double volumen = Double.parseDouble(valores[2]);
                double rsi = Double.parseDouble(valores[3]);
                double macd = Double.parseDouble(valores[4]);


                // Crear una nueva instancia de Accion y añadirla a la lista
                Accion accion = new Accion(nombre,precio,new Indicador(volumen,rsi,macd,null));
                listaAcciones.add(accion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaAcciones;
    }
}
