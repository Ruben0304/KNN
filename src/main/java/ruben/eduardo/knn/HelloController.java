package ruben.eduardo.knn;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ruben.eduardo.knn.models.Cotizacion;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController {


    @FXML
    private ScatterChart<Number, Number> scatterChart;

    @FXML
    private TableView<Cotizacion> tablaCotizaciones;


    public void addRandomDataToChart() {


        // Crear la primera serie de datos y agregar tus datos
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.setName("Comprar");
        for (double[] dato : datosComprar) {
            XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(dato[1], dato[2]);
            Circle circle = new Circle();
            circle.setRadius(dato[0] * 0.000002); // Ajusta el factor de escala según sea necesario
            circle.setFill(Color.GREEN);
            dataPoint.setNode(circle);
            series1.getData().add(dataPoint);
        }

        // Crear la segunda serie de datos y agregar tus datos
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series2.setName("Mantener");
        for (double[] dato : datosMantener) {
            XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(dato[1], dato[2]);
            Circle circle = new Circle();
            circle.setRadius(dato[0] * 0.000002); // Ajusta el factor de escala según sea necesario
            circle.setFill(Color.ORANGE);
            dataPoint.setNode(circle);
            series2.getData().add(dataPoint);
        }

        // Crear la segunda serie de datos y agregar tus datos
        XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
        series3.setName("Vender");
        for (double[] dato : datosVender) {
            XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(dato[1], dato[2]);
            Circle circle = new Circle();
            circle.setRadius(dato[0] * 0.000002); // Ajusta el factor de escala según sea necesario
            circle.setFill(Color.RED);
            dataPoint.setNode(circle);
            series3.getData().add(dataPoint);
        }

        scatterChart.getData().addAll(series1, series2, series3);
    }


}