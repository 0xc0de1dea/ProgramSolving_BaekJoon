import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, v;
    static ArrayList<Integer>[] graph;
    static boolean[] isVisited;
    static StringBuilder sb = new StringBuilder();

    public static void dfs(int start){
        isVisited[start] = true;
        sb.append(start).append(' ');

        for (int next : graph[start]){
            if (!isVisited[next]){
                dfs(next);
            }
        }
    }

    public static void bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        isVisited[start] = true;

        while (queue.size() != 0){
            int cur = queue.remove();
            sb.append(cur).append(' ');

            for (int next : graph[cur]){
                if (!isVisited[next]){
                    queue.add(next);
                    isVisited[next] = true;
                }
            }
        }

        sb.append('\n');
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 0; i <= n; i++)
            graph[i].sort(Comparator.naturalOrder());

        isVisited = new boolean[n + 1];
        dfs(v);
        sb.append('\n');
        isVisited = new boolean[n + 1];
        bfs(v);

        System.out.println(sb);
    }
}