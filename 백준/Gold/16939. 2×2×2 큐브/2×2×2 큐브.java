import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Cube {
    private int[] cube = new int[24];

    public Cube(int[] cubeData){
        cube = cubeData.clone();
    }

    public void rotateCWTopXY(){
        int tmp = cube[0];
        int tmp2 = cube[1];
        cube[0] = cube[14];
        cube[1] = cube[12];
        cube[14] = cube[11];
        cube[12] = cube[10];
        cube[11] = cube[17];
        cube[10] = cube[19];
        cube[17] = tmp;
        cube[19] = tmp2;
    }

    public void rotateCWFrontXZ(){
        int tmp = cube[7];
        int tmp2 = cube[6];
        cube[7] = cube[19];
        cube[6] = cube[18];
        cube[19] = cube[23];
        cube[18] = cube[22];
        cube[23] = cube[15];
        cube[22] = cube[14];
        cube[15] = tmp;
        cube[14] = tmp2;
    }

    public void rotateCWRightYZ(){
        int tmp = cube[7];
        int tmp2 = cube[5];
        cube[7] = cube[3];
        cube[5] = cube[1];
        cube[3] = cube[20];
        cube[1] = cube[22];
        cube[20] = cube[11];
        cube[22] = cube[9];
        cube[11] = tmp;
        cube[9] = tmp2;
    }

    public boolean isFit(){
        boolean check = true;

        for (int i = 0; i <= 20; i += 4){
            if (cube[i] != cube[i + 1] || cube[i + 1] != cube[i + 2] || cube[i + 2] != cube[i + 3] || cube[i + 3] != cube[i]){
                check = false;
                break;
            }
        }

        return check;
    }
}

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] cubeData = new int[24];

        for (int i = 0; i < 24; i++){
            cubeData[i] = Integer.parseInt(st.nextToken());
        }

        Cube cube = new Cube(cubeData);
        cube.rotateCWTopXY();

        if (cube.isFit()){
            System.out.println(1);
            return;
        }

        cube.rotateCWTopXY();
        cube.rotateCWTopXY();

        if (cube.isFit()){
            System.out.println(1);
            return;
        }

        cube.rotateCWTopXY();
        cube.rotateCWFrontXZ();

        if (cube.isFit()){
            System.out.println(1);
            return;
        }

        cube.rotateCWFrontXZ();
        cube.rotateCWFrontXZ();

        if (cube.isFit()){
            System.out.println(1);
            return;
        }

        cube.rotateCWFrontXZ();
        cube.rotateCWRightYZ();

        if (cube.isFit()){
            System.out.println(1);
            return;
        }

        cube.rotateCWRightYZ();
        cube.rotateCWRightYZ();

        if (cube.isFit()){
            System.out.println(1);
            return;
        }

        System.out.println(0);
    }
}