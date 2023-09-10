import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void soe(boolean[] isPrime){
        int end1 = (int)Math.sqrt(isPrime.length - 1);
        int end2 = isPrime.length;
        isPrime[1] = true;

        for (int i = 2; i <= end1; i++){
            for (int j = 2 * i; j < end2; j += i){
                isPrime[j] = true;
            }
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        boolean[] isPrime = new boolean[n + 1];
        ArrayList<Integer> primeList = new ArrayList<>();
        soe(isPrime);

        for (int i = 2; i <= n; i++){
            if (!isPrime[i]){
                primeList.add(i);
            }
        }
        primeList.add(4000001);

        int cnt = 0;
        int l = 0, r = 0, sum = primeList.get(0);
        int to = primeList.size() - 1;

        while (r < to){
            if (sum > n){
                sum -= primeList.get(l++);
            }
            else if (sum < n){
                sum += primeList.get(++r);
            }
            else {
                sum += primeList.get(++r);
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}