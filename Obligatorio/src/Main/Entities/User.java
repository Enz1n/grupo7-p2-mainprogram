package entities;

import adt.linkedlist.MyLinkedList;

public class User {
    private static long idCounter = 0;
    private long id;

    private String name;

    private MyLinkedList<Tweets> tweets;

    private boolean isVerified;

    private int favourites;

    public User(long id, String name, boolean isVerified, int favourites) {
        this.id = ++idCounter;;
        this.name = name;
        this.isVerified = isVerified;
        this.favourites = favourites;
        this.tweets = new MyLinkedList<>();
    }

    public User() {
        this.id = ++idCounter;;
        this.tweets = new MyLinkedList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public int getFavourites() {
        return favourites;
    }

    public void setFavourites(int favourites) {
        this.favourites = favourites;
    }


}
