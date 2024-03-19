package ruben.eduardo.knn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ruben.eduardo.knn.interfaces.*;
import ruben.eduardo.knn.models.*;
import ruben.eduardo.knn.services.Clasificador;
import ruben.eduardo.knn.services.LectorFicheros;

import java.io.File;

public class Controlador {



    @FXML
    private Pane root;
    @FXML
    private Button btnEntrenar;
    @FXML
    private ScatterChart scatterChart;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private Button btnSelecArchClasificado;
    @FXML
    private Button btnSelecArchSinClasif;
    @FXML
    private Button btnClasificar;

    private IRegistroClasificados registroClasificados;

    private IRegistroNoClasificados registroNoClasificados;
    private Clasificador clasificador;


    private String direccionArchivoClasificado;
    private String direccionArchivoNoClasificado;




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
//   private Button btnMatriz;
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
//   public void initialize(){
//       btnMatriz.setOnAction(e -> {
//           FileChooser filechooser = new FileChooser();
//           filechooser.setTitle("Open File");
//           File selected = filechooser.showOpenDialog(ge);
//       });

    public void handleOpenFileActionArchClasific(ActionEvent event) {
        direccionArchivoClasificado = obtenerDireccionArchivo(event);
        btnSelecArchClasificado.setDisable(true);

        if (direccionArchivoClasificado != null && direccionArchivoNoClasificado != null){
            btnEntrenar.setDisable(false);
        }
    }


    public void handleOpenFileActionArchNoClasific(ActionEvent event) {
        direccionArchivoNoClasificado = obtenerDireccionArchivo(event);
        btnSelecArchSinClasif.setDisable(true);

        if (direccionArchivoClasificado != null && direccionArchivoNoClasificado != null){
            btnEntrenar.setDisable(false);
        }
    }


    private String obtenerDireccionArchivo (ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Archivo");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV", "*.csv")
        );

        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());


        return selectedFile.getAbsolutePath();
    }

    public void intentarClasificar(ActionEvent event) {
        LectorFicheros lectorFicheros = new LectorFicheros(direccionArchivoNoClasificado);
        registroNoClasificados = new DatosAClasificar(lectorFicheros.leerArchivo());
//        clasificador.clasificarParalelo(registroNoClasificados);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Clasificación");
            alert.setHeaderText(null);
            alert.setContentText(clasificador.clasificarParalelo(registroNoClasificados).toString());


            // Mostrar el diálogo y esperar hasta que el usuario lo cierre
            alert.showAndWait();


    }

    public void intentarEntrenar(ActionEvent event) {

        LectorFicheros lectorFicheros = new LectorFicheros(direccionArchivoClasificado);

        int posicionClasif = lectorFicheros.obtenerPosicionClasificacion();
        registroClasificados = new DatosEntrenamiento(lectorFicheros.leerArchivo(posicionClasif));
        clasificador = new Clasificador(registroClasificados);

        btnClasificar.setDisable(false);



    }

//       button2.setOnAction(e -> {
//           FileChooser filechooser = new FileChooser();
//           filechooser.setTitle("Save File");
//           File selected = filechooser.showSaveDialog(primaryStage);
//       });

//       VBox layout = new VBox(btnMatriz);
//       layout.setAlignment(Pos.CENTER);
//       layout.setSpacing(25);
//
//   }
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