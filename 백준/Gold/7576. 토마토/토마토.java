import java.util.LinkedList;
import java.util.Queue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int[] dimension = new int[2];
        int size = 1;

        for (int i = 0; i < 2; i++){
            dimension[i] = in.nextInt();
            size *= dimension[i];
        }

        int[] map = new int[size];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < size / dimension[0]; i++){
            for (int j = 0; j < dimension[0]; j++){
                map[i * dimension[0] + j] = in.nextInt();

                if (map[i * dimension[0] + j] == 1){
                    queue.add(i * dimension[0] + j);
                }
            }
        }

        while (!queue.isEmpty()){
            int cur = queue.poll();
            int k = 1;
            int d = 1;

            for (int i = 0; i < 2; i++){
                d *= dimension[i];

                for (int j = -1; j <= 1; j += 2){
                    int nxt = cur + j * k;

                    if (0 <= nxt && nxt < size){
                        if (nxt / d == cur / d){
                            if (map[nxt] == 0){
                                queue.add(nxt);
                                map[nxt] = map[cur] + 1;
                            }
                        }
                    }
                }

                k *= dimension[i];
            }
        }

        int time = 0;
        boolean flag = true;

        for (int i = 0; i < size; i++){
            time = Math.max(time, map[i] - 1);

            if (map[i] == 0){
                flag = false;
                break;
            }
        }

        System.out.print(flag ? time : -1);
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