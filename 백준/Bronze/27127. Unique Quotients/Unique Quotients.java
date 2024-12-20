import java.util.ArrayList;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static boolean[] isVisited = new boolean[10];
    static ArrayList<Integer> upList = new ArrayList<>();
    static ArrayList<Integer> dnList = new ArrayList<>();
    static ArrayList<String> list = new ArrayList<>();
    static int cnt = 0;

    public static void bruthforce(int depth){
        if (depth == 10){
            int up = 0;
            int dn = 0;

            for (int i = 0; i < upList.size(); i++){
                up *= 10;
                up += upList.get(i);
            }

            for (int i = 0; i < dnList.size(); i++){
                dn *= 10;
                dn += dnList.get(i);
            }

            if (up * 1.f / dn == 9){
                list.add(up + "/" + dn + "." + ++cnt);
            }
        }

        for (int i = 0; i <= 9; i++){
            if (!isVisited[i]){
                isVisited[i] = true;
                
                if (depth < 5){
                    upList.add(i);
                    bruthforce(depth + 1);
                    upList.remove(upList.size() - 1);
                } else {
                    dnList.add(i);
                    bruthforce(depth + 1);
                    dnList.remove(dnList.size() - 1);
                }

                isVisited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();

        if (n == 1){
            System.out.println("57429 06381");
        } else if (n == 2){
            System.out.println("58239 06471");
        } else if (n == 3){
            System.out.println("75249 08361");
        } else if (n == 4){
            System.out.println("95742 10638");
        } else if (n == 5){
            System.out.println("95823 10647");
        } else if (n == 6){
            System.out.println("97524 10836");
        }
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
        while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
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