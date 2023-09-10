//import java.io.FileInputStream;

class FenwickTree {
    final int SIZE;
    long[] tree;

    public FenwickTree(int size){
        SIZE = size;
        tree = new long[SIZE];
    }

    public void update(int idx, long value){
        while (idx < SIZE){
            tree[idx] += value;
            idx += (idx & -idx);
        }
    }

    public long sum(int idx){
        long sum = 0;

        while (idx > 0){
            sum += tree[idx];
            idx -= (idx & -idx);
        }

        return sum;
    }
}

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int n = in.nextInt();

        int[] A = new int[n + 1];
        int[] idxA = new int[n + 1];
        int[] B = new int[n + 1];
        int[] idxB = new int[n + 1];

        int[] AtoB = new int[n + 1];
        int[] BtoA = new int[n + 1];

        FenwickTree fwTree = new FenwickTree(n + 1);

        for (int i = 1; i <= n; i++){
            int v = in.nextInt();
            A[i] = v;
            idxA[v] = i;
        }

        for (int i = 1; i <= n; i++){
            int v = in.nextInt();
            B[i] = v;
            idxB[v] = i;
            AtoB[idxA[v]] = i;
            BtoA[i] = idxA[v];
        }

        long inversionCounting = 0;
        
        for (int i = n; i > 0; i--){
            inversionCounting += fwTree.sum(AtoB[idxA[A[i]]] - 1);
            fwTree.update(AtoB[idxA[A[i]]], 1L);
        }

        long min = inversionCounting;

        for (int i = n; i > 0; i--){
            inversionCounting = inversionCounting - (n - BtoA[idxB[B[i]]]) + (BtoA[idxB[B[i]]] - 1);
            min = Math.min(min, inversionCounting);
        }

        fwTree = new FenwickTree(n + 1);
        inversionCounting = 0;

        for (int i = n; i > 0; i--){
            inversionCounting += fwTree.sum(BtoA[idxB[B[i]]] - 1);
            fwTree.update(BtoA[idxB[B[i]]], 1L);
        }

        for (int i = n; i > 0; i--){
            inversionCounting = inversionCounting - (n - AtoB[idxA[A[i]]]) + (AtoB[idxA[A[i]]] - 1);
            min = Math.min(min, inversionCounting);
        }

        System.out.print(min);
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