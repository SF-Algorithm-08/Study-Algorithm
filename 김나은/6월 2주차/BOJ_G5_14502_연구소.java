package algo;

import java.io.*;
import java.util.*;

public class BOJ_G5_14502_연구소 {

	static int N, M, ans;
	static int[][] map, copyMap;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0, 3);
		System.out.println(ans);
	}

	private static void dfs(int r, int c, int cnt) {
		if(cnt == 0) {
			virus();
			count();
			return;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1; // 벽 세우기
					dfs(i, j, cnt-1);
					map[i][j] = 0;
				}
			}
		}
	}

	private static void virus() {
		copyMap = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j]) continue;
				if (copyMap[i][j] == 2) bfs(i,j);
			}
		}
	}

	private static void bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<int[]>();
		
		visited[r][c] = true;
		queue.offer(new int[] {r, c});
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if(nr<0 || nr>=N || nc<0 || nc>=M || visited[nr][nc]) continue;
				if(copyMap[nr][nc] == 0) {
					copyMap[nr][nc] = 2;
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
				}
			}
		}
	}
	
	private static void count() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(copyMap[i][j] == 0) sum++;
			}
		}
		ans = Math.max(ans, sum);
	}
}
