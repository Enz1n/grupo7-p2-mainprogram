package Main.Java.uy.edu.um.adt.binarysearchtree;

public class MyBinarySearchTree <K extends Comparable<K>, V> implements BinarySearchTree<K, V>{
    private Node<K, V> root;


    public MyBinarySearchTree() {
        root = null;
    }

    public Node<K, V> getRoot() {
        return root;
    }

    public void setRoot(Node<K, V> root) {
        this.root = root;
    }


    @Override
    public void add(K key, V value) {
        Node<K, V> elementToAdd = new Node<>(key, value);
        if (root == null) {
            root = elementToAdd;
        } else {
            root.add(key, value);
        }
    }
    @Override
    public void remove(K key) {
        if (root != null) {
            root = root.delete(key);
        }
    }

    @Override
    public V find(K key) {
        return findAux(key, root);
    }

    private V findAux(K key, Node<K, V> root){
        V value = null;
        if (root != null) {
            if (root.getKey().compareTo(key) == 0) {
                value = root.getValue();
            } else if (key.compareTo(root.getKey()) > 0) {
                value = findAux(key, root.getRight());
            } else {
                value = findAux(key, root.getLeft());
            }
        }
        return value;
    }

}
