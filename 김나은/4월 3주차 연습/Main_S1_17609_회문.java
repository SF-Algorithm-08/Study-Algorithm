package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_S1_17609_회문 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			System.out.println(check(br.readLine()));
		}
	}

	private static int check(String line) {

		int length = line.length();
		boolean flag = false;
		int cnt = 0;

		for (int start = 0, end = length - 1; start <= end; start++, end--) {
			if (line.charAt(start) != line.charAt(end)) {
				cnt++;
				if (!flag) {
					flag = true;
					start--;
				}
			}
		}

		if (cnt == 0) return 0;
		else if (cnt == 1) return 1;

		flag = false;
		cnt = 0;
		for (int start = 0, end = length - 1; start <= end; start++, end--) {
			if (line.charAt(start) != line.charAt(end)) {
				cnt++;
				if (!flag) {
					flag = true;
					end++;
				}
			}
		}

		if (cnt == 1) return 1;
		else return 2;

	}

}
