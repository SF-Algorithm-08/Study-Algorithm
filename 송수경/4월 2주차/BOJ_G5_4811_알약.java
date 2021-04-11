package boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_4811_알약 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		long[][] dp = new long[31][31];
		for(int i=0; i<31; i++) dp[i][0]=1;
		int N;
		
		for(int i=0; i<31; i++) {
			for(int j=0; j<31; j++) {
				if(i<j || j==0) continue;
				dp[i][j] = dp[i][j-1] + dp[i-1][j];
			}
		}
		
		while(true) {
			N = Integer.parseInt(br.readLine().trim());
			if(N==0) break;
			sb.append(dp[N][N]).append("\n");
		}
		System.out.println(sb);
	}

}
