package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_S1_2716 {
	/**
	 * 원숭이 매달기
	 * 가장 안쪽 겹의 괄호가 몇 겹으로 싸여있는지 구한다 (inside)
	 * 정답 : 2^inside
	 */

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test = 0 ; test<T ; test++) {
			String str = br.readLine();
			Stack<Character> stack = new Stack<Character>();
			int inside = 0;
			for(int i = 0 ; i<str.length() ; i++) {
				if(str.charAt(i) == '[') {
					stack.push('[');
					inside = Math.max(inside, stack.size());
				}
				else  stack.pop();
			}
			int ans = (int) Math.pow(2, inside);
			System.out.println(ans);
		}
	}

}
