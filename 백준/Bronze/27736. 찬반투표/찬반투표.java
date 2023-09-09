import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] vote = new int[3];
        
        while (st.hasMoreTokens()){
            vote[Integer.parseInt(st.nextToken()) + 1]++;
        }

        if (vote[1] >= (int)Math.ceil((double)n / 2)){
            System.out.println("INVALID");
        }
        else {
            if (vote[2] > vote[0]){
                System.out.println("APPROVED");
            }
            else {
                System.out.println("REJECTED");
            }
        }
    }
}