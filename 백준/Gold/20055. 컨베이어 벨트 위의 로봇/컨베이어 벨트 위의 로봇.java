import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] belt = new int[2 * n];
        boolean[] robot = new boolean[n + 1];
        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < 2 * n; i++)
            belt[i] = Integer.parseInt(st.nextToken());

        int step = 0;

        while (k > 0){
            int tmp = belt[belt.length - 1];
            
            for (int i = belt.length - 1; i > 0; i--){
                belt[i] = belt[i - 1];
            }

            belt[0] = tmp;

            for (int i = n - 2; i >= 0; i--){
                if (!robot[i]) continue;

                if (belt[i + 2] != 0 && !robot[i + 2]){
                    robot[i] = false;

                    if (i + 2 < n - 1){
                        robot[i + 2] = true;

                        if (belt[i + 2] > 1){
                            belt[i + 2]--;
                        }
                        else {
                            belt[i + 2]--;
                            k--;
                        }
                    }
                    else if (i + 2 == n - 1){
                        if (belt[i + 2] > 1){
                            belt[i + 2]--;
                        }
                        else {
                            belt[i + 2]--;
                            k--;
                        }
                    }
                }
                else {
                    robot[i] = false;

                    if (i + 1 < n - 1){
                        robot[i + 1] = true;
                    }
                }
            }

            if (belt[0] != 0){
                robot[0] = true;

                if (belt[0] > 1){
                    belt[0]--;
                }
                else {
                    belt[0]--;
                    k--;
                }
            }

            step++;
        }

        System.out.println(step);
    }
}