package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G4_9466_텀프로젝트 {

	static int[] student;
	static boolean[] visited;
	static boolean[] finished;
	static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			student = new int[N + 1];
			visited = new boolean[N + 1];
			finished = new boolean[N + 1];
			cnt = 0;

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				student[j] = Integer.parseInt(st.nextToken());
			}

			for (int j = 1; j <= N; j++) {
				if (!visited[j]) dfs(j);
			}

			System.out.println(N - cnt);
		}
	}

	private static void dfs(int cur) {

		visited[cur] = true;
		int next = student[cur];

		if (visited[next]) {
			if (!finished[next]) {
				for (int temp = next; temp != cur; temp = student[temp]) {
					cnt++;
				}
				cnt++; // 자기 자신
			}
		} else {
			dfs(next);
		}
		finished[cur] = true;

	}
}

// visited == false, finished == false : 아직 방문하지 않은 정점
// visited == true, finished == false : 싸이클 생성
// visited == false, finished == true : 나올 수 없는 경우
// visited == true, finished == true : 이미 탐색이 종료된 경우
