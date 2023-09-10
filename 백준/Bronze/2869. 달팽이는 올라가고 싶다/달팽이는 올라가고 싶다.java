import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double v = sc.nextDouble();
    
        if(v == a) System.out.println(1);
        else System.out.println((long)Math.ceil((v-a)/(a-b))+1);
    }
 
}
