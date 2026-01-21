
import java.util.HashMap;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        HashMap<String, Integer> ranking = new HashMap<>();
        String[] idx = new String[players.length + 1];
        
        for (int i = 0; i < players.length; i++){
            ranking.put(players[i], i + 1);
            idx[i + 1] = players[i];
        }

        for (String s : callings){
            int aLoc = ranking.get(s);
            int bLoc = aLoc - 1;
            String t = idx[bLoc];
            ranking.put(t, aLoc);
            ranking.put(s, bLoc);
            idx[aLoc] = t;
            idx[bLoc] = s;
        }

        String[] res = new String[players.length];

        for (int i = 1; i < idx.length; i++){
            res[i - 1] = idx[i];
        }

        return res;
    }
}