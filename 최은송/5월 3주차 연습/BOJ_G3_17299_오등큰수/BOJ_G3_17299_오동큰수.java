package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_G3_17299_오동큰수 {

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int[] input = new int[N];		//원본 배열 저장
		int[] cnt = new int[1000001];	//등장 횟수 저장 
		
		for(int i = 0; i <N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
			cnt[input[i]]++;
		}
		
		Stack<int[]> stack = new Stack<int[]>();	//[0]: num, [1]: cnt
		int[] result = new int[N];
		for(int i = N-1; i>=0; i--) {
			while(!stack.isEmpty() && cnt[input[i]] >= stack.peek()[1]) {
				stack.pop();
			}
			if(!stack.isEmpty())	result[i] = stack.peek()[0];
			else					result[i] = -1;
			stack.push(new int[] {input[i], cnt[input[i]]});
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i <N; i++)
			sb.append(result[i]+" ");
		System.out.println(sb.toString());
	}

}
