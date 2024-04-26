import java.util.LinkedList;
import java.util.Queue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int[] abc = new int[3];
    static boolean[] ans = new boolean[201];
    static boolean[][][] isVisited = new boolean[201][201][201];
    static int[] send = { 0, 0, 1, 1, 2, 2 };
    static int[] recv = { 1, 2, 0, 2, 0, 1 };

    public static void bfs(int a, int b, int c){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {a, b, c});
        isVisited[a][b][c] = true;
        ans[c] = true;

        while (!queue.isEmpty()){
            int[] cur = queue.poll();

            for (int i = 0; i < 6; i++){
                int[] nxt = new int[] { cur[0], cur[1], cur[2] };
                nxt[recv[i]] += nxt[send[i]];
                nxt[send[i]] = 0;

                if (nxt[recv[i]] > abc[recv[i]]){
                    nxt[send[i]] += nxt[recv[i]] - abc[recv[i]];
                    nxt[recv[i]] = abc[recv[i]];
                }

                if (!isVisited[nxt[0]][nxt[1]][nxt[2]]){
                    isVisited[nxt[0]][nxt[1]][nxt[2]] = true;
                    queue.add(new int[] { nxt[0], nxt[1], nxt[2] });

                    if (nxt[0] == 0){
                        ans[nxt[2]] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        abc[0] = a;
        abc[1] = b;
        abc[2] = c;

        bfs(0, 0, c);

        for (int i = 0; i <= 200; i++){
            if (ans[i]){
                sb.append(i).append(' ');
            }
        }

        System.out.print(sb);
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    String nextString() throws Exception {
        StringBuilder sb = new StringBuilder();
        byte c;
        while ((c = read()) < 32) { if (size < 0) return "endLine"; }
        do sb.appendCodePoint(c);
        while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
        return sb.toString();
    }

    char nextChar() throws Exception {
        byte c;
        while ((c = read()) < 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
        return (char)c;
    }
    
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32) { if (size < 0) return -1; }
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    long nextLong() throws Exception {
        long n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32);
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    double nextDouble() throws Exception {
        double n = 0, div = 1;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32) { if (size < 0) return -12345; }
        if (c == 45) { c = read(); isMinus = true; }
        else if (c == 46) { c = read(); }
        do n = (n * 10) + (c & 15);
        while (isNumber(c = read()));
        if (c == 46) { while (isNumber(c = read())){ n += (c - 48) / (div *= 10); }}
        return isMinus ? -n : n;
    }

    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }

    boolean isAlphabet(byte c){
        return (64 < c && c < 91) || (96 < c && c < 123);
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}