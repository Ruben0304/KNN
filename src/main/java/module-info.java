module ruben.eduardo.knn {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires org.jetbrains.annotations;

    opens ruben.eduardo.knn to javafx.fxml;
    exports ruben.eduardo.knn;
}