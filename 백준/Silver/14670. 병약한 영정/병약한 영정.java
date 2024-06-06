//TESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTEST

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   
        int N = Integer.parseInt(br.readLine());

        HashMap<Integer, Integer> drug = new HashMap<>();
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int effect = Integer.parseInt(st.nextToken());
            int name = Integer.parseInt(st.nextToken());

            drug.put(effect, name);
        }

        StringBuilder sb = new StringBuilder();
        int R = Integer.parseInt(br.readLine());

        for(int i=0; i<R; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            StringBuilder temp = new StringBuilder();

            int L = Integer.parseInt(st.nextToken());
            for(int j=0; j<L; j++) {
                int S = Integer.parseInt(st.nextToken());
                if(drug.containsKey(S))
                    temp.append(drug.get(S)).append(" ");
                else {
                    temp = new StringBuilder();
                    temp.append("YOU DIED");
                    break;
                }
            }
            sb.append(temp.toString()).append("\n");
        }

        System.out.println(sb.toString());
        br.close();
        // int n = in.nextInt();
        // HashMap<Integer, Integer> map = new HashMap<>();
        // int[] match = new int[101];

        // for (int i = 0; i <= 100; i++){
        //     match[i] = -1;
        // }

        // for (int i = 0; i < n; i++){
        //     int Me = in.nextInt();
        //     int Mn = in.nextInt();
        //     map.put(Me, Mn);
        //     match[Me] = Mn;
        // }

        // int r = in.nextInt();

        // for (int i = 0; i < r; i++){
        //     int l = in.nextInt();
        //     int[] arr = new int[l];
        //     boolean flag = true;
        //     StringBuilder sb2 = new StringBuilder();

        //     for (int j = 0; j < l; j++){
        //         int s = in.nextInt();
        //         arr[j] = match[s];

        //         if (match[s] == -1){
        //             flag = false;
        //             break;
        //         }
        //     }

        //     if (flag){
        //         for (int item : arr){
        //             sb.append(item).append(' ');
        //         }
        //         sb.append('\n');
        //     } else {
        //         sb.append("YOU DIED").append('\n');
        //     }
        // }

        // System.out.print(sb);
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