package ruben.eduardo.knn.visual.componentes;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.converter.IntegerStringConverter;

public class SpinnerK {

    public static void inicializarspinnerK(Spinner<Integer> spinnerK){
        spinnerK.setValueFactory(new SpinnerValueFactory<Integer>() {

            private final int min = 3;
            private final int max = 15;
            private final int initialValue = 5;

            {
                setConverter(new IntegerStringConverter());
                setValue(initialValue);
            }

            @Override
            public void decrement(int steps) {
                int currentValue = getValue();
                int newValue = currentValue - (2 * steps);
                setValue(Math.max(min, newValue));
            }

            @Override
            public void increment(int steps) {
                int currentValue = getValue();
                int newValue = currentValue + (2 * steps);
                setValue(Math.min(max, newValue));
            }
        });
    }
}
