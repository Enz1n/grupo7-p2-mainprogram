package Main.Java.uy.edu.um.adt.queue;

public class NodoPriority<T> {
    private T value;

    private int priority;
    private NodoPriority<T> siguiente;

    public NodoPriority(T value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public NodoPriority<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoPriority<T> siguiente) {
        this.siguiente = siguiente;
    }
}
