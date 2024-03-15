package ruben.eduardo.knn.models;

public class Accion {
    private String nombre;
    private Double precio;

    private Indicador indicador;

    public Accion(String nombre, Double precio, Indicador indicador) {
        this.nombre = nombre;
        this.precio = precio;
        this.indicador = indicador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Indicador getIndicador() {
        return indicador;
    }

    public void setIndicador(Indicador indicador) {
        this.indicador = indicador;
    }
}
