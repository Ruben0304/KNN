package ruben.eduardo.knn.models;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public record DistanciaAClasificacion (Map.Entry<String,Double> distancia) implements Comparable<DistanciaAClasificacion>{

    @Override
    public int compareTo(@NotNull DistanciaAClasificacion o) {
        return distancia.getValue().compareTo(o.distancia.getValue());
    }

    @Override
    public String toString() {
        return distancia.toString();
    }
}
