package boj;

import java.util.Scanner;

public class BOJ_S1_2502_떡먹는호랑이 {	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int D = sc.nextInt();
		int K = sc.nextInt();
		
		int[] dp = new int[D+1];
		loop: for(int i = 1; i <= K; i++) {
			dp[1] = i;
			for(int j=i; j <= K; j++) {
				dp[2] = j;
				for(int k=3; k<= D; k++) {
					dp[k] = dp[k-1] + dp[k-2];
				}
				if(dp[D] == K) {
					System.out.println(dp[1]);
					System.out.println(dp[2]);
					break loop;
				}
			}
		}
	}
}
