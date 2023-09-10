import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Point {
    public int x, y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int r, c;
    static int[][] sudoku = new int[10][10];
    static ArrayList<Point> filling = new ArrayList<>();
    static int end;

    public static boolean check(Point point, int target){
        int sx = (point.x - 1) / 3 * 3 + 1;
        int sy = (point.y - 1) / 3 * 3 + 1;

        for (int i = sy; i < sy + 3; i++){
            for (int j = sx; j < sx + 3; j++){
                if (sudoku[i][j] == target){
                    return false;
                }
            }
        }

        return true;
    }

    public static void backtracking(int depth){
        if (depth == end){
            StringBuilder sb = new StringBuilder();

            for (int i = 1; i <= 9; i++){
                for (int j = 1; j <= 9; j++){
                    sb.append(sudoku[i][j]);
                }
                sb.append('\n');
            }
            System.out.println(sb);
            System.exit(0);
        }

        for (int i = 1; i <= 9; i++){
            Point cur = filling.get(depth);
            int bit = 1 << i;

            if ((bit & sudoku[cur.y][0]) != 0 || (bit & sudoku[0][cur.x]) != 0) continue;
            
            if (check(cur, i)){
                sudoku[cur.y][0] |= bit;
                sudoku[0][cur.x] |= bit;
                sudoku[cur.y][cur.x] = i;
                backtracking(depth + 1);
                sudoku[cur.y][0] ^= bit;
                sudoku[0][cur.x] ^= bit;
                sudoku[cur.y][cur.x] = 0;                
            }
        }
    }

    public static void print(){
        for (int i = 1; i <= 9; i++){
            for (int j = 1; j <= 9; j++){
                System.out.print(sudoku[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        for (int i = 1; i <= 9; i++){
            char[] input = br.readLine().toCharArray();

            for (int j = 1; j <= 9; j++){
                sudoku[i][j] = input[j - 1] - 48;
                sudoku[i][0] |= 1 << sudoku[i][j];
                sudoku[0][j] |= 1 << sudoku[i][j];

                if (sudoku[i][j] == 0){
                    filling.add(new Point(j, i));
                }
            }
        }

        end = filling.size();
        backtracking(0);
    }
}