import java.util.*;
import java.io.*;
 
 
public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int a,b,c,e,d,f;
        a= Integer.parseInt(s[0]);
        b= Integer.parseInt(s[1]);
        c= Integer.parseInt(s[2]);
        d= Integer.parseInt(s[3]);
        e= Integer.parseInt(s[4]);
        f= Integer.parseInt(s[5]);
        int answerX=0;
        int answerY=0;
        for(int i=-999; i<1000; i++){
            for(int j=-999; j<1000; j++){
                if((a*i+b*j==c)&&(d*i+e*j==f)){
                    answerX=i;
                    answerY=j;
                    break;
                }
            }
        }
        System.out.println(answerX +" "+answerY);
    }
}//////#^@*>@^÷&~*~&~&