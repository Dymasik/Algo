import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Graph {
    private final int V;
    private int E;
    private ArrayList<Integer>[] adj;

    public Graph(int vertex){
        this.V = vertex;
        this.E = 0;
        adj = (ArrayList<Integer>[]) new ArrayList[vertex];
        for(int i = 0; i < this.V; i++){
            adj[i] = new ArrayList<>();
        }
    }

    public Graph(Scanner in){
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
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public int degree(int v){
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

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Graph G = new Graph(in);
        CC cc = new CC(G);

        // number of connected components
        int m = cc.count();
        System.out.println(m + " components");

        // compute list of vertices in each connected component
        LinkedList<Integer>[] components = (LinkedList<Integer>[]) new LinkedList[m];
        for (int i = 0; i < m; i++) {
            components[i] = new LinkedList<>();
        }
        for (int v = 0; v < G.V(); v++) {
            components[cc.id(v)].add(v);
        }

        // print results
        for (int i = 0; i < m; i++) {
            for (int v : components[i]) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }

}
