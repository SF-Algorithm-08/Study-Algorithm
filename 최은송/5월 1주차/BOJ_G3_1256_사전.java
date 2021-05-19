package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G3_1256_사전 {
	// 200C100일 때 자연수 범위 넘어감 
	static long dp[][];
	static int N, M, K;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()); 
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp = new long[N+1][M+1];
		
		for(int n = 0; n <=N; n++) {
			Arrays.fill(dp[n], 1);
		}
		for(int n = 1; n <= N; n++) {
			for(int m = 1; m <= M; m++) {
				dp[n][m] = Math.min(dp[n-1][m] + dp[n][m-1], 1000000001); 
			}
		}
		if(K > dp[N][M])	sb.append("-1");	
		else				getString(N, M);
		System.out.println(sb.toString());
	}
	static void getString(int n, int m) {
		if(n == 0) {
			while(--m >= 0) {
				sb.append("z");
			}
			return;
		}
		if(m == 0) {
			while(--n >= 0) {
				sb.append("a");
			}
			return;
		}
		if(dp[n-1][m] >= K) {
			sb.append("a");
			getString(n-1, m);
		}else {
			sb.append("z");
			K -= dp[n-1][m];
			getString(n, m-1);
		}
	}
	
}