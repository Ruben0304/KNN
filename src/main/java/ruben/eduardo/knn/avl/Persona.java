package ruben.eduardo.knn.avl;

public class Persona implements Comparable<Persona>{

    public String name;
    public Integer edad;

    public Persona(String name, int edad){

        this.name = name;
        this.edad = edad;
    }

    @Override
    public int compareTo(Persona o) {
        return edad.compareTo(o.edad);
    }

    @Override
    public String toString() {
        return name;

    }
}
