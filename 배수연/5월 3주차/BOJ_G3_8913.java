package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 문자열 뽑기 - 재귀, 문자열
 * 문자 앞에서부터 for 반복
 * i에서 가리키는 문자와 연속으로 같은 문자인 마지막  j 인덱스를 구함
 * 그룹 문자열 길이가 2 이상이라면 그 그룹 빼고 앞뒤로 붙여서 새 문자열로 다시 재귀 돌리기
 * 문자열의 길이가 0이 되면 리턴
 */

public class BOJ_G3_8913 {
	static boolean isEmpty;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 0 ; t<T ; t++) {
			String str = br.readLine();
			isEmpty = false;
			func(str);
			if(isEmpty) System.out.println(1);
			else System.out.println(0);
		}
	}

	private static void func(String str) {
		if(isEmpty) return;
		if(str.length() == 0) {
			isEmpty = true;
			return;
		}
		for(int i = 0, j ; i<str.length() ; i++) {
			j = i+1;
			while(j<str.length() && str.charAt(i) == str.charAt(j)) {
				j++;
			}
			if(j-i >= 2) func(str.substring(0, i)+str.substring(j));
			i = j-1;
		}
	}
}
