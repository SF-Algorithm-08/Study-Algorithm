package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_2616_소형기관차 {
	static int N, train[], K, max = 0;
	static int dp[][];

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());

		train = new int[N+1];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i=1; i<=N; i++) {
			int temp = Integer.parseInt(st.nextToken());
			train[i] = train[i-1] + temp;
		}
		K = Integer.parseInt(in.readLine());	// 소형기관차 객실 수
		dp = new int[3][N+1];

		System.out.println(dfs(0, 1));
	}
	static int dfs(int trainIdx, int curIdx) {
		if(trainIdx == 3 || curIdx > N) {
			return 0;
		}
		if(dp[trainIdx][curIdx] != 0)
			return dp[trainIdx][curIdx];

		// max(현재 객실 선택 안함, 현재 객실부터 최대 객실 수까지 선택)
		if(curIdx + K - 1 <= N)
			dp[trainIdx][curIdx] = Math.max(dfs(trainIdx, curIdx+1), train[curIdx + K - 1] - train[curIdx - 1] + dfs(trainIdx+1, curIdx + K));

		return dp[trainIdx][curIdx];
	}
}
