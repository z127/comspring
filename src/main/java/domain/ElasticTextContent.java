package domain;

public class ElasticTextContent {
    String content;
 String mongo_adress;
    public String getContent() {
        return content;
    }

    public ElasticTextContent() {

    }

    public String getMongo_adress() {
        return mongo_adress;
    }

    public void setMongo_adress(String mongo_adress) {
        this.mongo_adress = mongo_adress;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
