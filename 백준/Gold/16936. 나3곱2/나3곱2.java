import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

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
        ArrayList<Long> list = new ArrayList<>();
        
        Long minVal = Long.MAX_VALUE;
        int minIdx = 0;

        for (int i = 0; i < n; i++){
            Long num = in.nextLong();
            list.add(num);

            if (minVal > num){
                minVal = num;
                minIdx = i;
            }
        }

        Deque<Long> left = new ArrayDeque<>();
        Deque<Long> right = new ArrayDeque<>();
        left.add(list.get(minIdx));
        right.add(list.get(minIdx));
        list.remove(minIdx);

        while (true){
            boolean searched = false;

            for (int i = 0; i < list.size(); i++){
                if (left.peekFirst() * 3 == list.get(i)){
                    left.addFirst(list.get(i));
                    list.remove(i);
                    searched = true;
                    break;
                } else if (left.peekFirst() % 2 == 0 && left.peekFirst() / 2 == list.get(i)){
                    left.addFirst(list.get(i));
                    list.remove(i);
                    searched = true;
                    break;
                }
            }

            if (!searched){
                left.pollLast();
                break;
            }
        }

        while (true){
            boolean searched = false;

            for (int i = 0; i < list.size(); i++){
                if (right.peekLast() % 3 == 0 && right.peekLast() / 3 == list.get(i)){
                    right.addLast(list.get(i));
                    list.remove(i);
                    searched = true;
                    break;
                } else if (right.peekLast() * 2 == list.get(i)){
                    right.addLast(list.get(i));
                    list.remove(i);
                    searched = true;
                    break;
                }
            }

            if (!searched) break;
        }

        for (Long num : left){
            sb.append(num).append(' ');
        }

        for (Long num : right){
            sb.append(num).append(' ');
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