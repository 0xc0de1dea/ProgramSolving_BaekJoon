import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int k;
    static int[] prime;
    static int[] unionOperator;

    public static int soe(boolean[] isPrime){
        int maxPrime = isPrime.length - 1;
        int idx = 1;

        for (int i = 2; i <= maxPrime; i++){
            if (isPrime[i]) continue;

            for (int j = 2 * i; j <= maxPrime; j += i)
                isPrime[j] = true;

            prime[idx++] = i;
        }

        return idx - 1;
    }

    public static long lowerBound(long s, long e, int key){
        while (s <= e){
            long m = (s + e) >> 1;
            long result = m - countSquarefulNumber(m);

            if (result < key)
                s = m + 1;
            else
                e = m - 1;
        }

        return s;
    }

    public static int countSquarefulNumber(long n){
        int cnt = 0;

        for (int i = 2; i * i <= n; i++){
            cnt += n / (i * i) * unionOperator[i];
        }

        return cnt;
    }

    public static void initUnionOperator(int n, int unionCnt){
        jump : for (int i = 2; i <= n; i++){
            for (int j = 2; j * j <= i; j++)
                if (i % (j * j) == 0)
                    continue jump;

            int number = i;
            int overlap = 0;

            for (int j = 1; j <= unionCnt; j++){
                if (number < prime[j]) break;

                if (number % prime[j] == 0){
                    number /= prime[j];
                    overlap++;
                }
            }

            if (overlap % 2 == 0)
                unionOperator[i] = -1;
            else
                unionOperator[i] = 1;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        int maxPrime = (int)Math.sqrt(2 * k);
        boolean[] isPrime = new boolean[maxPrime + 1];
        prime = new int[maxPrime + 1];
        unionOperator = new int[maxPrime + 1];

        int cnt = soe(isPrime);
        initUnionOperator(maxPrime, cnt);
        long kthSqareFreeInt = lowerBound(1, 2 * k, k);

        System.out.println(kthSqareFreeInt);
    }
}