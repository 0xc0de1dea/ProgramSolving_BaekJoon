//TEST!@#$&*)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	//LOVE 철자마다 개수를 저장하기 위한 배열
	static int[] arr = new int[4];
	//최댓값은 100으로 나눴을때 0이 올 수도 있기 때문에 -1로 선언
	static int max = -1;
	static String love = "LOVE";
	//우승확률이 높은 팀 이름을 저장하기 위한 변수
	static String win = "";
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String Ename = br.readLine();
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			String Tname = br.readLine();
			
			//L부터 E까지 순차적으로 팀 이름과 연두 이름에 포함된 개수를 배열에 저장
			for(int j = 0; j < 4; j++) {
				int sum = Ename.length() - Ename.replace(String.valueOf(love.charAt(j)), "").length();
				sum += Tname.length() - Tname.replace(String.valueOf(love.charAt(j)), "").length();
				arr[j] = sum;
			}
			
			//(L+O) × (L+V) × (L+E) × (O+V) × (O+E) × (V+E)의 연산 값을 저장하기 위한 변수
			int total = 1;
			
			//연산하는 반복문
			for(int j = 0; j < 4; j++) {
				for(int k = (j + 1); k < 4; k++) {
					total *= (arr[j] + arr[k]);
				}
			}
			
			//현재 확률 최댓값과 이번 팀이름의 확률 값이 같을때
			if(max == (total % 100) && win != "") {
				String[] arr2 = new String[2];
				arr2[0] = win;
				arr2[1] = Tname;
				Arrays.sort(arr2);
				win = arr2[0];
			}
			
			//팀이름의 확률 값이 더 클때
			if(max < (total % 100)) {
				max = total % 100;
				win = Tname;
			}
		}
		System.out.println(win);
	}

}