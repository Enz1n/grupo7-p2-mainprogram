package uy.edu.um.prog2.adt.queue;

public class PriorityQueue<T> implements MyPriorityQueue<T> {


    private NodoPriority<T> primero;


    private NodoPriority<T> ultimo;

    public PriorityQueue() {
        this.primero = null;
        this.ultimo = null;
    }

    public NodoPriority<T> getPrimero() {
        return primero;
    }

    public void setPrimero(NodoPriority<T> primero) {
        this.primero = primero;
    }

    public NodoPriority<T> getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoPriority<T> ultimo) {
        this.ultimo = ultimo;
    }
    @Override
    public void enqueueWithPriority(T element, int prioridad) {
        NodoPriority<T> newnodo = new NodoPriority<>(element,prioridad);

        if (isEmpty()){
            setPrimero(newnodo);
            setUltimo(newnodo);
        }else {
            NodoPriority<T> aux = getPrimero();
            NodoPriority<T> aux2 =null;
            if (aux.getSiguiente() == null){
                if (newnodo.getPriority() <= aux.getPriority()){
                    this.enqueuerigth(element,prioridad);
                }else{
                    this.enqueueleft(element,prioridad);
                }

            }else {
                while ((aux.getSiguiente() != null) && (aux.getPriority() >= newnodo.getPriority())){
                    aux2 = aux;
                    aux = aux.getSiguiente();
                }
                if (aux.getPriority() < newnodo.getPriority()){
                    if (aux2 == null){
                        this.enqueueleft(element,prioridad);
                    }else {
                        aux2.setSiguiente(newnodo);
                        newnodo.setSiguiente(aux);
                    }
                }

                if (aux.getSiguiente() == null) {
                    aux.setSiguiente(newnodo);
                    setUltimo(newnodo);
                    newnodo.setSiguiente(null);
                }
            }


        }
    }
    @Override
    public void enqueueleft(T element,int priority) {
        NodoPriority<T> newnodo = new NodoPriority<>(element,priority);
        if (isEmpty()) {
            setUltimo(newnodo);
            setPrimero(newnodo);
        }else {
            newnodo.setSiguiente(getPrimero());
            setPrimero(newnodo);
        }
    }

    @Override
    public void borrarElemento(T elemento) {

    }

    @Override
    public void enqueueleft(T element) {

    }

    @Override
    public void enqueuerigth(T element) {

    }

    @Override
    public T dequeuerigth() throws EmptyQueueException {
        return null;
    }


    @Override
    public void enqueuerigth(T element, int priority) {
        NodoPriority<T> newnodo = new NodoPriority<>(element,priority);
        if (isEmpty()) {
            setUltimo(newnodo);
            setPrimero(newnodo);
        }else {
            getUltimo().setSiguiente(newnodo);
            setUltimo(newnodo);
            newnodo.setSiguiente(null);
        }
    }

    public T dequeueRight() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("La cola está vacía");
        }
        NodoPriority<T> nodoPrioridad = getPrimero();
        setPrimero(nodoPrioridad.getSiguiente());

        if (getPrimero() == null) {
            setUltimo(null);
        }

        return nodoPrioridad.getValue();
    }

    public T dequeueleft() throws EmptyQueueException {
        return null;
    }

    public boolean isEmpty() {
        return (getPrimero() == null) && (getUltimo() == null);
    }

    public void printqueue() {
        NodoPriority<T> aux = getPrimero();
        while (aux.getSiguiente() != null){
            System.out.print(aux.getValue() + ", ");
            aux = aux.getSiguiente();
        }
        System.out.println(aux.getValue());
    }

    @Override
    public boolean contains(T elemento) {
        return false;
    }
}
