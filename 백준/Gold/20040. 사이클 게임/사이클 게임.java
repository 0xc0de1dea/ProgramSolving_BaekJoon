import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    static int[] rank;

    public static int find_root(int node){
        if (parent[node] == node) return node;

        return parent[node] = find_root(parent[node]);
    }

    public static void union_root(int x, int y){
        // x = find_root(x);
        // y = find_root(y);

        if (x != y){
            if (rank[x] > rank[y]) parent[y] = x;
            else parent[x] = y;

            if (rank[x] == rank[y]){
                rank[y]++;
            }
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        rank = new int[n + 1];
        rank[n] = Integer.MAX_VALUE;

        for (int i = 0; i <= n; i++){
            parent[i] = i;
        }
        
        for (int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if ((a = find_root(a)) != (b = find_root(b))){
                union_root(a, b);
            }
            else {
                System.out.print(i);
                return;
            }
        }

        System.out.print(0);
    }
}