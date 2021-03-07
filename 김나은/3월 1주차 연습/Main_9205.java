// 맥주 마시면서 걸어가기

package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_9205 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int store = Integer.parseInt(br.readLine());
			int[][] dist = new int[store + 2][store + 2];
			boolean[][] visit = new boolean[store + 2][store + 2];
			List<int[]> pos = new ArrayList<>();

			for (int i = 0; i < store + 2; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				pos.add(new int[] { x, y });
			}

			for (int i = 0; i < store + 2; i++) {
				for (int j = 0; j < store + 2; j++) {
					dist[i][j] = Math.abs(pos.get(i)[0] - pos.get(j)[0]) + Math.abs(pos.get(i)[1] - pos.get(j)[1]);

					if (dist[i][j] <= 20 * 50) visit[i][j] = true;
				}
			}

			for (int i = 0; i < store + 2; i++) { // i : 거쳐가는 곳
				for (int j = 0; j < store + 2; j++) { // j : 시작
					for (int k = 0; k < store + 2; k++) { // k : 도착
						if (visit[j][i] & visit[i][k]) visit[j][k] = true; // j → i로 도착할 수 있고, i → k로 도착할 수 있으면  => j → k도 도착할 수 있다!
					}
				}
			}

			System.out.println(visit[0][store + 1] ? "happy" : "sad");
		}
	}
}
