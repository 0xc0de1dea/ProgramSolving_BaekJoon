import java.util.ArrayList;
import java.util.TreeMap;

class Solution {
    public int[] solution(int k, int[] score) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < score.length; i++){
            if (i < k){
                tm.put(score[i], tm.getOrDefault(score[i], 0) + 1);
            } else {
                int last = tm.firstKey();

                if (last < score[i]){
                    tm.put(last, tm.get(last) - 1);

                    if (tm.get(last) == 0){
                        tm.remove(last);
                    }

                    tm.put(score[i], tm.getOrDefault(score[i], 0) + 1);
                }
            }

            list.add(tm.firstKey());
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}