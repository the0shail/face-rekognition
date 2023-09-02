package league.common.appaws;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import league.common.appaws.service.BaseController;

public class HelloController extends BaseController {
    public static final String URL_FXML = "../views/hello-view.fxml";

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}