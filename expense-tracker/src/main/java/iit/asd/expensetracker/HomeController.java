package iit.asd.expensetracker;

import iit.asd.expensetracker.util.singleton.MainStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HomeController {
    // The Transaction Pane
    @FXML
    private Pane paneTransactions;

    // The category pane
    @FXML
    private Pane paneCategories;

    // The budget pane
    @FXML
    private Pane paneBudget;

    /**
     * load the transaction view when button click
     */
    @FXML
    protected void onClickTransactions() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/transaction-manager-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1500, 600);

            Stage stage = MainStage.getInstance();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * load the category view when button click
     */
    @FXML
    protected void onClickCategories() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/category-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 320);

            Stage stage = MainStage.getInstance();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * load the budget view when button click
     */
    @FXML
    protected void onClickBudget() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/budget-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1200, 590);

            Stage stage = MainStage.getInstance();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}