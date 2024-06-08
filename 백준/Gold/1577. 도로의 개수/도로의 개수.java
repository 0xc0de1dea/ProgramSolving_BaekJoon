import java.util.ArrayList;
import java.util.HashMap;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Axis {
    int x, y;
    int cashing = 1;
    
    public Axis(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }

        if (!(o instanceof Axis)){
            return false;
        }

        Axis other = (Axis)o;

        if (!other.canEqual(this)){
            return false;
        }

        return other.x == this.x && other.y == this.y;
    }

    @Override
    public int hashCode(){
        int hash = cashing;
        final int PRIME = 31;

        if (hash == 1){
            hash = PRIME * hash + this.x;
            hash = PRIME * hash + this.y;
            cashing = hash;
        }

        return hash;
    }

    boolean canEqual(Object other){
        return other instanceof Axis;
    }
}

public class Main {
    static int n, m;
    static HashMap<Axis, Axis> obstacle = new HashMap<>();
    static ArrayList<Integer>[][] map;
    static long[][] dp;
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static long dp(int x, int y){
        if (dp[x][y] >= 0) return dp[x][y];
        
        if (x == n && y == m) return dp[x][y] = 1;

        long sum = 0;

        jump : for (int i = 0; i < 2; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx <= n && 0 <= ny && ny <= m){
                for (int j = 0; j < map[x][y].size(); j++){
                    for (int k = 0; k < map[nx][ny].size(); k++){
                        if (map[x][y].get(j) == map[nx][ny].get(k)){
                            continue jump;
                        }
                    }
                }

                sum += dp(nx, ny);
            }
        }

        return dp[x][y] = sum;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        n = in.nextInt();
        m = in.nextInt();
        map = new ArrayList[n + 1][m + 1];
        dp = new long[n + 1][m + 1];

        for (int i = 0; i <= n; i++){
            for (int j = 0; j <= m; j++){
                map[i][j] = new ArrayList<>();
                dp[i][j] = -1;
            }
        }

        int k = in.nextInt();
        int id = 1;
        
        for (int i = 0; i < k; i++){
            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();
            map[x1][y1].add(id);
            map[x2][y2].add(id);
            id++;
        }

        System.out.println(dp(0, 0));

        // for (int i = m; i >= 0; i--){
        //     for (int j = 0; j <= n; j++){
        //         System.out.printf("%2d", dp[j][i]);
        //     }
        //     System.out.println();
        // }
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