package boj;

import java.util.Scanner;

public class BOJ_G5_4811_알약 {
	static long dp[][];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N;
		while((N = sc.nextInt()) != 0) { 
			dp = new long[31][31];
			System.out.println(find(N, 0));
		}
	}
	static long find(int one, int half) {
		if(dp[one][half] != 0)	return dp[one][half];
		// 1개 알약만 남은 경우 
		if(one == 0)	return 1;
		// 1개 알약 먹을 때
		dp[one][half] += find(one-1, half+1);
		// 반개 알약 먹을 때
		if(half > 0) {
			dp[one][half] += find(one, half-1);
		}
		return dp[one][half];
	}
}
