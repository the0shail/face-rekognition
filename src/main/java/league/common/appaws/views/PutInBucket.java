package league.common.appaws.views;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;
import java.util.Objects;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import league.common.appaws.service.BaseController;
import league.common.appaws.service.DatabaseConnect;
import league.common.appaws.service.ImageChooser;
import league.common.aws.PutBucket;


public class PutInBucket extends BaseController {
    public static final String URL_FXML = "../views/PutInBucket.fxml";
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink screenCreateBucket;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField inputImageName;

    @FXML
    private TextField inputImageTag;

    @FXML
    private ChoiceBox<String> selectBucket;

    @FXML
    private Button selectImage;

    @FXML
    private Button buttonNext;

    @FXML
    void initialize() {
        ImageChooser imageChooser = new ImageChooser();
        imageChooser.imageSelect(selectImage, imageView);

        if (DatabaseConnect.connect() != null){
            try {
                Statement statement = DatabaseConnect.connect().createStatement();
                ResultSet result = statement.executeQuery("SELECT name FROM buckets");

                ObservableList<String> items = FXCollections.observableArrayList();

                while (result.next()){
                    items.add(result.getString("name"));
                }
                if (items.size() > 0){
                    selectBucket.setValue(items.get(0));
                    selectBucket.setItems(items);
                } else {
                    selectBucket.setValue("Нет подходящей записи");
                }
                statement.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        buttonNext.setOnAction(event -> {
            String name = "";
            String tag = inputImageTag.getText();
            if(!inputImageName.getText().isEmpty()){
                name = inputImageName.getText();
            }
            else name = Objects.toString(imageView.getImage());

            PutBucket.putS3Object(
                    selectBucket.getValue(),
                    name,
                    tag,
                    imageView.getImage().getUrl().substring(6)
            );

            try {
                Formatter query = new Formatter();
                query.format("INSERT INTO images (name, created_at, tag, bucket_id) values ('%s', '%s', '%s', %s)",
                    name,
                    System.currentTimeMillis(),
                    tag,
                    getWhereColumn("buckets", selectBucket.getValue())
                );

                Statement statement = DatabaseConnect.connect().createStatement();
                statement.executeUpdate(query.toString());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public static int getWhereColumn(String nameTable, String nameValue){
        try {
            Statement statement = DatabaseConnect.connect().createStatement();
            ResultSet result = statement.executeQuery("SELECT id FROM " + nameTable + " WHERE name='" + nameValue + "'");

            int id = result.getInt("id");
            statement.close();
            return id;
        } catch (SQLException e) {
            System.out.println("Error: \n" + e.getMessage());
            return 0;
        }
    }

}
