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
    exports ruben.eduardo.knn.services;
    opens ruben.eduardo.knn.services to javafx.fxml;
}