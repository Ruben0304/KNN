package ruben.eduardo.knn.modelos;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public record DistanciaAClasificacion(Map.Entry<String, Double> distancia, int contador) implements Comparable<DistanciaAClasificacion> {

    public DistanciaAClasificacion(Map.Entry<String, Double> distancia) {
        this(distancia, 1); // Inicializa el contador a 1 por defecto
    }


    @Override
    public int compareTo(@NotNull DistanciaAClasificacion o) {
        return distancia.getValue().compareTo(o.distancia.getValue());
    }

    @Override
    public String toString() {
        return distancia.toString() + " [Contador: " + contador + "]";
    }
}
