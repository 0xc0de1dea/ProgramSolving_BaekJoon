import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] time;
    static int[] from;
    static Stack<Integer> s = new Stack<>();
    static int day;

    public static int bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        time[start] = 1;
        from[start] = start;
        int cnt = 1;

        while (q.size() != 0){
            int cur = q.remove();

            if (cur == k){
                break;
            }

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

                if (time[next] == 0){
                    q.add(next);
                    time[next] = time[cur] + 1;
                    from[next] = cur;
                    cnt = time[next];

                    if (next == k)
                        return cnt - 1;
                }
            }
        }

        return cnt - 1;
    }

    public static void tracking(){
        s.add(k);
        int cur = from[k];

        while (cur != n){
            s.add(cur);
            cur = from[cur];
        }

        if (day != 0)
            s.add(n);
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        time = new int[100001];
        from = new int[100001];

        day = bfs(n);
        sb.append(day).append('\n');
        tracking();

        while (s.size() != 0)
            sb.append(s.pop()).append(' ');

        System.out.println(sb);
    }
}