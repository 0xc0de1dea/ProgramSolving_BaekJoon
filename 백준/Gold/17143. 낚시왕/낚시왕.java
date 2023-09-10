import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Map.Entry;

class CustomIntegerArray {
    private int[] data;
    private int cashing = 1;

    public CustomIntegerArray(int[] data){
        this.data = data;
    }

    public int getData(int idx){
        return data[idx];
    }

    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        if (!(o instanceof CustomIntegerArray)){
            return false;
        }

        CustomIntegerArray other = (CustomIntegerArray)o;

        if (!other.canEqual(this)){
            return false;
        }
        if (!Arrays.equals(this.data, other.data)){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode(){
        int hash = cashing;
        final int PRIME = 31;

        if (hash == 1){
            for (int i = 0; i < data.length; i++){
                hash = PRIME * hash + data[i];
            }

            cashing = hash;
        }

        return hash;
    }

    protected boolean canEqual(Object other){
        return other instanceof CustomIntegerArray;
    }
}

public class Main {
    static int R, C, M;
    static int Rm, Cm;
    static HashMap<CustomIntegerArray, int[]> shark = new HashMap<>(10000);
    static int sum = 0;

    public static void fishing(int c){
        for (int r = 0; r < R; r++){
            CustomIntegerArray cur = new CustomIntegerArray(new int[] { r, c });
            
            if (shark.containsKey(cur)){
                sum += shark.get(cur)[2];
                //System.out.println(shark.get(cur)[2] + "잡힘");
                shark.remove(cur);
                return;
            }
        }
    }

    public static void moving(){
        int rem = 0;
        int mod = 0;
        HashMap<CustomIntegerArray, int[]> copy = new HashMap<>(10000);

        for (Entry<CustomIntegerArray, int[]> entry : shark.entrySet()){
            copy.put(entry.getKey(), entry.getValue());
        }

        shark.clear();

        for (Entry<CustomIntegerArray, int[]> entry : copy.entrySet()){
            CustomIntegerArray cPos = entry.getKey();
            CustomIntegerArray nPos;
            int[] info = entry.getValue();
            int dist = 0;

            switch (info[1]){
                case 1:
                    dist = info[0] + Rm - cPos.getData(0);
                    rem = dist / Rm;
                    mod = dist % Rm;

                    if (rem % 2 == 0){
                        nPos = new CustomIntegerArray(new int[] { Rm - mod, cPos.getData(1) });
                    }
                    else {
                        nPos = new CustomIntegerArray(new int[] { mod, cPos.getData(1) });
                        turnDirect(info);
                    }

                    //copy.remove(cPos);
                    catchAndEat(nPos, info);
                    break;

                case 4:
                    dist = info[0] + Cm - cPos.getData(1);
                    rem = dist / Cm;
                    mod = dist % Cm;

                    if (rem % 2 == 0){
                        nPos = new CustomIntegerArray(new int[] { cPos.getData(0), Cm - mod });
                    }
                    else {
                        nPos = new CustomIntegerArray(new int[] { cPos.getData(0), mod });
                        turnDirect(info);
                    }

                    //copy.remove(cPos);
                    catchAndEat(nPos, info);
                    break;

                case 2:
                    dist = info[0] + cPos.getData(0);
                    rem = dist / Rm;
                    mod = dist % Rm;

                    if (rem % 2 == 1){
                        nPos = new CustomIntegerArray(new int[] { Rm - mod, cPos.getData(1) });
                        turnDirect(info);
                    }
                    else {
                        nPos = new CustomIntegerArray(new int[] { mod, cPos.getData(1) });
                    }

                    //copy.remove(cPos);
                    catchAndEat(nPos, info);
                    break;

                case 3:
                    dist = info[0] + cPos.getData(1);
                    rem = dist / Cm;
                    mod = dist % Cm;

                    if (rem % 2 == 1){
                        nPos = new CustomIntegerArray(new int[] { cPos.getData(0), Cm - mod });
                        turnDirect(info);
                    }
                    else {
                        nPos = new CustomIntegerArray(new int[] { cPos.getData(0), mod });
                    }

                    //copy.remove(cPos);
                    catchAndEat(nPos, info);
                    break;
            }
        }
    }

    public static void turnDirect(int[] info){
        switch (info[1]){
            case 1:
                info[1] = 2;
                break;
            
            case 2:
                info[1] = 1;
                break;

            case 3:
                info[1] = 4;
                break;

            case 4:
                info[1] = 3;
                break;
        }
    }

    public static void catchAndEat(CustomIntegerArray nPos, int[] info){


        int[] nInfo = new int[] { info[0], info[1], info[2] };

        if (!shark.containsKey(nPos)){
            shark.put(nPos, nInfo);
        }
        else {
            if (shark.get(nPos)[2] < info[2]){
                //System.out.println(shark.get(nPos)[2] + " " + nInfo[2]);
                shark.put(nPos, nInfo);
            }
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Rm = R - 1;
        Cm = C - 1;

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            shark.put(new CustomIntegerArray(new int[] { r - 1, c - 1 }), new int[] { s, d, z });
        }

        for (int c = 0; c < C; c++){
            fishing(c);
            moving();

            /*for (Entry<CustomIntegerArray, int[]> entry : shark.entrySet()){
                System.out.println(entry.getKey().getData(0) + " " + entry.getKey().getData(1) + " 상어 " + entry.getValue()[2]);
            }
            System.out.println();*/
        }

        System.out.println(sum);
    }  
}