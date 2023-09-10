//import java.io.FileInputStream;

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        final int MAX = 1_001;
        
        char[] A = new char[MAX];
        char[] B = new char[MAX];
        int szA = 1;
        int szB = 1;

        while (true){
            char c = (char)in.read();

            if (c <= 32) break;
            else A[szA++] = c;
        }

        while (true){
            char c = in.nextChar();

            if (c <= 0) break;
            else B[szB++] = c;
        }

        int[][] dp = new int[szA][szB];

        for (int i = 1; i < szA; i++){
            for (int j = 1; j < szB; j++){
                if (A[i] == B[j]) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        int i = szA - 1;
        int j = szB - 1;
        int len = dp[i][j];
        sb.append(len).append('\n');
        char[] ans = new char[len + 1];

        while (len > 0){
            if (A[i] == B[j]){
                ans[len--] = A[i];
                i--;
                j--;
            }
            else {
                if (dp[i - 1][j] > dp[i][j - 1]) i--;
                else j--;
            }
        }

        for (int k = 1; k < ans.length; k++) sb.append(ans[k]);
        
        System.out.print(sb);
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    char nextChar() throws Exception {
        byte c;
        while ((c = read()) <= 32); { if (size < 0) return 0; }
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

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}