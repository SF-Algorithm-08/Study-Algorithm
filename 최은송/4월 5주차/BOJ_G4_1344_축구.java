package boj;

import java.util.Scanner;

public class BOJ_G4_1344_축구 {
	static double a, b, dp[][][];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		a = sc.nextInt() / 100.0;
		b = sc.nextInt() / 100.0;

		dp = new double[19][19][19];
		System.out.print(dfs(0,0,0));
	}

	static double dfs(int ascore, int bscore, int cnt) {
		if(cnt == 18) {
			if(isPrime(ascore))	return 1.0;
			if(isPrime(bscore)) return 1.0;
			return 0.0;
		}
		if(dp[ascore][bscore][cnt] != 0.0)
			return dp[ascore][bscore][cnt];

		dp[ascore][bscore][cnt] += a * (1-b) * dfs(ascore+1, bscore, cnt+1);
		dp[ascore][bscore][cnt] += (1-a) * b * dfs(ascore, bscore+1, cnt+1);
		dp[ascore][bscore][cnt] += a * b * dfs(ascore+1, bscore+1, cnt+1);
		dp[ascore][bscore][cnt] += (1-a) * (1-b) * dfs(ascore, bscore, cnt+1);

		return dp[ascore][bscore][cnt];
	}
	static boolean isPrime(int x) {
		if(x == 2 || x == 3 || x == 5 || x == 7 || x == 11 || x == 13 || x == 17)
			return true;
		return false;
	}
}
