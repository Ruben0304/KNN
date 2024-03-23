package ruben.eduardo.knn.visual.componentes;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import ruben.eduardo.knn.modelos.DatosPrueba;

import java.util.Iterator;

public class Grafico {

    public static void addRandomDataToChart(ScatterChart scatterChart) {
        DatosPrueba datosPrueba = new DatosPrueba();

        // Crear la primera serie de datos y agregar tus datos
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.setName("Indicadores");

        for (DatosPrueba.DatoPrueba i : datosPrueba.getClasificados()) {
            XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(i.x(), i.y());
            Circle circle = new Circle();
            circle.setRadius(4.8);
            circle.setFill(i.calificacion().equals("C1") ? Color.GREEN : (i.calificacion().equals("C3") ? Color.RED : Color.ORANGE));
            dataPoint.setNode(circle);
            series1.getData().add(dataPoint);
        }


        scatterChart.getData().addAll(series1);

        addRandomDataToChart2(scatterChart);


    }

    private static void addRandomDataToChart2(ScatterChart scatterChart) {
        DatosPrueba datosPrueba = new DatosPrueba();
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series2.setName("prueba");

        // Crea un iterador para tus datos no clasificados
        Iterator<DatosPrueba.DatoPrueba> iterator = datosPrueba.getNoClasificados().iterator();

        // Crea un Timeline que se repita cada 2.5 segundos
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2.5), event -> {
            if (iterator.hasNext()) {
                DatosPrueba.DatoPrueba i = iterator.next();
                XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(i.x(), i.y());
                Circle circle = new Circle();
                circle.setRadius(7);
                circle.setFill(Color.PINK);
                dataPoint.setNode(circle);
                series2.getData().add(dataPoint);
            }
        }));

        // Configura el Timeline para repetirse indefinidamente
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        scatterChart.getData().addAll(series2);
    }
}
