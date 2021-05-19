package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_1756_피자굽기 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[] oven = new int[D];
		st = new StringTokenizer(br.readLine());
		oven[0] = Integer.parseInt(st.nextToken());
		for (int i = 1; i < D; i++) {
			oven[i] = Math.min(Integer.parseInt(st.nextToken()), oven[i-1]);
		}

		int[] pizza = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			pizza[i] = Integer.parseInt(st.nextToken());
		}

		int cnt = 0, ans = 0;
		for (int depth = D-1; depth >= 0; depth--) {
			if (pizza[cnt] <= oven[depth]) {
				cnt++;
				ans = depth;
			}
			if (cnt == N) break;
		}
		
		if (cnt == N) System.out.println(ans+1);
		else System.out.println(0);
	}
}
