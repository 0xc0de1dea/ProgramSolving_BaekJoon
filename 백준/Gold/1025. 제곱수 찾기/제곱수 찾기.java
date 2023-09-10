public class Main {
    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        int n = in.nextInt();
        int m = in.nextInt();
 
        int[][] arr = new int[10][10];
        int result = -1;
 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = in.nextInt();
            }
        }
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                for (int mi = -n; mi < n; ++mi)
                    for (int mj = -m; mj < m; ++mj)
                    {
                        if (mi == 0 && mj == 0) { // 둘다 움직이지 않을 때
                            continue;
                        }
 
                        int t = 0;
                        int newI = i;
                        int newJ = j;

                        while (newI >= 0 && newI < n && newJ >= 0 && newJ < m) // 위치가 0>= && <범위를 설정해줍니다.
                        {
                            t = 10 * t + arr[newI][newJ]; // 기존에 담긴 숫자가 있다면 *10해주고 더해줍니다. 
                            
                            if (Math.abs(Math.sqrt(t) - (int)Math.sqrt(t)) < 1e-10){ // 완전 제곱수인지 판별합니다.
                                result = Math.max(result, t);
                            }
                            
                            newI += mi;
                            newJ += mj;
                        }
                    }

        System.out.println(result);
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;
    
    int nextInt() throws Exception {
        byte c;
        while ((c = read()) <= 32);
        return c & 15;
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