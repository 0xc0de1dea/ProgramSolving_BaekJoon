public class Main {
    static final int MOD = 1000;

    public static long[][] matrix_nxn_pow(long[][] matrix, long exp){
        int n = matrix.length;
        long[][] m = new long[n][n];

        for (int i = 0; i < n; i++){
            m[i][i] = 1;
        }

        while (exp > 0){
            if ((exp & 1) == 1){
                long[][] tmp = new long[n][n];

                for (int i = 0; i < n; i++){
                    for (int j = 0; j < n; j++){
                        for (int k = 0; k < n; k++){
                            tmp[i][j] += m[i][k] * matrix[k][j];
                        }

                        tmp[i][j] %= MOD;
                    }
                }

                for (int i = 0; i < n; i++){
                    for (int j = 0; j < n; j++){
                        m[i][j] = tmp[i][j];
                    }
                }
            }

            long[][] tmp = new long[n][n];

            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    for (int k = 0; k < n; k++){
                        tmp[i][j] += matrix[i][k] * matrix[k][j];
                    }

                    tmp[i][j] %= MOD;
                }
            }

            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    matrix[i][j] = tmp[i][j];
                }
            }

            exp = exp >> 1;
        }

        return m;
    }

    public static void main(String[] argu) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        int n = in.nextInt();
        long b = in.nextLong();
        long[][] matrix = new long[n][n];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                matrix[i][j] = in.nextInt();
            }
        }

        matrix = matrix_nxn_pow(matrix, b);

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                sb.append(matrix[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
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