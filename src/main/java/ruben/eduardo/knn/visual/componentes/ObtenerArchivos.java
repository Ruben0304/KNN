package ruben.eduardo.knn.visual.componentes;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.FileChooser;

import java.io.File;

public class ObtenerArchivos {
    public static String obtenerDireccionArchivo(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Archivo");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV", "*.csv")
        );

        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());


        return selectedFile.getAbsolutePath();
    }
}
