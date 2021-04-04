package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_17845_수강과목 {

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[K+1][N+1];
		int[] value = new int[K+1];
		int[] time = new int[K+1];
		
		for(int i=1; i<=K; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			value[i] = Integer.parseInt(st.nextToken());
			time[i] = Integer.parseInt(st.nextToken());
		}
		
		int cv = 0, ct = 0;
		for(int i = 1; i <= K; i++) {
			cv = value[i];
			ct = time[i];
			for(int t = 1; t <= N; t++) {
				if(ct <= t) {
					dp[i][t] = Math.max(dp[i-1][t-ct] + cv, dp[i-1][t]);
				}else {
					dp[i][t] = dp[i-1][t];
				}
			}
		}
		System.out.println(dp[K][N]);
	}

}
