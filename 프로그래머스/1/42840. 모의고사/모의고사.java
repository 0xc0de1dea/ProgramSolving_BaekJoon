import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[] solution(int[] answers) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] c = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int[] score = new int[3];

        int ptrA = 0;
        int ptrB = 0;
        int ptrC = 0;

        for (int answer : answers){
            if (a[ptrA++] == answer) score[0]++;

            if (b[ptrB++] == answer) score[1]++;

            if (c[ptrC++] == answer) score[2]++;

            ptrA %= a.length;
            ptrB %= b.length;
            ptrC %= c.length;
        }

        int max = Arrays.stream(score).max().getAsInt();
        ArrayList<Integer> list = new ArrayList<>();

        if (max == score[0]) list.add(1);

        if (max == score[1]) list.add(2);

        if (max == score[2]) list.add(3);

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}