package adt.heap;

public class MyHeap <T extends Comparable<T>> implements Heap<T> {
    private T[] values;
    private int lastValuePosition;
    private boolean isHeapMax;
    private boolean isHeapMin;
    public MyHeap(T[] values, boolean heapMax, boolean heapMin) {
        this.values = values;
        this.lastValuePosition = 0;
        this.isHeapMax = heapMax;
        this.isHeapMin = heapMin;
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
        this.values[lastValuePosition] = value;
        int valuePos = lastValuePosition;
        lastValuePosition++;
        if (isHeapMax == true) {
            while (valuePos != 0 && value.compareTo(getFather(valuePos))>0){
                T change = getFather(valuePos);
                this.values[getFatherPosition(valuePos)] = value;
                this.values[valuePos] = change;
                valuePos = getFatherPosition(valuePos);
            }
        }
        if (isHeapMin==true) {
            while (valuePos!=0 && value.compareTo(getFather(valuePos))<0){
                T change = getFather(valuePos);
                this.values[getFatherPosition(valuePos)] = value;
                this.values[valuePos] = change;
                valuePos = getFatherPosition(valuePos);
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

    // Restablecer el orden del heap en caso de ser un Heap máximo
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

    // Restablecer el orden del heap en caso de ser un Heap mínimo
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


    @Override
    public int size() {
        return lastValuePosition;
    }
}
