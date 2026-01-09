import java.util.ArrayList;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] ans = new int[commands.length];

        for (int i = 0; i < commands.length; i++){
            ArrayList<Integer> list = new ArrayList<>();
            
            for (int j = commands[i][0] - 1; j <= commands[i][1] - 1; j++){
                list.add(array[j]);
            }

            list.sort(null);

            ans[i] = list.get(commands[i][2] - 1);
        }

        return ans;
    }
}