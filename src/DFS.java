import java.util.Stack;

public class DFS {
    private boolean[] used;
    private int[] edgeTo;
    private int s;

    public DFS(Graph g, int s){
        this.s = s;
        edgeTo = new int[g.V()];
        used = new boolean[g.V()];
        dfs(g, s);
    }

    private void dfs(Graph g, int v){
        used[v] = true;
        for(int w: g.adj(v)){
            if(!used[w]){
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

    public DFS(Digraph g, int s){
        this.s = s;
        edgeTo = new int[g.V()];
        used = new boolean[g.V()];
        dfs(g, s);
    }

    private void dfs(Digraph g, int v){
        used[v] = true;
        for(int w: g.adj(v)){
            if(!used[w]){
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

    public boolean hasPathTo(int v){
        return used[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v))
            return null;
        Stack<Integer> stack = new Stack<>();
        for(int i = v; i != s; i = edgeTo[i]){
            stack.push(i);
        }
        stack.push(s);
        return stack;
    }
}
