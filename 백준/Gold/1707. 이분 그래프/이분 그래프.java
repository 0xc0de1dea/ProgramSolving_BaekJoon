import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static ArrayList<Integer>[] graph;
    static int[] state;
    static boolean isBipartiteGraph;
    static StringBuilder sb = new StringBuilder();

    public static void bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        state[start] = 1;

        jump : while (queue.size() != 0){
            int cur = queue.remove();

            for (int next : graph[cur]){
                if (state[next] == 0){
                    queue.add(next);
                    state[next] = -1 * state[cur];
                }
                else if (state[next] == state[cur]){
                    isBipartiteGraph = false;
                    break jump;
                }
            }
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            graph = new ArrayList[n + 1];
            state = new int[n + 1];
            isBipartiteGraph = true;

            for (int i = 0; i <= n; i++)
                graph[i] = new ArrayList<>();

            for (int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                graph[b].add(a);
            }

            for (int i = 1; i <= n; i++){
                if (!isBipartiteGraph)
                    break;

                if (state[i] == 0)
                    bfs(i);
            }

            sb.append(isBipartiteGraph == true ? "YES" : "NO").append('\n');
        }

        System.out.println(sb);
    }
}