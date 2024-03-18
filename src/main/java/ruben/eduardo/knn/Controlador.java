package ruben.eduardo.knn;

import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import ruben.eduardo.knn.interfaces.AnalizadorKNN;
import ruben.eduardo.knn.interfaces.IGeneradorMatrices;
import ruben.eduardo.knn.models.*;

public class Controlador {

//    AnalizadorKNN analizadorKNN;
//    IGeneradorMatrices generadorMatrices;
//    public Controlador() {
//        analizadorKNN = new AnalistaFinanciero();
//        generadorMatrices = new GeneradorMatrices(analizadorKNN);
//    }
//
//
//    @FXML
//    private ScatterChart<Number, Number> scatterChart;
//
//    @FXML
//    private Label lblApple;
//
//    @FXML
//    private Label lblBtc;
//
//    @FXML
//    private Label lblNetflix;
//
//    @FXML
//    private Label lblAmazon;
//
//    @FXML
//    private Label lblTesla;
//
//    @FXML
//    private Button ButtonAccion;
//
//    @FXML
//    private Button btnMatriz;
//
//    @FXML
//    private TextField macd;
//
//    @FXML
//    private TextField rsi;
//
//    @FXML
//    private TextField Momentum;
//
//
//    public void initialize(){
//        XYChart.Series<Number, Number> series1 = addRandomDataToChart();
//        ButtonAccion.setOnAction(event -> {
//            Double mo = Double.parseDouble(Momentum.getText()) ;
//            Double ma = Double.parseDouble(macd.getText()) ;
//            Double rs = Double.parseDouble(rsi.getText()) ;
//            Accion a = new Accion("prueba",123.0, new Indicador(mo,rs,ma,null));
//            a.getIndicador().setClasificacion(analizadorKNN.clasificarAccion(a));
//            // Crear un nuevo diálogo de alerta
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Clasificación");
//            alert.setHeaderText(null);
//            alert.setContentText(a.getIndicador().getClasificacion().toString());
//
//            addAcciontoChart(a, series1);
//            // Mostrar el diálogo y esperar hasta que el usuario lo cierre
//            alert.showAndWait();
//        });
//
//        btnMatriz.setOnAction(event ->{
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("");
//            alert.setHeaderText(null);
//            generadorMatrices.escribirMatrizEnFichero(generadorMatrices.generarMatriz());
//            alert.setContentText("Matriz generada corractamente");
//            alert.showAndWait();
//        });
//
//
//
//        for (Accion a : Bolsa.getAcciones()) {
//            a.getIndicador().setClasificacion(analizadorKNN.clasificarAccion(a));
//            Clasificacion c = a.getIndicador().getClasificacion();
//            String clase;
//            switch (c) {
//                case Comprar:
//                    clase = "success";
//                    break;
//                case Vender:
//                    clase = "danger";
//                    break;
//                default:
//                    clase = "warning";
//                    break;
//            }
//
//            switch (a.getNombre()) {
//                case "AAPL":
//                    lblApple.getStyleClass().add(clase);
//                    lblApple.setText(c.toString());
//                    break;
//                case "BTC":
//                    lblBtc.getStyleClass().add(clase);
//                    lblBtc.setText(c.toString());
//                    break;
//                case "NFLX":
//                    lblNetflix.getStyleClass().add(clase);
//                    lblNetflix.setText(c.toString());
//                    break;
//                case "AMZN":
//                    lblAmazon.getStyleClass().add(clase);
//                    lblAmazon.setText(c.toString());
//                    break;
//                case "TSLA":
//                    lblTesla.getStyleClass().add(clase);
//                    lblTesla.setText(c.toString());
//                    break;
//                default:
//                    // Manejar cualquier acción no especificada aquí
//                    break;
//            }
//            addAcciontoChart(a, series1);
//        }
//
//    }
//
//    private XYChart.Series<Number, Number> addRandomDataToChart() {
//
//
//        // Crear la primera serie de datos y agregar tus datos
//        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
//        series1.setName("Indicadores");
//        for (Indicador i : Bolsa.getIndicadores()) {
//            XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(i.Momentum, i.MACD);
//            Circle circle = new Circle();
//            circle.setRadius(i.RSI * 0.065 );
//            circle.setFill(i.getClasificacion().equals(Clasificacion.Comprar) ? Color.GREEN : (i.getClasificacion().equals(Clasificacion.Vender) ? Color.RED : Color.ORANGE));
//            dataPoint.setNode(circle);
//            series1.getData().add(dataPoint);
//        }
//
//
//
//        scatterChart.getData().addAll(series1);
//        return series1;
//    }
//
//
//    private void addAcciontoChart(Accion a , XYChart.Series<Number, Number> serie){
//        XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(a.getIndicador().Momentum, a.getIndicador().MACD);
//        Label label = new Label(a.getNombre().substring(0,2));
//        label.setPrefHeight(a.getIndicador().RSI*0.45);
//        dataPoint.setNode(label);
//        serie.getData().add(dataPoint);
//    }
}