package boj.Silver;
//S1 떡 먹는 호랑이 (다이나믹 프로그래밍,수학)
//solved

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_2502_떡먹는호랑이 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int day = Integer.parseInt(st.nextToken());
		int cake = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[day+1][2];
		dp[1] = new int[] {1,0};
		dp[2] = new int[] {0,1};
		for(int i=3; i<=day; i++) {
			dp[i][0]=dp[i-2][0]+dp[i-1][0];
			dp[i][1]=dp[i-2][1]+dp[i-1][1];
		}
		
		int first=1;
		while(true) {
			if((cake-first*dp[day][0])%dp[day][1]==0) break;
			else first++;
		}
		
		System.out.printf("%d\n%d", first, (cake-first*dp[day][0])/dp[day][1]);
	}
}
