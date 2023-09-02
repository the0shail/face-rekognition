package league.common.appaws.service;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.List;

public class ImageChooser {

    private final FileChooser chooser;

    private final List<FileChooser.ExtensionFilter> filters; // Фильтры файлов по их расширениям.

    public ImageChooser() {
        chooser = new FileChooser();
        filters = chooser.getExtensionFilters();
    }

    // Метод для выбора изображения.
    public Image openImage() {
        File file = chooser.showOpenDialog(null); // Открываем файл.

        if (file != null) {
            URI uri = file.toURI(); // Преобразуем файл в URI.
            return new Image(uri.toString());
        }
        return null; // Если изображение не выбрано, тогда возвращаем null.
    }

    // Метод для утановки форматов.
    public void setAvailableFormats(String ... formats) {
        filters.clear(); // Удаляем все прошлые форматы.

        if (formats != null && formats.length > 0) { // Если есть что добавить.
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(String.join(", ", formats), formats);
            filters.add(filter);
        }

    }

    public static void imageSelect(Button selectImage, ImageView placeImage){
        ImageChooser chooser = new ImageChooser();
        chooser.setAvailableFormats("*.png", "*.jpg", "*.jpeg");

        selectImage.setOnAction((event) -> { // Обработчик событий для нажатия кнопки.
            Image image = chooser.openImage(); // Выбираем изображение.

            if (image != null) {
                placeImage.setImage(image); // Установка изображения.
                placeImage.setFitWidth(200);
                placeImage.setFitHeight(200);
            }
        });
    }
}