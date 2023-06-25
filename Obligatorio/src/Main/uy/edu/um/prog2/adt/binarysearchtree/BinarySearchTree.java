package uy.edu.um.prog2.adt.binarysearchtree;

public interface BinarySearchTree <K extends Comparable<K>, V> {

    void add(K key, V value);

    void remove(K key);

    V find(K key);
}
