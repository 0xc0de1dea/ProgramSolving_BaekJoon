import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int r, c;
    static int m, n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = r - 1;
        n = c - 1;
        int t = Integer.parseInt(st.nextToken());

        Point[] machine = new Point[2];
        int[][] map = new int[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    machine[machine[0] == null ? 0 : 1] = new Point(i, j);
                }
            }
        }
        br.close();
        origin = new int[r][c];

        for (int i = 0; i < t; i++) {
            spread(map);
            cleanUpAir(map, machine[0].x, machine[1].y);
            cleanDownAir(map, machine[1].x,machine[1].y);
        }

        System.out.println(sumDust(map));
    }

    private static int sumDust(int[][] map) {
        int sum = 2;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sum += map[i][j];
            }
        }
        return sum;
    }

    private static void cleanDownAir(int[][] map, int x, int y) {
        pullArray(map, x + 1, m, 0, 1);
        System.arraycopy(map[m], 1, map[m], 0, n);
        pullArray(map, m, x, n, -1);
        System.arraycopy(map[x], 1, map[x], 2, n - 1);
        map[x][y + 1] = 0;
    }

    private static void cleanUpAir(int[][] map, int x, int y) {
        pullArray(map, x - 1, 0, 0, -1);
        System.arraycopy(map[0], 1, map[0], 0, n);
        pullArray(map, 0, x, n, 1);
        System.arraycopy(map[x], 1, map[x], 2, n - 1);
        map[x][y + 1] = 0;
    }

    private static void pullArray(int[][] map, int start, int end, int y, int dir) {
        for (int i = start; i != end; i += dir) {
            map[i][y] = map[i + dir][y];
        }
    }

    static int[][] origin;
    private static void spread(int[][] map) {
        for (int i = 0; i < r; i++) {
            System.arraycopy(map[i], 0, origin[i], 0, c);
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] >= 0) {
                    map[i][j] = calculateDust(map, origin, i, j);
                }
            }
        }
    }

    static int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static int calculateDust(int[][] map, int[][] origin, int i, int j) {
        int count = 0;
        int dust = 0;

        for (int[] dir : dirs) {
            int x = dir[0] + i;
            int y = dir[1] + j;

            if (x < 0 || y < 0 || x >= r || y >= c || map[x][y] == -1) {
                continue;
            }
            count++;
            dust += origin[x][y] / 5;
        }

        return origin[i][j] + dust - (origin[i][j] < 5 ? 0 : ((origin[i][j] / 5) * count));
    }

}//asdf