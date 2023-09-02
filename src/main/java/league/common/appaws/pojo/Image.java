package league.common.appaws.pojo;

public class Image {
    private String name;
    private String tag;
    private long created_at;
    private int bucket_id;

    public Image(String name, String tag, int bucket_id, long created_at){
        this.name = name;
        this.tag = tag;
        this.bucket_id = bucket_id;
        this.created_at = created_at;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public int getBucket_id() {
        return bucket_id;
    }

    public long getCreated_at() {
        return created_at;
    }
}
