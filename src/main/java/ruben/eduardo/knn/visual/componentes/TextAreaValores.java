package ruben.eduardo.knn.visual.componentes;

import javafx.scene.control.TextArea;
import ruben.eduardo.knn.servicios.LectorFicheros;

import java.awt.*;
import java.util.LinkedList;

public class TextAreaValores {

    public static LinkedList<Double> obtenerDatosDelTextArea(TextArea txtAreaClasifUnEelemnt, String direccionArchivoClasificado) {

        LectorFicheros lectorFicheros = new LectorFicheros(direccionArchivoClasificado);

        int numColumnas = lectorFicheros.leerEncabezado().size();

        LinkedList<Double> listaDatos = new LinkedList<>();

        String texto = txtAreaClasifUnEelemnt.getText();


        String[] valores = texto.split(",");


        if (valores.length != numColumnas - 1) {
            Alertas.alertaTextAreaErroneo();
            return null;
        }


        for (String valor : valores) {
            try {
                double numero = Double.parseDouble(valor.trim());
                listaDatos.add(numero);
            } catch (NumberFormatException e) {
                Alertas.alertaTextAreaSoloNumeros();
                return null;
            }
        }

        return listaDatos;
    }
}
