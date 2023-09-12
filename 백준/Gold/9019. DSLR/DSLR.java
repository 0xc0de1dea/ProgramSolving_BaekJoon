/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

import java.util.LinkedList;
import java.util.Queue;

class DSLR {
    int n;
    StringBuilder cmd;

    public DSLR(int n, StringBuilder cmd){
        this.n = n;
        this.cmd = cmd;
    }

    int d() { return (n << 1) % 10000; }
    int s() { return (n + 9999) % 10000; }
    int l() { return (n * 10 + n / 1000) % 10000; }
    int r() { return (n % 10 * 1000) + n / 10; }
}

public class Main {
    static String bfs(int start, int end){
        Queue<DSLR> q = new LinkedList<>();
        q.add(new DSLR(start, new StringBuilder()));
        boolean[] isVisited = new boolean[10_000];
        isVisited[start] = true;

        StringBuilder ret = new StringBuilder();
        String[] cmds = new String[] { "D", "S", "L", "R" };

        jump : while(!q.isEmpty()){
            DSLR cur = q.poll();

            for (int i = 0; i < 4; i++){
                int nextN = 0;
                StringBuilder nextCmd = new StringBuilder();
                nextCmd.append(cur.cmd);
                nextCmd.append(cmds[i]);
                
                switch (cmds[i]){
                    case "D": nextN = cur.d(); break;
                    case "S": nextN = cur.s(); break;
                    case "L": nextN = cur.l(); break;
                    case "R": nextN = cur.r(); break;
                }

                if (!isVisited[nextN]){
                    q.add(new DSLR(nextN, nextCmd));
                    isVisited[nextN] = true;

                    if (nextN == end){
                        ret = nextCmd;
                        break jump;
                    }
                }
            }
        }

        return ret.toString();
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int t = in.nextInt();

        while (t-- > 0){
            int a = in.nextInt();
            int b = in.nextInt();
            sb.append(bfs(a, b)).append('\n');
        }
        
        System.out.print(sb);
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