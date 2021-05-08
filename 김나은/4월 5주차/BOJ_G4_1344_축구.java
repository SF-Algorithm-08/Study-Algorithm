package algo;

import java.util.Scanner;

public class BOJ_G4_1344_축구 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		double A = sc.nextDouble() / 100;
		double B = sc.nextDouble() / 100;
		double[][][] dp = new double[19][19][19];

		dp[0][0][0] = 1;
		for (int interval = 0; interval < 18; interval++) {
			for (int a = 0; a <= interval; a++) {
				for (int b = 0; b <= interval; b++) {
					double cur = dp[interval][a][b];
					dp[interval + 1][a][b] += cur * (1 - A) * (1 - B);
					dp[interval + 1][a + 1][b] += cur * A * (1 - B);
					dp[interval + 1][a][b + 1] += cur * (1 - A) * B;
					dp[interval + 1][a + 1][b + 1] += cur * A * B;
				}
			}
		}

		double ans = 0;
		int[] num = { 0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18 };
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				ans += dp[18][num[i]][num[j]];
			}
		}

		System.out.println(1 - ans);
	}

}
