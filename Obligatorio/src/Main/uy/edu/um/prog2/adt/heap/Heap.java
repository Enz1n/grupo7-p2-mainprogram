package uy.edu.um.prog2.adt.heap;

public interface Heap<T extends Comparable<T>>{

    void insert(T value);

    T deleteAndReturn();

    int size();
}
