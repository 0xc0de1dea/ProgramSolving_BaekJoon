import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Line {
    int x;
    int h1;
    int h2;

    public Line(int x, int h1, int h2){
        this.x = x;
        this.h1 = h1;
        this.h2 = h2;
    }
}

 // 부계 실험용
public class Main {
    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int k = in.nextInt();
        int[] dy = { 0, 0, 0, -1, 1 };
        int[] dx = { 0, 1, -1, 0, 0 };

        int lastX = 500;
        int lastY = 500;
        ArrayList<Line> lines = new ArrayList<>();

        for (int i = 0; i < 6; i++){
            int dir = in.nextInt();
            int len = in.nextInt();

            if (dir == 3){
                lines.add(new Line(lastX, lastY, lastY - len));
            } else if (dir == 4){
                lines.add(new Line(lastX, lastY + len, lastY));
            }

            lastX += len * dx[dir];
            lastY += len * dy[dir];
        }

        Collections.sort(lines, new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2){
                return o1.x - o2.x;
            }
        });

        int area = 0;
        lastX = lines.get(0).x;
        int lastY1 = lines.get(0).h1;
        int lastY2 = lines.get(0).h2;

        for (int i = 1; i < 3; i++){
            Line line = lines.get(i);
            area += (line.x - lastX) * (lastY1 - lastY2);

            if (lastY2 == line.h1){
                lastY2 = line.h2;
            } else if (lastY1 == line.h2){
                lastY1 = line.h1;
            } else if (lastY1 == line.h1){
                lastY1 = line.h2;
            } else if (lastY2 == line.h2){
                lastY2 = line.h1;
            }

            lastX = line.x;
        }

        System.out.print(area * k);
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