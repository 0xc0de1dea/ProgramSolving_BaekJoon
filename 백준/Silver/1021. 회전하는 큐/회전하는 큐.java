import java.util.Arrays;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        // 배열을 이용한 풀이
        int n = in.nextInt();
        int m = in.nextInt();
        int[] seq = new int[n];

        for (int i = 0; i < n; i++) seq[i] = i + 1;

        int min = 0;
        int len = n;
        int ptr = 0;

        // 뽑아내려는 M개의 수의 이동 최소값은
        // 각각의 뽑아내려는 수의 이동 최소값의 합과 같다.
        // 배열의 길이는 매 순간마다 어떤 수를 뽑아내든 n->n-1->n-2->...1 의 길이를 보장하고
        // 뽑아낸 수로부터 다시 출발을 하기 때문이다.
        for (int i = 0; i < m; i++){
            int num = in.nextInt();
            int cnt = 0;

            // 현재 포인터가 num와 일치할 때까지 회전
            while (seq[ptr] != num){
                ptr = (ptr + 1) % n;
                if (seq[ptr] != 0) cnt++;
            }

            seq[ptr] = 0; // 현재값을 0으로 만들고(삭제하고)
            while (seq[ptr] == 0 && len > 1) ptr = (ptr + 1) % n; // 삭제하지 않은 수가 나올 때 까지 포인터 이동
            min += Math.min(len - cnt, cnt); // 왼쪽으로 회전(cnt), 오른쪽으로 회전(len - cnt) 중 최소인 값
            len--; // 배열 길이 감소
        }

        System.out.print(min);
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
        while ((c = read()) <= 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
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