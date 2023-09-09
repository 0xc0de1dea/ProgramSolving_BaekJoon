import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Boolean[] booleans = new Boolean[1001];
        Arrays.fill(booleans, false);
        booleans[1] = true;
        for(int i=2; i<booleans.length/2; i++){
            for(int j=2; j*i<booleans.length; j++){
                booleans[j*i] = true;
            }
        }

        int count = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int resultCount = 0;
        while(count-->0){
            int index = Integer.parseInt(st.nextToken());
            if(booleans[index]!=true){
                resultCount +=1;
            }
        }
        System.out.println(resultCount);
    }
    public static void main(String args[]) throws IOException {
        new Main().solution();
    }
}
