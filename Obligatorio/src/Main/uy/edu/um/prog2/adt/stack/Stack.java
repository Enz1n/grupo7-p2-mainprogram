package uy.edu.um.prog2.adt.stack;

import java.util.EmptyStackException;

public class Stack<T extends Comparable<T>> implements MyStack<T> {

    public Stack() {
    }
    public Stack(Nodo<T> ultimo) {
        this.ultimo = ultimo;
    }
    private Nodo<T> ultimo;
    public Nodo<T> getUltimo() {
        return ultimo;
    }

    public void setUltimo(Nodo<T> ultimo) {
        this.ultimo = ultimo;
    }

    @Override
    public void pop() throws EmptyStackException {
        if (this.isEmpty()){
            throw new EmptyStackException();
        }
       setUltimo(getUltimo().getSiguiente());

    }

    @Override
    public T top() throws EmptyStackException {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return getUltimo().getValue();
    }

    @Override
    public void push(T element) {
        Nodo<T> nuevonodo = new Nodo<T>(element);
        if (this.isEmpty()) {
            setUltimo(nuevonodo);
        } else {
            Nodo<T> aux = getUltimo();
            nuevonodo.setSiguiente(aux);
            setUltimo(nuevonodo);
        }
    }
    @Override
    public void print() {
        Nodo<T> nodoActual = getUltimo();
        while (nodoActual != null) {
            System.out.print(nodoActual.getValue() +", ");
            nodoActual = nodoActual.getSiguiente();
        }
        System.out.println();
    }
    @Override
    public boolean isEmpty() {
        return (ultimo == null);
    }

    @Override
    public void makeEmpty() {
        while (!isEmpty()){
            pop();
        }
    }
    @Override
    public boolean contains(T elemento) {
        Nodo<T> nodoActual = ultimo;
        while (nodoActual != null) {
            if (nodoActual.getValue().equals(elemento)) {
                return true;
            }
            nodoActual = nodoActual.getSiguiente();
        }
        return false;
    }
    @Override
    public void delete(T elemento) {
        Nodo<T> nodoAnterior = null;
        Nodo<T> nodoActual = ultimo;
        while (nodoActual != null && !nodoActual.getValue().equals(elemento)) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getSiguiente();
        }
        if (nodoActual == null) {
            return; // el elemento no está en la pila
        }
        if (nodoActual == ultimo) {
            ultimo = nodoAnterior;
        }
        if (nodoAnterior == null) {
            // el nodo a eliminar es el último de la pila
            ultimo = null;
        } else {
            nodoAnterior.setSiguiente(nodoActual.getSiguiente());
        }
    }
}