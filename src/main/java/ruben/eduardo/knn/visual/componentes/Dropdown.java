package ruben.eduardo.knn.visual.componentes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import ruben.eduardo.knn.interfaces.ILectorFicheros;

import java.util.ArrayList;

public class Dropdown {



    public static void llenarValores(ComboBox comboBox, ILectorFicheros lectorFicheros){
        ArrayList<String> encabezados = lectorFicheros.leerEncabezado();
        int pos = lectorFicheros.obtenerPosicionClasificacion();
        ObservableList<String> listaObservable = FXCollections.observableArrayList(encabezados);
        if (pos != -1){
            String elementoPrimero = listaObservable.remove(pos);
            listaObservable.add(0, elementoPrimero);
            comboBox.setItems(listaObservable);
            comboBox.setValue(elementoPrimero);
        }
        comboBox.setItems(listaObservable);
    }

    public static int obtenerPosSeleccionada(ComboBox comboBox, ILectorFicheros lectorFicheros){
        ArrayList<String> encabezados = lectorFicheros.leerEncabezado();
        String seleccionado = (String) comboBox.getValue();
        for (int i = 0; i < encabezados.size(); i++)
            if (seleccionado.equals(encabezados.get(i)))
                return i;
        return -1;
    }
}
