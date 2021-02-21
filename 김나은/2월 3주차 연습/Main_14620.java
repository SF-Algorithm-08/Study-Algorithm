// 꽃길

package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main_14620 {

	static int N;
	static int[][] map, points;
	static boolean[][] selected;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		selected = new boolean[N][N];
		points = new int[3][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		selectBlocks(0, 0);
		System.out.println(answer);
	}

	public static void selectBlocks(int cnt, int start) {
		if (cnt == 3) {
			answer = Math.min(answer, getCost(points));
			return;
		}

		// 조합으로 (행,열) 구하기
		for (int i = start; i < (N - 2) * (N - 2); i++) {
			int r = i / (N - 2) + 1;
			int c = i % (N - 2) + 1;

			if (selected[r][c] || selected[r - 1][c] || selected[r][c + 1] || selected[r + 1][c] || selected[r][c - 1])	continue;
			selected[r][c] = true;
			selected[r - 1][c] = true;
			selected[r + 1][c] = true;
			selected[r][c - 1] = true;
			selected[r][c + 1] = true;
			points[cnt][0] = r;
			points[cnt][1] = c;
			selectBlocks(cnt + 1, i + 1);

			selected[r][c] = false;
			selected[r - 1][c] = false;
			selected[r + 1][c] = false;
			selected[r][c - 1] = false;
			selected[r][c + 1] = false;
		}
	}

	public static int getCost(int[][] blocks) {
		int sum = 0;
		for (int i = 0; i < 3; i++) {
			int r = blocks[i][0];
			int c = blocks[i][1];
			sum += map[r][c] + map[r - 1][c] + map[r + 1][c] + map[r][c - 1] + map[r][c + 1];
		}

		return sum;
	}
}
