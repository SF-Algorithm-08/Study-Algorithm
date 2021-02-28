// 비트 우정지수

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S5_12782 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			String s1 = st.nextToken();
			String s2 = st.nextToken();

			if (s1.equals(s2)) System.out.println(0);
			else System.out.println(count(s1, s2));
		}
	}

	private static int count(String s1, String s2) {

		int bit1 = 0, bit0 = 0, ans = 0;
		for (int idx = 0, end = s1.length(); idx < end; idx++) {
			if (s1.charAt(idx) == s2.charAt(idx)) continue;
			else if (s1.charAt(idx) == '1') bit1++;
			else bit0++;
		}

		if (bit1 >= bit0) ans = bit1;
		else ans = bit0;

		return ans;
	}
}
