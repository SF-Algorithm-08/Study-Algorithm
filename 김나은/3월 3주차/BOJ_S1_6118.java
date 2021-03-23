// 숨바꼭질

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S1_6118 {

	static int N, M;
	static ArrayList<Integer>[] adjList;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		M = Integer.parseInt(st.nextToken()); // 간선의 개수
		adjList = new ArrayList[N + 1]; // 정점 1부터 시작

		for (int i = 0; i <= N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			adjList[to].add(from);
		}

		bfs(1); // 정점 1부터 시작
	}

	private static void bfs(int cur) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N + 1];
		int[] distance = new int[N + 1];

		queue.offer(cur);
		visited[cur] = true;

		while (!queue.isEmpty()) {
			cur = queue.poll();
			for (int temp : adjList[cur]) {
				if (!visited[temp]) {
					queue.offer(temp);
					visited[temp] = true;
					distance[temp] = distance[cur] + 1;
				}
			}
		}

		int idx = 0, dist = 0, cnt = 0;
		for (int i = 0; i <= N; i++) {
			if (distance[i] > dist) {
				dist = distance[i];
				idx = i;
				cnt = 1;
			} else if (distance[i] == dist)
				cnt++;
		}

		System.out.println(idx + " " + dist + " " + cnt);
	}
}
