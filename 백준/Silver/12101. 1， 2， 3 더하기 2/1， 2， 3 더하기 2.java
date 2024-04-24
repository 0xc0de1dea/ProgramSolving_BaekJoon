import java.util.ArrayList;
import java.util.Collections;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static ArrayList<ArrayList<String>> list = new ArrayList<>();

    public static void bruteforce(int n, int cur){
        if (cur > n) return;

        for (String s : list.get(cur - 1)){
            list.get(cur).add(s + "+1");
        }

        for (String s : list.get(cur - 2)){
            list.get(cur).add(s + "+2");
        }

        for (String s : list.get(cur - 3)){
            list.get(cur).add(s + "+3");
        }

        bruteforce(n, cur + 1);
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int n = in.nextInt();
        int k = in.nextInt();

        for (int i = 0; i <= 10; i++){
            list.add(new ArrayList<>());
        }

        list.get(0).add("");
        list.get(1).add("1");
        list.get(2).add("1+1");
        list.get(2).add("2");
        list.get(3).add("1+1+1");
        list.get(3).add("2+1");
        list.get(3).add("1+2");
        list.get(3).add("3");
        bruteforce(n, 4);

        Collections.sort(list.get(n));
        int idx = 1;

        for (String s : list.get(n)){
            if (k == idx){
                sb.append(s);
                break;
            }

            idx++;
        }

        System.out.print(k > list.get(n).size() ? -1 : sb);
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    String nextString() throws Exception {
        StringBuilder sb = new StringBuilder();
        byte c;
        while ((c = read()) < 32) { if (size < 0) return "endLine"; }
        do sb.appendCodePoint(c);
        while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
        return sb.toString();
    }

    char nextChar() throws Exception {
        byte c;
        while ((c = read()) < 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
        return (char)c;
    }
    
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32) { if (size < 0) return -1; }
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
        while ((c = read()) <= 32) { if (size < 0) return -12345; }
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
        return (64 < c && c < 91) || (96 < c && c < 123);
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}