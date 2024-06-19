class Solution {
    public int solution(int[][] board) {
        final int INF = 0x7f7f7f7f;
        int[] dr = { -1, 0, 1, 1, 1, 0, -1, -1 };
        int[] dc = { 1, 1, 1, 0, -1, -1, -1, 0 };

        int rowSize = board.length;
        int colSize = board[0].length;
        int totSize = rowSize * colSize;

        int[][] map = new int[rowSize + 2][colSize + 2];

        for (int i = 0; i <= rowSize + 1; i++){
            map[i][0] = map[i][colSize + 1] = INF;
        }

        for (int i = 0; i <= colSize + 1; i++){
            map[0][i] = map[rowSize + 1][i] = INF;
        }

        for (int i = 1; i <= rowSize; i++){
            for (int j = 1; j <= colSize; j++){
                map[i][j] = board[i - 1][j - 1];
            }
        }

        for (int i = 1; i <= rowSize; i++){
            for (int j = 1; j <= colSize; j++){
                for (int k = 0; k < 8; k++){
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    
                    if (map[i][j] == 1 && map[nr][nc] != 1) map[nr][nc] = -1;
                }
            }
        }

        int cnt = 0;

        for (int i = 1; i <= rowSize; i++){
            for (int j = 1; j <= colSize; j++){
                if (map[i][j] != 0){
                    cnt++;
                }
            }
        }
        
        return totSize - cnt == 0 ? 0 : totSize - cnt;
    }
}