import java.util.HashMap;

class Solution {
    public boolean isValid(char[][] map, int x, int y, int h, int w){
        return !(x < 0 || x >= h || y < 0 || y >= w || map[x][y] == 'X');
    }

    public int[] solution(String[] park, String[] routes) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        int h = park.length;
        int w = park[0].length();

        char[][] map = new char[h][w];

        int sx = 0;
        int sy = 0;

        for (int i = 0; i < h; i++){
            for (int j = 0; j < w; j++){
                map[i][j] = park[i].charAt(j);

                if (map[i][j] == 'S'){
                    sx = i;
                    sy = j;
                }
            }
        }

        HashMap<Character, Integer> hm = new HashMap<>();
        hm.put('E', 0);
        hm.put('S', 1);
        hm.put('W', 2);
        hm.put('N', 3);

        for (String cmd : routes){
            String[] split = cmd.split(" ");
            int dir = hm.get(split[0].charAt(0));
            int dist = Integer.parseInt(split[1]);

            int nx = sx;
            int ny = sy;

            boolean flag = true;

            for (int i = 1; i <= dist; i++){
                nx += dx[dir];
                ny += dy[dir];

                if (!isValid(map, nx, ny, h, w)){
                    flag = false;
                    break;
                }
            }

            if (flag){
                sx += dx[dir] * dist;
                sy += dy[dir] * dist;
            }
        }

        return new int[] {sx, sy};
    }
}