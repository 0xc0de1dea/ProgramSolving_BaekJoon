class Solution {
    public int[] solution(String[] wallpaper) {
        int h = wallpaper.length;
        int w = wallpaper[0].length();

        int minX = h;
        int minY = w;
        int maxX = -1;
        int maxY = -1;

        for (int i = 0; i < h; i++){
            for (int j = 0; j < w; j++){
                char c = wallpaper[i].charAt(j);

                if (c == '#'){
                    minX = Math.min(minX, i);
                    minY = Math.min(minY, j);
                    maxX = Math.max(maxX, i + 1);
                    maxY = Math.max(maxY, j + 1);
                }
            }
        }

        return new int[] {minX, minY, maxX, maxY};
    }
}