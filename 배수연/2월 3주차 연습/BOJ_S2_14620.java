package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_14620 {
	static int N, min;
	static int[][] map;
	static boolean[][] checked;
//	static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1, -2, 2, 0, 0}; // 상하좌우 좌상우상우하좌하 상2하2좌2우2
//	static int[] dc = {0, 0, -1, 1, -1, 1, 1, -1, 0, 0, -2, 2};
	static int[] dr = {-1, 1, 0, 0}; // 상하좌우
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		checked = new boolean[N][N];
		min = Integer.MAX_VALUE;
		for(int i = 0 ; i<N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j<N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		comb(0);
		System.out.println(min);
	}
	private static void comb(int idx) {
		if(idx == 3) {
			min = Math.min(min, sum());
			return;
		}
		for(int i = 1 ; i<N-1 ; i++) {
			for(int j = 1 ; j<N-1 ; j++) {
				int flag = 1;
				for(int d = 0 ; d<4 ; d++) {
					int nr = i+dr[d];
					int nc = j+dc[d];
					if(checked[nr][nc]) {
						flag = 0;
						break;
					}
				}
				if(flag == 0) continue;
				makeState(i, j, true);
				comb(idx+1);
				makeState(i, j, false);
			}
		}
	}
	private static int sum() {
		int sum = 0;
		int cnt = 0;
		for(int i = 0 ; i<N ; i++) {
			for(int j = 0 ; j<N ; j++) {
				if(checked[i][j]) {
					sum += map[i][j];
					cnt++;
					if(cnt == 15) return sum;
				}
			}
		}
		return -1;
	}
	private static void makeState(int i, int j, boolean tf) {
		checked[i][j] = tf;
		for(int d = 0 ; d<4 ; d++) {
			int nr = i+dr[d];
			int nc = j+dc[d];
			checked[nr][nc] = tf;
		}
	}
}
/*
6
1 0 2 3 3 4
1 1 1 1 1 1
0 0 1 1 1 1
3 9 9 0 1 99
9 11 3 1 0 3
12 3 0 0 0 1
*/
