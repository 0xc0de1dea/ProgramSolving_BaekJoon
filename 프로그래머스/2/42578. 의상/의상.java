import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {
        int cnt = 1;
        HashMap<String, Integer> hm = new HashMap<>();

        for (int i = 0; i < clothes.length; i++){
            String type = clothes[i][1];
            hm.put(type, hm.getOrDefault(type, 0) + 1);
        }

        for (int val : hm.values()){
            cnt *= val + 1;
        }

        return cnt - 1;
    }
}