package Main.Java.uy.edu.um.adt.queue;

public class Queue<T> implements MyQueue<T>{
    private Nodo<T> primero;

    private Nodo<T> ultimo;

    public Queue() {
        this.primero = null;
        this.ultimo = null;
    }

    public Nodo<T> getPrimero() {
        return primero;
    }

    public void setPrimero(Nodo<T> primero) {
        this.primero = primero;
    }

    public Nodo<T> getUltimo() {
        return ultimo;
    }

    public void setUltimo(Nodo<T> ultimo) {
        this.ultimo = ultimo;
    }

    @Override
    public void borrarElemento(T elemento) {
        Nodo<T> nodoAnterior = null;
        Nodo<T> nodoActual = primero;
        while (nodoActual != null && !nodoActual.getValue().equals(elemento)) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getSiguiente();
        }
        if (nodoActual == null) {
            return; // el elemento no está en la cola
        }
        if (nodoAnterior == null) {
            primero = nodoActual.getSiguiente();
        } else {
            nodoAnterior.setSiguiente(nodoActual.getSiguiente());
        }
        if (nodoActual == ultimo) {
            ultimo = nodoAnterior;
        }
    }
    @Override
    public void enqueueleft(T element) {
        Nodo<T> newnodo = new Nodo<>(element);
        if (isEmpty()) {
            setUltimo(newnodo);
            setPrimero(newnodo);
        }else {
            newnodo.setSiguiente(getPrimero());
            setPrimero(newnodo);
        }
    }

    @Override
    public void enqueuerigth(T element) {
        Nodo<T> newnodo = new Nodo<>(element);
        if (isEmpty()) {
            setUltimo(newnodo);
            setPrimero(newnodo);
        }else {
            getUltimo().setSiguiente(newnodo);
            setUltimo(newnodo);
            newnodo.setSiguiente(null);
        }
    }

    @Override
    public T dequeuerigth() throws EmptyQueueException {
        T item = null;
        if (isEmpty()) {
            throw new EmptyQueueException();
        } else {
            item = getUltimo().getValue();
            Nodo<T> aux = getPrimero();
            if (aux.getSiguiente() == null) { // un elemento solo
                setPrimero(null);
                setUltimo(null);
            } else {
                while (aux.getSiguiente() != getUltimo()) {
                    aux = aux.getSiguiente();
                }
                setUltimo(aux);
                aux.setSiguiente(null);
            }
        }
        return item;

    }

    @Override
    public T dequeueleft() throws EmptyQueueException {
        T item = null;
        if (isEmpty()) {
            throw new EmptyQueueException();
        } else {
            item = getPrimero().getValue();
            setPrimero(getPrimero().getSiguiente());
        }
        return item;
    }


    @Override
    public boolean isEmpty() {
        return (getPrimero() == null) && (getUltimo() == null);
    }

    @Override
    public void printqueue() {
        Nodo<T> aux = getPrimero();
        while (aux.getSiguiente() != null){
            System.out.print(aux.getValue() + ", ");
            aux = aux.getSiguiente();
        }
        System.out.println(aux.getValue());
    }

    @Override
    public boolean contains(T elemento) {
        Nodo<T> nodoActual = primero;
        while (nodoActual != null) {
            if (nodoActual.getValue().equals(elemento)) {
                return true;
            }
            nodoActual = nodoActual.getSiguiente();
        }
        return false;
    }
}
