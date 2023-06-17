package adt.hashtable;

import adt.linkedlist.MyLinkedList;

public class MyHashTable<K, V extends Comparable<V>> implements HashTable<K, V> {

    HashLinkedList<K,V>[] buckets;
    int size;

    private static final int INITIAL_CAPACITY = 10;
    private static final double LOAD_FACTOR = 0.75;
    public MyHashTable() {
        this(INITIAL_CAPACITY);
    }

    public MyHashTable(int initialCapacity) {
        this.size = initialCapacity;
        this.buckets = (HashLinkedList<K, V>[]) new HashLinkedList[initialCapacity];
        for (int i = 0; i < initialCapacity; i++) {
            buckets[i] = new HashLinkedList<>();
        }
    }

    public int hashCode(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % size;
    }

    @Override
    public void put(K key, V value) {
        int code = hashCode(key);
        HashLinkedList<K, V> bucket = buckets[code];
        if (contains(key)) {
            HashNode<K, V> existingNode = bucket.getNode(key);
            existingNode.setValue(value); // Actualiza el valor existente
        } else {
            bucket.add(new HashNode<>(key, value)); // Agrega un nuevo nodo si la clave no existe
        }
        if ((double) size / buckets.length > LOAD_FACTOR) {
            rehash();
        }
    }
    @Override
    public boolean contains(K key) {
        int code = hashCode(key);
        HashLinkedList<K, V> bucket = buckets[code];
        HashNode<K, V> current = bucket.getFirst();
        while (current != null) {
            if (current.getKey().equals(key)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }



    @Override
    public HashNode<K, V> get(K key) {
      int code = hashCode(key);
      return buckets[code].getNode(key);
    }


    private void rehash() {
        int newCapacity = buckets.length * 2;
        HashLinkedList<K, V>[] newBuckets = (HashLinkedList<K, V>[]) new HashLinkedList[newCapacity];

        // Initialize new buckets
        for (int i = 0; i < newCapacity; i++) {
            newBuckets[i] = new HashLinkedList<>();
        }

        // Transfer elements from old buckets to new buckets
        for (int i = 0; i < buckets.length; i++) {
            HashLinkedList<K, V> bucket = buckets[i];
            HashNode<K, V> current = bucket.getFirst();
            while (current != null) {
                int code = hashCode(current.getKey());
                newBuckets[code].add(current);
                current = current.getNext();
            }
        }

        // Update reference to new buckets
        buckets = newBuckets;
    }

    public void printHashTable() {
        for (int i = 0; i < size; i++) {
            System.out.print("Bucket " + i + ": ");
            HashLinkedList<K, V> bucket = buckets[i];
            bucket.print();
            System.out.println();
        }
    }

    public MyLinkedList<HashNode<K, V>> getAllEntries() {
        MyLinkedList<HashNode<K, V>> entries = new MyLinkedList<>();

        for (HashLinkedList<K, V> bucket : buckets) {
            HashNode<K, V> current = bucket.getFirst();
            while (current != null) {
                entries.add(current);
                current = current.getNext();
            }
        }

        return entries;
    }
    @Override
    public void remove(K key) {
        int code = hashCode(key);
        HashNode<K,V> cleannode = buckets[code].getNode(key);
        buckets[code].remove(cleannode);
    }

    @Override
    public int size() {
        int count = 0;
        for (int i = 0; i < buckets.length; i++) {
            count += buckets[i].size();
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
