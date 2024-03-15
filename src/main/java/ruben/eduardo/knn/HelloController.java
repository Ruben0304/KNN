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

    public void initialize() {
        // Añadir columnas a la tabla si no están definidas en FXML
        TableColumn<Cotizacion, String> columnaIndice = new TableColumn<>("Indice");
        TableColumn<Cotizacion, Number> columnaPrecio = new TableColumn<>("Precio (USD)");
        TableColumn<Cotizacion, String> columnaRecomendacion = new TableColumn<>("Recomendación");

        // Configurar cómo se mostrarán los datos en las columnas
        columnaIndice.setCellValueFactory(new PropertyValueFactory<>("indice"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columnaRecomendacion.setCellValueFactory(new PropertyValueFactory<>("recomendacionML"));

        // Añadir las columnas a la tabla si no están definidas en FXML
        tablaCotizaciones.getColumns().addAll(columnaIndice, columnaPrecio, columnaRecomendacion);

        // Crear una lista observable para los datos de la tabla
        ObservableList<Cotizacion> datos = FXCollections.observableArrayList();

        // Añadir datos a la lista (aquí puedes obtener los datos reales de las cotizaciones)
        datos.addAll(
                new Cotizacion("BTC", 50000, obtenerRecomendacionAleatoria()), // Bitcoin
                new Cotizacion("Netflix", 350, obtenerRecomendacionAleatoria()), // Netflix
                new Cotizacion("AMD", 207.39, obtenerRecomendacionAleatoria()), // Advanced Micro Devices
                new Cotizacion("NVDA", 875.28, obtenerRecomendacionAleatoria()), // NVIDIA Corp
                new Cotizacion("TSLA", 175.34, obtenerRecomendacionAleatoria()), // Tesla Inc
                new Cotizacion("AAPL", 170.73, obtenerRecomendacionAleatoria()), // Apple Inc
                new Cotizacion("AMZN", 175.35, obtenerRecomendacionAleatoria()) // Amazon.com Inc
        );


        // Asignar los datos a la tabla
      //  tablaCotizaciones.setItems(datos);

        // Personalizar el color de la celda de recomendación según el valor
//        columnaRecomendacion.setCellFactory(column -> new TableCell<>() {
//            @Override
//            protected void updateItem(String item, boolean empty) {
//                super.updateItem(item, empty);
//                if (item == null || empty) {
//                    setText(null);
//                //    setStyle("");
//                } else {
//                    setText(item);
//                //    setStyle("-fx-background-color: " + obtenerColorRecomendacion(item));
//                }
//            }
//        });
    }

    private String obtenerRecomendacionAleatoria() {
        String[] recomendaciones = {"Comprar", "Vender", "Mantener"};
        return recomendaciones[new Random().nextInt(recomendaciones.length)];
    }

    private String obtenerColorRecomendacion(String recomendacion) {
        switch (recomendacion) {
            case "Comprar":
                return "#00FF00"; // Verde
            case "Vender":
                return "#FF0000"; // Rojo
            case "Mantener":
                return "#FFA500"; // Naranja
            default:
                return "#FFFFFF"; // Blanco por defecto
        }
    }


}