import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception{
        Reader in = new Reader();
        int N = in.nextInt();
        int[] tree = new int[N];
        
        for(int i = 0; i < N; i++){
            tree[i] = in.nextInt();
        }
        
        Arrays.sort(tree);
        int gcd=0;
        
        for(int i = 0; i < N - 1; i++){
            int distance = tree[i+1] - tree[i];
            gcd = gcd(distance, gcd);
        }
        
        System.out.print((tree[N-1] - tree[0]) / gcd + 1 - (tree.length));
    }

    static int gcd(int a, int b){
        if(b == 0) return a;
        else return gcd(b, a % b);
    }
}

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