import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class ChessPiece {
    public int x, y;
    public int dir;
    public int height = 1;

    public ChessPiece(int x, int y, int dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void turnDir(){
        if (dir == 1) dir = 2;
        else if (dir == 2) dir = 1;
        else if (dir == 3) dir = 4;
        else dir = 3;
    }
}

class BaseData {
    public int destX, destY;
    public int destSize;
    public int currSize;
    public int offset;
    public int orderType;

    public BaseData(int offset, int orderType){
        this.offset = offset;
        this.orderType = orderType;
    }
}

public class Main {
    static int n;
    static int[][] chessboard;
    static ArrayList<ChessPiece> chesspiece = new ArrayList<>();
    static ArrayList<ChessPiece>[][] chessLayer;
    static int[] dx = new int[] { 0, 1, -1, 0, 0 };
    static int[] dy = new int[] { 0, 0, 0, -1, 1 };

    public static boolean isOut(int x, int y){
        if (x > 0 && x <= n && y > 0 && y <= n) return false;
        else return true;
    }

    public static BaseData getMoveData(ChessPiece base){
        BaseData baseData = new BaseData(base.height - 1, 2);
        int nx = base.x + dx[base.dir];
        int ny = base.y + dy[base.dir];

        if (!isOut(nx, ny)){
            baseData.destX = nx;
            baseData.destY = ny;
            baseData.destSize = chessLayer[ny][nx].size();

            if (chessboard[ny][nx] == 0){
                baseData.orderType = 0;
            }
            else if (chessboard[ny][nx] == 1){
                baseData.currSize = chessLayer[base.y][base.x].size();
                baseData.orderType = 1;
            }
            else {
                int nnx = base.x - dx[base.dir];
                int nny = base.y - dy[base.dir];
                base.turnDir();

                if (!isOut(nnx, nny) && chessboard[nny][nnx] != 2){
                    baseData.destX = nnx;
                    baseData.destY = nny;
                    baseData.currSize = chessLayer[base.y][base.x].size();
                    baseData.destSize = chessLayer[nny][nnx].size();
                    
                    if (chessboard[nny][nnx] == 0)
                        baseData.orderType = 0;
                    else
                        baseData.orderType = 1;                    
                }
            }
        }
        else {
            int nnx = base.x - dx[base.dir];
            int nny = base.y - dy[base.dir];
            base.turnDir();

            if (chessboard[nny][nnx] != 2){
                baseData.destX = nnx;
                baseData.destY = nny;
                baseData.currSize = chessLayer[base.y][base.x].size();
                baseData.destSize = chessLayer[nny][nnx].size();

                if (chessboard[nny][nnx] == 0)
                    baseData.orderType = 0;
                else
                    baseData.orderType = 1;
            }
        }

        return baseData;
    }

    public static int movePiece(ChessPiece chessPiece, BaseData baseData){
        if (baseData.orderType == 0){
            chessLayer[baseData.destY][baseData.destX].add(chessPiece);
            chessPiece.x = baseData.destX;
            chessPiece.y = baseData.destY;
            chessPiece.height += baseData.destSize - baseData.offset;
        }
        else {
            chessLayer[baseData.destY][baseData.destX].add(chessPiece);
            chessPiece.x = baseData.destX;
            chessPiece.y = baseData.destY;
            chessPiece.height = baseData.currSize + 1 + baseData.offset - chessPiece.height;
            chessPiece.height += baseData.destSize - baseData.offset;
        }

        return chessPiece.height;
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        chessboard = new int[n + 1][n + 1];
        chessLayer = new ArrayList[n + 1][n + 1];

        for (int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++){
                chessboard[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i <= n; i++){
            for (int j = 0; j <= n; j++){
                chessLayer[i][j] = new ArrayList<>();
            }
        }

        for (int id = 0; id < k; id++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            ChessPiece tmp = new ChessPiece(x, y, dir);

            chesspiece.add(tmp);
            chessLayer[y][x].add(tmp);
        }

        int cnt = 0;
        boolean check = false;
        int limit = 999;

        jump : while (limit-- > 0){
            cnt++;
            int moveCnt = 0;

            for (int id = 0; id < k; id++){
                ChessPiece base = chesspiece.get(id);

                if (true){
                    int baseX = base.x;
                    int baseY = base.y;
                    BaseData bd = getMoveData(base);

                    if (bd.orderType != 2){
                        int size = chessLayer[baseY][baseX].size();
                        moveCnt++;

                        for (int idx = size - 1; idx >= 0; idx--){
                            if (chessLayer[baseY][baseX].get(idx).height > bd.offset){
                                int height = movePiece(chessLayer[baseY][baseX].get(idx), bd);
                                chessLayer[baseY][baseX].remove(idx);

                                if (height >= 4){
                                    check = true;
                                    break jump;
                                }
                            }
                        }
                    }
                }
            }

            if (moveCnt == 0){
                check = false;
                break jump;
            }

            /*for (int i = 0; i < k; i++){
                System.out.println(i + "번째 체스 : " + chesspiece.get(i).y + " " + chesspiece.get(i).x + " 높이 " + chesspiece.get(i).height + " 방향 " + chesspiece.get(i).dir);
            }
            System.out.println();*/
        }

        if (check) System.out.println(cnt);
        else System.out.println(-1);
    }
}