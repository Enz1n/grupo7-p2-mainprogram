package adt.heap;

import java.util.Arrays;

public class MyHeap<T extends Comparable<T>> implements Heap<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] values;
    private int lastValuePosition;
    private boolean isHeapMax;
    private boolean isHeapMin;

    public MyHeap(boolean heapMax, boolean heapMin) {
        this.values = (T[]) new Comparable[DEFAULT_CAPACITY];
        this.lastValuePosition = 0;
        this.isHeapMax = heapMax;
        this.isHeapMin = heapMin;
    }

    private void ensureCapacity() {
        if (lastValuePosition == values.length) {
            int newCapacity = values.length * 2;
            values = Arrays.copyOf(values, newCapacity);
        }
    }

    private T getFather (int childPos){
        return values[(childPos-1)/2];
    }
    private int getFatherPosition (int childPos) {
        return (childPos-1)/2;
    }
    private T getLeftChild (int fatherPos){
        return values[2*fatherPos + 1];
    }
    private int getLeftChildPosition (int fatherPos){
        return 2*fatherPos + 1;
    }
    private T getRightChild (int fatherPos){
        return values[2*fatherPos + 2];
    }
    private int getRightChildPosition (int fatherPos){
        return 2*fatherPos + 2;
    }

    @Override
    public void insert(T value) {
        ensureCapacity();
        this.values[lastValuePosition] = value;
        int valuePos = lastValuePosition;
        lastValuePosition++;

        if (isHeapMax) {
            while (valuePos != 0 && value.compareTo(getFather(valuePos)) > 0) {
                int fatherPos = getFatherPosition(valuePos);
                swap(valuePos, fatherPos);
                valuePos = fatherPos;
            }
        }

        if (isHeapMin) {
            while (valuePos != 0 && value.compareTo(getFather(valuePos)) < 0) {
                int fatherPos = getFatherPosition(valuePos);
                swap(valuePos, fatherPos);
                valuePos = fatherPos;
            }
        }
    }

    @Override
    public T deleteAndReturn() {
        if (lastValuePosition == 0) {
            return null; // No hay elementos en el heap
        }

        T root = values[0];
        T lastValue = values[lastValuePosition - 1];
        values[0] = lastValue;
        values[lastValuePosition - 1] = null; // Eliminar el último valor del heap
        lastValuePosition--;

        if (isHeapMax) {
            heapifyMax(0);
        } else if (isHeapMin) {
            heapifyMin(0);
        }

        return root;
    }

    private void heapifyMax(int index) {
        int largest = index;
        int leftChild = getLeftChildPosition(index);
        int rightChild = getRightChildPosition(index);

        if (leftChild < lastValuePosition && values[leftChild].compareTo(values[largest]) > 0) {
            largest = leftChild;
        }

        if (rightChild < lastValuePosition && values[rightChild].compareTo(values[largest]) > 0) {
            largest = rightChild;
        }

        if (largest != index) {
            swap(index, largest);
            heapifyMax(largest);
        }
    }

    private void heapifyMin(int index) {
        int smallest = index;
        int leftChild = getLeftChildPosition(index);
        int rightChild = getRightChildPosition(index);

        if (leftChild < lastValuePosition && values[leftChild].compareTo(values[smallest]) < 0) {
            smallest = leftChild;
        }

        if (rightChild < lastValuePosition && values[rightChild].compareTo(values[smallest]) < 0) {
            smallest = rightChild;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapifyMin(smallest);
        }
    }

    // Intercambiar dos elementos en el arreglo de valores
    private void swap(int i, int j) {
        T temp = values[i];
        values[i] = values[j];
        values[j] = temp;
    }

    public T get(int index) {
        if (index < 0 || index >= lastValuePosition) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        return values[index];
    }


    @Override
    public int size() {
        return lastValuePosition;
    }
}
