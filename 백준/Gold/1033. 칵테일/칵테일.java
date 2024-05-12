import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Rate {
    long a, b, p, q;

    public Rate(long a, long b, long p, long q){
        this.a = a;
        this.b = b;
        this.p = p;
        this.q = q;
    }
}

public class Main {
    static int n;
    static ArrayList<ArrayList<Rate>> edge = new ArrayList<>();
    static long[] up;
    static long[] dn;

    public static long gcd(long a, long b){
        if (b == 0) return a;

        return gcd(b, a % b);
    }

    public static void bfs(int start){
        Rate second = edge.get(start).get(0);

        Queue<Rate> queue = new LinkedList<>();
        queue.add(new Rate(second.a, second.b, second.p, second.q));

        boolean[] isVisited = new boolean[n];
        isVisited[start] = true;
        isVisited[(int)second.b] = true;

        up[start] = second.p / gcd(second.p, second.q);
        dn[start] = 1;

        while (!queue.isEmpty()){
            Rate cur = queue.poll();

            long gcd = gcd(cur.p * dn[(int)cur.a], cur.q * up[(int)cur.a]);
            long p = (cur.p * dn[(int)cur.a]) / gcd;
            long q = (cur.q * up[(int)cur.a]) / gcd;

            dn[(int)cur.b] = p;
            up[(int)cur.b] = q;

            boolean flag = false;

            for (Rate nxt : edge.get((int)cur.b)){
                if (!isVisited[(int)nxt.b]){
                    isVisited[(int)nxt.b] = true;
                    queue.add(nxt);
                    flag = true;
                }
            }
        }

        long lcm = dn[0];

        for (int i = 1; i < n; i++){
            lcm = (dn[i] * lcm) / gcd(dn[i], lcm);
        }

        for (int i = 0; i < n; i++){
            up[i] *= lcm;
            up[i] /= dn[i];
        }
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();

        if (n == 1){
            System.out.print(1);
            return;
        }

        up = new long[n];
        dn = new long[n];

        for (int i = 0; i < n; i++){
            edge.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            long p = in.nextInt();
            long q = in.nextInt();

            edge.get(a).add(new Rate(a, b, p, q));
            edge.get(b).add(new Rate(b, a, q, p));
        }

        int startIdx = 0;

        for (int i = 0; i < edge.size(); i++){
            if (edge.get(i).size() == 1){
                startIdx = i;
                break;
            }
        }

        bfs(startIdx);

        for (int i = 0; i < n; i++){
            sb.append(up[i]).append(' ');
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
        while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
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