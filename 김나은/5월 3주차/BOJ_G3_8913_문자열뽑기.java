package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G3_8913_문자열뽑기 {

	static boolean flag;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String line = br.readLine();

			flag = false;
			solve(line);
			System.out.println(flag == true ? 1 : 0);
		}
	}

	private static void solve(String line) {
		if (flag) return;
		if (line.isEmpty()) {
			flag = true;
			return;
		}

		for (int i = 0, j; i < line.length(); i++) {
			for (j = i + 1; j < line.length() && line.charAt(i) == line.charAt(j); j++)
				;
			if (j - i >= 2) solve(line.substring(0, i) + line.substring(j));
			i = j - 1;
		}
	}
}
