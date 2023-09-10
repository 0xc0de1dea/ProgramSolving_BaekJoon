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
        int m = in.nextInt();
        int k = in.nextInt();
        
        long[] sequence = new long[n + 1];
        FenwickTree fwTree = new FenwickTree(n + 1);

        for (int i = 1; i <= n; i++){
            sequence[i] = in.nextLong();
            fwTree.update(i, sequence[i]);
        }

        for (int i = m + k; i > 0; i--){
            int a = in.nextInt();

            if ((a & 1) == 1){
                int b = in.nextInt();
                long c = in.nextLong();
                long value = c - sequence[b];
                sequence[b] = c;
                fwTree.update(b, value);
            }
            else {
                int b = in.nextInt();
                int c = in.nextInt();
                sb.append(fwTree.sum(c) - fwTree.sum(b - 1)).append('\n');
            }
        }

        System.out.print(sb);
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