import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Digraph {
    private final int V;
    private int E;
    private ArrayList<Integer>[] adj;

    public Digraph(int vertex){
        this.V = vertex;
        this.E = 0;
        adj = (ArrayList<Integer>[]) new ArrayList[vertex];
        for(int i = 0; i < this.V; i++){
            adj[i] = new ArrayList<>();
        }
    }

    public Digraph(Scanner in){
        StringTokenizer line = new StringTokenizer(in.nextLine());
        V = Integer.parseInt(line.nextToken());
        adj = (ArrayList<Integer>[]) new ArrayList[V];
        for(int i = 0; i < this.V; i++){
            adj[i] = new ArrayList<>();
        }
        int m = Integer.parseInt(line.nextToken());
        for(int i = 0; i < m; i++){
            line = new StringTokenizer(in.nextLine());
            int v = Integer.parseInt(line.nextToken());
            int w = Integer.parseInt(line.nextToken());
            addEdge(v, w);
        }

    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public int outdegree(int v){
        return adj[v].size();
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges \n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append('\n');
        }
        return s.toString();
    }

    public Digraph reverse(Digraph g){
        Digraph reversed = new Digraph(g.V());
        for(int v = 0; v < g.V(); v++){
            for(int w: adj(v)){
                reversed.addEdge(w, v);
            }
        }
        return reversed;
    }
}
