package boj;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 전체 경우의 수 : (중복조합) nHm = n+m-1Cm 
 * nCm = n-1Cm + n-1Cm-1
 * n+mCm = n+m-1Cm + n+m-1Cm-1
 * nHm 	= n+m-1Cm
 * 		= n+m-2Cm + n+m-2Cm-1
 *		= n-1Hm + nHm-1
 * 
 * 맨 앞 한 자리 고정한 경우의 수를 K와 비교
 * K보다 작으면 맨 앞 a로 고정, 크면 z로 시작
 * 반복
 */

public class BOJ_G3_1256 {
	static int N, M, K;
	static long[][] DP;
	static String ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		DP = new long[N+1][M+1];
		ans = "";
		
		// 모든 경우의 수 구해서 DP에 넣기
		getDP();
		
		if(K>DP[N][M]) ans = "-1";
		else find(N, M, K-1);
		System.out.println(ans);
	}

	private static void getDP() {
		for(int n = 0 ; n<=N ; n++) {
			Arrays.fill(DP[n], 1);
		}
		for(int n = 1 ; n<=N ; n++) {
			for(int m = 1 ; m<=M ; m++) {
				DP[n][m] = Math.min(DP[n-1][m] + DP[n][m-1], 1000000001);
			}
		}
	}

	private static void find(int Acnt, int Zcnt, long left) {
		// A나 Z의 개수가 0이 된다면 남은 개수만큼 ans에 쭉 붙여주기
		if(Acnt == 0) {
			for(int m = 0 ; m<Zcnt ; m++) {
				ans += "z";
			}
			return;
		}
		if(Zcnt == 0) {
			for(int n = 0 ; n<Acnt ; n++) {
				ans += "a";
			}
			return;
		}
		
		// 맨 앞 한 자리 고정한 경우의 수를 K에서 쓴거 빼고 나머지와 비교
		long temp = DP[Acnt-1][Zcnt];
		if(left<temp) {
			ans += "a";
			find(Acnt-1, Zcnt, left);
		}
		else {
			ans += "z";
			find(Acnt, Zcnt-1, left-temp);
		}
	}
}
