import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.StringBuilder;
import java.util.StringTokenizer;
public class Main{
    static StringBuilder sb = new StringBuilder();
    static boolean [] boolVisited;
    static int [] arr;
    static int n;
    public static void permutation(int k, int depth){
        if (depth==k){
            for(int a : arr){
                sb.append(a).append(' ');
            }
            sb.append("\n");
            return;
        }
        for (int i=0;i<n;i++){
            if (boolVisited[i]==false){
                boolVisited[i]=true;
                arr[depth]=i+1;
                permutation(k, depth+1);
                boolVisited[i]=false;
            }
        }
        return;
    }
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolVisited = new boolean[n];
        arr = new int[m];
        permutation(m, 0);
        System.out.print(sb);
    }
}