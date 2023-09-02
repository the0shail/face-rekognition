package league.common.appaws.views;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import league.common.appaws.Main;
import league.common.appaws.pojo.Image;
import league.common.appaws.service.BaseController;
import league.common.appaws.service.DatabaseConnect;
import league.common.aws.GetPhoto;
import software.amazon.awssdk.services.s3.model.S3Object;

public class ListImage extends BaseController {
    public static final String URL_FXML = "../views/ListImage.fxml";

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Image> listImage;

    @FXML
    void initialize() {
        try {
            String query = "SELECT * FROM images";
            Statement statement = DatabaseConnect.connect().createStatement();
            ResultSet result = statement.executeQuery(query);

            ObservableList<Image> items = FXCollections.observableArrayList();

            while (result.next()){
                String name = result.getString("name");
                String tag = result.getString("tag");
                int bucket_id = result.getInt("bucket_id");
                long created_at = result.getLong("created_at");

                Image image = new Image(name, tag, bucket_id, created_at);
                items.add(image);
            }

            listImage.setItems(items);
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        listImage.getSelectionModel().selectedItemProperty().addListener((observableValue, image, t1) -> {

        });

    }

    String getBucketName(int id){
        try {
            Statement statement = DatabaseConnect.connect().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM buckets where id='" + id + "'");

            String bucketName = resultSet.getString("name");

            return bucketName;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
