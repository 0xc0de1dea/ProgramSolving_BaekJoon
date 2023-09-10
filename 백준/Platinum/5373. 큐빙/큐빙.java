/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Plane {
    char[][] pieces = new char[3][3];

    public Plane(char color){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                pieces[i][j] = color;
            }
        }
    }

    public Plane(char[][] pieces){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                this.pieces[i][j] = pieces[i][j];
            }
        }
    }

    // '+' : CW , '-' : CCW
    void rotate(char dir){
        if (dir == '+'){
            char t1 = pieces[0][0];
            char t2 = pieces[0][1];
            pieces[0][0] = pieces[2][0];
            pieces[2][0] = pieces[2][2];
            pieces[2][2] = pieces[0][2];
            pieces[0][2] = t1;
            pieces[0][1] = pieces[1][0];
            pieces[1][0] = pieces[2][1];
            pieces[2][1] = pieces[1][2];
            pieces[1][2] = t2;
        }
        else if (dir == '-'){
            char t1 = pieces[0][0];
            char t2 = pieces[0][1];
            pieces[0][0] = pieces[0][2];
            pieces[0][2] = pieces[2][2];
            pieces[2][2] = pieces[2][0];
            pieces[2][0] = t1;
            pieces[0][1] = pieces[1][2];
            pieces[1][2] = pieces[2][1];
            pieces[2][1] = pieces[1][0];
            pieces[1][0] = t2;
        }
    }
}

class Cube {
    Plane[] planes = new Plane[6]; // { 앞 : R, 왼 : G, 뒤 : O, 오 : B, 위 : W, 아 : Y }

    public Cube(){
        char[] colors = new char[] { 'r', 'g', 'o', 'b', 'w', 'y' };

        for (int i = 0; i < 6; i++){
            planes[i] = new Plane(colors[i]);
        }
    }

    void rotateFront(char dir){
        Plane t = new Plane(planes[1].pieces);
        if (dir == '+'){
            for (int i = 0; i < 3; i++) planes[1].pieces[i][2] = planes[5].pieces[2][2 - i];
            for (int i = 0; i < 3; i++) planes[5].pieces[2][i] = planes[3].pieces[i][0];
            for (int i = 0; i < 3; i++) planes[3].pieces[i][0] = planes[4].pieces[2][i];
            for (int i = 0; i < 3; i++) planes[4].pieces[2][i] = t.pieces[2 - i][2];
        }
        else if (dir == '-'){
            for (int i = 0; i < 3; i++) planes[1].pieces[i][2] = planes[4].pieces[2][2 - i];
            for (int i = 0; i < 3; i++) planes[4].pieces[2][i] = planes[3].pieces[i][0];
            for (int i = 0; i < 3; i++) planes[3].pieces[i][0] = planes[5].pieces[2][i];
            for (int i = 0; i < 3; i++) planes[5].pieces[2][i] = t.pieces[2 - i][2];
        }
        planes[0].rotate(dir);
    }

    void rotateLeft(char dir){
        Plane t = new Plane(planes[2].pieces);
        if (dir == '+'){
            for (int i = 0; i < 3; i++) planes[2].pieces[i][2] = planes[5].pieces[i][2];
            for (int i = 0; i < 3; i++) planes[5].pieces[i][2] = planes[0].pieces[2 - i][0];
            for (int i = 0; i < 3; i++) planes[0].pieces[i][0] = planes[4].pieces[i][0];
            for (int i = 0; i < 3; i++) planes[4].pieces[i][0] = t.pieces[2 - i][2];
        }
        else if (dir == '-'){
            for (int i = 0; i < 3; i++) planes[2].pieces[i][2] = planes[4].pieces[2 - i][0];
            for (int i = 0; i < 3; i++) planes[4].pieces[i][0] = planes[0].pieces[i][0];
            for (int i = 0; i < 3; i++) planes[0].pieces[i][0] = planes[5].pieces[2 - i][2];
            for (int i = 0; i < 3; i++) planes[5].pieces[i][2] = t.pieces[i][2];
        }
        planes[1].rotate(dir);
    }

    void rotateBack(char dir){
        Plane t = new Plane(planes[3].pieces);
        if (dir == '+'){
            for (int i = 0; i < 3; i++) planes[3].pieces[i][2] = planes[5].pieces[0][i];
            for (int i = 0; i < 3; i++) planes[5].pieces[0][i] = planes[1].pieces[2 - i][0];
            for (int i = 0; i < 3; i++) planes[1].pieces[i][0] = planes[4].pieces[0][2 - i];
            for (int i = 0; i < 3; i++) planes[4].pieces[0][i] = t.pieces[i][2];
        }
        else if (dir == '-'){
            for (int i = 0; i < 3; i++) planes[3].pieces[i][2] = planes[4].pieces[0][i];
            for (int i = 0; i < 3; i++) planes[4].pieces[0][i] = planes[1].pieces[2 - i][0];
            for (int i = 0; i < 3; i++) planes[1].pieces[i][0] = planes[5].pieces[0][2 - i];
            for (int i = 0; i < 3; i++) planes[5].pieces[0][i] = t.pieces[i][2];
        }
        planes[2].rotate(dir);
    }

    void rotateRight(char dir){
        Plane t = new Plane(planes[0].pieces);
        if (dir == '+'){
            for (int i = 0; i < 3; i++) planes[0].pieces[i][2] = planes[5].pieces[2 - i][0];
            for (int i = 0; i < 3; i++) planes[5].pieces[i][0] = planes[2].pieces[i][0];
            for (int i = 0; i < 3; i++) planes[2].pieces[i][0] = planes[4].pieces[2 - i][2];
            for (int i = 0; i < 3; i++) planes[4].pieces[i][2] = t.pieces[i][2];
        }
        else if (dir == '-'){
            for (int i = 0; i < 3; i++) planes[0].pieces[i][2] = planes[4].pieces[i][2];
            for (int i = 0; i < 3; i++) planes[4].pieces[i][2] = planes[2].pieces[2 - i][0];
            for (int i = 0; i < 3; i++) planes[2].pieces[i][0] = planes[5].pieces[i][0];
            for (int i = 0; i < 3; i++) planes[5].pieces[i][0] = t.pieces[2 - i][2];
        }
        planes[3].rotate(dir);
    }

    void rotateTop(char dir){
        Plane t = new Plane(planes[0].pieces);
        if (dir == '+'){
            for (int i = 0; i < 3; i++) planes[0].pieces[0][i] = planes[3].pieces[0][i];
            for (int i = 0; i < 3; i++) planes[3].pieces[0][i] = planes[2].pieces[0][i];
            for (int i = 0; i < 3; i++) planes[2].pieces[0][i] = planes[1].pieces[0][i];
            for (int i = 0; i < 3; i++) planes[1].pieces[0][i] = t.pieces[0][i];
        }
        else if (dir == '-'){
            for (int i = 0; i < 3; i++) planes[0].pieces[0][i] = planes[1].pieces[0][i];
            for (int i = 0; i < 3; i++) planes[1].pieces[0][i] = planes[2].pieces[0][i];
            for (int i = 0; i < 3; i++) planes[2].pieces[0][i] = planes[3].pieces[0][i];
            for (int i = 0; i < 3; i++) planes[3].pieces[0][i] = t.pieces[0][i];
        }
        planes[4].rotate(dir);
    }

    void rotateBottom(char dir){
        Plane t = new Plane(planes[0].pieces);
        if (dir == '+'){
            for (int i = 0; i < 3; i++) planes[0].pieces[2][i] = planes[1].pieces[2][i];
            for (int i = 0; i < 3; i++) planes[1].pieces[2][i] = planes[2].pieces[2][i];
            for (int i = 0; i < 3; i++) planes[2].pieces[2][i] = planes[3].pieces[2][i];
            for (int i = 0; i < 3; i++) planes[3].pieces[2][i] = t.pieces[2][i];
        }
        else if (dir == '-'){
            for (int i = 0; i < 3; i++) planes[0].pieces[2][i] = planes[3].pieces[2][i];
            for (int i = 0; i < 3; i++) planes[3].pieces[2][i] = planes[2].pieces[2][i];
            for (int i = 0; i < 3; i++) planes[2].pieces[2][i] = planes[1].pieces[2][i];
            for (int i = 0; i < 3; i++) planes[1].pieces[2][i] = t.pieces[2][i];
        }
        planes[5].rotate(dir);
    }
}

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        //Reader in = new Reader();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0){
            Cube cube = new Cube();
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for (int i = 0; i < n; i++){
                char[] input = st.nextToken().toCharArray();
                char face = input[0];
                char dir = input[1];

                switch (face){
                    case 'F' :
                        cube.rotateFront(dir);
                        break;

                    case 'L' :
                        cube.rotateLeft(dir);
                        break;

                    case 'B' :
                        cube.rotateBack(dir);
                        break;

                    case 'R' :
                        cube.rotateRight(dir);
                        break;

                    case 'U' :
                        cube.rotateTop(dir);
                        break;

                    case 'D' :
                        cube.rotateBottom(dir);
                        break;
                }
            }

            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    sb.append(cube.planes[4].pieces[i][j]);
                }
                sb.append('\n');
            }
        }

        System.out.print(sb);
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    char nextChar() throws Exception {
        char ch = ' ';
        byte c;
        while ((c = read()) <= 32);
        do ch = (char)c;
        while (isAlphabet(c = read()));
        return ch;
    }
    
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32); //{ if (size < 0) return -1; }
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    long nextLong() throws Exception {
        long n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32);
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    double nextDouble() throws Exception {
        double n = 0, div = 1;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32);
        if (c == 45) { c = read(); isMinus = true; }
        else if (c == 46) { c = read(); }
        do n = (n * 10) + (c & 15);
        while (isNumber(c = read()));
        if (c == 46) { while (isNumber(c = read())){ n += (c - 48) / (div *= 10); }}
        return isMinus ? -n : n;
    }

    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }

    boolean isAlphabet(byte c){
        return 96 < c && c < 123;
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}