import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static long x;
    static long[] burger = new long[51];
    static long[] patty = new long[51];

    public static long levelLBurger(int level, long eatenB){
        if (level == 0){
            if (eatenB >= x){
                return 0;
            }
            else {
                return 1;
            }
        }

        if (eatenB >= x){
            return 0;
        }

        eatenB++;

        if (x > eatenB + burger[level - 1]){
            return patty[level - 1] + 1 + levelLBurger(level - 1, eatenB + burger[level - 1] + 1);
        }
        else if (x == eatenB + burger[level - 1]){
            return patty[level - 1];
        }
        else {
            return levelLBurger(level - 1, eatenB);
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        x = Long.parseLong(st.nextToken());
        burger[0] = patty[0] = 1;
        
        for (int i = 1; i < 51; i++){
            burger[i] = 2 * burger[i - 1] + 3;
            patty[i] = 2 * patty[i - 1] + 1;
        }

        long cnt = levelLBurger(n, 0);
        System.out.println(cnt);
    }
}