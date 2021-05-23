package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G2_16472_고냥이 {

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		String s = in.readLine();
		
		int alpha[] = new int[26];
		alpha[s.charAt(0)-'a'] = 1;
		
		int l = 0, r = 0, cnt = 1, max = 1;
		int end = s.length();
		while(r < end) {
			r++;
			if(r == end)	break;
			// 새로운 종류의 알파벳이 나오면  cnt++
			if(alpha[s.charAt(r) - 'a']++ == 0) {
				cnt++;
			}
			// cnt가 N보다 작거나 같으면 길이 갱
			if(cnt <= N) {
				max = Math.max(r - l + 1, max);
			}else {			// 아니면 l을 오른쪽으로 땡겨오기 
				while(l < r && cnt > N) {
					if(alpha[s.charAt(l) - 'a']-- == 1)
						cnt--;
					l++;
				}
			}
		}
		System.out.println(max);
	}

}
