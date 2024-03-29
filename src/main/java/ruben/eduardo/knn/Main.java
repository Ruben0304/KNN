package ruben.eduardo.knn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ruben.eduardo.knn.interfaces.ILectorFicheros;
import ruben.eduardo.knn.servicios.LectorFicheros;
import ruben.eduardo.knn.visual.Controlador;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("KNN");


        scene.getStylesheets().add(Main.class.getResource("assets/cupertino-dark.css").toString());
        scene.getStylesheets().add(Main.class.getResource("assets/Personalizado.css").toString());
        Controlador controller = fxmlLoader.getController();


        stage.setScene(scene);
        // Establecer dimensiones mínimas
        stage.setMinWidth(1050);
        stage.setMinHeight(800);

        // Desactivar el redimensionamiento
        stage.setResizable(false);

        // Obtener las dimensiones de la pantalla
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Calcular la posición central
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
