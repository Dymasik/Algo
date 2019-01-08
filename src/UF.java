public class UF {
    private int[] id;
    private int[] sz;

    public UF(int n) {
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++){
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int i) {
        while(i != id[i]){
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        if(i == j) return;
        if(sz[i] > sz[j]) {
            id[j] = i;
            sz[i] += sz[j];
        } else {
            id[i] = j;
            sz[j] += sz[i];
        }

    }

    public static void main(String[] args) {
        UF uf = new UF(5);
        uf.union(1, 0);
        uf.union(1, 3);
        uf.union(2, 4);
        System.out.println(uf.connected(1, 2));
        System.out.println(uf.connected(1, 0));
        uf.union(2, 3);
        System.out.println(uf.connected(0, 4));
    }
}
