package league.common.appaws;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import league.common.appaws.service.Navigation;
import league.common.appaws.views.ListImage;
import league.common.appaws.views.PutInBucket;

import java.io.IOException;

public class Main extends Application {
    public static double APPLICATION_WIDTH = 600;
    public static double APPLICATION_HEIGHT = 400;

    private static Navigation navigation;
    public static Navigation getNavigation() {
        return navigation;
    }

    @Override
    public void start(Stage primatyStage) throws IOException {
        navigation = new Navigation(primatyStage);

        primatyStage.setTitle("Face Rekognition");
        primatyStage.show();

        // Navigate to first view

//        System.out.println(Main.getNavigation().load(HelloController.URL_FXML));
        Main.getNavigation().load(ListImage.URL_FXML).Show();
    }

    public static void main(String[] args) {
        launch();
    }
}

//jdbc:sqlite:C:\Users\Aziz\Desktop\faceId\appAws\src\main\resources\buckets