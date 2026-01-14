class Solution {
    public String solution(String X, String Y) {
        char[] cX = X.toCharArray();
        char[] cY = Y.toCharArray();

        int[] cntX = new int[10];
        int[] cntY = new int[10];

        for (int i = 0; i < cX.length; i++){
            cntX[cX[i] - '0']++;
        }

        for (int i = 0; i < cY.length; i++){
            cntY[cY[i] - '0']++;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 9; i >= 0; i--){
            while (cntX[i]-- > 0 && cntY[i]-- > 0){
                sb.append(i);
            }
        }

        if (sb.length() != 0){
            if (sb.indexOf("0") == 0 && sb.length() > 1){
                return "0";
            } else {
                return sb.toString();
            }
        } else {
            return "-1";
        }
    }
}