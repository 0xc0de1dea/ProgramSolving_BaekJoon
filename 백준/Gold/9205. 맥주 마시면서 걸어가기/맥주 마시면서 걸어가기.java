import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Point {
    int x, y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {

    public static boolean bfs(int n, ArrayList<ArrayList<Integer>> edges, int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        boolean[] isVisited = new boolean[n];
        isVisited[start] = true;

        while (!queue.isEmpty()){
            int cur = queue.poll();

            if (cur == n - 1){
                return true;
            }

            for (int nxt : edges.get(cur)){
                if (!isVisited[nxt]){
                    isVisited[nxt] = true;
                    queue.add(nxt);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int t = in.nextInt();

        while (t-- > 0){
            int n = in.nextInt();
            ArrayList<ArrayList<Integer>> edges = new ArrayList<>();

            Point[] point = new Point[n + 2];

            for (int i = 0; i < n + 2; i++){
                int x = in.nextInt();
                int y = in.nextInt();
                point[i] = new Point(x, y);
                edges.add(new ArrayList<>());
            }

            for (int i = 0; i < n + 1; i++){
                for (int j = i + 1; j < n + 2; j++){
                    Point p1 = point[i];
                    Point p2 = point[j];

                    if (Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y) <= 1000){
                        edges.get(i).add(j);
                        edges.get(j).add(i);
                    }
                }
            }

            boolean check = bfs(n + 2, edges, 0);

            sb.append(check ? "happy" : "sad").append('\n');
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