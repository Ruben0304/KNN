package ruben.eduardo.knn;

import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import ruben.eduardo.knn.interfaces.AnalizadorKNN;
import ruben.eduardo.knn.models.*;

public class Controlador {

    AnalizadorKNN analizadorKNN = new AnalistaFinanciero();

    @FXML
    private ScatterChart<Number, Number> scatterChart;


    public void addRandomDataToChart() {


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

        for (Accion a : Bolsa.getAcciones()) {
            addAcciontoChart(a,series1);
        }

        scatterChart.getData().addAll(series1);
    }

    public void agregarAccionViaFormulario(Accion a){

    }

    private void addAcciontoChart(Accion a , XYChart.Series<Number, Number> serie){
        XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(a.getIndicador().Momentum, a.getIndicador().MACD);
        Rectangle circle = new Rectangle();
        circle.setHeight(5);
        circle.setWidth(a.getIndicador().RSI*0.3);
        Clasificacion c = analizadorKNN.clasificarAccion(a);
        circle.setFill(c.equals(Clasificacion.Comprar) ? Color.GREEN : (c.equals(Clasificacion.Vender) ? Color.RED : Color.ORANGE));
        dataPoint.setNode(circle);
        serie.getData().add(dataPoint);
    }
}