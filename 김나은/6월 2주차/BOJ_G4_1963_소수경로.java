package algo;

import java.io.*;
import java.util.*;

public class BOJ_G4_1963_소수경로 {

	static boolean[] isNotPrime = new boolean[10000];
	static int[] digit = { 1000, 100, 10, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());

		findPrime();

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int ans = 0;

			if (A == B) System.out.println(ans);
			else {
				ans = bfs(A, B);
				System.out.println(ans == -1 ? "Impossible" : ans);
			}
		}
	}

	private static int bfs(int A, int B) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[10000];

		queue.offer(A);
		visited[A] = true;

		int step = -1;
		while (!queue.isEmpty()) {
			step++;
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				int cur = queue.poll();
				if (cur == B) return step;

				int[] divide = new int[4];
				for (int j = 0; j < 4; j++) {
					divide[j] = cur / digit[j];
					cur %= digit[j];
				}

				for (int j = 0; j < 4; j++) {
					for (int k = 0; k < 10; k++) {
						divide[j] = divide[j] + 1 == 10 ? 0 : divide[j] + 1;

						int num = 0;
						for (int l = 0; l < 4; l++) {
							num += divide[l] * digit[l];
						}

						if (num < 1000) continue;
						if (visited[num]) continue;
						if (isNotPrime[num]) continue;

						queue.offer(num);
						visited[num] = true;
					}
				}
			}
		}

		return -1;
	}

	private static void findPrime() {
		for (int i = 2; i < 10000; i++) {
			if (isNotPrime[i]) continue;
			for (int j = 2; i * j < 10000; j++) {
				isNotPrime[i * j] = true;
			}
		}
	}

}
