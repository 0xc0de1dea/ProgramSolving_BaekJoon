import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Snake {
    int r, c;
    
    public Snake(int r, int c){
        this.r = r;
        this.c = c;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        int k = in.nextInt();
        int[][] map = new int[n + 1][n + 1];

        for (int i = 0; i < k; i++){
            int r = in.nextInt();
            int c = in.nextInt();
            map[r][c] = 1;
        }

        int l = in.nextInt();
        HashMap<Integer, Character> hm = new HashMap<>();

        for (int i = 0; i < l; i++){
            int x = in.nextInt();
            char c = in.nextChar();
            hm.put(x, c);
        }

        Deque<Snake> deque = new ArrayDeque<>();
        deque.addFirst(new Snake(1, 1));
        map[1][1] = -1;

        int time = 0;
        int dir = 0;
        int size = 1;

        int[] dr = { 0, 1, 0, -1 };
        int[] dc = { 1, 0, -1, 0 };

        while (true){
            Snake head = deque.peekFirst();
            int dd = dir;

            if (hm.containsKey(time)){
                char c = hm.get(time);

                if (c == 'L'){
                    dd = (dd + 3) % 4;
                } else if (c == 'D'){
                    dd = (dd + 1) % 4;
                }
            }

            dir = dd;
            time++;

            int nr = head.r + dr[dd];
            int nc = head.c + dc[dd];
            deque.addFirst(new Snake(nr, nc));

            if (0 < nr && nr <= n && 0 < nc && nc <= n){
                if (map[nr][nc] == -1){
                    break;
                } else if (map[nr][nc] == 1){
                    map[nr][nc] = -1;
                    size++;
                } else {
                    map[nr][nc] = -1;
                }
            } else break;

            if (deque.size() > size){
                Snake tail = deque.peekLast();
                map[tail.r][tail.c] = 0;
                deque.removeLast();
            }
        }

        System.out.print(time);
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