module Miguel.CronoThread {
    requires javafx.controls;
    requires javafx.fxml;

    opens Miguel.CronoThread to javafx.fxml;
    exports Miguel.CronoThread;
}
