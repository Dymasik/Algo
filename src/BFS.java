import java.util.AbstractQueue;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFS {
    private boolean[] used;
    private int s;
    private int[] dist;
    private int[] edgeTo;

    public BFS(Graph g, int s){
        this.s = s;
        used = new boolean[g.V()];
        dist = new int[g.V()];
        edgeTo = new int[g.V()];
        for(int i = 0; i < g.V(); i++)
            dist[i] = Integer.MAX_VALUE;
        bfs(g);
    }

    private void bfs(Graph g){
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(s);
        used[s] = true;
        dist[s] = 0;
        while(!queue.isEmpty()){
            int v = queue.poll();
            for(int w: g.adj(v)){
                if(!used[w]){
                    edgeTo[w] = v;
                    used[w] = true;
                    dist[w] = dist[v] + 1;
                    queue.add(w);
                }
            }
        }
    }

    public BFS(Digraph g, int s){
        this.s = s;
        used = new boolean[g.V()];
        dist = new int[g.V()];
        edgeTo = new int[g.V()];
        for(int i = 0; i < g.V(); i++)
            dist[i] = Integer.MAX_VALUE;
        bfs(g);
    }

    private void bfs(Digraph g){
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(s);
        used[s] = true;
        dist[s] = 0;
        while(!queue.isEmpty()){
            int v = queue.poll();
            for(int w: g.adj(v)){
                if(!used[w]){
                    edgeTo[w] = v;
                    used[w] = true;
                    dist[w] = dist[v] + 1;
                    queue.add(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v){
        return used[v];
    }

    public int distTo(int v){
        return dist[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v))
            return null;
        Stack<Integer> stack = new Stack<>();
        for(int i = v; dist[i] != 0; i = edgeTo[i]){
            stack.push(i);
        }
        stack.push(s);
        return stack;
    }
}
