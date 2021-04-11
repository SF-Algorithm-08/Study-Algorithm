package boj;

import java.util.Scanner;

/**
 * 알약
 * n=0, XX 1
 * n=1, WH 1
 * n=2, WHWH WWHH 2
 * n=3, WHWHWH WHWWHH WWHHWH WWHWHH WWWHHH 5
 */
// 처음 2일 간 WH로 먹는다면, 그 뒤 4일은 약 2알일 때와 동일
// 처음에 W로 먹는다면, 그 뒤는 HWW / WHW / WWH로 나뉨 (맨 마지막은 H로 고정)
// 1) HWW : 3일부터 4일 간 큰 알약 2개를 결정짓는 경우 => DP[0]*DP[2] (10번 line 1, 2번 경우)
// 2) WHW : 앞에 WH 뒤에 WH로 먹는 경우 => DP[1]*DP[1] (10번 line 3번 경우)
// 3) WWH : 2-5일의 큰 알약 2개를 결정하는 경우 = DP[2] =>DP[2]*DP[0] (10번 line 4, 5번 경우)
// => DP[3] = DP[0]*DP[2] + DP[1]*DP[1] + DP[2]*DP[0]

public class BOJ_G5_4811 {
	static long[] DP;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DP = new long[31];
		makeDP();
		while(true) {
			int N = sc.nextInt();
			if(N == 0) break;
			System.out.println(DP[N]);
		}
	}

	private static void makeDP() {
		DP[0] = 1;
		DP[1] = 1; // WH
		DP[2] = 2; // WHWH, WWHH
		DP[3] = 5;
		for(int i = 4 ; i<=30 ; i++) {
			for(int j = 0 ; j<i ; j++) {
				DP[i] += DP[j]*DP[i-j-1];
			}
		}
	}

}
