package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_2665_벽장문의이동 {
	static int N;
	static int T;
	static int[][][] dp;
	static int[] order;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int open1 = Integer.parseInt(st.nextToken());
		int open2 = Integer.parseInt(st.nextToken());
		
		T = Integer.parseInt(in.readLine());
		dp = new int[T][N+1][N+1];
		order = new int[T];
		for(int i=0; i<T; i++)
			order[i] = Integer.parseInt(in.readLine());
		
		System.out.println(dfs(0, open1, open2));
	}
	static int dfs(int cnt, int open1, int open2) {
		if(cnt >= T) {
			return 0;
		}
		
		int door = order[cnt];

		int first = Math.abs(door - open1) + dfs(cnt+1, door, open2);
		int second =Math.abs(open2 - door) + dfs(cnt+1, open1, door);

		dp[cnt][open1][open2] = Math.min(first, second);
		return dp[cnt][open1][open2];
	}
}
