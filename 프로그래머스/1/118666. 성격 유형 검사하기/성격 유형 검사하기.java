import java.util.HashMap;

class Solution {
    public String solution(String[] survey, int[] choices) {
        int[] score = new int[8];

        HashMap<Character, Integer> hm = new HashMap<>();
        hm.put('R', 0);
        hm.put('T', 1);
        hm.put('C', 2);
        hm.put('F', 3);
        hm.put('J', 4);
        hm.put('M', 5);
        hm.put('A', 6);
        hm.put('N', 7);

        HashMap<Integer, Integer> given = new HashMap<>();
        given.put(1, 3);
        given.put(2, 2);
        given.put(3, 1);
        given.put(5, 1);
        given.put(6, 2);
        given.put(7, 3);

        HashMap<Integer, Character> type = new HashMap<>();
        type.put(0, 'R');
        type.put(1, 'T');
        type.put(2, 'C');
        type.put(3, 'F');
        type.put(4, 'J');
        type.put(5, 'M');
        type.put(6, 'A');
        type.put(7, 'N');

        for (int i = 0; i < survey.length; i++){
            char a = survey[i].charAt(0);
            char b = survey[i].charAt(1);

            if (choices[i] < 4) score[hm.get(a)] += given.get(choices[i]);
            else if (choices[i] > 4) score[hm.get(b)] += given.get(choices[i]);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++){
            if (score[i << 1] >= score[i << 1 | 1]){
                sb.append(type.get(i << 1));
            } else {
                sb.append(type.get(i << 1 | 1));
            }
        }

        return sb.toString();
    }
}