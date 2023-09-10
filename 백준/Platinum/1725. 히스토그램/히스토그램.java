//import java.io.FileInputStream;

public class Main {
    static int[] histogram;

    public static long getArea(int len){
        int[] stack = new int[len];
        int sz = 0;
        int top = -1;

        long maxArea = 0;

        for (int i = 0; i <= len; i++){
            while (sz > 0 && histogram[stack[top]] >= histogram[i]){
                int h = histogram[stack[top--]];
                long w = --sz == 0 ? i : i - 1 - stack[top];

                maxArea = Math.max(maxArea, h * w);
            }

            stack[++top] = i;
            sz++;
        }

        return maxArea;
    }
    
    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int n = in.nextInt();
        histogram = new int[n + 1];

        for (int i = 0; i < n; i++){
            histogram[i] = in.nextInt();
        }

        System.out.print(getArea(n));
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