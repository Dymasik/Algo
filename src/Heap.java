import java.util.Comparator;
import java.util.Random;

public class Heap<Key> {

    private int n;
    private Key[] keys;
    private Comparator comparator;

    public Heap(){
        n = 0;
        keys = (Key[])new Object[2];
    }

    public Heap(int max){
        n = 0;
        keys = (Key[]) new Object[max + 1];
    }

    public Heap(int max, Comparator comparator){
        n = 0;
        keys = (Key[]) new Object[max + 1];
        this.comparator = comparator;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    public Key max(){
        return keys[1];
    }

    private void resize(int capacity){
        Key[] temp = (Key[]) new Object[capacity];
        for(int i = 1; i <= n; i++)
            temp[i] = keys[i];
        keys = temp;
    }

    public void insert(Key key){
        if(n == keys.length - 1)
            resize(keys.length * 2);
        n++;
        keys[n] = key;
        swim(n);
    }

    public Key delMax(){
        Key max = keys[1];
        exch(1, n--);
        sink(1);
        keys[n + 1] = null;
        if ((n > 0) && (n == (keys.length - 1) / 4)) resize(keys.length / 2);
        return max;
    }

    private void swim(int index){
        while(index > 1 && less(index / 2, index)){
            exch(index, index / 2);
            index /= 2;
        }
    }

    private void sink(int index){
        while(2 * index <= n){
            int j = 2 * index;
            if(j < n && less(j, j + 1))j++;
            if(!less(index, j))break;
            exch(index, j);
            index = j;
        }
    }

    private boolean less(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) keys[i]).compareTo(keys[j]) < 0;
        }
        else {
            return comparator.compare(keys[i], keys[j]) < 0;
        }
    }

    private void exch(int i, int j) {
        Key swap = keys[i];
        keys[i] = keys[j];
        keys[j] = swap;
    }

    public static void main(String[] args){
        Heap<Integer> heap = new Heap<Integer>();
        Random r = new Random();
        for(int i = 0; i < 4; i++){
            heap.insert(r.nextInt(20));
        }
        System.out.println(heap.max());
        System.out.println(heap.delMax());
        System.out.println(heap.max());
    }
}
