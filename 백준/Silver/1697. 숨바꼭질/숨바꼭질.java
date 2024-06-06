import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] time;
    static boolean[] isVisited;

    public static int bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        isVisited[start] = true;
        int cnt = 0;

        while (q.size() != 0){
            int cur = q.remove();

            if (cur == k)
                break;

            for (int i = 0; i < 3; i++){
                int next = cur;

                switch(i){
                    case 0: next -= 1;
                    break;

                    case 1: next += 1;
                    break;

                    case 2: next *= 2;
                    break;
                }

                if (next < 0 || next > 100000) continue;

                if (!isVisited[next]){
                    q.add(next);
                    time[next] = time[cur] + 1;
                    isVisited[next] = true;
                    cnt = time[next];

                    if (next == k)
                        return cnt;
                }
            }
        }

        return cnt;
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        time = new int[100001];
        isVisited = new boolean[100001];
        System.out.println(bfs(n));
    }
}