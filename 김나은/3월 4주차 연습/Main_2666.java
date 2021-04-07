// 벽장문의 이동

package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2666 {
	
	static int closet, length;
	static int[] order;
	static int[][][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		closet = Integer.parseInt(br.readLine()); // 벽장의 개수
		st = new StringTokenizer(br.readLine());
		int c1 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());
		length = Integer.parseInt(br.readLine()); // 사용할 벽장들의 순서의 길이
		order = new int[length+1]; // 사용할 벽장의 번호
		for (int i = 1; i <= length; i++) {
			order[i] = Integer.parseInt(br.readLine());
		}

		dp = new int[length+1][21][21];
		
		for (int i = 1; i <= length; i++) {
			for (int j = 1; j <= 20; j++) {
				Arrays.fill(dp[i][j], Integer.MAX_VALUE);
			}
		}
		
		System.out.println(memo(1,c1,c2));
		
	}

	private static int memo(int i, int c1, int c2) {
		
		if(dp[i][c1][c2] != Integer.MAX_VALUE) return dp[i][c1][c2];
		
		int abs1 = Math.abs(order[i]-c1);
		int abs2 = Math.abs(order[i]-c2);
		
		if(i == length) {
			dp[i][c1][c2] = Math.min(abs1, abs2);
		}else {
			dp[i][c1][c2] = Math.min(memo(i+1,order[i],c2)+abs1, memo(i+1,order[i],c1)+abs2);
		}
		
		return dp[i][c1][c2];
	}

}



/*
 * 그리디하게 풀면 최적의 답을 보장할 수 없음!
		int ans = 0;
		for (int i = 0; i < length; i++) {
			boolean flag = false;
			ans += Math.min(Math.abs(order[i] - c1), Math.abs(order[i] - c2));
			if (Math.abs(order[i] - c1) < Math.abs(order[i] - c2))
				flag = true;
			if (flag) c1 = order[i];
			else c2 = order[i];
		}
		
		System.out.println(ans);
 */