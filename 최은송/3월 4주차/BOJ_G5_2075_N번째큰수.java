package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G5_2075_N번째큰수 {
	static int N;
	static PriorityQueue<Node> pq = new PriorityQueue<Node>();
	
	static class Node implements Comparable<Node>{
		int num;
		public Node(int num) {
			this.num = num;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(o.num, this.num);
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		StringTokenizer st = null;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<N; j++) {
				pq.offer(new Node(Integer.parseInt(st.nextToken())));
			}
		}
		int cnt = 0;
		Node node = null;
		while(cnt < N) {
			node = pq.poll();
			cnt++;
		}
		System.out.println(node.num);
	}
} 
