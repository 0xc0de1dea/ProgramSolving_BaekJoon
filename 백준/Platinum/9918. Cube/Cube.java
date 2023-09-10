import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Cube {
    private int t, u, r, d, l, b;
    public int passed;

    public Cube(){
        t = u = r = d = l = b = passed = 0;
    }

    public int getBottom(){
        return b;
    }

    public void setBottom(int num){
        b = num;
        passed++;
    }

    public void rotatePosX(){
        int tmp = b;
        b = r;
        r = t;
        t = l;
        l = tmp;
    }

    public void rotateNegX(){
        int tmp = b;
        b = l;
        l = t;
        t = r;
        r = tmp;
    }

    public void rotatePosY(){
        int tmp = b;
        b = d;
        d = t;
        t = u;
        u = tmp;
    }

    public void rotateNegY(){
        int tmp = b;
        b = u;
        u = t;
        t = d;
        d = tmp;
    }

    public int getFace(){
        if (t == 1) return b;
        else if (u == 1) return d;
        else if (r == 1) return l;
        else if (d == 1) return u;
        else if (l == 1) return r;
        else return t;
    }
}

public class Main {
    static int[][] planarfigure;
    static int[] dx = new int[] { 1, 0, -1, 0 };
    static int[] dy = new int[] { 0, 1, 0, -1 };
    static boolean[][] isVisited;
    static boolean check;

    public static void search(Cube cube, int x, int y){
        for (int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= 6 || ny < 0 || ny >= 6) continue;

            if (planarfigure[ny][nx] > 0 && !isVisited[ny][nx]){
                isVisited[ny][nx] = true;

                switch (i){
                    case 0 :
                        cube.rotatePosX();

                        if (cube.getBottom() != 0){
                            check = false;
                            return;
                        }

                        cube.setBottom(planarfigure[ny][nx]);
                        search(cube, nx, ny);
                        cube.rotateNegX();
                        break;

                    case 1 :
                        cube.rotatePosY();

                        if (cube.getBottom() != 0){
                            check = false;
                            return;
                        }

                        cube.setBottom(planarfigure[ny][nx]);
                        search(cube, nx, ny);
                        cube.rotateNegY();
                        break;

                    case 2 :
                        cube.rotateNegX();
                        
                        if (cube.getBottom() != 0){
                            check = false;
                            return;
                        }
                        
                        cube.setBottom(planarfigure[ny][nx]);
                        search(cube, nx, ny);
                        cube.rotatePosX();
                        break;

                    case 3 :
                        cube.rotateNegY();
                        
                        if (cube.getBottom() != 0){
                            check = false;
                            return;
                        }
                        
                        cube.setBottom(planarfigure[ny][nx]);
                        search(cube, nx, ny);
                        cube.rotatePosY();
                        break;
                }
            }
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Cube cube = new Cube();
        int sx = 0, sy = 0;
        planarfigure = new int[6][6];
        isVisited = new boolean[6][6];
        check = true;
        int cnt = 0;

        for (int j = 0; j < 6; j++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int k = 0; k < 6; k++){
                planarfigure[j][k] = Integer.parseInt(st.nextToken());

                if (planarfigure[j][k] > 0){
                    sx = k;
                    sy = j;
                    cnt++;
                }
            }
        }

        cube.setBottom(planarfigure[sy][sx]);
        isVisited[sy][sx] = true;
        search(cube, sx, sy);

        if (cube.passed == 6 && cnt == 6){
            sb.append(cube.getFace());
        }
        else {
            sb.append(0);
        }

        System.out.println(sb);
    }  
}