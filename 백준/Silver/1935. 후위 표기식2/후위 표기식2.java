import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    public static boolean isNumber(char c){
        return 48 <= c && c <= 57;
    }

    public static boolean isNumber(String str){
        try {
            double num = Integer.parseInt(str);
        } catch (NumberFormatException e){
            return false;
        }

        return true;
    }

    public static String getPostfix(String infix){
        StringBuilder postfix = new StringBuilder();
        char[] arr = infix.toCharArray();

        HashMap<Character, Integer> priority = new HashMap<>();
        priority.put('+', 1);
        priority.put('-', 1);
        priority.put('*', 2);
        priority.put('/', 2);

        Deque<Character> stack = new ArrayDeque<>();

        char prev = ' ';

        for (char c : arr){
            if (isNumber(c)){
                if (priority.containsKey(prev)){
                    postfix.append(' ').append(c);
                } else {
                    postfix.append(c);
                }
            } else {
                while (!stack.isEmpty() && priority.get(stack.peek()) >= priority.get(c)){
                    postfix.append(' ').append(stack.pop());
                }

                stack.push(c);
            }

            prev = c;
        }

        while (!stack.isEmpty()) postfix.append(' ').append(stack.pop());

        return postfix.toString();
    }

    public static double calcPostfix(String postfix){
        double res = 0;
        String[] arr = postfix.split(" ");

        Deque<Double> stack = new ArrayDeque<>();

        for (String str : arr){
            if (isNumber(str)){
                stack.push(Double.parseDouble(str));
            } else {
                double b = stack.pop();
                double a = stack.pop();

                char c = str.charAt(0);

                if (c == '+'){
                    stack.push(a + b);
                } else if (c == '-'){
                    stack.push(a - b);
                } else if (c == '*'){
                    stack.push(a * b);
                } else if (c == '/'){
                    stack.push(a / b);
                }
            }
        }

        res = stack.pop();

        return res;
    }

    public static boolean isAlphabet(char c){
        return 'A' <= c && c <= 'Z';
    }
    
    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        String input = in.nextString();
        HashMap<Character, Integer> hm = new HashMap<>();
        
        for (int i = 0; i < n; i++){
            hm.put((char)(i + 'A'), in.nextInt());
        }

        for (int i = 0; i < input.length(); i++){
            if (i != 0) sb.append(' ');

            char c = input.charAt(i);

            if (isAlphabet(c)){
                int num = hm.get(c);
                sb.append(num);
            } else {
                sb.append(c);
            }
        }

        double res = calcPostfix(sb.toString());

        System.out.printf("%.2f", res);
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    String nextString() throws Exception {
        StringBuilder sb = new StringBuilder();
        byte c;
        while ((c = read()) < 32) { if (size < 0) return "endLine"; }
        do sb.appendCodePoint(c);
        while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
        return sb.toString();
    }

    char nextChar() throws Exception {
        byte c;
        while ((c = read()) <= 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
        return (char)c;
    }
    
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32) { if (size < 0) return -1; }
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    long nextLong() throws Exception {
        long n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32);
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    double nextDouble() throws Exception {
        double n = 0, div = 1;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32) { if (size < 0) return -12345; }
        if (c == 45) { c = read(); isMinus = true; }
        else if (c == 46) { c = read(); }
        do n = (n * 10) + (c & 15);
        while (isNumber(c = read()));
        if (c == 46) { while (isNumber(c = read())){ n += (c - 48) / (div *= 10); }}
        return isMinus ? -n : n;
    }

    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }

    boolean isAlphabet(byte c){
        return (64 < c && c < 91) || (96 < c && c < 123);
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}