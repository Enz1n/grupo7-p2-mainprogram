package adt.hashtable;

public interface HashTable<K, V> {
        public void put(K key, V value);
        V get(K key);
        public boolean contains(K key);
        public void remove(K key);
        int size();
        boolean isEmpty();
}
