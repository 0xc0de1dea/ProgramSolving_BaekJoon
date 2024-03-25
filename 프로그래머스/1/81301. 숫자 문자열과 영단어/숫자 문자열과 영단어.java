import java.util.HashMap;

class Solution {
    public int solution(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("zero", 0);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);

        String number = "";
        String ans = "";

        for (char c : s.toCharArray()){
            if (c >= 'a' && c <= 'z'){
                number += c;
            } else {
                ans += c;
            }

            if (map.containsKey(number)){
                ans += map.get(number);
                number = "";
            }
        }
        
        return Integer.parseInt(ans);
    }
}