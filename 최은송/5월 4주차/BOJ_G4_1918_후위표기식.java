package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_G4_1918_후위표기식 {
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		char line[] = in.readLine().toCharArray();
		
		Stack<Character> stack = new Stack<Character>();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0, end = line.length; i < end; i++) {
			if(65 <= line[i] && line[i] <= 90) {
				sb.append(line[i]);
			}else {
				if(line[i] == '(') {
					stack.push(line[i]);
				}else if(line[i] == ')') {
					while(stack.peek() != '(' ) {
						sb.append(stack.pop());
					}
					stack.pop();
				}else if(line[i]  == '+' || line[i] == '-') {
					while(!stack.isEmpty() && stack.peek() != '(') {
						sb.append(stack.pop());
					}
					stack.push(line[i]);
				}else if(line[i] == '*' || line[i] == '/'){
					while(!stack.isEmpty() && stack.peek() != '(' &&( stack.peek() == '*' || stack.peek() == '/')) {
						sb.append(stack.pop());
					}
					stack.push(line[i]);
				}
			}
		}
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb.toString());
	}
}

/*
(A+(B*C))-(D/E)
 * 
 * **/
