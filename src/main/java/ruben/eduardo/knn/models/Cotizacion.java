package ruben.eduardo.knn.models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Cotizacion {
    private final SimpleStringProperty indice;
    private final SimpleDoubleProperty precio;
    private final SimpleStringProperty recomendacionML;

    public Cotizacion(String indice, double precio, String recomendacionML) {
        this.indice = new SimpleStringProperty(indice);
        this.precio = new SimpleDoubleProperty(precio);
        this.recomendacionML = new SimpleStringProperty(recomendacionML);
    }

    public String getIndice() {
        return indice.get();
    }

    public SimpleStringProperty indiceProperty() {
        return indice;
    }

    public double getPrecio() {
        return precio.get();
    }

    public SimpleDoubleProperty precioProperty() {
        return precio;
    }

    public String getRecomendacionML() {
        return recomendacionML.get();
    }

    public SimpleStringProperty recomendacionMLProperty() {
        return recomendacionML;
    }
}
