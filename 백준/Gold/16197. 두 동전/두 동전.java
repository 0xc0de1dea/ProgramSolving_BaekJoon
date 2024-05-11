import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Coin {
    int x, y;
    int cnt = 0;

    public Coin(int x, int y, int cnt){
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

public class Main {
    static int n, m;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static int bfs(char[][] map, ArrayList<Coin> coins){
        Queue<Coin> queue = new LinkedList<>();
        queue.add(coins.get(0));
        queue.add(coins.get(1));

        while (!queue.isEmpty()){
            Coin coin1 = queue.poll();
            Coin coin2 = queue.poll();

            if (coin1.cnt == 10) break;

            for (int i = 0; i < 4; i++){
                int nx1 = coin1.x + dx[i];
                int ny1 = coin1.y + dy[i];
                int nx2 = coin2.x + dx[i];
                int ny2 = coin2.y + dy[i];
              
                int cnt = 0;

                if (nx1 < 0 || nx1 >= n || ny1 < 0 || ny1 >= m){
                    cnt++;
                }

                if (nx2 < 0 || nx2 >= n || ny2 < 0 || ny2 >= m){
                    cnt++;
                }

                if (cnt == 2){
                    continue;
                } else if (cnt == 1){
                    return coin1.cnt + 1;
                }

                if (map[nx1][ny1] == '#'){
                    nx1 = coin1.x;
                    ny1 = coin1.y;
                }

                if (map[nx2][ny2] == '#'){
                    nx2 = coin2.x;
                    ny2 = coin2.y;
                }

                if (nx1 == nx2 && ny1 == ny2){
                    continue;
                }
                
                queue.add(new Coin(nx1, ny1, coin1.cnt + 1));
                queue.add(new Coin(nx2, ny2, coin2.cnt + 1));
            }
        }

        return -1;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        m = in.nextInt();
        char[][] map = new char[n][m];
        ArrayList<Coin> coins = new ArrayList<>();

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                map[i][j] = in.nextChar();

                if (map[i][j] == 'o'){
                    coins.add(new Coin(i, j, 0));
                }
            }
        }

        int cnt = bfs(map, coins);

        System.out.print(cnt);
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