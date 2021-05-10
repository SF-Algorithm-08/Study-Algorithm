package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G3_1256_사전 {
	// 200C100일 때 자연수 범위 넘어감 
	static long dp[][];
	static int N, M, K;
	static String ans = "";
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()); 
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp = new long[N+1][M+1];
		
		for(int n = 1; n <= N; n++) {
			dp[n][0] = 1;
			for(int m = 1; m <= M; m++) {
				dp[n][m] = Math.min(dp[n-1][m] + dp[n][m-1], 1000000001);
			}
		}
		System.out.println();
		if(K>dp[N][M])
			ans = "-1";
		else
			find(N,M,K-1);
		System.out.println(ans);
	}
	
	static void find(int acnt, int zcnt, long left) {
		if(acnt == 0) {
			while(zcnt-- > 0) {
				ans += "z";
			}
			return;
		}
		if(zcnt == 0) {
			while(acnt-- > 0) {
				ans += "a";
			}
			return;
		}
		long temp = dp[acnt-1][zcnt];
		if(left < temp) {
			ans += "a";
			find(acnt-1, zcnt, left);
		}else {
			ans += "z";
			find(acnt, zcnt-1, left-temp);
		}
	}
}