import java.util.*;
import java.io.*;

public class Main_1593 {

	static int g,s,answer;
	static StringTokenizer st;
	static int[] small,capital,tsmall,tcapital;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		small = new int[26];
		capital = new int[26];
		tsmall = new int[26];
		tcapital = new int[26];
		g=Integer.parseInt(st.nextToken());
		s=Integer.parseInt(st.nextToken());
		String W=br.readLine();
		for(int i=0;i<g;i++) {
			char cur = W.charAt(i);
			if(cur>='a'&&cur<='z') small[cur-'a']++;
			else capital[cur-'A']++;
		}
		
		int start = 0;
		int end = g-1;
		String S = br.readLine();
		for(int i=0;i<g;i++) {
			char cur = S.charAt(i);
			if(cur>='a'&&cur<='z') tsmall[cur-'a']++;
			else tcapital[cur-'A']++;
		}
		
		while(true) {
			
			if(solution()) answer++;
			if(end==s-1) break;
			char schar = S.charAt(start);
			if(schar>='a'&&schar<='z') tsmall[schar-'a']--;
			else tcapital[schar-'A']--;
			start++;
			end++;
			char echar = S.charAt(end);
			if(echar>='a'&&schar<='z') tsmall[echar-'a']++;
			else tcapital[echar-'A']++;
			
		}
		
		System.out.println(answer);
		
	}

	private static boolean solution() {
		for(int i=0;i<26;i++) {
			if(tsmall[i]==small[i]&&tcapital[i]==capital[i]) continue;
			else return false;
		}
		return true;
		
	}

}
