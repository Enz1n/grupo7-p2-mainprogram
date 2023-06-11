package entities;

import adt.linkedlist.MyLinkedList;

public class Tweets {

    private Long id;

    private String content;

    private String source;

    private boolean isRetweet;

    private MyLinkedList<Hashtag> Hashtags;

    private String date;

    public Tweets(Long id, String content, String source, boolean isRetweet, MyLinkedList<Hashtag> hashtags, String date) {
        this.id = id;
        this.content = content;
        this.source = source;
        this.isRetweet = isRetweet;
        Hashtags = new MyLinkedList<>();
        this.date = date;
    }

    public Tweets() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isRetweet() {
        return isRetweet;
    }

    public void setRetweet(boolean retweet) {
        isRetweet = retweet;
    }

    public MyLinkedList<Hashtag> getHashtags() {
        return Hashtags;
    }

    public void setHashtags(MyLinkedList<Hashtag> hashtags) {
        Hashtags = hashtags;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
