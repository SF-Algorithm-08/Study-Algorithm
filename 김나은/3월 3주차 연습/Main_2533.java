// 사회망 서비스(SNS) => dp 또는 dfs

package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_2533 {

	static int N;
	static ArrayList<Integer>[] adjList;
	static int[][] dp;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[N+1];
		dp = new int[N+1][2];

		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adjList[u].add(v);
			adjList[v].add(u);
		}

		dp(1, 0); // 1을 루트로 가정
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}

	private static void dp(int cur, int parent) {
		dp[cur][0] = 0; // 현재 노드가 얼리 아답터가 아닌 경우 => 인접 노드들은 모두 얼리 아답터여야 함
		dp[cur][1] = 1; // 현재 노드가 얼리 아답터인 경우  => 인접 노드는 얼리 아답터일 수도 있고, 아닐 수도 있음

		for (int temp : adjList[cur]) {
			if (temp != parent) {
				dp(temp, cur);
				dp[cur][0] += dp[temp][1];
				dp[cur][1] += Math.min(dp[temp][0], dp[temp][1]);
			}
		}
	}

}
