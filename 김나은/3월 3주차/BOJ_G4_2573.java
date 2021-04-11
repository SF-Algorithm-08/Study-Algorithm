// 빙산

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_2573 {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] iceberg = new int[N][M];
		int ans = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				iceberg[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			ans++;

			// 빙산이 녹는 양
			int[][] melt = new int[N][M];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (iceberg[r][c] > 0) {
						int m = 0;
						for (int d = 0; d < 4; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];
							if (0 <= nr && nr < N && 0 <= nc && nc < M && iceberg[nr][nc] == 0)
								m++;
						}
						melt[r][c] = m;
					}
				}
			}

			Queue<int[]> queue = new LinkedList<int[]>();
			boolean[][] visit = new boolean[N][M];
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					iceberg[i][j] -= melt[i][j]; // 빙산 녹음
					if (iceberg[i][j] < 0)
						iceberg[i][j] = 0; // 높이는 0보다 더 줄어들지 않는다
					else if (iceberg[i][j] > 0) {
						cnt++;
						if (queue.isEmpty()) {
							queue.offer(new int[] { i, j });
							visit[i][j] = true;
						}
					}
				}
			}

			if (cnt == 0) {
				System.out.println(0);
				return;
			}

			int count = 1;
			while (!queue.isEmpty()) {
				int[] cur = queue.poll();
				for (int d = 0; d < 4; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					if (0 <= nr && nr < N && 0 <= nc && nc < M && iceberg[nr][nc] > 0 && !visit[nr][nc]) {
						visit[nr][nc] = true;
						queue.offer(new int[] { nr, nc });
						count++;
					}
				}
			}

			if (count != cnt) {
				System.out.println(ans);
				return;
			}
		}

	}
}
