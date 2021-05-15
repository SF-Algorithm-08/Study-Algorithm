package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G4_1593_문자해독 {

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int wlen = Integer.parseInt(st.nextToken());
		int slen = Integer.parseInt(st.nextToken());
		
		String W = in.readLine();
		String S = in.readLine();
		int w_used[] = new int[58];	//A: 65 ~ z: 122
		int s_used[] = new int[58];
		
		// 미리 W단어에 사용된 문자수 카운팅 
		for(int i = 0; i < wlen; i++) {
			int alpha = W.charAt(i) - 65;
			w_used[alpha]++;
		}
		
		int ans = 0;
		int len = 0;			//윈도우에서 W와 알파벳과 동일한 개수 
		for(int i = 0; i < slen; i++) {
			s_used[S.charAt(i) - 65]++;	//윈도우 뒤에 추가 
			len++;
			if(len == wlen) {	// S의 부분수열이 W의 길이와 같아질 때 비교 
				if(Arrays.equals(w_used, s_used)) {	//S의 부분수열에서 사용된 알파벳이 W에서 사용된 알파벳과 동일해질때  
					ans++;
				}
				s_used[S.charAt(i-wlen+1)-65]--;	//윈도우 앞을 제거 
				len--;
			}
		}
		System.out.println(ans);
	}

}
