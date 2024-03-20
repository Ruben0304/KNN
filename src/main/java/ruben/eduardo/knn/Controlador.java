package ruben.eduardo.knn;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
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

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.awt.Desktop;
import java.util.stream.Stream;
import javax.swing.JOptionPane;

public class Controlador {


    @FXML
    public ProgressIndicator progress;
    @FXML
    public ProgressIndicator progressVerde;
    public TextArea txtAreaClasifUnEelemnt;
    public Button btnClasificar1Elemnt;
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

    private int totalElementos = 0;
    private int elementosProcesados = 0;
    private String direccionArchivoClasificado;
    private String direccionArchivoNoClasificado;


    private LinkedList<Double> listaDatos = new LinkedList<>();


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

    public void initialize() {
        txtAreaClasifUnEelemnt.setStyle("-fx-control-inner-background: #1c1c1e");

    }

    public LinkedList<Double> obtenerDatosDelTextArea() {

        LectorFicheros lectorFicheros = new LectorFicheros(direccionArchivoClasificado);

        int numColumnas = lectorFicheros.leerEncabezado().size();

        LinkedList<Double> listaDatos = new LinkedList<>();
        // Obtener el texto del TextArea
        String texto = txtAreaClasifUnEelemnt.getText();

        // Dividir el texto en valores separados por comas
        String[] valores = texto.split(",");

        // Verificar si el número de valores es igual al número de columnas en el archivo CSV
        if (valores.length != numColumnas - 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Cantidad de valores errónea");

            alert.showAndWait();
            return null;
        }

        // Convertir cada valor a Double y añadirlo a la lista
        for (String valor : valores) {
            try {
                double numero = Double.parseDouble(valor.trim());
                listaDatos.add(numero);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Solo se aceptan valores numéricos");

                alert.showAndWait();
                return null;
            }
        }

        return listaDatos;
    }

    public void intentarClasificar1element(ActionEvent actionEvent) {
        LinkedList<Double> lista = obtenerDatosDelTextArea();

        if (lista!=null) {
            String resultadoClasificacion = clasificador.clasificar(lista);



            // Mostrar un Alert con el resultado de la clasificación
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resultado de la Clasificación");
            alert.setHeaderText(null);
            alert.setContentText("El resultado de la clasificación es: " + resultadoClasificacion);

            alert.showAndWait();
        }


    }

    public void handleOpenFileActionArchClasific(ActionEvent event) {
        direccionArchivoClasificado = obtenerDireccionArchivo(event);
        btnSelecArchClasificado.setDisable(true);

        txtAreaClasifUnEelemnt.setDisable(false);
        btnClasificar1Elemnt.setDisable(false);

        if (direccionArchivoClasificado != null) {
            btnEntrenar.setDisable(false);
        }

    }


    public void handleOpenFileActionArchNoClasific(ActionEvent event) {
        direccionArchivoNoClasificado = obtenerDireccionArchivo(event);
        btnSelecArchSinClasif.setDisable(true);
    }


    private String obtenerDireccionArchivo(ActionEvent event) {
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
        iniciarClasificacion();


    }

    public void intentarEntrenar(ActionEvent event) {

        LectorFicheros lectorFicheros = new LectorFicheros(direccionArchivoClasificado);

        int posicionClasif = lectorFicheros.obtenerPosicionClasificacion();
        registroClasificados = new DatosEntrenamiento(lectorFicheros.leerArchivo(posicionClasif));
        clasificador = new Clasificador(registroClasificados);

        btnClasificar1Elemnt.setDisable(false);
        progressVerde.setProgress(1.0);


        if (registroClasificados!=null && registroNoClasificados!=null)
            btnClasificar.setDisable(false);


    }

    public void iniciarClasificacion() {


        progress.setProgress(0);

        totalElementos = registroNoClasificados.getElementos().size();
        Task<ConcurrentHashMap<LinkedList<Double>, String>> task = new Task<ConcurrentHashMap<LinkedList<Double>, String>>() {
            @Override
            protected ConcurrentHashMap<LinkedList<Double>, String> call() throws Exception {

                ConcurrentHashMap<LinkedList<Double>, String> clasificaciones = new ConcurrentHashMap<>();
                ForkJoinPool customThreadPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
                customThreadPool.submit(() ->
                        registroNoClasificados.getElementos().parallelStream().forEach(c -> {
                            clasificaciones.put(c, clasificador.clasificar(c));
                            elementosProcesados++;
                            Platform.runLater(() -> progress.setProgress((double) elementosProcesados / totalElementos));

                        })
                ).join();

                return clasificaciones;
            }
        };


        task.setOnSucceeded(e -> {
            progress.setProgress(1.0);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Proceso Completado");
            alert.setHeaderText(null);
            alert.setContentText("Datos analizados correctamente");


            ButtonType verCsvButtonType = new ButtonType("Ver CSV");
            alert.getButtonTypes().setAll(ButtonType.OK, verCsvButtonType);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == verCsvButtonType) {

                mostrarClasificacionesComoCsv(task.getValue());
            }
        });
        task.setOnFailed(e -> progress.setProgress(0));

        new Thread(task).start();
    }

    private void mostrarClasificacionesComoCsv(ConcurrentHashMap<LinkedList<Double>, String> clasificaciones) {

        exportarClasificacionesACsv(clasificaciones);

        // abrir el archivo con la aplicación predeterminada del sistema
        try {
            Desktop.getDesktop().open(new File("C:/Users/Usuario/IdeaProjects/KNN/src/main/resources/ruben/eduardo/knn/Data/clasificado.csv"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void exportarClasificacionesACsv(ConcurrentHashMap<LinkedList<Double>, String> clasificaciones) {
        StringBuilder csvBuilder = new StringBuilder();
        // Definir el encabezado del CSV
        try (Stream<String> lines = Files.lines(Paths.get(direccionArchivoClasificado))) {
            String header = lines.findFirst().orElse("");
            csvBuilder.append(header).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Iterar sobre el ConcurrentHashMap y construir el contenido del CSV
        for (Map.Entry<LinkedList<Double>, String> entrada : clasificaciones.entrySet()) {
            // Convertir la clave (LinkedList) a String
            String claveComoCadena = entrada.getKey().stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(","));
            // Agregar la clave y el valor al StringBuilder
            csvBuilder.append(claveComoCadena)
                    .append(",")
                    .append(entrada.getValue())
                    .append("\n");
        }

        // Escribir el contenido del StringBuilder en un archivo CSV
        try (PrintWriter out = new PrintWriter("C:/Users/Usuario/IdeaProjects/KNN/src/main/resources/ruben/eduardo/knn/Data/clasificado.csv")) {
            out.println(csvBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void cantDecolum(KeyEvent keyEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Proceso Completado");
        alert.setHeaderText(null);
        alert.setContentText("Datos analizados correctamente");
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
    private XYChart.Series<Number, Number> addRandomDataToChart() {


        // Crear la primera serie de datos y agregar tus datos
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.setName("Indicadores");
        for (Indicador i : Bolsa.getIndicadores()) {
            XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(i.Momentum, i.MACD);
            Circle circle = new Circle();
            circle.setRadius(i.RSI * 0.065 );
            circle.setFill(i.getClasificacion().equals(Clasificacion.Comprar) ? Color.GREEN : (i.getClasificacion().equals(Clasificacion.Vender) ? Color.RED : Color.ORANGE));
            dataPoint.setNode(circle);
            series1.getData().add(dataPoint);
        }



        scatterChart.getData().addAll(series1);
        return series1;
    }
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