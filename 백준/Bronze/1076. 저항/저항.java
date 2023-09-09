/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        //Reader in = new Reader();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String color1 = br.readLine();
        String color2 = br.readLine();
        String color3 = br.readLine();

        HashMap<String, long[]> ohm = new HashMap<>(10);
        ohm.put("black",  new long[] { 0, 1 });
        ohm.put("brown",  new long[] { 1, 10 });
        ohm.put("red",    new long[] { 2, 100 });
        ohm.put("orange", new long[] { 3, 1_000 });
        ohm.put("yellow", new long[] { 4, 10_000 });
        ohm.put("green",  new long[] { 5, 100_000 });
        ohm.put("blue",   new long[] { 6, 1_000_000 });
        ohm.put("violet", new long[] { 7, 10_000_000 });
        ohm.put("grey",   new long[] { 8, 100_000_000 });
        ohm.put("white",  new long[] { 9, 1_000_000_000 });

        long resist = (10 * ohm.get(color1)[0] + ohm.get(color2)[0]) * ohm.get(color3)[1];

        System.out.print(resist);
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