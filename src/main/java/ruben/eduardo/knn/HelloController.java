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
        Random random = new Random();

        // Crear la primera serie de datos y agregar datos aleatorios
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.setName("Comprar"); // Opcional: establecer un nombre para la leyenda
        for (int i = 0; i < 5; i++) {
            series1.getData().add(new XYChart.Data<>(random.nextDouble() * 100, random.nextDouble() * 100));
        }

        // Crear la segunda serie de datos y agregar datos aleatorios
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series2.setName("Mantener");
        for (int i = 0; i < 5; i++) {
            series2.getData().add(new XYChart.Data<>(random.nextDouble() * 100, random.nextDouble() * 100));
        }

        // Crear la tercera serie de datos y agregar datos aleatorios
        XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
        series3.setName("Vender");
        for (int i = 0; i < 5; i++) {
            series3.getData().add(new XYChart.Data<>(random.nextDouble() * 100, random.nextDouble() * 100));
        }

        // Añadir las series al gráfico
        scatterChart.getData().addAll(series1, series2, series3);
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