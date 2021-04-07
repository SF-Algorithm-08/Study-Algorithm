import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2666 {

	static int N, M, answer;
	static int[] door;
	static int[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int open1 = Integer.parseInt(st.nextToken());
		int open2 = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());
		door = new int[M];
		for (int i = 0; i < M; i++) {
			door[i] = Integer.parseInt(br.readLine());
		}
		answer = Integer.MAX_VALUE;
		dfs(0, open1, open2, 0);

		dp = new int[M][N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N + 1; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}

		answer = dynamic(0, open1, open2);
		for(int i=0;i<M;i++) {
			for(int j=0;j<N+1;j++) {
				for(int k=0;k<N+1;k++) {
					System.out.println(dp[i][j][k]);
				}
			}
		}
		System.out.println(answer);
	}

	private static int dynamic(int cnt, int open1, int open2) {

		if (cnt == M)
			return 0;
//		if (dp[cnt][open1][open2] != -1)
//			return dp[cnt][open1][open2];

		dp[cnt][open1][open2] = Math.min(
				Math.abs(door[cnt] - open1) + dynamic(cnt + 1, door[cnt], open2),
				Math.abs(door[cnt] - open2) + dynamic(cnt + 1, open1, door[cnt]));
		
		return dp[cnt][open1][open2];
	}

	private static void dfs(int cnt, int open1, int open2, int move) {
		if (answer <= move)
			return;

		if (cnt == M) {
			answer = move;
			return;
		}

		int move1 = Math.abs(door[cnt] - open1);
		int move2 = Math.abs(door[cnt] - open2);

		dfs(cnt + 1, door[cnt], open2, move + move1);
		dfs(cnt + 1, open1, door[cnt], move + move2);
	}

}
