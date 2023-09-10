import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] tree;
    static int[] parent;

    public static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()){
            int cur = q.poll();
            int size = tree[cur].size();

            for (int i = 0; i < size; i++){
                if (parent[tree[cur].get(i)] == 0){
                    parent[tree[cur].get(i)] = cur;
                    q.add(tree[cur].get(i));
                }
            }
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n + 1];
        parent = new int[n + 1];
        parent[1] = 1;
        StringTokenizer st;
        int a = 0, b = 0;

        for (int i = 1; i <= n; i++){
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        bfs(1);

        for (int i = 2; i <= n; i++){
            sb.append(parent[i]).append('\n');
        }

        System.out.println(sb);
    }
}