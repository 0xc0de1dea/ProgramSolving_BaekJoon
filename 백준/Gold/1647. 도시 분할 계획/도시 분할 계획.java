import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    public int a, b;
    public int cost;

    public Edge(int a, int b, int cost){
        this.a = a;
        this.b = b;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge target){
        return this.cost - target.cost;
    }
}

public class Main {
    static int[] parent;
    static int[] rank;

    public static int find_root(int x){
        if (parent[x] == x) return x;

        return parent[x] = find_root(parent[x]);
    }

    public static boolean union_root(int a, int b){
        a = find_root(a);
        b = find_root(b);

        if (a != b){
            if (rank[a] > rank[b]) parent[b] = a;
            else parent[a] = b;

            if (rank[a] == rank[b]){
                rank[b]++;
            }

            return true;
        }

        return false;
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Edge[] edge = new Edge[m];
        parent = new int[n + 1];
        rank = new int[n + 1];

        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edge[i] = new Edge(a, b, cost);
        }

        for (int i = 1; i <= n; i++){
            parent[i] = i;
        }

        Arrays.sort(edge);
        int sum = 0;
        int last = 0;
        
        for (int i = 0; i < m; i++){
            Edge cur = edge[i];
            boolean check = union_root(cur.a, cur.b);

            if (check){
                sum += cur.cost;
                last = cur.cost;
            }
        }

        System.out.println(sum - last);
    }
}