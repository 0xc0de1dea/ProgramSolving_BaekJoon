import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Student implements Comparable<Student> {
    int x, y;
    int like;
    int blank;

    public Student(int x, int y, int like, int blank){
        this.x = x;
        this.y = y;
        this.like = like;
        this.blank = blank;
    }

    @Override
    public int compareTo(Student o) {
        if (this.like == o.like){
            if (this.blank == o.blank){
                if (this.x == o.x){
                    return this.y - o.y;
                }
                return this.x - o.x;
            }
            return o.blank - this.blank;
        }
        return o.like - this.like;
    }
}

public class Main {
    static int n;
    static ArrayList<ArrayList<Integer>> list;
    static int[][] map;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static void setSeat(int src){
        PriorityQueue<Student> pq = new PriorityQueue<>();
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (map[i][j] == 0){
                    int like = 0;
                    int blank = 0;

                    for (int k = 0; k < 4; k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];
    
                        if (0 <= nx && nx < n && 0 <= ny && ny < n){
                            if (map[nx][ny] != 0){
                                for (int trg : list.get(src)){
                                    if (map[nx][ny] == trg){
                                        like++;
                                    }
                                }
                            } else {
                                blank++;
                            }
                        }

                        
                    }
                    
                    //System.out.printf("src : %d, i : %d, j : %d, like : %d, blnak : %d\n", src, i, j, like, blank);
                    pq.add(new Student(i, j, like, blank));
                }
            }
        }

        Student seat = pq.poll();
        map[seat.x][seat.y] = src;
    }

    public static int pow(int a, int b){
        int res = 1;

        while (b > 0){
            if ((b & 1) == 1) res *= a;

            a *= a;
            b >>= 1;
        }

        return res;
    }

    public static int getSatisfaction(){
        int res = 0;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                int src = map[i][j];
                int sum = 0;

                for (int k = 0; k < 4; k++){
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (0 <= nx && nx < n && 0 <= ny && ny < n){
                        for (int trg : list.get(src)){
                            if (trg == map[nx][ny]){
                                sum++;
                            }
                        }
                    }
                }

                if (sum > 0){
                    res += pow(10, sum - 1);
                }
            }
        }

        return res;
    }

    public static void print(){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.printf("%2d", map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        list = new ArrayList<>();
        map = new int[n][n];

        for (int i = 0; i <= n * n; i++){
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < n * n; i++){
            int src = in.nextInt();
            
            for (int j = 0; j < 4; j++){
                int trg = in.nextInt();
                list.get(src).add(trg);
            }

            setSeat(src);
        }
        //print();
        int res = getSatisfaction();
        
        System.out.println(res);
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