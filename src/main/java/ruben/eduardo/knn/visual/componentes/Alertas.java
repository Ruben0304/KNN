package ruben.eduardo.knn.visual.componentes;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import ruben.eduardo.knn.servicios.LectorFicheros;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class Alertas {

    public static void alertaMatriz(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText("Matriz generada correctamente");

        ButtonType buttonTypeVer = new ButtonType("Ver matriz", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeCerrar = new ButtonType("Cerrar", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonTypeVer, buttonTypeCerrar);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeVer) {
            // Aquí puedes abrir el archivo binario para ver la matriz
            try {
                Desktop.getDesktop().open(new File("matrizDistancia.bin"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void alertaTextAreaErroneo() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Cantidad de valores errónea");

        alert.showAndWait();
    }

    public static void alertaTextAreaSoloNumeros() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Solo se aceptan valores numéricos");

        alert.showAndWait();
    }

    public static void resultadoClasificacion(String resultadoClasificacion) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado de la Clasificación");
        alert.setHeaderText(null);
        alert.setContentText("El resultado de la clasificación es: " + resultadoClasificacion);

        alert.showAndWait();

    }

    public static void resultadoClasificacionConjunto() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Proceso Completado");
        alert.setHeaderText(null);
        alert.setContentText("Datos clasificados correctamente");
        ButtonType verCsvButtonType = new ButtonType("Ver CSV");
        alert.getButtonTypes().setAll(ButtonType.OK, verCsvButtonType);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == verCsvButtonType) {

            LectorFicheros.mostrarClasificacionesComoCsv();
        }

    }

    public static void nohayRegistros() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("No se han seleccionado los registros");

        alert.showAndWait();

    }










}
