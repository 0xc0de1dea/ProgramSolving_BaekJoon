import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[][] table = new String[5][15];
        
        for (int i = 0; i < 5; i++){
            String[] tmp = br.readLine().split("");

            for (int j = 0; j < tmp.length; j++)
                table[i][j] = tmp[j];
        }

        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 5; j++){
                if (table[j][i] != null){
                    sb.append(table[j][i]);
                }
            }
        }
        
        System.out.println(sb);
    }
}