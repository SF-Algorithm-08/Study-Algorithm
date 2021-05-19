package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 오큰수 - 스택
 * 스택에 있는 이전에 봤던 값이 자기보다 작으면 이전 값의 인덱스에 현재 값을 넣어줌
 */
public class BOJ_G4_17298 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		Arrays.fill(arr, -1);
		Stack<int[]> stack = new Stack<int[]>(); // 인덱스, 값
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int n = 0 ; n<N ; n++) {
			int num = Integer.parseInt(st.nextToken());
			while(stack.size()>0) {
				if(stack.peek()[1]<num) arr[stack.pop()[0]] = num;
				else break;
			}
			stack.push(new int[] {n, num});
		}
		
		StringBuilder sb = new StringBuilder();
		for(int n = 0 ; n<N ; n++) {
			sb.append(arr[n]+" ");
		}
		System.out.println(sb.toString());
	}

}
