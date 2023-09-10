//import java.io.FileInputStream;

public class Main {
    static int[] sorted;
    static long inversionCounting = 0;

    public static void merge(int[] A, int s, int m, int e){
        int l = s;
        int r = m + 1;
        int idx = s;

        while (l <= m && r <= e){
            if (A[l] <= A[r]){
                sorted[idx++] = A[l++];
            }
            else {
                sorted[idx++] = A[r++];
                inversionCounting += (long)r - idx;
            }
        }

        while (l <= m) sorted[idx++] = A[l++];
        while (r <= e) sorted[idx++] = A[r++];

        for (int i = s; i <= e; i++) A[i] = sorted[i];
    }

    public static void mergeSort(int[] A, int s, int e){
        if (s < e){
            int m = s + e >> 1;
            mergeSort(A, s, m);
            mergeSort(A, m + 1, e);
            merge(A, s, m, e);
        }
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int n = in.nextInt();
        int[] A = new int[n];
        sorted = new int[n];

        for (int i = 0; i < n; i++){
            A[i] = in.nextInt();
        }

        mergeSort(A, 0, n - 1);
        System.out.print(inversionCounting);
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    char nextChar() throws Exception {
        byte c;
        while ((c = read()) <= 32);
        return (char)c;
    }
    
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32) //{ if (size < 0) return -1; }
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