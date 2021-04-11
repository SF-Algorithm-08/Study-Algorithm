package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_14888 {
	static int N, min, max;
	static int[] A, op;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		op = new int[4]; // [+, -, *, /]
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		st = new StringTokenizer(br.readLine());
		for(int n = 0 ; n<N ; n++) {
			A[n] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i<4 ; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		
		func(A[0], 1);
		System.out.println(max);
		System.out.println(min);
	}

	private static void func(int num, int idx) {
		if(idx == N) {
			max = Math.max(max, num);
			min = Math.min(min, num);
			return;
		}
		
		int temp = 0;
		for(int o = 0 ; o<4 ; o++) {
			if(op[o] != 0) {
				op[o]--;
				if(o == 0) {
					temp = num+A[idx];
				} else if(o == 1) {
					temp = num-A[idx];
				} else if(o == 2) {
					temp = num*A[idx];
				} else if(o == 3) {
					if(num<0 && A[idx]>0) { // A의 수는 무조건 양수
						num *= -1;
						temp = num/A[idx];
						temp *= -1;
					} else temp = num/A[idx];
				}
				func(temp, idx+1);
				op[o]++;
			}
		}
	}

}


