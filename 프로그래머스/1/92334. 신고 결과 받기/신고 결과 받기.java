
import java.util.HashMap;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] ans = new int[id_list.length];

        HashMap<String, Integer> id = new HashMap<>();
        int n = 0;

        for (String s : id_list){
            id.put(s, n++);
        }

        int[][] table = new int[id_list.length][id_list.length];

        for (String s : report){
            String[] split = s.split(" ");
            String a = split[0];
            String b = split[1];

            if (table[id.get(a)][id.get(b)] == 0){
                table[id.get(a)][id.get(b)] = 1;
            }
        }

        for (int i = 0; i < id_list.length; i++){
            int receive = 0;

            for (int j = 0; j < id_list.length; j++){
                if (table[j][i] == 1){
                    receive++;
                }
            }

            if (receive >= k){
                for (int j = 0; j < id_list.length; j++){
                    if (table[j][i] == 1){
                        ans[j]++;
                    }
                }
            }
        }

        return ans;
    }
}