import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17298 {

	static class Pair {
		int idx;
		int val;

		public Pair(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int[] num = new int[N];
		int[] answer = new int[N];
		Arrays.fill(answer, -1);
		Stack<Pair> stack = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			num[i] = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		stack.add(new Pair(0, num[0]));

		for (int i = 1; i < N; i++) {
			int cur = num[i];
			while (true) {
				if (stack.isEmpty() || cur <= stack.peek().val)
					break;
				answer[stack.pop().idx] = cur;
			}
			stack.add(new Pair(i, cur));
		}

		for (int i = 0; i < N; i++)
			sb.append(answer[i]).append(" ");
		System.out.println(sb);
	}

}
