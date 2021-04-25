package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_G2_1938_통나무옮기기 {

	private static class Pos {
		int r, c, dist, state;

		public Pos(int r, int c, int dist, int state) {
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.state = state;
		}
	}

	private static int N, ans;
	private static char[][] map;
	private static boolean[][][] visited;
	private static int dr[] = { -1, 1, 0, 0, -1, -1, 1, 1 }; // 상, 하, 좌, 우, 좌상, 우상, 좌하, 우하
	private static int dc[] = { 0, 0, -1, 1, -1, 1, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N][2]; // [행][열][방향] => 0 : 가로, 1 : 세로
		
		int R = 0, C = 0, state = 0, cnt = 0;
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'B') {
					if (cnt == 1) { // 통나무 중심점만 고려
						R = i;
						C = j;
						if (j > 0 && map[i][j-1] == 'B') state = 0; // 가로
						else state = 1; // 세로
						cnt++;
					} else {
						cnt++;
					}
				}
			}
		}
		
		bfs(R, C, state);
		System.out.println(ans);
	}

	
	private static void bfs(int R, int C, int State) {
		
		Queue<Pos> queue = new LinkedList<>();
		queue.offer(new Pos(R, C, 0, State));
		visited[R][C][State] = true;

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			if ((cur.state == 0 && cur.c >= 1 && cur.c < N-1 && map[cur.r][cur.c-1] == 'E' && map[cur.r][cur.c] == 'E' && map[cur.r][cur.c+1] == 'E')
					|| (cur.state == 1 && cur.r >= 1 && cur.r < N-1 && map[cur.r-1][cur.c] == 'E' && map[cur.r][cur.c] == 'E' && map[cur.r+1][cur.c] == 'E')) {
				ans = cur.dist;
				break;
			}

			boolean isRotate = true;
			int nstate = (cur.state + 1) % 2;
			if (!visited[cur.r][cur.c][nstate]) {
				for (int d = 0; d < 8; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == '1') {
						isRotate = false;
						break;
					}
				}
			}
			
			if (isRotate && !visited[cur.r][cur.c][nstate]) {
				visited[cur.r][cur.c][nstate] = true;
				queue.offer(new Pos(cur.r, cur.c, cur.dist + 1, nstate));
			}

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == '1' || visited[nr][nc][cur.state]) continue;

				if (cur.state == 0) {
					if (nc >= 1 && nc < N-1 && map[nr][nc-1] != '1' && map[nr][nc+1] != '1') {
						visited[nr][nc][cur.state] = true;
						queue.offer(new Pos(nr, nc, cur.dist + 1, cur.state));
					}
				} else {
					if (nr >= 1 && nr < N-1 && map[nr-1][nc] != '1' && map[nr+1][nc] != '1') {
						visited[nr][nc][cur.state] = true;
						queue.offer(new Pos(nr, nc, cur.dist + 1, cur.state));
					}
				}
			}
		}
	}

}
