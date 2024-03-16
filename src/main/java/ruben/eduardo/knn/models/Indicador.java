package ruben.eduardo.knn.models;

public class Indicador {
    public final double Momentum;

    public final double RSI;

    public final double MACD;

    private Clasificacion clasificacion;

    public Indicador(double momentum, double RSI, double MACD, Clasificacion clasificacion) {
        this.Momentum = momentum;
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
