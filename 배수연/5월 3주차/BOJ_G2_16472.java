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
//		alpha[str.charAt(0)-'a']++;
//		
//		while(right<str.length()) {
//			right++;
//			if(right == str.length()) break;
//			
//			// 종류가 N개 넘으면 left 개수 빼주고 다른 문자 나올 때까지 left 한 칸 뒤로
//			while(cnt>N) {
//				if(alpha[str.charAt(left)-'a']-- == 0) cnt--;
//				left++;
//			}
//			// right 문자열 추가해주기
//			if(alpha[str.charAt(right)-'a']++ == 1) cnt++;
//			// 매번 사용한 문자열의 길이가 N 이하면 ans 갱신
//			if(cnt<=N) ans = Math.max(ans, right-left+1);
//		}
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
