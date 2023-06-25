package uy.edu.um.prog2.adt.linkedlist;


import uy.edu.um.prog2.adt.stack.MyStack;
import uy.edu.um.prog2.adt.stack.Stack;

public class MyLinkedList<T> implements MyList<T> {
    private Node<T> first;
    private Node<T> last;

    public Node<T> getFirst() {
        return first;
    }

    public void setFirst(Node<T> first) {
        this.first = first;
    }

    public Node<T> getLast() {
        return last;
    }

    public void setLast(Node<T> last) {
        this.last = last;
    }

    public MyLinkedList() {
        this.first = null;
        this.last = null;
    }

    @Override
    public void add(T value) {
        if (value != null) {
            Node<T> nodeToAdd = new Node<>(value);
            if (first == null) {
                first = nodeToAdd;
                last = nodeToAdd;
            } else {
                last.setNext(nodeToAdd);
                last = nodeToAdd;
            }
        }
    }
    @Override
    public Node<T> getObject(T value) {
        Node<T> current = first;
        while (current != null) {
            if (current.getValue().equals(value)) {
                return current;
            }
            current = current.getNext();
        }
        return null; // El valor no se encontró en la lista
    }

    @Override
    public T get(int position) {
        T value = null;
        if (position == 0){
            return first.getValue();
        }
        Node<T> current = first.getNext();
        int size = this.size();
        int posiciones = size - 1;
        for (int i = 1 ; i <= posiciones; i++){
            if (i == position){
                return current.getValue();
            }
            current = current.getNext();
        }
        return value;
    }

    @Override
    public void remove(T value) {
        Node<T> beforeCurrentNode = null;
        Node<T> currentNode = this.first;
        while (currentNode != null && currentNode.getValue() != value) {
            beforeCurrentNode = currentNode;
            currentNode = beforeCurrentNode.getNext();
        }
        if (currentNode != null) {
            if (currentNode.getValue().equals(value)) {
                if (currentNode == this.first && currentNode == this.last) {
                    this.first = null;
                } else if (currentNode == this.first) {
                    this.first = currentNode.getNext();
                } else if (currentNode == this.last) {
                    this.last = beforeCurrentNode;
                    this.last.setNext(null);
                } else {
                    beforeCurrentNode.setNext(currentNode.getNext());
                }
            }
        }
    }

    @Override
    public int size() {
        if (first == null){
            return 0;
        }
        else{
            int size = 1;
            Node<T> current = first;
            while (current.getNext() != null){
                size++;
                current = current.getNext();
            }
            return size;
        }
    }



    @Override
    public boolean contains(T value) {
        Node<T> current = first;
        while (current != null) {
            if (current.getValue().equals(value)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void swap(int index1, int index2) {
        if (index1 < 0 || index1 >= size() || index2 < 0 || index2 >= size()) {
            throw new IndexOutOfBoundsException();
        }

        if (index1 == index2) {
            return;
        }

        Node<T> node1 = getNode(index1);
        Node<T> node2 = getNode(index2);

        T temp = node1.getValue();
        node1.setValue(node2.getValue());
        node2.setValue(temp);
    }

    public Node<T> getNode(int position) {
        if (position < 0 || position >= size()) {
            return null;
        }

        Node<T> current = first;
        for (int i = 0; i < position; i++) {
            current = current.getNext();
        }
        return current;
    }

    public void print(){
        Node<T> current = first;
        for (int i = 0 ; i < this.size() ; i++){
            System.out.println(current.getValue());
            if (current.getNext() != null){
                current = current.getNext();
            }
        }
    }

    public boolean isEmpty() {
        if (first == null){
            return true;
        }
        else{
            return false;
        }
    }

    public void addLast(T user) {
        Node<T> nodeToAdd = new Node<>(user);
        if (first == null) {
            first = nodeToAdd;
            last = nodeToAdd;
        } else {
            last.setNext(nodeToAdd);
            last = nodeToAdd;
        }
    }

    public void removeLast() {
        Node<T> current = first;
        Node<T> previous = null;
        while (current.getNext() != null){
            previous = current;
            current = current.getNext();
        }
        if (previous == null){
            first = null;
        }
        else{
            previous.setNext(null);
        }
    }

    public void quicksort() {
        if (first != null && first != last) {
            first = quicksortRecursive(first, last);
        }
    }

    private Node<T> quicksortRecursive(Node<T> head, Node<T> tail) {
        if (head == null || head == tail) {
            return head;
        }

        Node<T> pivot = tail;
        Node<T> prev = null;
        Node<T> current = head;

        while (current != pivot) {
            if (compareTweetsQty(current.getValue(), pivot.getValue()) <= 0) {
                if (prev != null) {
                    prev.setNext(current.getNext());
                }
                if (current == head) {
                    head = current.getNext();
                }
                Node<T> temp = current.getNext();
                current.setNext(pivot.getNext());
                pivot.setNext(current);
                current = temp;
            } else {
                prev = current;
                current = current.getNext();
            }
        }

        Node<T> newHead = quicksortRecursive(head, prev.getNext());
        Node<T> newTail = pivot.getNext();
        pivot.setNext(quicksortRecursive(pivot.getNext(), tail));

        if (newHead == null) {
            return pivot;
        }

        return newHead;
    }


    private int compareTweetsQty(T user1, T user2) {
        int tweetsQty1 = ((entities.User) user1).getTweets().size();
        int tweetsQty2 = ((entities.User) user2).getTweets().size();
        return Integer.compare(tweetsQty1, tweetsQty2);
    }





}

