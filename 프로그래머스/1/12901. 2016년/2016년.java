class Solution {
    public String solution(int a, int b) {
        int[] months = new int[] {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] week = new String[] {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};

        int today = 4 + b;

        for (int i = 1; i < a; i++){
            today += months[i];
        }

        today %= 7;

        return week[today];
    }
}