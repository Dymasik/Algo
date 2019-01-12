public class CC {
    private boolean[] used;
    private int[] id;
    private int[] size;
    private int count;

    public CC(Graph g){
        count = 0;
        used = new boolean[g.V()];
        id = new int[g.V()];
        size = new int[g.V()];
        for(int i = 0; i < g.V(); i++){
            if(!used[i]){
                dfs(g, i);
                count++;
            }
        }
    }

    private void dfs(Graph g, int v){
        used[v] = true;
        id[v] = count;
        size[count]++;
        for(int w: g.adj(v)){
            if(!used[w]){
                dfs(g, w);
            }
        }
    }

    public int count(){
        return count;
    }

    public int id(int v){
        return id[v];
    }

    public int size(int v){
        return size[id[v]];
    }

    public boolean connected(int v, int w){
        return id(w) == id(v);
    }
}
