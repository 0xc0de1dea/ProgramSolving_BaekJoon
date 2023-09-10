import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main{
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int[] n = Stream.of(st.nextToken().split("")).mapToInt(Integer::parseInt).toArray();
        char[][] lcd = new char[2 * s + 3][n.length * (s + 3)];
        char[][] ref = new char[10][7];

        for (int i = 0; i < lcd.length; i++){
            for (int j = 0; j < lcd[0].length; j++){
                lcd[i][j] = ' ';
            }
        }

        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 7; j++){
                ref[i][j] = ' ';
            }
        }

        ref[0][0] = ref[2][0] = ref[3][0] = ref[5][0] = ref[6][0] = ref[7][0] = ref[8][0] = ref[9][0] = '-';
        ref[0][1] = ref[4][1] = ref[5][1] = ref[6][1] = ref[8][1] = ref[9][1] = '|';
        ref[0][2] = ref[1][2] = ref[2][2] = ref[3][2] = ref[4][2] = ref[7][2] = ref[8][2] = ref[9][2] = '|';
        ref[2][3] = ref[3][3] = ref[4][3] = ref[5][3] = ref[6][3] = ref[8][3] = ref[9][3] = '-';
        ref[0][4] = ref[2][4] = ref[6][4] = ref[8][4] = '|';
        ref[0][5] = ref[1][5] = ref[3][5] = ref[4][5] = ref[5][5] = ref[6][5] = ref[7][5] = ref[8][5] = ref[9][5] = '|';
        ref[0][6] = ref[2][6] = ref[3][6] = ref[5][6] = ref[6][6] = ref[8][6] = ref[9][6] = '-';

        for (int i = 0; i < n.length; i++){
            for (int j = i * (s + 3) + 1; j <= i * (s + 3) + s; j++){
                lcd[0][j] = ref[n[i]][0];
                lcd[s + 1][j] = ref[n[i]][3];
                lcd[2 * (s + 1)][j]  = ref[n[i]][6];
            }

            for (int j = 1; j < 1 + s; j++){
                lcd[j][i * (s + 3)] = ref[n[i]][1];
                lcd[j][i * (s + 3) + (s + 1)] = ref[n[i]][2];
            }

            for (int j = 2 + s ; j < 2 * (1 + s); j++){
                lcd[j][i * (s + 3)] = ref[n[i]][4];
                lcd[j][i * (s + 3) + (s + 1)] = ref[n[i]][5];
            }
        }

        for (int i = 0; i < lcd.length; i++){
            for (int j = 0; j < lcd[0].length; j++){
                sb.append(lcd[i][j]);
            }

            sb.append('\n');
        }

        System.out.println(sb);
    }
}