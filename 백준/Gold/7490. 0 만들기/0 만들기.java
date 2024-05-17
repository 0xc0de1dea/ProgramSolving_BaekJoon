import java.util.ArrayList;
import java.util.Arrays;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static ArrayList<String> list;

    public static boolean isNumber(char c){
        return 48 <= c && c <= 57;
    }

    public static void backtracking(int n, int idx, StringBuilder expression){
        if (idx == n - 1){
            char[] arr = expression.toString().toCharArray();
            int left = 0;
            char oper = '+';
            int right = arr[0] - '0';

            for (int i = 1; i < arr.length; i++){
                if (arr[i] == ' '){
                    right = right * 10 + (arr[i + 1] - '0');
                    i++;
                } else if (isNumber(arr[i])){
                    right = arr[i] - '0';
                } else {
                    if (oper == '+'){
                        left += right;
                        right = 0;
                    } else if (oper == '-'){
                        left -= right;
                        right = 0;
                    }

                    if (arr[i] == '+'){
                        oper = '+';
                    } else if (arr[i] == '-'){
                        oper = '-';
                    }
                }
            }

            if (oper == '+'){
                left += right;
                right = 0;
            } else if (oper == '-'){
                left -= right;
                right = 0;
            }

            if (left == 0){
                list.add(expression.toString());
            }

            return;
        }

        expression.append('+').append(idx + 2);
        backtracking(n, idx + 1, expression);
        expression.delete(expression.length() - 2, expression.length());

        expression.append('-').append(idx + 2);
        backtracking(n, idx + 1, expression);
        expression.delete(expression.length() - 2, expression.length());

        expression.append(' ').append(idx + 2);
        backtracking(n, idx + 1, expression);
        expression.delete(expression.length() - 2, expression.length());
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int t = in.nextInt();

        while (t-- > 0){
            int n = in.nextInt();
            list = new ArrayList<>();
            StringBuilder expression = new StringBuilder();
            expression.append(1);
            backtracking(n, 0, expression);
            
            list.sort(null);

            for (int i = 0; i < list.size(); i++){
                sb.append(list.get(i)).append('\n');
            }

            sb.append('\n');
        }

        System.out.print(sb);
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