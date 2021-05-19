package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G2_16472_고냥이 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String line = br.readLine();

		int[] alphabet = new int[26];
		int prev = 0, cnt = 0, ans = 0;
		for (int i = 0; i < line.length(); i++) {
			int cur = line.charAt(i) - 'a';
			if (alphabet[cur] > 0) alphabet[cur]++;
			else {
				while (cnt == N) {
					int first = line.charAt(prev) - 'a';
					alphabet[first]--;
					prev++;
					if (alphabet[first] == 0) cnt--;
				}
				alphabet[cur]++;
				cnt++;
			}
			ans = Math.max(ans, i - prev + 1);
		}
		System.out.println(ans);
	}

}
