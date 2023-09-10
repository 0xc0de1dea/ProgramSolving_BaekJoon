import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String asdf = br.readLine();
        int awefcvsda = asdf.length();
        int zsdv = 0;
        int ljl = 0;
        for (int i = 0; i < awefcvsda - 2; i++){
            if (asdf.charAt(i) == ':' && asdf.charAt(i + 1) == '-'){
                if (asdf.charAt(i + 2) == ')'){
                    zsdv += 1;
                }
                else if (asdf.charAt(i + 2) == '('){
                    ljl += 1;
                }
            }
        }
        if (zsdv == 0 && ljl == 0){
            System.out.println("none");
            return;
        }
        else{
            if (zsdv == ljl){
                System.out.println("unsure");
            }
            else if (zsdv > ljl){
                System.out.println("happy");
            }
            else {
                System.out.println("sad");
                // 
            }
        }
    }
}