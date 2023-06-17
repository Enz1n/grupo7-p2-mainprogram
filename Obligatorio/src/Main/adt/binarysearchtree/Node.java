package adt.binarysearchtree;

public class Node <K extends Comparable<K>, V> {
    private K key;
    private V value;
    private Node<K, V> left;
    private Node<K, V> right;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
    }
    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<K, V> getLeft() {
        return left;
    }

    public void setLeft(Node<K, V> left) {
        this.left = left;
    }

    public Node<K, V> getRight() {
        return right;
    }

    public void setRight(Node<K, V> right) {
        this.right = right;
    }

    // Métodos para el árbol binario de busqueda:

    public void add(K key, V value) {
        Node<K, V> elementToAdd = new Node<>(key, value);
        if (key.compareTo(this.key) > 0) {
            if (right == null) {
                right = elementToAdd;
            } else {
                right.add(key, value);
            }
        } else {
            if (left == null) {
                left = elementToAdd;
            } else {
                left.add(key, value);
            }
        }
    }

    public Node<K, V> findMin() {
        Node<K, V> Return = this;
        if (left != null) {
            Return = left.findMin();
        }
        return Return;
    }

    public Node<K, V> delete(K key) {
        Node<K, V> elementToReturn = this;
        if (key.compareTo(this.key) > 0) {
            if (right != null) {
                right = right.delete(key);
            }
        } else if (key.compareTo(this.key) < 0) {
            if (left != null) {
                left = left.delete(key);
            }
        } else if (left != null && right != null) {
            Node<K, V> min = right.findMin();
            this.key = min.getKey();
            this.value = min.getValue();
            right = right.delete(min.getKey());
        } else {
            if (left != null) {
                elementToReturn = left;
            } else {
                elementToReturn = right;
            }
        }
        return elementToReturn;
    }
}
