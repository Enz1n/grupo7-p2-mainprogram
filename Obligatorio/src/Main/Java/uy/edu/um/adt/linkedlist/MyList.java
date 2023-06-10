package Main.Java.uy.edu.um.adt.linkedlist;

public interface MyList <T> {

    void add(T value);

    T get(int position);

    void remove(T value);

    int size();
}
