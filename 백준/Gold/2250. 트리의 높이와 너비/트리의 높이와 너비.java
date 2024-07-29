import java.util.ArrayList;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Node2 {
    int left;
    int right;

    public Node2(int left, int right){
        this.left = left;
        this.right = right;
    }
}

public class Main {
    static int n;
    static ArrayList<ArrayList<Node2>> Nodes = new ArrayList<>();
    static int[] relDist;
    static int[] absDist;
    static ArrayList<ArrayList<Integer>> level = new ArrayList<>();

    public static int calcRelDist(int cur, int dir){
        int leftRelDist = 0;
        int rightRelDist = 0;
        boolean flag = false;

        for (Node2 nxt : Nodes.get(cur)){
            int left = nxt.left;
            int right = nxt.right;
            flag = true;

            if (left != -1){
                leftRelDist = calcRelDist(left, -1);
            }
            
            if (right != -1){
                rightRelDist = calcRelDist(right, 1);
            }

            if (dir == -1) relDist[cur] = dir * (rightRelDist + 1);
            else if (dir == 1) relDist[cur] = dir * (leftRelDist + 1);
        }

        if (!flag) return relDist[cur] = 1;

        return leftRelDist + rightRelDist + 1;
    }

    public static void calcAbsDist(int cur, int pos, int lvl){
        absDist[cur] = pos + relDist[cur];
        level.get(lvl).add(cur);

        for (Node2 nxt : Nodes.get(cur)){
            int left = nxt.left;
            int right = nxt.right;

            if (left != -1){
                calcAbsDist(left, absDist[cur], lvl + 1);
            }

            if (right != -1){
                calcAbsDist(right, absDist[cur], lvl + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        relDist = new int[n + 1];
        absDist = new int[n + 1];
        
        for (int i = 0; i <= n; i++){
            Nodes.add(new ArrayList<>());
            level.add(new ArrayList<>());
        }

        boolean[] chkChild = new boolean[n + 1];

        for (int i = 1; i <= n; i++){
            int parent = in.nextInt();
            int childA = in.nextInt();
            int childB = in.nextInt();
            Nodes.get(parent).add(new Node2(childA, childB));

            if (childA != -1) chkChild[childA] = true;
            if (childB != -1) chkChild[childB] = true;
        }

        int root = 0;

        for (int i = 1; i <= n; i++){
            if (!chkChild[i]){
                root = i;
                break;
            }
        }

        calcRelDist(root, 0);
        calcAbsDist(root, 10, 1);

        int maxLevel = 10_000;
        int maxDist = 0;

        for (int i = 1; i <= n; i++){
            if (level.get(i).isEmpty()) break;

            int left = level.get(i).get(0);
            int right = level.get(i).get(level.get(i).size() - 1);
            int diff = absDist[right] - absDist[left] + 1;

            if (diff > maxDist){
                maxDist = diff;
                maxLevel = i;
            } else if (diff == maxDist){
                if (maxLevel > i){
                    maxLevel = i;
                }
            }
        }

        System.out.printf("%d %d", maxLevel, maxDist);
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