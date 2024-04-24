/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int k;
    static int[] temp;
    static StringBuilder sb = new StringBuilder();

    public static void merge(int[] members, int left, int mid, int right){
        int l = left;
        int r = mid + 1;
        int idx = left;

        while (l <= mid && r <= right){
            if (members[l] <= members[r]){
                temp[idx++] = members[l++];
            } else {
                temp[idx++] = members[r++];
            }
        }

        while (l <= mid){
            temp[idx++] = members[l++];
        }

        while (r <= right){
            temp[idx++] = members[r++];
        }

        for (int i = left; i <= right; i++){
            members[i] = temp[i];
        }
    }

    public static void mergeSort(int[] members, int left, int right, int K){
        if (left < right){
            int mid = left + right >> 1;

            mergeSort(members, left, mid, K << 1);
            mergeSort(members, mid + 1, right, K << 1);
            merge(members, left, mid, right);

            if (k == K){
                for (int i = left; i <= right; i++){
                    sb.append(members[i]).append(' ');
                }
            }
        }
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        
        int n = in.nextInt();
        int[] members = new int[n];
        temp = new int[n];

        for (int i = 0; i < n; i++){
            members[i] = in.nextInt();
        }

        k = in.nextInt();

        mergeSort(members, 0, n - 1, 1);

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