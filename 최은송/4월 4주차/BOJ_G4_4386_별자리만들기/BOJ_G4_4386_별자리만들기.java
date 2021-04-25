package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G4_4386_별자리만들기 {
	static class Node implements Comparable<Node>{
		public int idx;
		public double cost;
		public Node(int idx, double cost) {
			this.idx = idx;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return Double.compare(this.cost, o.cost);
		}
		@Override
		public String toString() {
			return "Node [idx=" + idx + ", cost=" + cost + "]";
		}
		
	}
	static double[][] adj;
	static boolean[] visited;
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		double[][] stars = new double[N][2];
		StringTokenizer st = null;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			stars[i][0] = Double.parseDouble(st.nextToken());
			stars[i][1] = Double.parseDouble(st.nextToken());
		}
		
		adj = new double[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				adj[i][j] = adj[j][i] = Math.sqrt(Math.pow(stars[i][0] - stars[j][0], 2) + Math.pow(stars[i][1] - stars[j][1], 2));
			}
		}
		System.out.printf("%.2f\n", prim());
	}
	static double prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		double minEdge[] = new double[N];
		boolean visited[] = new boolean[N];
		
		Arrays.fill(minEdge, 10000000);
		
		minEdge[0] = 0;
		pq.offer(new Node(0, 0));
		
		double result = 0;
		int cnt = 0;
		while(true) {
			Node cur = pq.poll();
			if(visited[cur.idx]) continue; 
			
			visited[cur.idx] = true;
			result += cur.cost;
			if(++cnt == N)	break;
			
			for(int i=0; i<N; i++) {
				if(!visited[i] && minEdge[i] > adj[cur.idx][i]) {
					minEdge[i] = adj[cur.idx][i];
					pq.offer(new Node(i, minEdge[i]));
				}
			}
		}
		return result;
	}
}
