package boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_14502_연구소 {

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, M, max = 0;
	static ArrayList<Pos> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0)
					list.add(new Pos(i, j));
			}
		}

		for (int i = 0, size = list.size(); i < size - 2; i++) {
			map[list.get(i).r][list.get(i).c] = 1;

			for (int j = i + 1; j < size - 1; j++) {
				map[list.get(j).r][list.get(j).c] = 1;

				for (int k = j + 1; k < size; k++) {
					map[list.get(k).r][list.get(k).c] = 1;

					int[][] copy = new int[N][M];
					for (int r = 0; r < N; r++) {
						for (int c = 0; c < M; c++)
							copy[r][c] = map[r][c];
					}
					check(copy);
					map[list.get(k).r][list.get(k).c] = 0;
				}
				map[list.get(j).r][list.get(j).c] = 0;
			}
			map[list.get(i).r][list.get(i).c] = 0;
		}

		System.out.println(max);
	}

	static boolean[][] checked;

	private static void check(int[][] map) {
//		바이러스 퍼뜨리기
		checked = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!checked[i][j] && map[i][j] == 2)
					bfs(map, i, j); // 해당 구역 바이러스 퍼뜨리기
			}
		}

//		안전 영역 세기
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					cnt++;
			}
		}

//		안전영역 개수 갱신
		max = cnt > max ? cnt : max;
	}

	static int[][] dir = { { -1, 1, 0, 0 }, { 0, 0, -1, 1 } };

	private static void bfs(int[][] map, int i, int j) {
		Queue<Pos> queue = new LinkedList<>();
		queue.add(new Pos(i, j));
		checked[i][j] = true;

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dir[0][d];
				int nc = cur.c + dir[1][d];
				if (nr == -1 || nr == N || nc == -1 || nc == M || map[nr][nc] != 0)
					continue;
				map[nr][nc] = 2;
				checked[nr][nc] = true;
				queue.add(new Pos(nr, nc));
			}
		}
	}
}
