package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 후위 표기식 - 문자열, 스택
 */

public class BOJ_G4_1918 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		String ans = "";
		Stack<Character> stack = new Stack<Character>();
		for(int i = 0 ; i<line.length() ; i++) {
			char temp = line.charAt(i);
			if('A'<=temp && temp<='Z') ans += temp;
			else if(temp == '(') stack.push(temp);
			else if(temp == '*' || temp == '/') {
				while(!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
					ans += stack.pop();
				}
				stack.push(temp);
			}
			else if(temp == '+' || temp == '-' || temp == ')') {
				while(!stack.isEmpty() && stack.peek() != '(') {
					ans += stack.pop();
				}
				if(temp == '+' || temp == '-') stack.push(temp);
				else stack.pop();
			}
		}
		while(!stack.isEmpty()) ans += stack.pop();
		System.out.println(ans);
	}
}
