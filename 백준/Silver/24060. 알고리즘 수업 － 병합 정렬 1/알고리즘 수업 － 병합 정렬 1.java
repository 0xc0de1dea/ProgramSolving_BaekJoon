import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int count, answer, N, K , A[], tmp[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stz.nextToken());
        K = Integer.parseInt(stz.nextToken());

        stz = new StringTokenizer(br.readLine());
        A = new int[N + 1];
        tmp = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(stz.nextToken());
        }
        mergeSort(1, N);
        System.out.println(-1);

    }

    private static void mergeSort(int p, int r) {
        if(count > K ) return;
        int q;
        if (p < r) {
            q = (p + r) / 2;
            mergeSort(p, q);
            mergeSort(q + 1, r);
            merge(p, q, r);
        }
    }

    private static void merge(int p, int q, int r) {
        int i = p;
        int j = q + 1;
        int t = 1;
        while (i <= q && j <= r) {
            if (A[i] <= A[j]) {
                tmp[t++] = A[i++];
            } else {
                tmp[t++] = A[j++];
            }
        }

        while (i <= q) {
            tmp[t++] = A[i++];
        }

        while (j <= r) {
            tmp[t++] = A[j++];
        }

        i = p;
        t = 1;

        while (i <= r) {
            A[i++] = tmp[t++];
            count++;
            if (count == K) {
                answer = A[i - 1];
                System.out.println(answer);
                System.exit(0);
            }
        }
    }
}//!@#$^&*()