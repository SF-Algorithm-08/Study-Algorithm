package boj.Gold;
//G3 버블 소트(정렬)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_G3_1377_버블소트 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		int[][] numbers = new int[n][2];
		for(int i=0; i<n; i++) {
			numbers[i][0] = Integer.parseInt(br.readLine().trim());
			numbers[i][1] = i;
		}
		//[][0]요소를 기준으로 오름차순 정렬
		Arrays.sort(numbers, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0]==o2[0]) return o2[0] - o1[0];
				return o1[0] - o2[0];
			}
		});
		
		int max=0;
		for(int i=0; i<n; i++) {
			max = Math.max(max, numbers[i][1]-i);
		}
		System.out.println(max+1);
	}
}
