// 부동산 다툼

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_20364 {

	static boolean[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 땅 개수
		int Q = Integer.parseInt(st.nextToken()); // 꽉꽉나라에 사는 오리 수
		visited = new boolean[N + 1]; // 0은 dummy

		for (int duck = 1; duck <= Q; duck++) {
			System.out.println(check(Integer.parseInt(br.readLine())));
		}
	}

	private static int check(int land) {
		int copy = land, idx = 0;
		int[] occupied = new int[20]; // 2 ≤ N ≤ 2^20

		while (land > 0) {
			if (visited[land]) occupied[idx++] = land;
			land /= 2;
		}

		if (idx == 0) {
			visited[copy] = true;
			return 0;
		} else {
			return occupied[idx - 1];
		}
	}

}
