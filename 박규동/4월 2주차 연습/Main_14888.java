import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_14888 {

	static int N;
	static int[] integer;
	static int[] operation;
	static int min_ans, max_ans;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		integer = new int[N];
		operation = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			integer[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operation[i] = Integer.parseInt(st.nextToken());
		}
		max_ans = -100000000;
		min_ans = 100000000;
		dfs(1, integer[0]);
		
		System.out.println(max_ans);
		System.out.println(min_ans);
	}

	private static void dfs(int cnt, int result) {
		if (cnt == N) {
			max_ans = Math.max(result, max_ans);
			min_ans = Math.min(result, min_ans);
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (operation[i] > 0) {
				operation[i]--;
				if (i == 0)
					dfs(cnt + 1, result + integer[cnt]);
				else if (i == 1)
					dfs(cnt + 1, result - integer[cnt]);
				else if (i == 2)
					dfs(cnt + 1, result * integer[cnt]);
				else
					dfs(cnt + 1, result / integer[cnt]);
				operation[i]++;
			}
		}

	}

}
