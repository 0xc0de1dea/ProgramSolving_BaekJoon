//import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Collections;

class Edge implements Comparable<Edge>{
    public int a, b;

    public Edge(int a, int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Edge o){
        return this.a != o.a ? this.a - o.a : o.b - this.b;
    }
}

public class Main {
    public static int lowerBound(int[] arr, int s, int e, int target){
        while (s != e){
            int m = (s + e) / 2;

            if (arr[m] < target) s = m + 1;
            else e = m;
        }
        return e;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int n = in.nextInt();
        int[] ori1 = new int[n + 1];
        int[] ori2 = new int[n + 1];
        int[] new1 = new int[n + 1];
        int[] new2 = new int[n + 1];

        for (int i = 1; i <= n; i++){
            ori1[i] = in.nextInt();
            new1[i] = ori1[i];
        }

        for (int i = 1; i <= n; i++){
            ori2[i] = in.nextInt();
            new2[ori2[i]] = i;
        }

        ArrayList<Edge> edge = new ArrayList<>();

        for (int i = 1; i <= n; i++){
            int l = new1[i];

            for (int r = l + 4; r >= l - 4; r--){
                if (r >= 1 && r <= n){
                    int j = new2[r];

                    if (Math.abs(l - r) <= 4){
                        edge.add(new Edge(i, j));
                    }
                }
            }
        }

        Collections.sort(edge);
        int[] lis = new int[8 * n];
        int len = 0;
        int sz = edge.size();

        for (int i = 0; i < sz; i++){
            if (edge.get(i).b > lis[len]){
                lis[++len] = edge.get(i).b;
            }
            else {
                lis[lowerBound(lis, 1, len, edge.get(i).b)] = edge.get(i).b;
            }
        }

        System.out.print(len);
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;
    
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32);//{ if (size == -1) return -1; }
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

    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}