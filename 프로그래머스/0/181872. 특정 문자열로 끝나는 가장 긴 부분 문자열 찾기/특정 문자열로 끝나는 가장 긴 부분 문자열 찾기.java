class Solution {
    public String solution(String myString, String pat) {
        String answer = "";
        
        for (int i = pat.length() - 1; i < myString.length(); i++){
            boolean flag = true;

            for (int j = 0; j < pat.length(); j++){
                if (myString.charAt(i - (pat.length() - 1) + j) != pat.charAt(j)){
                    flag = false;
                    break;
                }
            }

            if (flag){
                answer = myString.substring(0, i + 1);
            }
        }
        
        return answer;
    }
}