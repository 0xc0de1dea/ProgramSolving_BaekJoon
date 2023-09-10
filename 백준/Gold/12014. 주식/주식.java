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

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int t = in.nextInt();
        int cur = 1;

        while (cur <= t){
            int n = in.nextInt();
            int k = in.nextInt();
            int[] lis = new int[n + 1];
            int len = 0;

            for (int i = 0; i < n; i++){
                int stock = in.nextInt();

                if (stock > lis[len]){
                    lis[++len] = stock;
                }
                else {
                    lis[lowerBound(lis, 1, len, stock)] = stock;
                }
            }

            if (len >= k){
                sb.append("Case #").append(cur).append('\n').append(1).append('\n');
            }
            else {
                sb.append("Case #").append(cur).append('\n').append(0).append('\n');
            }
            cur++;
        }

        System.out.print(sb);
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