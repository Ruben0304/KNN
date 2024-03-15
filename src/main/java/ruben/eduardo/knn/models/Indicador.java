package ruben.eduardo.knn.models;

public class Indicador {
    public final double VOLUMEN;

    public final double RSI;

    public final double MACD;

    private Clasificacion clasificacion;

    public Indicador(double VOLUMEN, double RSI, double MACD, Clasificacion clasificacion) {
        this.VOLUMEN = VOLUMEN;
        this.RSI = RSI;
        this.MACD = MACD;
        this.clasificacion = clasificacion;
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }
}
