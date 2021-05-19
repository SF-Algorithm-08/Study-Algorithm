import java.util.*;
import java.io.*;

public class Main_16472 {

	private static int N;
	private static int[] alpha;
	private static HashSet<Character> set;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		alpha = new int[26];
		set = new HashSet<>();

		int start = 0;
		int end = 0;
		int len = 0;
		int answer = 0;
		while (end < str.length()) {
			if(set.size()==N) {
				if(set.contains(str.charAt(end))) {
					alpha[str.charAt(end)-'a']++;
					end++;
					len++;
				}
				else {
					alpha[str.charAt(start)-'a']--;
					if(alpha[str.charAt(start)-'a']==0) set.remove(str.charAt(start));
					start++;
					len--;
				}
			} else {
				alpha[str.charAt(end)-'a']++;
				set.add(str.charAt(end));
				end++;
				len++;
			}
			
			answer = Math.max(len, answer);

		}

		System.out.println(answer);

	}

}
