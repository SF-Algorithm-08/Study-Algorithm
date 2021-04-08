// 알약

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_4811 {

	static long[][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// [한 조각 전체][쪼갠 반 조각]
		dp = new long[31][31];
		dp[1][0] = 1;
		dp[2][0] = 2;
		dp[3][0] = 5;

		solve(30, 0);

		int N = 0;
		while ((N = Integer.parseInt(br.readLine())) != 0) {
			System.out.println(dp[N][0]);
		}
	}

	private static long solve(int one, int half) {
		if (dp[one][half] != 0) return dp[one][half];

		if (one == 0) return 1;
		else if (half == 0) return dp[one][half] = solve(one-1, half+1);
		else return dp[one][half] = solve(one-1, half+1) + solve(one, half-1);
	}
}

// 1 1 WH
// 2 2 WHWH WWHH
// 3 5 WHWHWH WHWWHH WWHWHH WWHHWH WWWHHH