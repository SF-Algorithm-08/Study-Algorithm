package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S1_17609_회문 {
	static int ans;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for(int i=0; i<T; i++) {
			String word = in.readLine();
//			System.out.println(find1(word, 0, word.length()-1));
//			System.out.println(palindrome1(word, 0, word.length()-1));
			System.out.println(palindrome2(word, 0, word.length()-1, false));
		}
		
	}
	
	static int palindrome2(String word, int i, int j, boolean flag) {
		while(i < j) {
			if(word.charAt(i) == word.charAt(j)) {
				i++;
				j--;
				continue;
			}
			if(flag || palindrome2(word, i+1, j, true) == 0 || palindrome2(word, i, j-1, true) == 0)
				return 1;
			return 2;
		}
		return 0;
	}
	
	// 시간 초과 코드 
	static int palindrome1(String word, int i, int j) {
		if(i > j) return 0;
		if(word.charAt(i) == word.charAt(j)) {
			return palindrome1(word, i+1, j-1);
		}else if(palindrome1(word, i+1, j) == 0 || palindrome1(word, i, j-1) == 0) {
			return 1;
		}else {
			return 2;
		}
	}
	
	static boolean find2(String word, int i, int j) {
		while(i <= j) {
			if(word.charAt(i) == word.charAt(j)) {
				i++;
				j--;
			}else {
				return false;
			}
		}
		return true;
	}
	
	static int find1(String word, int i, int j) {
		while(i <= j) {
			if(word.charAt(i) == word.charAt(j)) {
				i++;
				j--;
			}else {
				boolean left = find2(word, i+1, j);
				boolean right = find2(word, i, j-1);
				if(left || right) {
					return 1;
				}else {
					return 2;
				}
			}
		}
		return 0;
	}
}

/*
반례: baaba 

public static void main(String[] args) throws IOException{
BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
int T = Integer.parseInt(in.readLine());
String[] words = new String[T];
for(int i=0; i<T; i++) {
	words[i] = in.readLine();
}
loop:
for(int t=0; t<T; t++) {
	String word = words[t];
	int i = 0;
	int j = word.length()-1;
	boolean used = false;
	while(i <= j) {
		if(word.charAt(i) == word.charAt(j)) {
		}else if(!used && word.charAt(i+1) == word.charAt(j) && i > word.length()-j) {
			i++;
			used = true;
		}else if(!used && word.charAt(i) == word.charAt(j-1) && i < word.length()-j) {
			j--;
			used = true;
		}else {
			System.out.println("2");
			continue loop;
		}
		i++;
		j--;
	}
	System.out.printf("%d\n", used ? 1 : 0);
}
}
*/