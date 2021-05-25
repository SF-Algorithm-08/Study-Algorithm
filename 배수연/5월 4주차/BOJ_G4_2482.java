package boj;

import java.util.Scanner;

/**
 * 색상환
 * (선형)
 * K=0, 1
 * K=1, N
 * K>=1, N번째 색을 고르는 경우 / 안고르는 경우
 * N번째 색을 고르는 경우 		: N-2개에서 K-1개 고른 경우
 * N번째 색을 고르지 않는 경우 	: N-1개에서 K개 고른 경우
 * (원형)
 * 원의 시작점을 고른 경우 / 안고른 경우
 * 고른 경우	 : N-3개에서 K-1개 고른 경우
 * 안고른 경우	 : N-1개에서 K개 고른 경우
 */

public class BOJ_G4_2482 {
	static final int MOD = 1000000003;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		// 구할 수 없는 경우
		if((N)/2<K) {
			System.out.println("0");
			return;
		}
		
		// 초기 선언
		long[][] DP = new long[N+1][K+1];
		for(int n = 1 ; n<=N ; n++) {
			// K=0일 때, 1가지 경우만 존재
			DP[n][0] = 1;
			// K=1일 때, N번째 색을 고르는 경우는 N-1번째 색을 고른 경우+1
			DP[n][1] = n;
		}
		
		// DP
		for(int n = 2 ; n<=N ; n++) {
			for(int k = 2 ; k<=K ; k++) {
				DP[n][k] = (DP[n-2][k-1]+DP[n-1][k])%MOD;
			}
		}
		System.out.println((DP[N-3][K-1]+DP[N-1][K])%MOD);
	}
}
