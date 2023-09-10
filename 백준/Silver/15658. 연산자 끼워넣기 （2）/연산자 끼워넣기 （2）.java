import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] numbers;
    static int[] operator;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void solve(int depth, int num){
        if (depth == n){
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }

        for (int i = 0; i < 4; i++){
            if (operator[i] == 0) continue;

            switch(i){
                case 0:
                    operator[i]--;
                    solve(depth + 1, num + numbers[depth]);
                    operator[i]++;
                    break;

                case 1:
                    operator[i]--;
                    solve(depth + 1, num - numbers[depth]);
                    operator[i]++;
                    break;

                case 2:
                    operator[i]--;
                    solve(depth + 1, num * numbers[depth]);
                    operator[i]++;
                    break;

                case 3:
                    operator[i]--;
                    solve(depth + 1, num / numbers[depth]);
                    operator[i]++;
                    break;
            }
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        numbers = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i= 0; i < n; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        operator = new int[4];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 4; i++){
            operator[i] = Integer.parseInt(st.nextToken());
        }

        solve(1, numbers[0]);
        System.out.println(max);
        System.out.println(min);
    }  
}