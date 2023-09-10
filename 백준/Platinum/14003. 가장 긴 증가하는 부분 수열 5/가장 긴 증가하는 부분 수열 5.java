import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int lowerBound(int[] arr, int s, int e, int target){
        while (s < e){
            int m = (s + e) / 2;

            if (arr[m] < target) s = m + 1;
            else e = m;
        }
        return e;
    }

    public static void trace(int[] sequence, int[] idxArr, int[] copyArr, int len){
        int size = idxArr.length;

        while (len > 0){
            size--;

            if (idxArr[size] == len){
                copyArr[len--] = sequence[size];
            }
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(br.readLine());
        int[] sequence = new int[n];
        int[] lis = new int[n + 1];
        lis[0] = Integer.MIN_VALUE;
        int[] idxArr = new int[n];
        int len = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++){
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        
        for (int i = 0; i < n; i++){
            if (sequence[i] > lis[len]){
                lis[++len] = sequence[i];
                idxArr[idx++] = len;
            }
            else {
                int loc = lowerBound(lis, 1, len, sequence[i]);
                lis[loc] = sequence[i];
                idxArr[idx++] = loc;
            }
        }

        sb.append(len).append('\n');
        trace(sequence, idxArr, lis, len);

        for (int i = 1; i <= len; i++){
            sb.append(lis[i]).append(' ');
        }
        
        System.out.println(sb);
    }
}