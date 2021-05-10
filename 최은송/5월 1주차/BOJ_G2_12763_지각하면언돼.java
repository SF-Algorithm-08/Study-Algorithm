package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G2_12763_지각하면언돼 {
	static int N, map[][][], min, T, M;
	static boolean visited[];
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		T = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1][2];	//0: 시간, 1: 돈
		int L = Integer.parseInt(in.readLine());
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			int money = Integer.parseInt(st.nextToken());
			map[to][from][0] = map[from][to][0] = time;
			map[to][from][1] = map[from][to][1] = money;
		}
		
		min = Integer.MAX_VALUE;
		visited = new boolean[N+1];
		visited[1] = true;
		dfs(1, 0, 0);
		if(min == Integer.MAX_VALUE)	System.out.println(-1);
		else		System.out.println(min);
	}
	
	static void dfs(int n, int time, int money) {
		if(n == N) {
			min = Math.min(money, min);
			return;
		}
		for(int i = 1; i <= N; i++) {
			if(!visited[i] && map[n][i][0] > 0 && time + map[n][i][0] <= T && money + map[n][i][1] <= M) {
				visited[i] = true;
				dfs(i, time + map[n][i][0], money + map[n][i][1]);
				visited[i] = false;
			}
		}
	}
}
