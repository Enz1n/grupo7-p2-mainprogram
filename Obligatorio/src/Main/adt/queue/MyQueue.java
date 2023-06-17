package adt.queue;

public interface MyQueue<T> {
    void borrarElemento(T elemento);

    void enqueueleft(T element);
    void enqueuerigth(T element);
    T dequeuerigth() throws EmptyQueueException;
    T dequeueleft() throws EmptyQueueException;
    boolean isEmpty();
    void printqueue();

    boolean contains(T elemento);
}
