class Solution {
    public int[] solution(String myString) {
        int[] answer = {};
        String[] newMyString = myString.split("x", myString.length());
        answer = new int[newMyString.length];

        for (int i = 0; i < newMyString.length; i++){
            answer[i] = newMyString[i].length();
        }

        return answer;
    }
}