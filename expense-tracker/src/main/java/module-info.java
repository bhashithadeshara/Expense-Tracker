module iit.asd.expensetracker {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens iit.asd.expensetracker to javafx.fxml;
    opens iit.asd.expensetracker.entity to javafx.fxml;

    exports iit.asd.expensetracker;
    exports iit.asd.expensetracker.entity;
}





