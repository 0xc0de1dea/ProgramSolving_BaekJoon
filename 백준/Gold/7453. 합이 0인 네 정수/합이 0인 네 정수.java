import java.util.Arrays;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Main {
    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < 4; j++){
                arr[i][j] = in.nextInt();
            }
        }

        int[] ab = new int[n * n];
        int[] cd = new int[n * n];
        int ptr = 0;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                ab[ptr] = arr[i][0] + arr[j][1];
                cd[ptr] = arr[i][2] + arr[j][3];
                ptr++;
            }
        }

        Arrays.sort(ab);
        Arrays.sort(cd);

        int len = n * n;

        int left = 0;
        int right = len - 1;

        long cnt = 0;

        while (left < len && right >= 0){
            if (ab[left] + cd[right] == 0){
                int nxtLeft = left + 1;
                int nxtRight = right - 1;

                while (nxtLeft < len && ab[left] == ab[nxtLeft]) nxtLeft++;

                while (nxtRight >= 0 && cd[right] == cd[nxtRight]) nxtRight--;

                cnt += (long)(nxtLeft - left) * (long)(right - nxtRight);
                left = nxtLeft;
                right = nxtRight;
            } else if (ab[left] + cd[right] > 0) {
                right--;
            } else {
                left++;
            }
        }

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