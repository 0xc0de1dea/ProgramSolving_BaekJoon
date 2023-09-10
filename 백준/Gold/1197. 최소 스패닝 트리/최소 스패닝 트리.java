import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Vertex {
    public int a, b;
    public int cost;

    public Vertex(int a, int b, int cost){
        this.a = a;
        this.b = b;
        this.cost = cost;
    }
}

public class Main {
    static int[] parent;
    static int[] rank;
    static PriorityQueue<Vertex> pq = new PriorityQueue<>(new Comparator<Vertex>() {
        @Override
        public int compare(Vertex o1, Vertex o2){
            return o1.cost - o2.cost;
        }
    });

    public static int find_root(int x){
        if (parent[x] == x) return x;

        return parent[x] = find_root(parent[x]);
    }

    public static void union_root(int x, int y){
        if (x != y){
            if (rank[x] > rank[y]) parent[y] = x;
            else parent[x] = y;

            if (rank[x] == rank[y]){
                rank[y]++;
            }
        }
    }

    public static int kruskal(int v){
        int min = 0;
        int cnt = 1;

        while (cnt < v){
            Vertex cur = pq.poll();
            int x = find_root(cur.a);
            int y = find_root(cur.b);

            if (x != y){
                union_root(x, y);
                min += cur.cost;
                cnt++;
            }
        }

        return min;
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        parent = new int[v + 1];
        rank = new int[v + 1];
        
        for (int i = 1; i <= v; i++){
            parent[i] = i;
        }

        for (int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Vertex(a, b, c));
        }

        int min = kruskal(v);
        System.out.println(min);
    }
}