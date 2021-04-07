package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 수강 과목
 */

public class BOJ_G5_17845 {
	static int N, K, max;
	static int[] important;
	static int[] time;
	static int[][] results;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		max = 0;
		important = new int[K+1];
		time = new int[K+1];
		results = new int[K+1][N+1];
		for(int k = 1 ; k<=K ; k++) {
			st = new StringTokenizer(br.readLine());
			important[k] = Integer.parseInt(st.nextToken());
			time[k] = Integer.parseInt(st.nextToken());
		}
		for(int i = 1 ; i<=K ; i++) {
			for(int j = 1 ; j<=N ; j++) {
				if(time[i]<=j) {
					results[i][j] = Math.max(results[i-1][j], results[i-1][j-time[i]]+important[i]);
				}
				else results[i][j] = results[i-1][j];
			}
		}
		System.out.println(results[K][N]);
	}

}
