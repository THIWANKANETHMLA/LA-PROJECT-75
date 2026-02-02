module com.example.layeredarchitecture {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;
    //requires com.example.layeredarchitecture;
    //requires com.example.layeredarchitecture;
    //requires com.example.layeredarchitecture;
    //requires com.example.layeredarchitecture;
    //requires com.example.layeredarchitecture;

    opens com.example.layeredarchitecture to javafx.fxml;
    opens com.example.layeredarchitecture.controller to javafx.fxml;
    opens com.example.layeredarchitecture.view.tdm to javafx.base;

    exports com.example.layeredarchitecture;
    exports com.example.layeredarchitecture.controller;
}
