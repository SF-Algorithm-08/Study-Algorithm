package boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_1593_문자해독 {
	static int[][] alphabet = new int[26][2];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int len1 = Integer.parseInt(st.nextToken());
		int len2 = Integer.parseInt(st.nextToken());
		String str1 = br.readLine().trim();
		String str2 = br.readLine().trim();
		
		for(int i=0; i<len1; i++) {
			if(str1.charAt(i) > 'Z') alphabet[str1.charAt(i)-'a'][0]++;
			else alphabet[str1.charAt(i)-'A'][0]++;
			
			if(str2.charAt(i) > 'Z') alphabet[str2.charAt(i)-'a'][1]++;
			else alphabet[str2.charAt(i)-'A'][1]++;
		}
		
		int ans = 0;
		for(int i=len1, start=0; i<len2; i++) {
			if(same()) ans++;
			
			if(str2.charAt(start) > 'Z') alphabet[str2.charAt(start++)-'a'][1]--;
			else alphabet[str2.charAt(start++)-'A'][1]--;
			if(str2.charAt(i) > 'Z') alphabet[str2.charAt(i)-'a'][1]++;
			else alphabet[str2.charAt(i)-'A'][1]++;
		}
		
		System.out.println(ans);
	}
	
	private static boolean same() {
		for(int i=0; i<26; i++)
			if(alphabet[i][0]!=alphabet[i][1]) return false;
			
		return true;
	}
}

