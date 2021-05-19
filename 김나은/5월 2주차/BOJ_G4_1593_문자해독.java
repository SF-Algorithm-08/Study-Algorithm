package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G4_1593_문자해독 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int g = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());

		String W = br.readLine();
		String S = br.readLine();

		int[] upper = new int[26];
		int[] lower = new int[26];
		for (int i = 0; i < g; i++) {
			char c = W.charAt(i);
			if ('a' <= c && c <= 'z') lower[c-'a']++;
			else upper[c-'A']++;
		}

		int[] upperCnt = new int[26];
		int[] lowerCnt = new int[26];
		int length = 0, ans = 0;
		for (int i = 0; i < s; i++) {
			char c = S.charAt(i);
			if ('a' <= c && c <= 'z') lowerCnt[c-'a']++;
			else upperCnt[c-'A']++;
			length++;
			if (length == g) {
				c = S.charAt(i - g + 1);
				if (Arrays.equals(lower, lowerCnt) && Arrays.equals(upper, upperCnt)) ans++;
				if ('a' <= c && c <= 'z') lowerCnt[c-'a']--;
				else upperCnt[c-'A']--;
				length--;
			}
		}

		System.out.println(ans);
	}
}
