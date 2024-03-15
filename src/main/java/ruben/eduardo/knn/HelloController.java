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

public class HelloController  {


    @FXML
    private ScatterChart<Number, Number> scatterChart;

    @FXML
    private TableView<Cotizacion> tablaCotizaciones;



    public void addRandomDataToChart() {
        // Datos de ejemplo, reemplaza esto con tus datos reales
        double[][] datosComprar = {
                {4407489, 65, 1.20},
                {1297060, 75, 1.50},
                {1131490, 50, 0.85},
                {2000000, 62, 1.00},
                {3500000, 70, 1.25},
                {2750000, 68, 1.10},
                {3250000, 72, 1.40},
                {1500000, 63, 1.05},
                {1750000, 67, 1.15},
                {2250000, 69, 1.30},
                {3800000, 71, 1.35},
                {4000000, 74, 1.45},
                {4200000, 76, 1.55},
                {4500000, 78, 1.65},
                {4700000, 80, 1.75}
        };

        double[][] datosMantener = {
                {5537623, 60, -0.30},
                {2137890, 60, 0.10},
                {4500000, 55, -0.05},
                {5600000, 58, 0.00},
                {4400000, 57, -0.10},
                {2300000, 59, 0.05},
                {5200000, 56, -0.20},
                {4100000, 54, -0.15},
                {5700000, 53, 0.20},
                {2800000, 52, 0.25},
                {1900000, 51, 0.30},
                {4100000, 50, 0.35},
                {6100000, 49, 0.40},
                {5200000, 48, 0.45},
                {3300000, 47, 0.50}
        };

        double[][] datosVender = {
                {1860866, 30, -0.75},
                {1260866, 20, -0.65},
                {3000000, 25, 1.10},
                {3100000, 35, -0.80},
                {2200000, 40, -0.85},
                {1300000, 45, -0.90},
                {3400000, 50, -0.95},
                {3500000, 55, -1.00},
                {1600000, 28, -0.70},
                {3700000, 22, -0.60},
                {2800000, 18, -0.55},
                {3900000, 16, -0.50},
                {2000000, 14, -0.45},
                {4100000, 12, -0.40},
                {4200000, 10, -0.35}
        };


        // ... datos para "Vender" ...

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

        scatterChart.getData().addAll(series1, series2,series3);
    }

   


}