import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class MergeSort {

    private static final int CUTOFF = 7;

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
        if (hi <= lo + CUTOFF) {
            Arrays.sort(a, lo, hi + 1);
            return;
        }
        int mid = (lo + hi) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        if (!less(a[mid+1], a[mid])) {
            System.arraycopy(a, lo, aux, lo, hi - lo + 1);
            return;
        }
        merge(a, aux, lo, mid, hi);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
        for(int k = lo; k <= hi; k++)
            aux[k] = a[k];

        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k++) {
            if(i > mid) a[k] = aux[j++];
            else if(j > hi) a[k] = aux[i++];
            else if(less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];

        }
    }

    public static void Sort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args){
        Random r = new Random();
        Integer[] a = new Integer[50];
        for(int i = 0; i < 50; i++){
            a[i] = r.nextInt(50);
        }
        MergeSort.Sort(a);
        for(int i = 0; i < 50; i++)
            System.out.print(a[i] + " ");
    }
}
