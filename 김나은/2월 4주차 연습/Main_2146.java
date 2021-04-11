// 다리 만들기

/**
 *  그림 참고 : https://devowen.com/317
 *  코드 참고 : https://dundung.tistory.com/33
 */


package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {
	int row, col;

	public Pos(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

public class Main_2146 {

	static int[][] map, group, distance;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		group = new int[N][N];
		distance = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int island = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && group[i][j] == 0) { // 섬인데 어떤 섬인지 아직 표시가 안 되었다면
					Queue<Pos> queue = new LinkedList<Pos>();
					group[i][j] = ++island; // 어떤 섬인지 표시
					queue.offer(new Pos(i, j));

					while (!queue.isEmpty()) {
						Pos cur = queue.poll();
						int r = cur.row;
						int c = cur.col;
						for (int d = 0; d < 4; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];
							if (0 <= nr && nr < N && 0 <= nc && nc < N) {
								if (map[nr][nc] == 1 && group[nr][nc] == 0) {
									queue.offer(new Pos(nr, nc));
									group[nr][nc] = island; // 연결된 곳이면 같은 섬으로 표시
								}
							}
						}
					}
				}
			}
		}

		for (int i = 0; i < N; i++) {
			Arrays.fill(distance[i], -1); // -1로 초기화
		}
		
		Queue<Pos> queue = new LinkedList<Pos>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					queue.offer(new Pos(i, j)); // 섬을 queue에 넣고
					distance[i][j] = 0; // 0으로 표시
				}
			}
		}

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();
			int r = cur.row;
			int c = cur.col;
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (0 <= nr && nr < N && 0 <= nc && nc < N) {
					if (distance[nr][nc] == -1) { // 섬이 아니면
						distance[nr][nc] = distance[r][c] + 1; // 거리 +1
						group[nr][nc] = group[r][c]; // 섬의 영역도 섬을 표시한 숫자로 확장
						queue.offer(new Pos(nr, nc)); // bfs
					}
				}
			}
		}

		int ans = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if (0 <= nr && nr < N && 0 <= nc && nc < N) {
						if (group[i][j] != group[nr][nc]) { // 인접한 섬의 영역이 다를 때
							if (ans == -1 || ans > distance[i][j] + distance[nr][nc]) {
								ans = distance[i][j] + distance[nr][nc];
							}
						}
					}
				}
			}
		}

		System.out.println(ans);
	}
}
