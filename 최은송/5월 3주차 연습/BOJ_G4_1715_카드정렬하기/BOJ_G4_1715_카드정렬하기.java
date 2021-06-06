package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_G4_1715_카드정렬하기 {
	static int N;
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		for(int i = 0; i < N; i++)
			pq.offer(Integer.parseInt(in.readLine()));
		
		if(N == 1)	System.out.println(0);
		else		System.out.println(solve());
	}
	
	static int solve() {
		int ans = 0;
		while(!pq.isEmpty()) {
			int sum = pq.poll() + pq.poll();
			ans += sum;
			if(!pq.isEmpty())
				pq.offer(sum);
		}
		return ans;
	}
}
