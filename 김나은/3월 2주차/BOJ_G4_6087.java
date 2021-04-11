// 레이저 통신

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_6087 {

	static int col, row;
	static char[][] map;
	static int[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		col = Integer.parseInt(st.nextToken()); // W
		row = Integer.parseInt(st.nextToken()); // H
		map = new char[row][col];
		visited = new int[row][col];
		Pos start = null, end = null;

		boolean flag = false;
		for (int i = 0; i < row; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < col; j++) {
				if (map[i][j] == 'C') {
					if (!flag) {
						start = new Pos(i, j);
						flag = true;
					} else
						end = new Pos(i, j);
				}
			}
		}

		bfs(start, end);
		System.out.println(visited[end.r][end.c]-1);

	}

	private static void bfs(Pos start, Pos end) {

		Queue<Pos> queue = new LinkedList<Pos>();
		queue.offer(start);

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();
			if (cur.r == end.r && cur.c == end.c) return;

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				while (nr >= 0 && nr < row && nc >= 0 && nc < col && map[nr][nc] != '*') {

					if (visited[nr][nc] == 0) {
						visited[nr][nc] = visited[cur.r][cur.c] + 1;
						queue.offer(new Pos(nr, nc));
					}
					nr += dr[d];
					nc += dc[d];

				}
			}
		}
		
		return;

	}
}
