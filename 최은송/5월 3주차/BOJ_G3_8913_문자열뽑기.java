package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G3_8913_문자열뽑기 {

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		for(int i = 0; i < N; i++) {
			String s = in.readLine();
			System.out.printf("%d\n", dfs(s) ? 1 : 0);
		}
	}
	static boolean dfs(String s) {
		if(s.length() == 0) {
			return true;
		}
		int i = 0;
		int len = s.length();
		while(i < len - 1) {
			if(s.charAt(i) == s.charAt(i+1)) {
				int start = i;
				while(i < len-1 && s.charAt(i) == s.charAt(i+1)) {
					i++;
				}
				i++;
				int end = i;
				StringBuilder sb = new StringBuilder();
				sb.append(s.substring(0, start)).append(s.substring(end, len));
				
				if(dfs(sb.toString()))
					return true;
			}
			else	i++;
		}
		return false;
	}
}
