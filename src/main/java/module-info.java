module ruben.eduardo.knn {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires org.jetbrains.annotations;
    requires arboles;
    requires java.desktop;
    requires datafaker;

    opens ruben.eduardo.knn to javafx.fxml;
    exports ruben.eduardo.knn;
    exports ruben.eduardo.knn.servicios;
    opens ruben.eduardo.knn.servicios to javafx.fxml;
    exports ruben.eduardo.knn.visual;
    opens ruben.eduardo.knn.visual to javafx.fxml;
    exports ruben.eduardo.knn.visual.componentes;
    opens ruben.eduardo.knn.visual.componentes to javafx.fxml;
}