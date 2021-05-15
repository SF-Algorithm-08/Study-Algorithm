package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_1756_피자굽기 {
	static int D, N, oven[], pizza[];
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(in.readLine());
		D = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		oven = new int[D+1];
		st = new StringTokenizer(in.readLine());
		
		// 오븐을 전의 값으로 내림차순 
		oven[0] = Integer.MAX_VALUE;
		for(int i = 1; i <= D; i++) {
			oven[i] = Integer.parseInt(st.nextToken());
			if(oven[i] > oven[i-1])
				oven[i] = oven[i-1];
		}
		
		pizza = new int[N];
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) 
			pizza[i] = Integer.parseInt(st.nextToken());
		
		System.out.println(getDepth());
	}
	static int getDepth() {
		int depth = D;
		int pidx = 0;
		
		for(int d = D; d > 0; d--) {
			//만약 피자가 오븐보다 작거나 같으면 넣을 수 있음 => 다음 피자 탐색, depth 갱신 
			if(pizza[pidx] <= oven[d]){
				pidx++;
				depth = d;
			}
			// 오븐 탐색 중에 피자를 오븐에 전부 넣었으면 return depth
			if(pidx == N) {
				return depth;
			}
		}
		return 0;
	}
}
