/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class NumberBaseball {
    String num;
    int strike, ball;

    public NumberBaseball(String num, int strike, int ball){
        this.num = num;
        this.strike = strike;
        this.ball = ball;
    }
}

public class Main{

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        NumberBaseball[] nb = new NumberBaseball[n];

        for (int i = 0; i < n; i++){
            String num = in.nextString();
            int strike = in.nextInt();
            int ball = in.nextInt();
            nb[i] = new NumberBaseball(num, strike, ball);
        }

        int cnt = 0;

        for (int i = 123; i <= 987; i++){
            String tmp = String.valueOf(i);

            if (tmp.charAt(0) == tmp.charAt(1) || tmp.charAt(0) == tmp.charAt(2)
            || tmp.charAt(1) == tmp.charAt(2) || tmp.charAt(1) == '0' || tmp.charAt(2) == '0') continue;

            boolean flag = true;

            for (int j = 0; j < n; j++) {
                String src = String.valueOf(i);
                String trg = nb[j].num;
                int strike = 0;
                int ball = 0;

                boolean[] isVisited = new boolean[3];

                for (int p = 0; p < 3; p++){
                    for (int q = 0; q < 3; q++){
                        if (src.charAt(p) == trg.charAt(q) && !isVisited[q]){
                            if (p == q){
                                strike++;
                                isVisited[q] = true;
                            }
                            else {
                                ball++;
                                isVisited[q] = true;
                            }
                        }
                    }
                }

                if (strike != nb[j].strike || ball != nb[j].ball){
                    flag = false;
                    break;

                }
            }

            if (flag){
                cnt++;
                //System.out.println(i);
            }
        }

        System.out.println(cnt);
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