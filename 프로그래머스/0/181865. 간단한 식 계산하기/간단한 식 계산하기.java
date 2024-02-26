class Solution {
    public int solution(String binomial) {
        int answer = 0;
        String[] expression = binomial.split(" ");
        
        if (expression[1].equals("+")){
            answer = Integer.parseInt(expression[0]) + Integer.parseInt(expression[2]);
        }
        else if (expression[1].equals("-")){
            answer = Integer.parseInt(expression[0]) - Integer.parseInt(expression[2]);
        }
        else if (expression[1].equals("*")){
            answer = Integer.parseInt(expression[0]) * Integer.parseInt(expression[2]);
        } 
        
        return answer;
    }
}