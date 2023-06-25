package uy.edu.um.prog2.adt.hashtable;

public class HashNode<K, V extends Comparable<V>> implements Comparable<HashNode<K, V>> {
    private K key;
    private V value;

    private HashNode<K, V> next;

    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
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

    public HashNode<K, V> getNext() {
        return next;
    }

    public void setNext(HashNode<K, V> next) {
        this.next = next;
    }

    @Override
    public int compareTo(HashNode<K, V> o) {
        return this.getValue().compareTo(o.getValue());
    }
}
