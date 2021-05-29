package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_G4_9935 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String bomb = br.readLine();
		int bomb_len = bomb.length();
		
		Stack<Character> stack = new Stack<>();
		for(int i = 0, size=str.length() ; i<size ; i++) {
			stack.push(str.charAt(i));
			if(stack.size()>=bomb_len) {
				boolean flag = true;
				for(int j = 0 ; j<bomb_len ; j++) {
					if(stack.get(stack.size()-bomb_len+j) != bomb.charAt(j)) {
						flag = false;
						break;
					}
				}
				if(flag) {
					for(int j = 0 ; j<bomb_len ; j++) {
						stack.pop();
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (Character c : stack) {
			sb.append(c);
		}
		String ans = (sb.toString().length()>0)? sb.toString() : "FRULA";
		System.out.println(ans);
	}
}
