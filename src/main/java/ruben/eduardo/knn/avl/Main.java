package ruben.eduardo.knn.avl;

public class Main {
    public static void main(String[] args) {




        AvlTree<Integer> testTree = new AvlTree<>();
//        for (int i = 20; i > 14; i--) {
//            testTree.insert(i);
//        }
//
//        for (int i = 15; i < 400; i*=3) {
//            testTree.insert(i);
//        }
//        testTree.insert(null);
//        testTree.deleteValue(null);
//        cu.ruben.eduardo.avl.TreePrinter.print((cu.ruben.eduardo.avl.PrintableNode) testTree.getNodeRoot());
//
//
//        testTree.deleteValue(0);
//        testTree.deleteValue(16);


        testTree.insert(2);
        testTree.insert(5);
        testTree.insert(4);
        testTree.insert(6);
        testTree.insert(7);

//        testTree.deleteValue(2);
        testTree.deleteValue(6);
        testTree.deleteValue(7);



        TreePrinter.print((PrintableNode) testTree.getNodeRoot());



//
//        cu.ruben.eduardo.avl.AvlTree<String> arbolS = new cu.ruben.eduardo.avl.AvlTree<>("Ernesto");
//        arbolS.insert("Pedro");
//        arbolS.insert("Nestor");
//        arbolS.insert("Alejandro");
//        arbolS.insert("Xavier");
//        arbolS.insert("Carlos");
//        arbolS.insert("Valentin");
//        arbolS.insert("Manuel");
//
//
//        cu.ruben.eduardo.avl.TreePrinter.print((cu.ruben.eduardo.avl.PrintableNode) arbolS.getNodeRoot());
//        arbolS.deleteValue("Pedro");
//        arbolS.deleteValue("Xavier");
//        arbolS.deleteValue("Valentin");
//        cu.ruben.eduardo.avl.TreePrinter.print((cu.ruben.eduardo.avl.PrintableNode) arbolS.getNodeRoot());

        AvlTree<Persona> arbolP = new AvlTree<>(new Persona("Ernesto 15",15));
        arbolP.insert(new Persona("Pedro 11",11));
        arbolP.insert(new Persona("Yoel 19",19));
        arbolP.insert(new Persona("Adriana 2",2));
        arbolP.insert(new Persona("Manuel 90",90));
        arbolP.insert(new Persona("Sophia 45",45));
        arbolP.insert(new Persona("Lola 8",8));

        TreePrinter.print((PrintableNode)arbolP.getNodeRoot());

        arbolP.deleteValue(new Persona("Sophia 45",45));

        TreePrinter.print((PrintableNode)arbolP.getNodeRoot());
    }
}