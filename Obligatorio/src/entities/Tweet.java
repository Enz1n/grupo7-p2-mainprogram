package entities;

public class Tweet {
    private long id;
    private String content;
    private String source;
    private boolean isRetweet;

    public Tweet(long id, String content, String source, boolean isRetweet) {
        this.id = id;
        this.content = content;
        this.source = source;
        this.isRetweet = isRetweet;
    }
}
