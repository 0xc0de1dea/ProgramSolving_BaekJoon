class Solution {
    public String solution(String s) {
        String answer = "";

        int counting[] = new int[26];

        for (char c : s.toCharArray()){
            counting[c - 'a']++;
        }

        for (int i = 0; i < 26; i++){
            if (counting[i] == 1){
                answer += (char)(i + 'a');
            }
        }

        return answer;
    }
}