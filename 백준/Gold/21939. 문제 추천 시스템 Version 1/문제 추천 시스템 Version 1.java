import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        TreeMap<Integer, TreeMap<Integer,Integer>> problem = new TreeMap<>((x, y) -> x - y);
        HashMap<Integer, Integer> hm = new HashMap<>();
        
        for (int i = 0; i < n; i++){
            int p = in.nextInt();
            int l = in.nextInt();
            
            if (problem.containsKey(l)){
                TreeMap<Integer, Integer> tm = problem.get(l);
                tm.put(p, p);
            } else {
                TreeMap<Integer, Integer> tm = new TreeMap<>((x, y) -> x - y);
                tm.put(p, p);
                problem.put(l, tm);
            }
        }

        int m = in.nextInt();

        for (int i = 0; i < m; i++){
            String command = in.nextString();

            if (command.equals("recommend")){
                int x = in.nextInt();

                if (x == -1){
                    int key = 0;
                    TreeMap<Integer, Integer> val = null;

                    while (true){
                        key = problem.firstKey();
                        val = problem.get(key);
    
                        while (!val.isEmpty() && hm.containsKey(val.firstKey())){
                            hm.remove(val.remove(val.firstKey()));
                        }

                        if (val.size() != 0){
                            break;
                        } else {
                            problem.remove(key);
                        }
                    }

                    sb.append(val.firstKey()).append('\n');
                } else if (x == 1){
                    int key = 0;
                    TreeMap<Integer, Integer> val = null;

                    while (true){
                        key = problem.lastKey();
                        val = problem.get(key);
    
                        while (!val.isEmpty() && hm.containsKey(val.lastKey())){
                            hm.remove(val.remove(val.lastKey()));
                        }

                        if (val.size() != 0){
                            break;
                        } else {
                            problem.remove(key);
                        }
                    }

                    sb.append(val.lastKey()).append('\n');
                }
            } else if (command.equals("add")){
                int p = in.nextInt();
                int l = in.nextInt();

                if (problem.containsKey(l)){
                    TreeMap<Integer, Integer> tm = problem.get(l);
                    tm.put(p, p);
                } else {
                    TreeMap<Integer, Integer> tm = new TreeMap<>((x, y) -> x - y);
                    tm.put(p, p);
                    problem.put(l, tm);
                }
            } else if (command.equals("solved")){
                int p = in.nextInt();
                hm.put(p, 1);
            }
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
        while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
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