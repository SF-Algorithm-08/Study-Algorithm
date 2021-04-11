package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_14888_연산자끼워넣기 {
	static int[] num;
	static int[] op;
	static int[] order;
	static int N;
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		num = new int[N];
		op = new int[N-1];
		order = new int[N-1];
		int[] opcnt = new int[4];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i=0; i<N; i++)
			num[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine());
		for(int i=0, idx = 0; i<4; i++) {
			opcnt[i] = Integer.parseInt(st.nextToken());
			for(int j=0; j<opcnt[i]; j++)
				op[idx++] = i;
		}
		permu(0, 0);
		System.out.println(max);
		System.out.println(min);
	}
	
	static void permu(int cnt, int flag) {
		if(cnt == N-1) {
			// min, max 갱신
			int temp = num[0];
			for(int i = 1; i < N; i++) {
				switch(order[i-1]) {
				case 0: temp += num[i];	break;
				case 1: temp -= num[i]; break;
				case 2: temp *= num[i]; break;
				case 3: temp /= num[i]; break;
				}
			}
			min = Math.min(min, temp);
			max = Math.max(max, temp);
			return;
		}
		for(int i=0; i<N-1; i++) {
			if((flag & 1<<i) != 0)	continue;
			order[cnt] = op[i];
			permu(cnt+1, flag | 1<<i);
		}
	}
	
}
