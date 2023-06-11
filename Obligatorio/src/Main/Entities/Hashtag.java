package entities;

public class Hashtag {

    private long id = 0;

    private String text;
    public Hashtag(long id, String text) {
        this.id = id + 1;
        this.text = text;
    }

    public Hashtag() {
        this.id =  id + 1;
    }
}
