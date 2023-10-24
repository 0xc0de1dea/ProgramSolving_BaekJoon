/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        String[] curTime = in.nextString().split(":");
        String[] startTime = in.nextString().split(":");

        int curSec = Integer.parseInt(curTime[0]) * 3600 + Integer.parseInt(curTime[1]) * 60 + Integer.parseInt(curTime[2]);
        int startSec = Integer.parseInt(startTime[0]) * 3600 + Integer.parseInt(startTime[1]) * 60 + Integer.parseInt(startTime[2]);
        int diffSec = startSec - curSec;
        
        if (diffSec < 0) diffSec += 86400;
        
        int[] remTime = new int[3];
        remTime[0] = diffSec / 3600;
        diffSec %= 3600;
        remTime[1] = diffSec / 60;
        diffSec %= 60;
        remTime[2] = diffSec;
        
        if (remTime[0] / 10 == 0) sb.append('0');
        
        sb.append(remTime[0]).append(':');

        if (remTime[1] / 10 == 0) sb.append('0');

        sb.append(remTime[1]).append(':');

        if (remTime[2] / 10 == 0) sb.append('0');

        sb.append(remTime[2]);

        System.out.print(sb);
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    String nextString() throws Exception {
        StringBuilder sb = new StringBuilder();
        byte c;
        while ((c = read()) <= 32) { if (size < 0) return "endLine"; }
        do sb.appendCodePoint(c);
        while ((c = read()) > 32);
        return sb.toString();
    }

    char nextChar() throws Exception {
        byte c;
        while ((c = read()) <= 32);
        return (char)c;
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