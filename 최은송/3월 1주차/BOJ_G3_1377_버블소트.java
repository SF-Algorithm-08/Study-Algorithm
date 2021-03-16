package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_G3_1377_버블소트 {

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		int[][] arr = new int[N][2];
		for(int i=0; i<N; i++) {
			arr[i][0] = Integer.parseInt(in.readLine());
			arr[i][1] = i;
		}
		
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		int cnt = 0;
		for(int i=0; i<N; i++) {
			if(arr[i][1] - i > cnt)
				cnt = arr[i][1] - i;
		}
		System.out.println(cnt+1);
	}
}