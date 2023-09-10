import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    static int[] rank;

    public static int find_Root(int node){
        if (node == parent[node]) return node;

        return parent[node] = find_Root(parent[node]);
    }

    public static void union_Root(int node1, int node2){
        node1 = find_Root(node1);
        node2 = find_Root(node2);

        if (node1 != node2){
            if (rank[node1] < rank[node2]) parent[node1] = node2;
            else parent[node2] = node1;

            if (rank[node1] == rank[node2]){
                rank[node1]++;
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
        rank[0] = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++){
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int knownCnt = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < knownCnt; i++){
            union_Root(0, Integer.parseInt(st.nextToken()));
        }

        ArrayList<Integer>[] guests = new ArrayList[m];

        for (int i = 0; i < m; i++){
            guests[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            guests[i].add(num);
            guests[i].add(target);

            for (int j = 2; j <= num; j++){
                guests[i].add(Integer.parseInt(st.nextToken()));
                union_Root(target, guests[i].get(j));
            }
        }

        int cnt = m;

        if (knownCnt != 0) {
            for (int i = 0; i < m; i++){
                boolean check = true;
                int num = guests[i].get(0);

                for (int j = 1; j <= num; j++){
                    if (find_Root(guests[i].get(j)) == 0){
                        check = false;
                        break;
                    }
                }

                cnt -= check ? 0 : 1;
            }
        }

        System.out.println(cnt);
    }
}