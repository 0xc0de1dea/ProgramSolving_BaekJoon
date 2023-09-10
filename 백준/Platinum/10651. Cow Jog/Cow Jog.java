//import java.io.FileInputStream;

public class Main {
    public static int lowerBound(int[] arr, int s, int e, int target){
        while (s != e){
            int m = (s + e) / 2;

            if (arr[m] < target) s = m + 1;
            else e = m;
        }
        return e;
    }

    public static int upperBound(long[] arr, int s, int e, long target){
        while (s != e){
            int m = (s + e) / 2;

            if (arr[m] <= target) s = m + 1;
            else e = m;
        }
        return e;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        final long INF = Long.MIN_VALUE;
        
        int n = in.nextInt();
        int t = in.nextInt();
        long[] lis = new long[n + 1];
        for (int i = 0; i <= n; i++) lis[i] = -INF;
        int len = 0;
        int cnt = 0;

        for (int i = 0; i < n; i++){
            long x0 = in.nextLong();
            long x1 = -(x0 + in.nextLong() * t);

            if (x1 >= lis[len]){
                lis[++len] = x1;
                cnt++;
            }
            else {
                int idx = upperBound(lis, 1, len, x1);
                lis[idx] = x1;
            }
        }

        System.out.print(cnt);
    }
}


/* 빠른 입력 */
class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;
    
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32);//{ if (size == -1) return -1; }
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