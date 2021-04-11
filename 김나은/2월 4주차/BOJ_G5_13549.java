// 숨바꼭질 3

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_13549 {

	static int N, K, MAX = 100001;
	static int[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 수빈
		K = Integer.parseInt(st.nextToken()); // 동생
		visited = new int[MAX];

		for (int i = 0; i < MAX; i++) {
			visited[i] = Integer.MAX_VALUE;
		}

		bfs();

	}

	private static void bfs() {

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(N);
		visited[N] = 0;

		while (!queue.isEmpty()) {
			int p = queue.poll();
			if (p == K) {
				System.out.println(visited[p]);
				return;
			}

			if (p + 1 < MAX && visited[p] + 1 < visited[p + 1]) {
				visited[p + 1] = visited[p] + 1;
				queue.offer(p + 1);
			}

			if (p - 1 >= 0 && visited[p] + 1 < visited[p - 1]) {
				visited[p - 1] = visited[p] + 1;
				queue.offer(p - 1);
			}

			if (p * 2 < MAX && visited[p] < visited[p * 2]) {
				visited[p * 2] = visited[p];
				queue.offer(p * 2);
			}

		}

	}
}
