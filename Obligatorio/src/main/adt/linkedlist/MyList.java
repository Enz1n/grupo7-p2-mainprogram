package adt.linkedlist;

public interface MyList <T> {

    void add(T value);

    Node<T> getObject(T value);

    T get(int position);

    void remove(T value);

    int size();

    boolean contains(T value);
}
