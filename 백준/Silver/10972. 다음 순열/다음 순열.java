import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] sequence;
    static boolean[] isExisted = new boolean[10001];
    static int[] numidx = new int[10001];
    static int maximum;
    static StringBuilder sb = new StringBuilder();

    public static void swap(int a, int b){
        int tmp = sequence[a];
        sequence[a] = sequence[b];
        sequence[b] = tmp;
    }

    public static void print(boolean isLast){
        if (isLast){
            System.out.println(-1);
            return;
        }

        for (int e : sequence){
            sb.append(e + " ");
        }

        System.out.println(sb);
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sequence = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++){
            sequence[i] = Integer.parseInt(st.nextToken());
            numidx[sequence[i]] = i;
        }

        if (n == 1){
            print(true);
            return;
        }
        else if (n == 2){
            if (sequence[0] < sequence[1]){
                swap(0, 1);
                print(false);
                return;
            }
            else{
                print(true);
                return;
            }
        }

        if (sequence[n - 2] < sequence[n - 1]){
            swap(n - 2, n - 1);
            print(false);
        }
        else{
            int idx = n - 3;

            for (int i = n - 1; i >= idx; i--){
                maximum = Math.max(maximum, sequence[i]);
                isExisted[sequence[i]] = true; 
            }
            
            while (sequence[idx] == maximum){
                idx--;

                if (idx < 0){
                    print(true);
                    return;
                }

                maximum = Math.max(maximum, sequence[idx]);
                isExisted[sequence[idx]] = true;
            }

            for (int i = sequence[idx] + 1; i <= n; i++){
                if (isExisted[i]){
                    swap(idx, numidx[i]);
                    break;
                }
            }

            int start = idx + 1;
            int end = n - 1;
            
            while (start < end){
                swap(start, end);
                start++;
                end--;
            }

            print(false);
        }
    }
}