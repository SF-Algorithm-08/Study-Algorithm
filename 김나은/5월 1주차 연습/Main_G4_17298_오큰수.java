package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_G4_17298_오큰수 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());
		int[] ans = new int[N];

		Stack<int[]> stack = new Stack<>();
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			int num = Integer.parseInt(st.nextToken());

			while (!stack.isEmpty() && stack.peek()[1] < num) {
				ans[stack.pop()[0]] = num;
			}

			stack.push(new int[] { idx, num });
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			if (ans[i] == 0) ans[i] = -1;
			sb.append(ans[i] + " ");
		}
		System.out.println(sb);
	}
}