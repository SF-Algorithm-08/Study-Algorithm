// 양치기 꿍

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S2_3187 {

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
	static int R, C, wolf, lamb;
	static char[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == '#' || visited[i][j]) continue;
				bfs(i,j);
			}
		}

		System.out.print(lamb + " " + wolf);
	}

	private static void bfs(int r, int c) {
		Queue<Pos> queue = new LinkedList<Pos>();
		queue.offer(new Pos(r, c));
		visited[r][c] = true;

		int wolfCnt = 0, lambCnt = 0;
		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			if (map[cur.row][cur.col] == 'v') wolfCnt++;
			else if (map[cur.row][cur.col] == 'k') lambCnt++;
			for (int d = 0; d < 4; d++) {
				int nr = cur.row + dr[d];
				int nc = cur.col + dc[d];
				if (0 <= nr && nr < R && 0 <= nc && nc < C && !visited[nr][nc] && map[nr][nc] != '#') {
					queue.offer(new Pos(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}
		if (lambCnt > wolfCnt) lamb += lambCnt;
		else wolf += wolfCnt;

	}
}
