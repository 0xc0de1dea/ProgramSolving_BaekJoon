import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static long lowerBound(ArrayList<Long> arr, int l, int r, long target){
        while (l < r){
            int m = (l + r) / 2;

            if (arr.get(m) < target){
                l = m + 1;
            }
            else {
                r = m;
            }
        }
        return (long)r;
    }

    public static long upperBound(ArrayList<Long> arr, int l, int r, long target){
        while (l < r){
            int m = (l + r) / 2;

            if (arr.get(m) <= target){
                l = m + 1;
            }
            else {
                r = m;
            }
        }
        return (long)l;
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        long t = Long.parseLong(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] An = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++){
            An[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] Bn = new int[m + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= m; i++){
            Bn[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Long> prefixSumA = new ArrayList<>();
        ArrayList<Long> prefixSumB = new ArrayList<>();

        for (int i = 0; i < n; i++){
            long tmp = 0;

            for (int j = i + 1; j <= n; j++){
                tmp += An[j];
                prefixSumA.add(tmp);
            }
        }

        for (int i = 0; i < m; i++){
            long tmp = 0;

            for (int j = i + 1; j <= m; j++){
                tmp += Bn[j];
                prefixSumB.add(tmp);
            }
        }

        Collections.sort(prefixSumA);
        Collections.sort(prefixSumB);
        long sum = 0;

        for (long e : prefixSumA){
            sum += upperBound(prefixSumB, 0, prefixSumB.size(), t - e) - lowerBound(prefixSumB, 0, prefixSumB.size(), t - e);
        }

        System.out.println(sum);
    }
}