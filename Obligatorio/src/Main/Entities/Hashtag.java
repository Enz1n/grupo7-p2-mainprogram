package entities;

public class Hashtag {

    private long id;
    private static long idCounter = 0;

    private String text;
    public Hashtag(long id, String text) {
        this.id = id + 1;
        this.text = text;
    }

    public Hashtag() {
        this.id = ++idCounter;;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
