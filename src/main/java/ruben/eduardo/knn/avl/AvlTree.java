package ruben.eduardo.knn.avl;

import cu.edu.cujae.ceis.tree.binary.BinaryTree;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;

import java.util.ArrayList;

public class AvlTree<E extends Comparable<E>> extends BinaryTree<E> {
    public AvlTree(E root) {
        super(root);
        this.root = new AvlNode<>(root);
    }

    public AvlTree() {
        super();

    }



    public static class AvlNode<E> extends BinaryTreeNode<E> implements PrintableNode {
        public int height = 0;

        public AvlNode(E valor) {
            super(valor);

        }

        @Override
        public PrintableNode getIzquierdo(){
            return (PrintableNode) getLeft();
        }

        @Override
        public PrintableNode getDerecho(){
            return (PrintableNode) getRight();
        }

        @Override
        public String toString() {
            return info.toString();
        }
    }



    public void insert(E valor) {

        if (valor != null)
         root = insert((AvlNode<E>) root, valor);
    }


    private AvlNode<E> insert(AvlNode<E> node, E valor) {

        if (node == null) {
            return new AvlNode<>(valor);
        }


        int cmp = valor.compareTo(node.getInfo());


        if (cmp < 0) {
            node.setLeft(insert((AvlNode<E>)node.getLeft(), valor));
        }

        else if (cmp > 0) {
            node.setRight(insert((AvlNode<E>)node.getRight(), valor));

        }


        node.height = Math.max(height((AvlNode<E>)node.getLeft()), height((AvlNode<E>)node.getRight())) + 1;


        int balance = balance(node);




        if (balance < -1 && valor.compareTo(node.getLeft().getInfo()) < 0) {
            return rightRotate(node);
        }


        if (balance > 1 && valor.compareTo(node.getRight().getInfo()) > 0) {
            return leftRotate(node);
        }


        if (balance < -1 && valor.compareTo(node.getLeft().getInfo()) > 0) {
            node.setLeft(leftRotate((AvlNode<E>)node.getLeft()));
            return rightRotate(node);
        }


        if (balance > 1 && valor.compareTo(node.getRight().getInfo()) < 0) {
            node.setRight(rightRotate((AvlNode<E>)node.getRight()));
            return leftRotate(node);
        }


        return node;
    }



    private AvlNode<E> rightRotate(AvlNode<E> node) {

        AvlNode left = (AvlNode<E>)node.getLeft();


        AvlNode right = (AvlNode<E>)left.getRight();


        left.setRight(node);


        node.setLeft(right);


        node.height = Math.max(height((AvlNode<E>)node.getLeft()), height((AvlNode<E>)node.getRight())) + 1;
        left.height = Math.max(height((AvlNode<E>)left.getLeft()), height((AvlNode<E>)left.getRight())) + 1;


        return left;
    }


    private AvlNode leftRotate(AvlNode<E> node) {

        AvlNode right = (AvlNode<E>)node.getRight();


        AvlNode left = (AvlNode<E>)right.getLeft();


        right.setLeft(node);


        node.setRight(left);


        node.height = Math.max(height((AvlNode<E>)node.getLeft()), height((AvlNode<E>)node.getRight())) + 1;
        right.height = Math.max(height((AvlNode<E>)right.getLeft()), height((AvlNode<E>)right.getRight())) + 1;


        return right;
    }


    private int height(AvlNode<E> node) {
        return node == null ? -1 : node.height;
    }


    private int balance(AvlNode<E> node) {
        return node == null ? 0 : height((AvlNode<E>)node.getRight()) - height((AvlNode<E>)node.getLeft());
    }


    public void deleteValue(E value) {
       if (value != null)
        root = delete((AvlNode<E>) root, value);
    }

    public ArrayList<E> findNElements(int n, boolean findMax) {
        ArrayList<E> result = new ArrayList<>();
        findNElements((AvlNode<E>) root, n, findMax, result);
        return result;
    }

    private void findNElements(AvlNode<E> node, int n, boolean findMax, ArrayList<E> result) {
        if (node == null || result.size() >= n) {
            return;
        }

        // Si findMax es true, busca los mayores (recorrido inverso)
        if (findMax) {
            findNElements((AvlNode<E>) node.getRight(), n, findMax, result);
            if (result.size() < n) {
                result.add(node.getInfo());
                findNElements((AvlNode<E>) node.getLeft(), n, findMax, result);
            }
        } else {
            // Si findMax es false, busca los menores (recorrido normal)
            findNElements((AvlNode<E>) node.getLeft(), n, findMax, result);
            if (result.size() < n) {
                result.add(node.getInfo());
                findNElements((AvlNode<E>) node.getRight(), n, findMax, result);
            }
        }
    }

    private AvlNode<E> delete(AvlNode<E> node, E value) {

        if (node == null) {
            return node;
        }


        int cmp = value.compareTo(node.getInfo());


        if (cmp < 0) {
            node.setLeft(delete((AvlNode<E>) node.getLeft(), value));;
        }

        else if (cmp > 0) {
            node.setRight(delete((AvlNode<E>) node.getRight(), value)); 
        }

        else {

            if (node.getLeft() == null && node.getRight()== null) {
                return null;
            }

            else if (node.getLeft() == null) {
                return (AvlNode<E>) node.getRight();
            }
            else if (node.getRight()== null) {
                return (AvlNode<E>) node.getLeft();
            }

            else {
                node.setInfo(getMax((AvlNode<E>)node.getLeft()));

                node.setLeft(delete((AvlNode<E>) node.getLeft(), node.getInfo()));
            }
        }


        node.height = Math.max(height((AvlNode<E>) node.getLeft()), height((AvlNode<E>)node.getRight())) + 1;


        int balance = balance(node);


        if (balance < -1 && balance((AvlNode<E>) node.getLeft()) <= 0) {
            return rightRotate(node);
        }


        if (balance > 1 && balance((AvlNode<E>) node.getRight()) >= 0) {
            return leftRotate(node);
        }


        if (balance < -1 && balance((AvlNode<E>) node.getLeft()) > 0) {
            node.setLeft(leftRotate((AvlNode<E>) node.getLeft()));
            return rightRotate(node);
        }


        if (balance > 1 && balance((AvlNode<E>) node.getRight()) < 0) {
            node.setRight(rightRotate((AvlNode<E>) node.getRight()));
            return leftRotate(node);
        }


        return node;
    }


    private E getMax(AvlNode<E> node) {

        if (node == null) {
            return null;
        }

        if (node.getRight() == null) {
            return node.getInfo();
        }

        return getMax((AvlNode<E>) node.getRight());
    }




}


