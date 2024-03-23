package ruben.eduardo.knn.visual;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import ruben.eduardo.knn.clasificadores.Clasificador;
import ruben.eduardo.knn.interfaces.*;
import ruben.eduardo.knn.modelos.*;
import ruben.eduardo.knn.clasificadores.ClasificadorLista;
import ruben.eduardo.knn.servicios.GeneradorFicheros;
import ruben.eduardo.knn.servicios.GeneradorMatrices;
import ruben.eduardo.knn.servicios.LectorFicheros;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.awt.Desktop;
import java.util.stream.Stream;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import ruben.eduardo.knn.visual.componentes.*;


import java.util.Optional;
public class Controlador {


    @FXML
    public ProgressIndicator progress;
    @FXML
    public ProgressIndicator progressVerde;
    public TextArea txtAreaClasifUnEelemnt;
    public Button btnClasificar1Elemnt;
    public Button btnMatriz;
    public Spinner<Integer> spinnerK;
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

    private IGeneradorMatrices generadorMatrices;
    private IGeneradorFicheros generadorFicheros;

    private int totalElementos = 0;
    private int elementosProcesados = 0;
    private String direccionArchivoClasificado;
    private String direccionArchivoNoClasificado;


    public void initialize() {
        txtAreaClasifUnEelemnt.setStyle("-fx-control-inner-background: #1c1c1e");
        btnClasificar.setDisable(false);
        btnClasificar1Elemnt.setDisable(false);
        SpinnerK.inicializarspinnerK(spinnerK);
        Grafico.addRandomDataToChart(scatterChart);
    }



    public void intentarClasificar1element(ActionEvent actionEvent) {
        LinkedList<Double> lista = TextAreaValores.obtenerDatosDelTextArea(txtAreaClasifUnEelemnt,direccionArchivoClasificado);
        if (lista != null) {
            clasificador.setK(spinnerK.getValue());
            String resultadoClasificacion = clasificador.clasificar(lista);
           Alertas.resultadoClasificacion(resultadoClasificacion);
        }

    }

    public void handleOpenFileActionArchClasific(ActionEvent event) {
        direccionArchivoClasificado = ObtenerArchivos.obtenerDireccionArchivo(event);
        btnSelecArchClasificado.setDisable(true);

        txtAreaClasifUnEelemnt.setDisable(false);
        btnClasificar1Elemnt.setDisable(false);

        if (direccionArchivoClasificado != null && direccionArchivoNoClasificado != null){
            btnMatriz.setDisable(false);
        }

    }


    public void handleOpenFileActionArchNoClasific(ActionEvent event) {
        direccionArchivoNoClasificado = ObtenerArchivos.obtenerDireccionArchivo(event);
        btnSelecArchSinClasif.setDisable(true);
    }

    public void intentarClasificar(ActionEvent event) {

        if (direccionArchivoNoClasificado!=null && direccionArchivoClasificado!=null) {

            LectorFicheros lectorFicheros = new LectorFicheros(direccionArchivoNoClasificado);
            registroNoClasificados = new DatosAClasificar(lectorFicheros.leerArchivo());

            iniciarClasificacion();
        }
        else {
          Alertas.nohayRegistros();
        }

    }

    public void intentarEntrenar(ActionEvent event) {

        if (direccionArchivoClasificado != null) {
            LectorFicheros lectorFicheros = new LectorFicheros(direccionArchivoClasificado);

            int posicionClasif = lectorFicheros.obtenerPosicionClasificacion();
            registroClasificados = new DatosEntrenamiento(lectorFicheros.leerArchivo(posicionClasif));
            clasificador = new ClasificadorLista(registroClasificados);

            btnClasificar1Elemnt.setDisable(false);
            btnMatriz.setDisable(false);
            progressVerde.setProgress(1.0);
        }
        else {
           Alertas.nohayRegistros();
        }

    }

    public void iniciarClasificacion() {

        progress.setProgress(0);
        clasificador.setK(spinnerK.getValue());
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
            generadorFicheros.exportarClasificacionesACsv(task.getValue(),"ResultadoClasificacion.csv");
           Alertas.resultadoClasificacionConjunto();
        });
        task.setOnFailed(e -> progress.setProgress(0));

        new Thread(task).start();
    }

    public void generarMatrizDistancia() {



        HashMap<LinkedList<Double>, String> elementosC =registroClasificados.getElementos();
        LinkedList<LinkedList<Double>> elementosNC = registroNoClasificados.getElementos();

        generadorMatrices = new GeneradorMatrices(clasificador);
        double[][] matrizDistancia = generadorMatrices.generarMatriz(elementosC, elementosNC);

        // Guardar la matriz en un archivo binario
        generadorFicheros = new GeneradorFicheros();
        generadorFicheros.exportarMatrizArchivoBinario(matrizDistancia, "matrizDistancia.bin");

        generadorMatrices.imprimirMatrizEnConsola(matrizDistancia);

        Alertas.alertaMatriz();
    }






}