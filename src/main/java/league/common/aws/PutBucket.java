package league.common.aws;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PutBucket {
    private static S3Client client = S3Connect.s3client();

    // This example uses RequestBody.fromFile to avoid loading the whole file into memory.
    public static void putS3Object(String bucketName, String imageName, String tag, String imagePath) {
        try {
            Map<String, String> metadata = new HashMap<>();
            metadata.put("x-amz-meta-myVal", "test");
            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(imageName)
                    .tagging(tag)
                    .contentType("image")
                    .metadata(metadata)
                    .build();

            client.putObject(putOb, RequestBody.fromFile(new File(imagePath)));
            System.out.println("Successfully placed " + imageName +" into bucket "+bucketName);

        } catch (S3Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
