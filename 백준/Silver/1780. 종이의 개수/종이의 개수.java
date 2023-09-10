import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] paper;
    static int[] cnt = new int[3];

    public static void recursion(int size, int sx, int sy, int ex, int ey){
        if (size == 1){
            cnt[paper[sy][sx] + 1]++;
            return;
        }

        int cut = size / 3;
        boolean check = true;

        jump : for (int i = sy; i < ey; i++){
            for (int j = sx; j < ex; j++){
                if (paper[sy][sx] != paper[i][j]){
                    check = false;
                    break jump;
                }
            }
        }

        if (check){
            cnt[paper[sy][sx] + 1]++;
        }
        else {
            for (int i = sy; i < ey; i += cut){
                for (int j = sx; j < ex; j += cut){
                    recursion(cut, j, i, j + cut, i + cut);
                }
            }
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        paper = new int[n][n];
        
        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++){
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recursion(n, 0, 0, n, n);
        sb.append(cnt[0]).append('\n').append(cnt[1]).append('\n').append(cnt[2]);
        System.out.println(sb);
    }
}