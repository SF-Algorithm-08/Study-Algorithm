package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G4_2482_색상환 {
	static final int MOD = 1000000003;
	static int dp[][], N, K;
	public static void main(String[] args) throws IOException{

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		K = Integer.parseInt(in.readLine());
		
		dp = new int[N+1][K+1];
		
		if(K > N/2) {
			System.out.println(0);
		}else {
			for(int i = 1; i <= N; i++)
				dp[i][1] = i;
			
			for(int n = 4; n <= N; n++)
				for(int k = 2; k <= K; k++)
					dp[n][k] = (dp[n-1][k] + dp[n-2][k-1]) % MOD;
			 
			System.out.println(dp[N][K]);
		}
	}
	
}
