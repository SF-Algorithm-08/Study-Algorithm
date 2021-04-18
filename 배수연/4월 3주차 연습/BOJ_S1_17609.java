package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 회문 - 문자열
 */

public class BOJ_S1_17609 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test = 1 ; test<=T ; test++) {
			String str = br.readLine();
			if(isPalindrome(str)) sb.append("0\n");
			else if(isPseudoPalindrome(str)) sb.append("1\n");
			else sb.append("2\n");
		}
		System.out.print(sb.toString());
	}

	// 유사회문 검사 : 투포인터로 검사하다가 불일치한 자리가 있으면, 왼쪽에서 하나 제거하거나 오른쪽에서 하나 제거하고 그 중간값이 팰린드롬인지 검사
	private static boolean isPseudoPalindrome(String str) {
		int left = 0, right = str.length()-1;
		while(left<=right) {
			if(str.charAt(left) == str.charAt(right)) {
				left++;
				right--;
			}
//			bcbcc
			// cbcc, bcbc
			else {
				if(isPalindrome(str.substring(left+1, right+1)) || isPalindrome(str.substring(left, right))) {
					return true;
				}
				else return false;
			}
		}
		return true;
	}

	// 회문 검사
	private static boolean isPalindrome(String str) {
		int left = 0, right = str.length()-1;
		while(left<=right) {
			if(str.charAt(left) == str.charAt(right)) {
				left++;
				right--;
			}
			else return false;
		}
		return true;
	}

}
