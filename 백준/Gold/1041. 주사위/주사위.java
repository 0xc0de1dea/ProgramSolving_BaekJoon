import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Dice {
    int[] nums;

    public Dice(int[] nums){
        this.nums = new int[6];

        for (int i = 0; i < 6; i++){
            this.nums[i] = nums[i];
        }
    }

    public int getSumFiveSide(){
        int[][] list = {
            { 1, 2, 3, 4, 5 },
            { 0, 2, 3, 4, 5 },
            { 0, 1, 3, 4, 5 },
            { 0, 1, 2, 4, 5 },
            { 0, 1, 2, 3, 5 },
            { 0, 1, 2, 3, 4 },
        };

        int min = 0x7f7f7f7f;

        for (int i = 0; i < list.length; i++){
            int sum = 0;

            for (int j = 0; j < 5; j++){
                sum += nums[list[i][j]];
            }

            min = Math.min(min, sum);
        }

        return min;
    }

    public int getSumThreeSide(){
        int[][] list = {
            { 0, 1, 2 },
            { 0, 1, 3 },
            { 0, 2, 4 },
            { 0, 3, 4 },
            { 1, 2, 5 },
            { 1, 3, 5 },
            { 2, 4, 5 },
            { 3, 4, 5 }
        };

        int min = 0x7f7f7f7f;

        for (int i = 0; i < list.length; i++){
            int sum = 0;

            for (int j = 0; j < 3; j++){
                sum += nums[list[i][j]];
            }

            min = Math.min(min, sum);
        }

        return min;
    }

    public int getSumTwoSide(){
        int[][] list = {
            { 0, 1 }, { 0, 2 },
            { 0, 3 }, { 0, 4 },
            { 1, 2 }, { 1, 3 },
            { 2, 4 }, { 3, 4 },
            { 1, 5 }, { 2, 5 },
            { 3, 5 }, { 4, 5 }
        };

        int min = 0x7f7f7f7f;

        for (int i = 0; i < list.length; i++){
            int sum = 0;

            for (int j = 0; j < 2; j++){
                sum += nums[list[i][j]];
            }

            min = Math.min(min, sum);
        }

        return min;
    }

    public int getSumOneSide(){
        int[] list = { 0, 1, 2, 3, 4, 5 };

        int min = 0x7f7f7f7f;

        for (int i = 0; i < 6; i++){
            min = Math.min(min, nums[list[i]]);
        }

        return min;
    }
}

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        long n = in.nextInt();
        int[] nums = new int[6];

        for (int i = 0; i < 6; i++){
            nums[i] = in.nextInt();
        }

        Dice dice = new Dice(nums);

        if (n == 1){
            System.out.println(dice.getSumFiveSide());
        } else {
            long sum = 0;
            sum += dice.getSumThreeSide() * 4;
            sum += dice.getSumTwoSide() * (8 * n - 12);
            sum += dice.getSumOneSide() * (5 * n - 6) * (n - 2);
            System.out.println(sum);
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