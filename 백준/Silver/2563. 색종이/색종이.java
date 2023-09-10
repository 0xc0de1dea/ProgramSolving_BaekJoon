import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        boolean[][] paper = new boolean[101][101];

        while (n-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            for (int i = y; i < y + 10; i++)
                for (int j = x; j < x + 10; j++)
                    paper[i][j] = true;
        }

        int cnt = 0;

        for (int i = 1; i <= 100; i++)
            for (int j = 1; j <= 100; j++)
                if (paper[i][j])
                    cnt++;

        System.out.println(cnt);
    }
}