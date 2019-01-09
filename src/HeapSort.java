import java.util.Random;

public class HeapSort {
    public HeapSort(){}

    public static void Sort(Comparable[] a){
        int n = a.length;
        for(int i = n / 2; i >= 1; i--){
            sink(a, i, n);
        }
        while(n > 1){
            exch(a, 1, n--);
            sink(a, 1, n);
        }
    }

    private static void sink(Comparable[] a, int k, int n){
        while(2 * k <= n){
            int j = 2 * k;
            if(j < n && less(a, j, j + 1))j++;
            if(!less(a, k, j))break;
            exch(a, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[] a, int i, int j){
        return a[i - 1].compareTo(a[j - 1]) < 0;
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable temp = a[i - 1];
        a[i - 1] = a[j - 1];
        a[j - 1] = temp;
    }

    public static void main(String[] args){
        Random r = new Random();
        Integer[] a = new Integer[50];
        for(int i = 0; i < 50; i++){
            a[i] = r.nextInt(50);
        }
        HeapSort.Sort(a);
        for(int i = 0; i < 50; i++)
            System.out.print(a[i] + " ");
    }
}
