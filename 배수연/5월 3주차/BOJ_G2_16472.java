package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 고냥이 - 문자열
 */
public class BOJ_G2_16472 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		int[] alpha = new int[26];
		int left = 0, cnt = 0, ans = 0;
		for(int right = 0 ; right<str.length() ; right++) {
			int temp = str.charAt(right)-'a';
			if(alpha[temp]>0) alpha[temp]++;
			else {
				if(cnt<N) {
					alpha[temp]++;
					cnt++;
				}
				else {
					while(cnt == N) {
						int first = str.charAt(left)-'a';
						alpha[first]--;
						left++;
						if(alpha[first] == 0) cnt--;
					}
					alpha[temp]++;
					cnt++;
				}
			}
			ans = Math.max(ans, right-left+1);
		}
		System.out.println(ans);
	}
}
