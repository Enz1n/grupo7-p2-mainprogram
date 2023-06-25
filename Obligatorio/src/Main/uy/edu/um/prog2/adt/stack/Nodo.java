package uy.edu.um.prog2.adt.stack;

public class Nodo<T> {
    private T value;
    private Nodo<T> siguiente;

    public Nodo() {
    }

    public Nodo(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
}
