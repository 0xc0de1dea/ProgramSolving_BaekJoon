import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    static int n, m;
    static ArrayList<Integer>[] edge;
    static int[] in_degree;
    static ArrayList<Integer> ans = new ArrayList<>();

    public static void bfs(){
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= n; i++){
            if (in_degree[i] == 0){
                pq.add(i);
            }
        }

        while (!pq.isEmpty()){
            int cur = pq.poll();
            ans.add(cur);

            for (int e : edge[cur]){
                if (in_degree[e] != 0){
                    in_degree[e]--;

                    if (in_degree[e] == 0){
                        pq.add(e);
                    }                    
                }
            }
        }
    }

    public static void main(String[] argu) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        m = in.nextInt();
        edge = new ArrayList[n + 1];
        in_degree = new int[n + 1];

        for (int i = 1; i <= n; i++){
            edge[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            edge[a].add(b);
            in_degree[b]++;
        }

        bfs();

        for (int e : ans){
            sb.append(e).append(' ');
        }

        System.out.println(sb);
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        while ((c = read()) <= 32);
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return n;
    }
    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }
    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}