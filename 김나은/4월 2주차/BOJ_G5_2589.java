// 보물섬

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_2589 {

	static class Pos {
		int row, col;

		public Pos(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int R, C;
	static char[][] map;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int ans = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'L') ans = Math.max(ans, bfs(i, j));
			}
		}

		System.out.println(ans);
	}

	private static int bfs(int r, int c) {
		int cnt = 0;
		boolean[][] visited = new boolean[R][C]; // 초기화
		Queue<Pos> queue = new LinkedList<Pos>();
		queue.offer(new Pos(r, c));
		visited[r][c] = true;

		while (!queue.isEmpty()) {
			cnt++;

			for (int i = 0, size = queue.size(); i < size; i++) {
				Pos cur = queue.poll();
				for (int d = 0; d < 4; d++) {
					int nr = cur.row + dr[d];
					int nc = cur.col + dc[d];
					if (0 <= nr && nr < R && 0 <= nc && nc < C && !visited[nr][nc] && map[nr][nc] == 'L') {
						queue.offer(new Pos(nr, nc));
						visited[nr][nc] = true;
					}
				}
			}
		}
		return cnt - 1;
	}

}
