package uy.edu.um.prog2.adt.hashtable;

public interface HashTable<K, V extends Comparable<V>> {
        public void put(K key, V value);
        HashNode<K, V> get(K key);
        public boolean contains(K key);
        public void remove(K key);
        int size();
        boolean isEmpty();
}
