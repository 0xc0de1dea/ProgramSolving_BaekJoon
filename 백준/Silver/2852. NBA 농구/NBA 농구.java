/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int n = in.nextInt();
        int winTime1 = 0;
        int winTime2 = 0;

        int lastWinTime = 0;
        int lastwinTeam = 0;

        int goalCnt1 = 0;
        int goalCnt2 = 0;

        for (int i = 0; i < n; i++){
            int team = in.nextInt();
            String[] time = in.nextString().split(":");
            int minute = Integer.parseInt(time[0]);
            int second = Integer.parseInt(time[1]);

            if (team == 1) goalCnt1++;
            else goalCnt2++;

            if (goalCnt1 == goalCnt2){
                if (lastwinTeam == 1){
                    lastwinTeam = 0;
                    winTime1 += (minute * 60 + second) - lastWinTime;
                    lastWinTime = minute * 60 + second;
                } else if (lastwinTeam == 2){
                    lastwinTeam = 0;
                    winTime2 += (minute * 60 + second) - lastWinTime;
                    lastWinTime = minute * 60 + second;
                }
            }

            if (goalCnt1 > goalCnt2 && lastwinTeam != 1) {
                lastwinTeam = 1;
                lastWinTime = minute * 60 + second;
            }
            else if (goalCnt1 < goalCnt2 && lastwinTeam != 2) {
                lastwinTeam = 2;
                lastWinTime = minute * 60 + second;
            }
        }

        if (goalCnt1 > goalCnt2){
            winTime1 += 2880 - lastWinTime;
            lastWinTime = 2880;
        } else if (goalCnt1 < goalCnt2){
            winTime2 += 2880 - lastWinTime;
            lastWinTime = 2880;
        }

        System.out.printf("%02d:%02d\n", winTime1 / 60, winTime1 % 60);
        System.out.printf("%02d:%02d\n", winTime2 / 60, winTime2 % 60);
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
        while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
        return sb.toString();
    }

    char nextChar() throws Exception {
        byte c;
        while ((c = read()) <= 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
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