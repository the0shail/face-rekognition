package league.common.aws;

import software.amazon.awssdk.services.s3.model.Tag;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
    public static String pathResource = "src/main/resources/images/aws/";
    public static String bucketName = "helloworld-myfriends";
    public static void main(String[] args) throws IOException {

//        CreateBucket.createBucket(bucketName);
        String keyName1 = "1";
        byte[] bytes1 = GetPhoto.getObjectBytes(bucketName, keyName1);
        List<Tag> tags1 = GetPhoto.listTags(bucketName, keyName1);

        convertBytesToImage(bytes1, keyName1, pathResource);

        System.out.println(tags1.get(0).key());

        String keyName2 = "2";
        byte[] bytes2 = GetPhoto.getObjectBytes(bucketName, keyName2);
        List<Tag> tags2 = GetPhoto.listTags(bucketName, keyName2);

        convertBytesToImage(bytes2, keyName2, pathResource);

        System.out.println(tags2.get(0).key());

    }

    public static void convertBytesToImage(byte[] arrayByte, String keyName, String pathResource){
        try {

            ByteArrayInputStream bis = new ByteArrayInputStream(arrayByte);
            BufferedImage bImage2 = ImageIO.read(bis);
            ImageIO.write(bImage2, "jpg", new File(pathResource + keyName + ".jpg"));
            System.out.println("image created");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
