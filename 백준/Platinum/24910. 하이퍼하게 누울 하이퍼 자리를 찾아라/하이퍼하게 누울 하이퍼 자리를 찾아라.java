import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[][] obstacles;
    static int[][] sorted;

    public static void traversalDimension(int[][] axisArray, int axis){
        for (int i = 0; i < k; i++){
            axisArray[i][0] = obstacles[i][axis];
            axisArray[i][1] = i;
        }
    }

    public static void mergesort(int[][] axisArray, int l, int r){
        if (l < r){
            int m = (l + r) / 2;
            mergesort(axisArray, l, m);
            mergesort(axisArray, m + 1, r);
            merge(axisArray, l, m, r);
        }
    }

    public static void merge(int[][] axisArray, int l, int m, int r){
        int ls = l;
        int rs = m + 1;
        int copyIdx = l;

        while (ls <= m && rs <= r){
            if (axisArray[ls][0] <= axisArray[rs][0]){
                sorted[copyIdx][0] = axisArray[ls][0];
                sorted[copyIdx][1] = axisArray[ls][1];
                ls++;
            } 
            else{
                sorted[copyIdx][0] = axisArray[rs][0];
                sorted[copyIdx][1] = axisArray[rs][1];
                rs++;
            }

            copyIdx++;
        }

        if (ls > m){
            for (int i = rs; i <= r; i++){
                sorted[copyIdx][0] = axisArray[i][0];
                sorted[copyIdx][1] = axisArray[i][1];
                copyIdx++;
            }
        }
        else {
            for (int i = ls; i <= m; i++){
                sorted[copyIdx][0] = axisArray[i][0];
                sorted[copyIdx][1] = axisArray[i][1];
                copyIdx++;
            }
        }

        for (int i = l; i <= r; i++){
            axisArray[i][0] = sorted[i][0];
            axisArray[i][1] = sorted[i][1];
        }
    }

    public static int countSeat(int[][] axisArray, int axisType){
        int alpha = 0;
        int[] prevArray = new int[2];
        Stack<Integer> candidates = new Stack<>();

        for (int i = axisArray.length - 1; i >= 0; i--){
            if (axisArray[i][0] == 0) continue;

            prevArray[0] = n + 1;
            prevArray[1] = -1;
            int cnt = -1;
            int ceilGap = n + 1 - axisArray[i][0];

            if (ceilGap > 2){
                if (axisArray[i][0] != 0){
                    cnt++;
                }
            }

            prevArray[0] = axisArray[i][0];
            prevArray[1] = axisArray[i][1];
            candidates.add(i);

            for (int j = i - 1; j >= 0; j--){
                int gap = prevArray[0] - axisArray[j][0];
                if (axisArray[j][0] != 0 && axisArray[j][0] != prevArray[0]){
                    if (isParallelToAxis(prevArray, axisArray[j], axisType)){
                        if (gap > 2)
                            cnt++;
                        
                        prevArray[0] = axisArray[j][0];
                        prevArray[1] = axisArray[j][1];
                        candidates.add(j);
                    }
                }
            }

            if (prevArray[0] > 2){
                cnt++;
            }

            while (!candidates.empty()){
                axisArray[candidates.pop()][0] = 0;
            }

            alpha += cnt;
        }

        return alpha;
    }

    public static boolean isParallelToAxis(int[] a, int[] b, int axisType){
        if (a[1] == -1)
            return true;

        for (int i = 0; i < 11; i++){
            if (i == axisType) continue;
            
            if (obstacles[a[1]][i] != obstacles[b[1]][i]){
                return false;
            }
        }

        return true;
    }
    
    /*public static void debugging(int[][] t){
        for (int i = 0; i < t.length; i++){
            System.out.println(t[i][0] + " " + t[i][1]);
        }
        System.out.println();
    }

    public static void debugging2(int[] t){
        System.out.println(t[0] + " " + t[1]);
        System.out.println();
    }*/

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        obstacles = new int[k][11];
        
        for (int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 11; j++){
                obstacles[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] axisArray = new int[k][2];
        sorted = new int[k][2];

        for (int i = 0; i < 11; i++){
            BigInteger base = new BigInteger(Integer.toString(1));
            BigInteger target = new BigInteger(Integer.toString(n));

            for (int j = 1; j < 11; j++)
                base = base.multiply(target);

            traversalDimension(axisArray, i);
            mergesort(axisArray, 0, axisArray.length - 1);
            int alpha = countSeat(axisArray, i);
            BigInteger bigAlpha = new BigInteger(Integer.toString(alpha));
            base = base.add(bigAlpha);

            if (n == 1)
                sb.append(0).append('\n');
            else
                sb.append(base).append('\n');
        }

        System.out.println(sb);
    }
}