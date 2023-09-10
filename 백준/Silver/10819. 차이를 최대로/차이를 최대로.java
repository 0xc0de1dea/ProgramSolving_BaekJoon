import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    static int max;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int maxIdx = n - 1;
        int minIdx = 0;
        n -= 2;
        max += arr[maxIdx] - arr[minIdx];

        while (n > 0){
            if (n != 1){
                max += arr[maxIdx] - arr[minIdx + 1] + arr[maxIdx - 1] - arr[minIdx];
            }
            else{
                max += Math.max(arr[maxIdx] - arr[maxIdx - 1], arr[minIdx + 1] - arr[minIdx]);
            }

            maxIdx -= 1;
            minIdx += 1;
            n -= 2;
        }

        System.out.println(max);
    }
}