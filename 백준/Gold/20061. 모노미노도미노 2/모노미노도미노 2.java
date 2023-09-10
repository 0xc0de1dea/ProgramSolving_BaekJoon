import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BlockData {
    public int x, y;
    public int type;
    
    public BlockData(int x, int y, int type){
        this.x = x;
        this.y = y;
        this.type = type;
    }
}

class DeletePosData {
    public int x, y;
    public int score;
}

public class Main {
    public static BlockData setBlockGreen(int[][] board, BlockData inputBlock){
        BlockData outputBlock = new BlockData(0, inputBlock.y, inputBlock.type);
        
        if (inputBlock.type == 1){
            for (int x = 5; x < 10; x++){
                if (board[x + 1][inputBlock.y] == 1){
                    board[x][inputBlock.y] = 1;
                    outputBlock.x = x;
                    break;
                }
            }
        }
        else if (inputBlock.type == 2){
            for (int x = 5; x < 10; x++){
                if (board[x + 1][inputBlock.y] == 1 || board[x + 1][inputBlock.y + 1] == 1){
                    board[x][inputBlock.y] = board[x][inputBlock.y + 1] = 1;
                    outputBlock.x = x;
                    break;
                }
            }
        }
        else {
            for (int x = 4; x < 9; x++){
                if (board[x + 2][inputBlock.y] == 1){
                    board[x][inputBlock.y] = board[x + 1][inputBlock.y] = 1;
                    outputBlock.x = x;
                    break;
                }
            }
        }

        return outputBlock;
    }

    public static BlockData setBlockBlue(int[][] board, BlockData inputBlock){
        BlockData outputBlock = new BlockData(inputBlock.x, 0, inputBlock.type);
        
        if (inputBlock.type == 1){
            for (int y = 5; y < 10; y++){
                if (board[inputBlock.x][y + 1] == 1){
                    board[inputBlock.x][y] = 1;
                    outputBlock.y = y;
                    break;
                }
            }
        }
        else if (inputBlock.type == 3){
            for (int y = 5; y < 10; y++){
                if (board[inputBlock.x][y + 1] == 1 || board[inputBlock.x + 1][y + 1] == 1){
                    board[inputBlock.x][y] = board[inputBlock.x + 1][y] = 1;
                    outputBlock.y = y;
                    break;
                }
            }
        }
        else {
            for (int y = 4; y < 9; y++){
                if (board[inputBlock.x][y + 2] == 1){
                    board[inputBlock.x][y] = board[inputBlock.x][y + 1] = 1;
                    outputBlock.y = y;
                    break;
                }
            }
        }

        return outputBlock;
    }

    public static DeletePosData checkLineGreen(int[][] board, BlockData bd){
        DeletePosData dpd = new DeletePosData();

        if (board[bd.x][0] == 1 && board[bd.x][1] == 1 && board[bd.x][2] == 1 && board[bd.x][3] == 1){
            dpd.x = bd.x;
            dpd.score++;

            for (int y = 0; y < 4; y++){
                board[bd.x][y] = 0;
            }
        }

        if (bd.type == 3){
            if (board[bd.x + 1][0] == 1 && board[bd.x + 1][1] == 1 && board[bd.x + 1][2] == 1 && board[bd.x + 1][3] == 1){
                dpd.x = bd.x + 1;
                dpd.score++;

                for (int y = 0; y < 4; y++){
                    board[bd.x + 1][y] = 0;
                }
            }
        }

        return dpd;
    }

    public static DeletePosData checkLineBlue(int[][] board, BlockData bd){
        DeletePosData dpd = new DeletePosData();

        if (board[0][bd.y] == 1 && board[1][bd.y] == 1 && board[2][bd.y] == 1 && board[3][bd.y] == 1){
            dpd.y = bd.y;
            dpd.score++;

            for (int x = 0; x < 4; x++){
                board[x][bd.y] = 0;
            }
        }

        if (bd.type == 2){
            if (board[0][bd.y + 1] == 1 && board[1][bd.y + 1] == 1 && board[2][bd.y + 1] == 1 && board[3][bd.y + 1] == 1){
                dpd.y = bd.y + 1;
                dpd.score++;

                for (int x = 0; x < 4; x++){
                    board[x][bd.y + 1] = 0;
                }
            }
        }

        return dpd;
    }

    public static void rearrangementAreaGreen(int[][] board, DeletePosData dpd, BlockData bd){
        if (dpd.score == 2){
            for (int x = dpd.x; x > 7; x--){
                System.arraycopy(board[x - 2], 0, board[x], 0, 4);
                System.arraycopy(board[0], 0, board[x - 2], 0, 4);
            }
        }
        else if (dpd.score == 1){
            for (int x = dpd.x; x > 5; x--){
                System.arraycopy(board[x - 1], 0, board[x], 0, 4);
            }

            for (int y = 0; y < 4; y++){
                board[5][y] = 0;
            }
        }
        else {
            if (bd.x == 5){
                for (int x = 9; x > 5; x--){
                    System.arraycopy(board[x - 1], 0, board[x], 0, 4);
                }

                for (int y = 0; y < 4; y++){
                    board[5][y] = 0;
                }
            }
            else if (bd.x == 4){
                for (int x = 9; x > 5; x--){
                    System.arraycopy(board[x - 2], 0, board[x], 0, 4);
                }

                for (int x = 4; x < 6; x++){
                    for (int y = 0; y < 4; y++){
                        board[x][y] = 0;
                    }
                }
            }
        }
    }

    public static void rearrangementAreaBlue(int[][] board, DeletePosData dpd, BlockData bd){
        if (dpd.score == 2){
            for (int y = dpd.y; y > 7; y--){
                for (int x = 0; x < 4; x++){
                    board[x][y] = board[x][y - 2];
                    board[x][y - 2] = 0;
                }
            }
        }
        else if (dpd.score == 1){
            for (int y = dpd.y; y > 5; y--){
                for (int x = 0; x < 4; x++){
                    board[x][y] = board[x][y - 1];
                }
            }

            for (int x = 0; x < 4; x++){
                board[x][5] = 0;
            }
        }
        else {
            if (bd.y == 5){
                for (int y = 9; y > 5; y--){
                    for (int x = 0; x < 4; x++){
                        board[x][y] = board[x][y - 1];
                    }
                }

                for (int x = 0; x < 4; x++){
                    board[x][5] = 0;
                }
            }
            else if (bd.y == 4){
                for (int y = 9; y > 5; y--){
                    for (int x = 0; x < 4; x++){
                        board[x][y] = board[x][y - 2];
                    }
                }

                for (int y = 4; y < 6; y++){
                    for (int x = 0; x < 4; x++){
                        board[x][y] = 0;
                    }
                }
            }
        }
    }

    public static void debugging(int[][] board){
        for (int x = 0; x < 11; x++){
            for (int y = 0; y < 11; y++){
                System.out.print(board[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[11][11];
        board[10][0] = board[10][1] = board[10][2] = board[10][3] = board[0][10] = board[1][10] = board[2][10] = board[3][10] = 1;
        int score = 0;
        StringTokenizer st;
        
        while (n-- > 0){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            BlockData getData = new BlockData(x, y, t);
            BlockData setDataGreen = setBlockGreen(board, getData);
            BlockData setDataBlue = setBlockBlue(board, getData);
            DeletePosData dpdGreen = checkLineGreen(board, setDataGreen);
            DeletePosData dpdBlue = checkLineBlue(board, setDataBlue);
            rearrangementAreaGreen(board, dpdGreen, setDataGreen);
            rearrangementAreaBlue(board, dpdBlue, setDataBlue);
            score += dpdGreen.score + dpdBlue.score;
        }

        int cnt = 0;

        for (int x = 6; x < 10; x++){
            for (int y = 0; y < 4; y++){
                if (board[x][y] == 1){
                    cnt++;
                }

                if (board[y][x] == 1){
                    cnt++;
                }
            }
        }

        sb.append(score).append('\n').append(cnt);
        System.out.println(sb);
    }
}