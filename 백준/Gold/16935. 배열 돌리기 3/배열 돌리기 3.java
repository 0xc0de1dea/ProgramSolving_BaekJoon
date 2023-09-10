import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int n, m, r;
    static int[][][] matrix;
    static int sizeN, sizeM;
    static StringBuilder sb = new StringBuilder();

    public static void operationA(int origin, int target){
        for (int i = 0; i < sizeN; i++){
            for (int j = 0; j < sizeM; j++){
                matrix[target][i][j] = matrix[origin][sizeN - 1 - i][j];
            }
        }
    }

    public static void operationB(int origin, int target){
        for (int i = 0; i < sizeN; i++){
            for (int j = 0; j < sizeM; j++){
                matrix[target][i][j] = matrix[origin][i][sizeM - 1 - j];
            }
        }
    }

    public static void operationC(int origin, int target){
        for (int i = 0; i < sizeM; i++){
            for (int j = 0; j < sizeN; j++){
                matrix[target][i][j] = matrix[origin][sizeN - 1 - j][i];
            }
        }

        int tmp = sizeN;
        sizeN = sizeM;
        sizeM = tmp;
    }

    public static void operationD(int origin, int target){
        for (int i = 0; i < sizeM; i++){
            for (int j = 0; j < sizeN; j++){
                matrix[target][i][j] = matrix[origin][j][sizeM - 1 - i];
            }
        }

        int tmp = sizeN;
        sizeN = sizeM;
        sizeM = tmp;
    }

    public static void operationE(int origin, int target){
        for (int i = 0; i < sizeN / 2; i++){
            for (int j = sizeM / 2; j < sizeM; j++){
                matrix[target][i][j] = matrix[origin][i][j - sizeM / 2];
            }
        }

        for (int i = sizeN / 2; i < sizeN; i++){
            for (int j = sizeM / 2; j < sizeM; j++){
                matrix[target][i][j] = matrix[origin][i - sizeN / 2][j];
            }
        }

        for (int i = sizeN / 2; i < sizeN; i++){
            for (int j = 0; j < sizeM / 2; j++){
                matrix[target][i][j] = matrix[origin][i][j + sizeM / 2];
            }
        }

        for (int i = 0; i < sizeN / 2; i++){
            for (int j = 0; j < sizeM / 2; j++){
                matrix[target][i][j] = matrix[origin][i + sizeN / 2][j];
            }
        }
    }

    public static void operationF(int origin, int target){
        for (int i = 0; i < sizeN / 2; i++){
            for (int j = sizeM / 2; j < sizeM; j++){
                matrix[target][i][j] = matrix[origin][i + sizeN / 2][j];
            }
        }

        for (int i = sizeN / 2; i < sizeN; i++){
            for (int j = sizeM / 2; j < sizeM; j++){
                matrix[target][i][j] = matrix[origin][i][j - sizeM / 2];
            }
        }

        for (int i = sizeN / 2; i < sizeN; i++){
            for (int j = 0; j < sizeM / 2; j++){
                matrix[target][i][j] = matrix[origin][i - sizeN / 2][j];
            }
        }

        for (int i = 0; i < sizeN / 2; i++){
            for (int j = 0; j < sizeM / 2; j++){
                matrix[target][i][j] = matrix[origin][i][j + sizeM / 2];
            }
        }
    }

    public static void result(int target){
        for (int i = 0; i < sizeN; i++){
            for (int j =  0; j < sizeM; j++){
                sb.append(matrix[target][i][j]).append(' ');
            }
            sb.append('\n');
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        int size = n > m ? n : m;
        sizeN = n;
        sizeM = m;
        matrix = new int[2][size][size];
        int[] operator = new int[r];

        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++){
                matrix[0][i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < r; i++){
            operator[i] = Integer.parseInt(st.nextToken());
        }

        int origin = 0;
        int target = 1;

        for (int i = 0; i < r; i++){
            switch (operator[i]){
                case 1:
                    operationA(origin, target);
                    break;

                case 2:
                    operationB(origin, target);
                    break;

                case 3:
                    operationC(origin, target);
                    break;

                case 4:
                    operationD(origin, target);
                    break;

                case 5:
                    operationE(origin, target);
                    break;

                case 6:
                    operationF(origin, target);
                    break;
            }

            origin = origin == 0 ? 1 : 0;
            target = target == 1 ? 0 : 1;
        }

        result(origin);
        System.out.println(sb);
    }
}