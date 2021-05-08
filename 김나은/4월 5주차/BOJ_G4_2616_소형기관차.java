package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_2616_소형기관차 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int train = Integer.parseInt(br.readLine());
		int[] passenger = new int[train+1];
		int[] sum = new int[train+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= train; i++) {
			passenger[i] = Integer.parseInt(st.nextToken());
			sum[i] = sum[i-1] + passenger[i];
		}

		int coach = Integer.parseInt(br.readLine());
		int[][] dp = new int[4][train+1];
		for (int i = 1; i <= 3; i++) {
			for (int j = i*coach; j <= train; j++) {
				dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-coach] + (sum[j]-sum[j-coach]));
			}
		}
		
		System.out.println(dp[3][train]);
	}

}
