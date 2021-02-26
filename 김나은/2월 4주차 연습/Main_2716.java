// 원숭이 매달기

package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2716 {

	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			ans = 0;
			String str = br.readLine();
			solve(str);
			if (ans == 0) System.out.println(1);
			else System.out.println((int)Math.pow(2, ans));
		}
	}

	private static void solve(String str) {
		int depth = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == '[') depth++;
			else {
				ans = Math.max(ans, depth);
				depth--;
			}
		}
	}

}
