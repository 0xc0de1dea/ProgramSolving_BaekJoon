class Solution {
    public int solution(String s) {
        int cnt = 0;

        while (s != ""){
            char target = s.charAt(0);
            int x = 0;
            int y = 0;
            int idx = 0;

            while (true){
                if (idx == s.length()){
                    cnt++;
                    s = "";
                    break;
                }

                if (s.charAt(idx++) == target) x++;
                else y++;

                if (x == y){
                    cnt++;
                    
                    if (idx >= s.length()){
                        s = "";
                    } else {
                        s = s.substring(idx, s.length());
                    }
                    break;
                }
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("abracadabra"));
    }
}