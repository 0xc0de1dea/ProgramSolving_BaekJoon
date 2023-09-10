import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Dice{
    public int t; //top
    public int b; //bottom
    public int u; //up
    public int r; //right
    public int d; //down
    public int l; //left
    public int x, y;

    public Dice(int x, int y){
        t = b = u = r = d = l = 0;
        this.x = x;
        this.y = y;
    }

    public void rollRight(){
        int tmp = t;
        t = l;
        l = b;
        b = r;
        r = tmp;
        y++;
    }

    public void rollLeft(){
        int tmp = t;
        t = r;
        r = b;
        b = l;
        l = tmp;
        y--;
    }

    public void rollUp(){
        int tmp = t;
        t = d;
        d = b;
        b = u;
        u = tmp;
        x--;
    }

    public void rollDown(){
        int tmp = t;
        t = u;
        u = b;
        b = d;
        d = tmp;
        x++;
    }
}

public class Main{
    static int n, m, x, y, k;
    static int[][] board;
    static int[] order;
    static int[] dx = new int[] { 0, 0, 0, -1, 1 };
    static int[] dy = new int[] { 0, 1, -1, 0, 0 };
    static StringBuilder sb = new StringBuilder();

    public static void copyAndPrint(Dice dice, int nx, int ny){
        if (board[nx][ny] != 0){
            dice.b = board[nx][ny];
            board[nx][ny] = 0;
        }
        else {
            board[nx][ny] = dice.b;
        }

        sb.append(dice.t).append('\n');
    }

    public static void diceRoll(){
        Dice dice = new Dice(x, y);

        for (int i = 0; i < k; i++){
            int nx = dice.x + dx[order[i]];
            int ny = dice.y + dy[order[i]];

            switch (order[i]){
                case 1:
                    if (ny < m){
                        dice.rollRight();
                        copyAndPrint(dice, nx, ny);
                    }
                    break;

                case 2:
                    if (0 <= ny){
                        dice.rollLeft();
                        copyAndPrint(dice, nx, ny);
                    }
                    break;

                case 3:
                    if (0 <= nx){
                        dice.rollUp();
                        copyAndPrint(dice, nx, ny);
                    }
                    break;

                case 4:
                    if (nx < n){
                        dice.rollDown();
                        copyAndPrint(dice, nx, ny);
                    }
                    break;
            }
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        order = new int[k];

        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < k; i++){
            order[i] = Integer.parseInt(st.nextToken());
        }

        diceRoll();
        System.out.println(sb);
    }
}