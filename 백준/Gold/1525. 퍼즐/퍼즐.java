import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Puzzle {
    int state;
    int blank;

    public Puzzle(int state, int blank){
        this.state = state;
        this.blank = blank;
    }
}

public class Main {
    static int[] dx = { 1, 3, -1, -3 };

    public static int pow(int a, int b){
        int res = 1; 

        while (b > 0){
            if ((b & 1) == 1) res *= a;

            a *= a;
            b >>= 1;
        }

        return res;
    }

    public static int swap(int state, int cur, int nxt){
        int res = state;

        int powA = pow(10, 8 - cur);
        int powB = pow(10, 8 - nxt);

        int a = res / powA % 10;
        int b = res / powB % 10;
        res -= a * powA + b * powB;
        res += b * powA + a * powB;

        return res;
    }

    public static int bfs(Puzzle puzzle){
        Queue<Puzzle> queue = new LinkedList<>();
        queue.add(puzzle);

        HashMap<Integer, Integer> visit = new HashMap<>();
        visit.put(puzzle.state, 1);

        int trg = 123456789;
        int cnt = 0;

        while (!queue.isEmpty()){
            int qSize = queue.size();

            while (qSize-- > 0){
                Puzzle cur = queue.poll();
                
                if (cur.state == trg) return cnt;

                for (int i = 0; i < 4; i++){
                    int nx = cur.blank + dx[i];

                    if (0 <= nx && nx < 9){
                        if (i == 0 || i == 2){
                            if (cur.blank / 3 != nx / 3){
                                continue;
                            }
                        }
                        
                        int res = swap(cur.state, cur.blank, nx);

                        if (!visit.containsKey(res)){
                            visit.put(res, 1);
                            queue.add(new Puzzle(res, nx));
                        }
                    }
                }
            }

            cnt++;
        }

        return -1;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int puzzleState = 0;
        int blankLoc = 0;
        
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                int state = in.nextInt();

                if (state == 0){
                    state = 9;
                    blankLoc = i * 3 + j;
                }

                puzzleState = puzzleState * 10 + state;
            }
        }

        int cnt = bfs(new Puzzle(puzzleState, blankLoc));

        System.out.println(cnt);
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