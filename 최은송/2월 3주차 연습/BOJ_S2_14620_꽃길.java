package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_14620_꽃길2 {
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {0, 1,-1,0,0};
	static int[] dc = {0, 0,0,-1,1};
	static int min = Integer.MAX_VALUE;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		dfs(0, 0);
		System.out.println(min);
	}
	
	private static void dfs(int cnt, int cost) {
		if(cost >= min)
			return;
		if(cnt == 3) {
			min = Math.min(min, cost);
			return;
		}
		for(int r=1; r<N-1; r++) {
			for(int c=1; c<N-1; c++) {
				if(!check(r,c))	continue;
					
				int sum = setVisit(r,c,true);
				dfs(cnt+1, cost+sum);
				setVisit(r,c,false);
			}
		}
	}
	
	private static int setVisit(int r, int c, boolean flag) {
		int nr = 0, nc = 0;
		int sum = 0;
		for(int i=0; i<5; i++) {
			nr = r+dr[i];
			nc = c+dc[i];
			sum += map[nr][nc];
			visited[nr][nc] = flag;
		}
		return sum;
	}
	
	private static boolean check(int r, int c) {
		int nr,nc;
		for(int i=0; i<5; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			if( visited[nr][nc])
				return false;
		}
		return true;
	}
}