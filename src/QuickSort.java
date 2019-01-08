import java.util.Random;

public class QuickSort {
    public static void Sort(Comparable[] a){
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void shuffle(Comparable[] a){
        Random r = new Random();
        for(int i = a.length - 1; i > 0; i--){
            int index = r.nextInt(i + 1);
            exch(a[index], a[i]);
        }
    }

    private static void sort(Comparable[] a, int lo, int hi){
        if(hi <= lo)
            return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo + 1;
        while(i <= gt){
            int cmp = v.compareTo(a[i]);
            if(cmp < 0)exch(a[i], a[gt--]);
            if(cmp > 0)exch(a[lt++], a[i++]);
            if(cmp == 0)i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable v, Comparable w){
        Comparable temp = v;
        v = w;
        w = v;
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
