package ruben.eduardo.knn.avl;

import java.util.ArrayList;
import java.util.List;

public class TreePrinter {

        // Método estático que recibe un cu.ruben.eduardo.avl.PrintableNode y lo imprime en consola
        public static void print(PrintableNode root) {
            // Se crea una lista de listas de Strings para almacenar las líneas del árbol
            List<List<String>> lines = new ArrayList<List<String>>();

            // Se crea una lista de cu.ruben.eduardo.avl.PrintableNode para almacenar el nivel actual del árbol
            List<PrintableNode> level = new ArrayList<PrintableNode>();

            // Se crea una lista de cu.ruben.eduardo.avl.PrintableNode para almacenar el nivel siguiente del árbol
            List<PrintableNode> next = new ArrayList<PrintableNode>();

            // Se agrega la raíz del árbol al nivel actual
            level.add(root);

            // Se inicializa una variable para contar el número de nodos no nulos
            int nn = 1;

            // Se inicializa una variable para guardar el ancho máximo de los nodos
            int widest = 0;

            // Se repite mientras haya nodos no nulos
            while (nn != 0) {
                // Se crea una lista de Strings para almacenar la línea actual
                List<String> line = new ArrayList<String>();

                // Se reinicia el contador de nodos no nulos
                nn = 0;

                // Se recorre cada nodo del nivel actual
                for (PrintableNode n : level) {
                    // Si el nodo es nulo, se agrega nulo a la línea y se agregan dos nulos al nivel siguiente
                    if (n == null) {
                        line.add(null);
                        next.add(null);
                        next.add(null);
                    } else {
                        // Si el nodo no es nulo, se obtiene su valor como String y se agrega a la línea
                        String aa = n.toString();
                        line.add(aa);
                        // Si el valor es más largo que el ancho máximo, se actualiza el ancho máximo
                        if (aa.length() > widest) widest = aa.length();

                        // Se agregan los nodos izquierdo y derecho al nivel siguiente
                        next.add(n.getIzquierdo());
                        next.add(n.getDerecho());

                        // Si alguno de los nodos no es nulo, se incrementa el contador de nodos no nulos
                        if (n.getIzquierdo() != null) nn++;
                        if (n.getDerecho() != null) nn++;
                    }
                }

                // Si el ancho máximo es impar, se le suma uno
                if (widest % 2 == 1) widest++;

                // Se agrega la línea a la lista de líneas
                lines.add(line);

                // Se intercambian las listas de nivel actual y nivel siguiente
                List<PrintableNode> tmp = level;
                level = next;
                next = tmp;
                next.clear();
            }

            // Se calcula el número de espacios por pieza, basado en el número de elementos de la última línea
            int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);

            // Se recorre cada línea
            for (int i = 0; i < lines.size(); i++) {
                // Se obtiene la línea actual
                List<String> line = lines.get(i);

                // Se calcula la mitad del número de espacios por pieza
                int hpw = (int) Math.floor(perpiece / 2f) - 1;

                // Si no es la primera línea, se imprimen las ramitas y los espacios
                if (i > 0) {
                    // Se recorre cada elemento de la línea
                    for (int j = 0; j < line.size(); j++) {

                        // Se elige el caracter de la ramita según la posición del elemento
                        char c = ' ';
                        if (j % 2 == 1) {
                            if (line.get(j - 1) != null) {
                                c = (line.get(j) != null) ? '┴' : '┘';
                            } else {
                                if (j < line.size() && line.get(j) != null) c = '└';
                            }
                        }
                        System.out.print(c);

                        // Se imprimen los espacios y las líneas según el ancho máximo
                        if (line.get(j) == null) {
                            for (int k = 0; k < perpiece - 1; k++) {
                                System.out.print(" ");
                            }
                        } else {
                            for (int k = 0; k < hpw; k++) {
                                System.out.print(j % 2 == 0 ? " " : "─");
                            }
                            System.out.print(j % 2 == 0 ? "┌" : "┐");
                            for (int k = 0; k < hpw; k++) {
                                System.out.print(j % 2 == 0 ? "─" : " ");
                            }
                        }
                    }
                    System.out.println();
                }

                // Se imprimen los espacios y los elementos de la línea
                for (int j = 0; j < line.size(); j++) {

                    // Se calcula el número de espacios antes y después del elemento
                    String f = line.get(j);
                    if (f == null) f = "";
                    int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                    int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                    // Se imprimen los espacios y el elemento
                    for (int k = 0; k < gap1; k++) {
                        System.out.print(" ");
                    }
                    System.out.print(f);
                    for (int k = 0; k < gap2; k++) {
                        System.out.print(" ");
                    }
                }
                System.out.println();

                // Se reduce el número de espacios por pieza para el siguiente nivel
                perpiece /= 2;
            }
        }
    }


