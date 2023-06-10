package Main.Java.uy.edu.um.adt.hashtable;

import uy.edu.um.adt.linkedlist.MyList;

public class HashLinkedList<K, V> implements MyList<HashNode<K, V>> {
    private HashNode<K, V> first;
    private HashNode<K, V> last;

    public HashLinkedList() {
        this.first = null;
        this.last = null;
    }

    @Override
    public void add(HashNode<K, V> node) {
        if (node != null) {
            if (first == null) {
                first = node;
                last = node;
            } else {
                last.setNext(node);
                last = node;
            }
        }
    }

    @Override
    public HashNode<K, V> get(int position) {
        HashNode<K, V> node = null;
        if (position == 0) {
            return first;
        }
        HashNode<K, V> current = first.getNext();
        int size = this.size();
        int posiciones = size - 1;
        for (int i = 1; i <= posiciones; i++) {
            if (i == position) {
                return current;
            }
            current = current.getNext();
        }
        return node;
    }

    public V get(K key) {
        HashNode<K, V> current = first;
        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.getNext();
        }
        return null; // Si no se encuentra la clave, retorna null o puedes lanzar una excepción
    }
    public HashNode<K, V> getNode(K key) {
        HashNode<K, V> current = first;
        while (current != null) {
            if (current.getKey().equals(key)) {
                return current;
            }
            current = current.getNext();
        }
        return null; // Si no se encuentra la clave, retorna null o puedes lanzar una excepción
    }

    @Override
    public void remove(HashNode<K, V> node) {
        HashNode<K, V> beforeCurrentNode = null;
        HashNode<K, V> currentNode = this.first;
        while (currentNode != null && currentNode != node) {
            beforeCurrentNode = currentNode;
            currentNode = beforeCurrentNode.getNext();
        }
        if (currentNode != null) {
            if (currentNode == node) {
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
        if (first == null) {
            return 0;
        } else {
            int size = 1;
            HashNode<K, V> current = first;
            while (current.getNext() != null) {
                size++;
                current = current.getNext();
            }
            return size;
        }
    }

    public void print() {
        HashNode<K, V> current = first;
        for (int i = 0; i < this.size(); i++) {
            System.out.print(current.getValue() );
            if (current.getNext() != null) {
                current = current.getNext();
            }
        }
    }

    public HashNode<K, V> getFirst() {
        return first;
    }

    public void setFirst(HashNode<K, V> first) {
        this.first = first;
    }

    public HashNode<K, V> getLast() {
        return last;
    }

    public void setLast(HashNode<K, V> last) {
        this.last = last;
    }
}

