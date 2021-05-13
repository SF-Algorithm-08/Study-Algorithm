package boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_1756_피자굽기 {
	static int[] depth;
	static int start, end;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int D = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine()," ");
		depth = new int[D+1];
		depth[1] = Integer.parseInt(st.nextToken());
		for(int i=2; i<=D; i++) depth[i] = Math.min(depth[i-1], Integer.parseInt(st.nextToken()));
		
		start=1; end=D+1;
		st = new StringTokenizer(br.readLine()," ");
		for(int i=0; i<N; i++) {
			find(Integer.parseInt(st.nextToken()));
			if(--end<1) break;
			start=1;
//			System.out.println(end);
		}
		
		System.out.println(end);
	}

	private static void find(int pizza) {	//이분탐색
		while(start<end) {
			int mid = (start+end)/2;
			if(depth[mid]<pizza) end = mid;
			else start=mid+1;
		}
	}

}
