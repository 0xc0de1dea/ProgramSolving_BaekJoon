/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static final long MOD = (long)1e9 + 7;

    // 분할정복을 이용한 거듭제곱 O(log n) 시간복잡도
    public static long pow(long x, long exp){
        long res = 1; // 결과를 저장할 변수

        while (exp > 0){ // 거듭제곱이 모두 소진될 때 까지
            if ((exp & 1) == 1){ // 거듭제곱이 홀수라면
                res *= x; // 결과에 미리 x하나를 곱해줘서 반영
                res %= MOD; // MOD연산자의 성질 -> (a * b) % MOD = (a % MOD * b % MOD) % MOD
            }

            // 예로 4^5 라면 위에서 res = 4로 갱신되고
            // 4^4 꼴로 변함 (아래 exp >>= 1 에서 한번에 계산됨)
            // 여기서 4^4를 분할정복을 해 (4^1 * 4^1)를 계산한다.

            //                   4^5
            //                  ／  ＼
            //                4^2    4^2    4^1
            //              ／  ＼   ／  ＼
            //             4^1  4^1 4^1  4^1

            // (4^1 * 4^1)를 계산 한 것이 x *= x
            // 이 떄 x는 16
            // 그리고 exp >>= 1; 2로 나눠줘서(└ exp / 2 ┘)
            // 4^5 -> 4^4 -> 4^2 가 다음 계산으로 넘어감
            x *= x;
            x %= MOD;
            exp >>= 1;

            // 그 다음 4^2에서 2는 짝수이므로 위의 조건은 걸러지고
            // 아래로 내려와서 (4^2 * 4^2)를 계산
            // 4^2는 이미 계산했으니 x *= x; 해준다.

            // 그 다음 exp는 1이되고 홀수이므로
            // 최종적으로 res 에 분할정복한 x값이 곱해지고 답이 도출된다.
        }

        return res;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        long n = in.nextLong();
        long m = in.nextLong();
        int a = in.nextInt();
        int h = in.nextInt();

        System.out.print(pow(m, n - 1));
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