import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Fireball {
    int x, y;
    int m, s, d;

    public Fireball(int x, int y, int m, int s, int d){
        this.x = x;
        this.y = y;
        this.m = m;
        this.s = s;
        this.d = d;
    }
}

class Main {
    static int n, m, k;
    static Queue<Fireball> queue = new LinkedList<>();
    static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

    public static void move(){
        int qSize = queue.size();

        while (qSize-- > 0){
            Fireball cur = queue.poll();
            int nxtX = (cur.x + dx[cur.d] * cur.s + n * cur.s) % n;
            int nxtY = (cur.y + dy[cur.d] * cur.s + n * cur.s) % n;
            queue.add(new Fireball(nxtX, nxtY, cur.m, cur.s, cur.d));
        }
    }

    public static void mergeAndDivide(){
        ArrayList<Fireball>[][] map = new ArrayList[n][n];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                map[i][j] = new ArrayList<>();
            }
        }

        while (!queue.isEmpty()){
            Fireball cur = queue.poll();
            map[cur.x][cur.y].add(new Fireball(cur.x, cur.y, cur.m, cur.s, cur.d));
        }

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (map[i][j].size() >= 2){
                    int mTot = 0;
                    int sTot = 0;
                    int odd = 0;
                    int even = 0;

                    for (Fireball cur : map[i][j]){
                        if (cur.d % 2 == 0) even++;
                        else odd++;

                        mTot += cur.m;
                        sTot += cur.s;
                    }

                    if (odd == 0 || even == 0){
                        if (mTot / 5 > 0){
                            queue.add(new Fireball(i, j, mTot / 5, sTot / map[i][j].size(), 0));
                            queue.add(new Fireball(i, j, mTot / 5, sTot / map[i][j].size(), 2));
                            queue.add(new Fireball(i, j, mTot / 5, sTot / map[i][j].size(), 4));
                            queue.add(new Fireball(i, j, mTot / 5, sTot / map[i][j].size(), 6));
                        }
                    } else {
                        if (mTot / 5 > 0){
                            queue.add(new Fireball(i, j, mTot / 5, sTot / map[i][j].size(), 1));
                            queue.add(new Fireball(i, j, mTot / 5, sTot / map[i][j].size(), 3));
                            queue.add(new Fireball(i, j, mTot / 5, sTot / map[i][j].size(), 5));
                            queue.add(new Fireball(i, j, mTot / 5, sTot / map[i][j].size(), 7));
                        }
                    }
                } else if (map[i][j].size() == 1){
                    queue.add(map[i][j].get(0));
                }
            }
        }
    }

    public static int getTotalMass(){
        int tot = 0;

        while (!queue.isEmpty()){
            tot += queue.poll().m;
        }

        return tot;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();

        for (int i = 0; i < m; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            int m = in.nextInt();
            int s = in.nextInt();
            int d = in.nextInt();
            queue.add(new Fireball(x, y, m, s, d));
        }

        for (int i = 0; i < k; i++){
            move();
            mergeAndDivide();
        }

        int tot = getTotalMass();

        System.out.println(tot);
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