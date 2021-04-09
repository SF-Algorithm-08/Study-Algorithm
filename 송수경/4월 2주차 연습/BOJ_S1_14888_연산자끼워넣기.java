package boj.Silver;
//S1 연산자 끼워넣기 (백트래킹,브루트포스 알고리즘)
//solved

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S1_14888_연산자끼워넣기 {
	static int N;
	static int[] num, op;
	static int max=Integer.MIN_VALUE, min=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		num = new int[N];
		op = new int[4];
		
//		1. 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i=0; i<N; i++) num[i]=Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine()," ");
		for(int i=0; i<4; i++) op[i]=Integer.parseInt(st.nextToken());
		
//		2. dfs
		dfs(1, num[0], new int[4]);	//몇 번째 계산인지, 현재 값, 연산자의 사용 횟수 담고 출발
		
//		3. 결과
		System.out.printf("%d\n%d", max, min);
	}

	static void dfs(int idx, int result, int[] used) {
		if(idx==N) {
			max = Math.max(max, result);
			min = Math.min(min, result);
//			System.out.println(result+" "+max+" "+min);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(used[i]<op[i]) {
//				System.out.println("i: "+i);
				used[i]++;
				dfs(idx+1, calc(result, num[idx], i), used);
				used[i]--;
			}
		}
	}
	
	static int calc(int a, int b, int o) {
		switch(o) {
		case 0:
			return a+b;
		case 1:
			return a-b;
		case 2:
			return a*b;
		case 3:
			return a/b;
		}
		return 0;
	}
}
