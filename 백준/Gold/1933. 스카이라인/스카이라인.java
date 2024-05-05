import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Building {
    int x, h;

    public Building(int x, int h){
        this.x = x;
        this.h = h;
    }
}

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        ArrayList<Building> list = new ArrayList<>();

        for (int i = 0; i < n; i++){
            int l = in.nextInt();
            int h = in.nextInt();
            int r = in.nextInt();

            list.add(new Building(l, h));
            list.add(new Building(r, -h));
        }

        list.sort((x, y) -> x.x == y.x ? y.h - x.h : x.x - y.x);

        TreeMap<Integer, Integer> treeMap = new TreeMap<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return o2 - o1;
            }
        });
        ArrayList<Building> candidate = new ArrayList<>();

        for (int i = 0; i < list.size(); i++){
            Building cur = list.get(i);

            if (cur.h > 0){
                treeMap.put(cur.h, treeMap.getOrDefault(cur.h, 0) + 1);
            } else {
                int key = -cur.h;
                int val = treeMap.get(key);

                if (val == 1){
                    treeMap.remove(key);
                } else {
                    treeMap.put(key, val - 1);
                }
            }

            if (treeMap.size() == 0){
                candidate.add(new Building(cur.x, 0));
            } else {
                candidate.add(new Building(cur.x, treeMap.firstKey()));
            }
        }

        ArrayList<Building> ans = new ArrayList<>();
        ans.add(new Building(candidate.get(0).x, candidate.get(0).h));
        int prev = candidate.get(0).h;

        for (int i = 1; i < candidate.size(); i++){
            Building cur = candidate.get(i);

            if (cur.h != prev){
                prev = cur.h;
                ans.add(new Building(cur.x, cur.h));
            }
        }

        for (Building building : ans){
            sb.append(building.x).append(' ').append(building.h).append(' ');
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