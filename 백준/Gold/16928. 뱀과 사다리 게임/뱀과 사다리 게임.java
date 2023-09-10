/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] map = new int[101];
    static int[] ladder = new int[101];
    static int[] snake = new int[101];

    static int bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        map[start] = 1;

        while (q.size() > 0){
            int cur = q.poll();
            for (int i = 1; i <= 6; i++){
                int next = cur + i;
                if (next <= 100 && map[next] == 0){
                    map[next] = map[cur] + 1;
                    if (ladder[next] > 0){
                        next = ladder[next];
                        if (map[next] == 0) map[next] = map[cur] + 1;
                        else map[next] = Math.min(map[cur] + 1, map[next]);
                    }
                    else if (snake[next] > 0){
                        next = snake[next];
                        if (map[next] == 0) map[next] = map[cur] + 1;
                        else map[next] = Math.min(map[cur] + 1, map[next]);
                    }
                    q.add(next);
                }
            }
        }

        return map[100] - 1;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        int m = in.nextInt();
        
        for (int i = 0; i < n; i++) ladder[in.nextInt()] = in.nextInt();
        for (int i = 0; i < m; i++) snake[in.nextInt()] = in.nextInt();

        System.out.print(bfs(1));
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    char nextChar() throws Exception {
        char ch = ' ';
        byte c;
        while ((c = read()) <= 32);
        do ch = (char)c;
        while (isAlphabet(c = read()));
        return ch;
    }
    
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32); //{ if (size < 0) return -1; }
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
        while ((c = read()) <= 32);
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
        return 96 < c && c < 123;
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}