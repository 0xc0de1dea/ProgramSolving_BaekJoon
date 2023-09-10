/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        char[] str = new char[1_000_001];
        char[] exploding = new char[37];
        char[] stack = new char[1_000_001];
        int ptr = -1, ptr2 = -1, ptr3 = -1;
        byte b;
        
        while ((b = in.read()) > 32) str[++ptr] = (char)b;
        while ((b = in.read()) <= 32); exploding[++ptr2] = (char)b;
        while ((b = in.read()) > 32) exploding[++ptr2] = (char)b;
        for (int i = 0; i <= ptr; i++){
            stack[++ptr3] = str[i];
            if (ptr3 >= ptr2){
                boolean flag = true;
                for (int j = 0; j <= ptr2; j++){
                    if (stack[ptr3 - ptr2 + j] != exploding[j]){
                        flag = false;
                        break;
                    }
                }
                if (flag) for (int j = 0; j <= ptr2; j++) stack[ptr3--] = 0;
            }
        }
        for (int i = 0; ; i++){
            if (stack[i] == 0) break;
            sb.append(stack[i]);
        }

        System.out.print(sb.length() != 0 ? sb : "FRULA");
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    char nextChar() throws Exception {
        char ch = ' ';
        byte c;
        while ((c = read()) <= 32);
        do ch = (char)c;
        while (isAlphabet(c = read()));
        return ch;
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

    boolean isAlphabet(byte c){
        return 96 < c && c < 123;
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}