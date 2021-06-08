package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  봄버맨 - 시뮬레이션
 *  0초 : 초기 상태
 *  1초 : X
 *  2초 : 폭탄 없는 곳에 폭탄 설치
 *  3초 : 0초 폭탄 폭발
 *  4초 : 폭탄 없는 곳에 폭탄 설치
 *  5초 : 2초 폭탄 폭발
 *  6초 : 폭탄 없는 곳에 폭탄 설치
 *  7초 : 4초 폭탄 폭발
 *  ...
 *  => 2초부터 짝수 초에 폭탄 설치, 홀수 초에 3초 전 폭탄 폭발
 */

public class BOJ_S1_16918 {
	static int R, C, N;
	static char[][] map;
	static int[][] explode; // 폭발할 시간 저장
	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		explode = new int[R][C];
		for(int r = 0 ; r<R ; r++) {
			String line = br.readLine();
			for(int c = 0 ; c<C ; c++) {
				map[r][c] = line.charAt(c);
				if(map[r][c] == 'O') explode[r][c] = 3;
			}
		}
		// 1초 동안 아무것도 안함
		int time = 2;
		while(true) {
			if(time == N+1) break;
			// 짝수 초에 폭탄 없는 곳에 폭탄 설치
			if(time%2 == 0) {
				for(int r = 0 ; r<R ; r++) {
					for(int c = 0 ; c<C ; c++) {
						if(map[r][c] != 'O') {
							map[r][c] = 'O';
							explode[r][c] = time+3;
						}
					}
				}
			}
			// 홀수 초에 3초 전에 설치한 폭탄 폭발
			else {
				for(int r = 0 ; r<R ; r++) {
					for(int c = 0 ; c<C ; c++) {
						if(explode[r][c] == time) {
							for(int d = 0 ; d<5 ; d++) {
								int nr = r+dr[d];
								int nc = c+dc[d];
								if(nr>-1 && nr<R && nc>-1 && nc<C && map[nr][nc] == 'O') {
									map[nr][nc] = '.';
								}
							}
							explode[r][c] = 0;
						}
					}
				}
			}
			time++;
		}
		for(int r = 0 ; r<R ; r++) {
			System.out.println(map[r]);
		}
	}
}
