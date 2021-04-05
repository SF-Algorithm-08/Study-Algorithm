// 수강 과목

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_17845 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 최대 공부시간
		int K = Integer.parseInt(st.nextToken()); // 과목 수

		int[] I = new int[K+1]; // 중요도
		int[] T = new int[K+1]; // 필요한 공부시간
		int[][] dp = new int[K+1][N+1];

		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			I[i] = Integer.parseInt(st.nextToken());
			T[i] = Integer.parseInt(st.nextToken());
		}

		for (int subject = 1; subject <= K; subject++) {
			for (int time = 1; time <= N; time++) {
				if (T[subject] <= time)
					dp[subject][time] = Math.max(dp[subject-1][time], dp[subject-1][time-T[subject]] + I[subject]);
				else {
					dp[subject][time] = dp[subject-1][time];
				}
			}
		}
		
		System.out.println(dp[K][N]);
	}
}
