import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        float a = 0;
        float b = 0;
        
        for (int i = 0; i < 20; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String subject = st.nextToken();
            float score = Float.parseFloat(st.nextToken());
            String grade = st.nextToken();

            switch (grade){
                case "A+": 
                    a += score * 4.5f;
                    b += score;
                    break;

                case "A0":
                    a += score * 4.0f;
                    b += score;
                    break;

                case "B+":
                    a += score * 3.5f;
                    b += score;
                    break;

                case "B0":
                    a += score * 3.0f;
                    b += score;
                    break;

                case "C+":
                    a += score * 2.5f;
                    b += score;
                    break;

                case "C0":
                    a += score * 2.0f;
                    b += score;
                    break;

                case "D+":
                    a += score * 1.5f;
                    b += score;
                    break;

                case "D0":
                    a += score * 1.0f;
                    b += score;
                    break;

                case "F":
                    b += score;
                    break;
            }
        }

        System.out.println(b == 0 ? 0f : String.format("%.6f", a / b));
    }
}