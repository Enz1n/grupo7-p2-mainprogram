package adt.stack;

import java.util.EmptyStackException;

public interface MyStack<T>{
    void pop() throws EmptyStackException;
    T top() throws EmptyStackException;
    void push(T element);
    boolean isEmpty ();

    public void print();
    void makeEmpty();

    boolean contains(T elemento);

    void delete(T elemento);
}
