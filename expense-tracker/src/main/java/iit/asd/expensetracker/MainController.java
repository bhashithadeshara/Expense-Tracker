package iit.asd.expensetracker;

import iit.asd.expensetracker.util.singleton.MainStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("views/home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        stage = MainStage.getInstance();
        stage.setTitle("Welcome to Expense Tracker!");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}