public class Main {
    static final long MOD = 1000000007;

    public static long[][] matrix_2x2_pow(long[][] matrix, long exp){
        long[][] m = new long[][] {{ 1, 0 }, { 0, 1 }};

        while (exp > 0){
            if ((exp & 1) == 1){
                long[][] tmp = new long[2][2];

                for (int i = 0; i < 2; i++){
                    for (int j = 0; j < 2; j++){
                        tmp[i][j] = (m[i][0] * matrix[0][j] + m[i][1] * matrix[1][j]) % MOD;
                    }
                }

                for (int i = 0; i < 2; i++){
                    for (int j = 0; j < 2; j++){
                        m[i][j] = tmp[i][j];
                    }
                }
            }

            long[][] tmp = new long[2][2];

            for (int i = 0; i < 2; i++){
                for (int j = 0; j < 2; j++){
                    tmp[i][j] = (matrix[i][0] * matrix[0][j] + matrix[i][1] * matrix[1][j]) % MOD;
                }
            }

            for (int i = 0; i < 2; i++){
                for (int j = 0; j < 2; j++){
                    matrix[i][j] = tmp[i][j];
                }
            }

            exp = exp >> 1;
        }

        return m;
    }

    public static long fibonacciNum(long n){
        long[][] matrix = new long[][] {{ 1, 1 }, { 1, 0 }};
        matrix = matrix_2x2_pow(matrix, n - 1);
        
        return matrix[0][0];
    }

    public static void main(String[] argu) throws Exception {
        Reader in = new Reader();
        long n = in.nextLong();

        if (n == 0){
            System.out.print(0);
        }
        else {
            System.out.println((fibonacciNum(n) * fibonacciNum(n + 1)) % MOD);
        }
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
        while ((c = read()) <= 32);
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