package ruben.eduardo.knn.modelos;

import java.util.LinkedList;
import net.datafaker.Faker;

public class DatosPrueba {
    public record DatoPrueba(double x, double y, String calificacion){}

    LinkedList<DatoPrueba> noClasificados;
    LinkedList<DatoPrueba> clasificados;

    public DatosPrueba() {
        noClasificados = new LinkedList<>();
        clasificados = new LinkedList<>();
      Faker faker = new Faker();
        for (int i = 0; i < 50; i++) {
            double d = faker.number().randomDouble(1,20,50);
            clasificados.addLast(new DatoPrueba(faker.number().randomDouble(1,20,50),faker.number().randomDouble(1,20,50),"C1"));
        }

        for (int i = 0; i < 50; i++) {
            double d = faker.number().randomDouble(1,-15,15);
            clasificados.addLast(new DatoPrueba(faker.number().randomDouble(1,-15,15),faker.number().randomDouble(1,-15,15),"C2"));
        }

        for (int i = 0; i < 50; i++) {
            double d = faker.number().randomDouble(1,-20,-50);
            clasificados.addLast(new DatoPrueba(faker.number().randomDouble(1,-20,-50),faker.number().randomDouble(1,-20,-50),"C3"));
        }

        noClasificados.addLast(new DatoPrueba(60,60,"NC"));

        noClasificados.addLast(new DatoPrueba(-60,-60,"NC"));
    }

    public LinkedList<DatoPrueba> getNoClasificados() {
        return noClasificados;
    }

    public LinkedList<DatoPrueba> getClasificados() {
        return clasificados;
    }
}
