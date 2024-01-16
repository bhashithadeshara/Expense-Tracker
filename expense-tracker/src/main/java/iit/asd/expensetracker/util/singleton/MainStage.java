package iit.asd.expensetracker.util.singleton;

import javafx.stage.Stage;

public class MainStage extends Stage {
    // The Main Stage
    private static MainStage mainStage;

    private MainStage() {}

    public static MainStage getInstance() {
        if (mainStage == null) {
            mainStage = new MainStage();
        }
        return mainStage;
    }
}
