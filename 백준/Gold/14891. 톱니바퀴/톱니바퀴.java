/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static final int THREE = 1 << 5;
    static final int NINE = 1 << 1;
    static final int ELEVEN = 1;
    static final int TWELVE = 1 << 7;

    public static int pow(int a, int b){
        int res = 1;

        while (b > 0){
            if ((b & 1) == 1) res *= a;

            a *= a;
            b >>= 1;
        }

        return res;
    }

    public static void shift(int[] gears, int id, int dir){
        if (dir > 0){
            int lsb = gears[id] & ELEVEN;
            gears[id] >>= 1;
            gears[id] |= lsb << 7;
        } else {
            int msb = gears[id] & TWELVE;
            gears[id] <<= 1;
            gears[id] |= msb >> 7;
        }
    }

    public static void rotate(int[] gears, int id, int dir){
        int[] docking = new int[3];
        
        for (int i = 0; i < 3; i++){
            int left = gears[i] & THREE;
            int right = gears[i + 1] & NINE;

            if ((left > 0 && right > 0) || (left == 0 && right == 0)){
                docking[i] = 1;
            } else {
                docking[i] = 0;
            }
        }

        if (id == 1){
            shift(gears, 0, dir);

            if (docking[0] == 0){
                shift(gears, 1, -dir);
                
                if (docking[1] == 0){
                    shift(gears, 2, dir);

                    if (docking[2] == 0){
                        shift(gears, 3, -dir);
                    }
                }
            }
        } else if (id == 2){
            shift(gears, 1, dir);

            if (docking[0] == 0) shift(gears, 0, -dir);
            if (docking[1] == 0){ 
                shift(gears, 2, -dir);

                if (docking[2] == 0) shift(gears, 3, dir);
            }
        } else if (id == 3){
            shift(gears, 2, dir);

            if (docking[2] == 0) shift(gears, 3, -dir);
            if (docking[1] == 0){
                shift(gears, 1, -dir);

                if (docking[0] == 0) shift(gears, 0, dir);
            }
        } else if (id == 4){
            shift(gears, 3, dir);

            if (docking[2] == 0){
                shift(gears, 2, -dir);

                if (docking[1] == 0){
                    shift(gears, 1, dir);

                    if (docking[0] == 0){
                        shift(gears, 0, -dir);
                    }
                }
            }
        }
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int[] gears = new int[4];

        for (int i = 0; i < 4; i++){
            gears[i] = Integer.parseInt(in.nextString(), 2);
        }

        int k = in.nextInt();

        for (int i = 0; i < k; i++){
            int id = in.nextInt();
            int dir = in.nextInt();

            rotate(gears, id, dir);
        }

        int score = 0;

        for (int i = 0; i < 4; i++){
            int state = gears[i] & TWELVE;

            if (state > 0) score += pow(2, i);
        }

        System.out.println(score);
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