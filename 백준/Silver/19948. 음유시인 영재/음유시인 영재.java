import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static ArrayList<ArrayList<Integer>> edges;
    static boolean[] isVisited;

    public static int dfs(int start){
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        isVisited[start] = true;
        int cnt = 0;

        while (!stack.isEmpty()){
            int cur = stack.pop();
            
            if (start != cur) cnt++;

            for (int next : edges.get(cur)){
                if (!isVisited[next]){
                    stack.push(next);
                    isVisited[next] = true;
                }
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        String[] poem = in.nextString().split("[ ]+");
        poem = Arrays.stream(poem).filter(x -> !x.isEmpty()).toArray(String[]::new);
        int spacebar = in.nextInt();
        int[] alphabets = new int[26];

        for (int i = 0; i < 26; i++){
            alphabets[i] = in.nextInt();
        }

        if (spacebar + 1 < poem.length){
            System.out.print(-1);
        } else {
            boolean flag = true;
            char prev = ' ';

            jump : for (int i = 0; i < poem.length; i++){
                int c2 = poem[i].charAt(0);
                int small2 = c2 >= 97 ? c2 - 'a' : c2 - 'A';
                alphabets[small2]--;
                
                for (int j = 0; j < poem[i].length(); j++){
                    char c = poem[i].charAt(j);

                    if (prev != c){
                        int small = c >= 97 ? c - 'a' : c - 'A';
                        alphabets[small]--;

                        if (alphabets[small] < 0){
                            flag = false;
                            break jump;
                        }
                    }

                    prev = c;
                }

                sb.append(poem[i].charAt(0) >= 97 ? (char)(poem[i].charAt(0) - 32) : (char)(poem[i].charAt(0)));
            }

            if (flag){
                System.out.print(sb);
            } else {
                System.out.print(-1);
            }
        }
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
        while ((c = read()) < 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
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