package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_G4_17298_오큰수 {
	static int N, arr[], result[]; 
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		arr = new int[N];
		result = new int[N];
	
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		solve();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++)
			sb.append(result[i] +" ");
		System.out.println(sb.toString());
	}
	
	static void solve() {
		result[N-1] = -1;
		Stack<Integer> stack = new Stack<>();
		stack.push(arr[N-1]);
		
		for(int i = N-2; i>=0; i--) {
			// stack의 값이 현재 값보다 클 때 
			if(arr[i] < stack.peek()) {
				result[i] = stack.peek();
			}else{
				while(!stack.isEmpty() && arr[i] >= stack.peek()) {
					stack.pop();
				}
				if(stack.isEmpty()) 	result[i] = -1;				// 큰 수가 없을 때 
				else 					result[i] = stack.peek();	// 큰 수가 있을 때 
			}
			stack.push(arr[i]);
		}
	}
}
