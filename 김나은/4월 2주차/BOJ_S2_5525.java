// IOIOI

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S2_5525 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		char[] s = br.readLine().toCharArray();
		int[] indexI = new int[M];

		int idx = 0;
		for (int i = 0; i < M; i++) {
			if (s[i] == 'I') indexI[idx++] = i;
		}

		int cnt = 0, ans = 0;
		for (int i = 1; i < idx; i++) {
			if (indexI[i] - indexI[i-1] == 2) cnt++;
			else cnt = 0; // 초기화
			if (cnt >= N) ans++;
		}

		System.out.println(ans);
	}
}

//		시간초과
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int N = Integer.parseInt(br.readLine());
//		
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < N + 2; i++) {
//			if (i % 2 == 0) sb.append('I');
//			else sb.append('O');
//		}
//
//		int M = Integer.parseInt(br.readLine());
//		String s = br.readLine();
//
//		int cnt = 0;
//		String str = sb.toString();
//		for (int i = 0; i <= M - str.length(); i++) {
//			if (str.equals(s.substring(i, i+N+2))) cnt++;
//		}
//		System.out.println(cnt);