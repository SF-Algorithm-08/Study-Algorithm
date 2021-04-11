package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 보물섬
 */

public class BOJ_G5_2589 {
	static int H, W;
	static char[][] map;
	static int[][] visited;
	static int max;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		max = 0;
		map = new char[H][W];
		for(int h = 0 ; h<H ; h++) {
			String line = br.readLine();
			for(int w = 0 ; w<W ; w++) {
				map[h][w] = line.charAt(w);
			}
		}
		for(int h = 0 ; h<H ; h++) {
			for(int w = 0 ; w<W ; w++) {
				if(map[h][w] == 'L') {
					BFS(h, w);
				}
			}
		}
		System.out.println(max-1); // 출발 칸부터 1로 시작하니까 마지막에 1을 빼줌
	}

	private static void BFS(int r, int c) {
		visited = new int[H][W];
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {r, c});
		visited[r][c] = 1;
		int len = 0;
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			int temp_r = temp[0];
			int temp_c = temp[1];
			int nr = 0, nc = 0;
			for(int d = 0 ; d<4 ; d++) {
				nr = temp_r+dr[d];
				nc = temp_c+dc[d];
				if(nr>-1 && nr<H && nc>-1 && nc<W && map[nr][nc] == 'L') {
					if(visited[nr][nc] == 0) {
						queue.add(new int[] {nr, nc});
						visited[nr][nc] = visited[temp_r][temp_c]+1;
						len = Math.max(len, visited[nr][nc]);
					}
				}
			}
		}
		max = Math.max(max, len);
	}
}
