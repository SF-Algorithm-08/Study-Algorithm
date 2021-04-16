package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S2_2644_촌수계산 {

	static int N, p1, p2, ans;
	static ArrayList<Integer>[] list;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		p1 = Integer.parseInt(st.nextToken());
		p2 = Integer.parseInt(st.nextToken());

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			list[parent].add(child);
			list[child].add(parent);
		}

		bfs();

		System.out.println(ans);
	}

	private static void bfs() {

		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N+1];
		queue.offer(p1);
		visited[p1] = true;

		while (!queue.isEmpty()) {
			for (int i = 0, size = queue.size(); i < size; i++) {
				int cur = queue.poll();
				if (cur == p2) return;
				for (int temp : list[cur]) {
					if (!visited[temp]) {
						queue.offer(temp);
						visited[temp] = true;
					}
				}
			}
			ans++;
		}
		ans = -1;
	}

}
