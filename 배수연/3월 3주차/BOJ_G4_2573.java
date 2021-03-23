package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 빙산
 * 빙산 덩어리 개수 구하면서 인접 바다 수 구하기
 * 빙산 높이에서 바다 수 빼면서 높이 변화시키기
 */

public class BOJ_G4_2573 {
	static int N, M, land, ans;
	static int[][] map;
	static int[][] melt;
	static int[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		melt = new int[N][M];
		visited = new int[N][M];
		for(int n = 0 ; n<N ; n++) {
			st = new StringTokenizer(br.readLine());
			for(int m = 0 ; m<M ; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = 0;
		while(true) {
			// 빙산 개수, 인접 바다 수 구하기
			land = 0;
			for(int i = 0 ; i<N ; i++) {
				Arrays.fill(visited[i], 0);
				Arrays.fill(melt[i], 0);
			}
			for(int i = 0 ; i<N ; i++) {
				for(int j = 0 ; j<M ; j++) {
					if(visited[i][j] == 0 && map[i][j] != 0) {
						countLand(i, j);
						land++;
					}
				}
			}
			
			// 빙산 개수 판별
			if(land == 0) {
				System.out.println(0);
				break;
			}
			else if(land >= 2) {
				System.out.println(ans);
				break;
			}
			
			// 빙산 녹이기
			for(int i = 0 ; i<N ; i++) {
				for(int j = 0 ; j<M ; j++) {
					map[i][j] -= melt[i][j];
					if(map[i][j] < 0) map[i][j] = 0;
//					visited[i][j] = 0;
//					melt[i][j] = 0;
				}
			}
			ans++;
		}
	}
	public static void countLand(int r, int c) {
		visited[r][c] = 1;
		int nr = 0, nc = 0;
		for(int d = 0 ; d<4 ; d++) {
			nr = r+dr[d];
			nc = c+dc[d];
			if(nr>-1 && nr<N && nc>-1 && nc<M && map[nr][nc] == 0) {
				melt[r][c]++;
			}
			if(visited[nr][nc] == 0 && map[nr][nc] != 0) {
				countLand(nr, nc);
			}
		}
	}

}
