package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G1_1162_도로포장 {
	static class Edge implements Comparable<Edge>{
		public long weight;
		public int to, k;
		public Edge(int to, long weight) {
			this.to = to;
			this.weight = weight;
		}
		public Edge(int to, long weight, int k) {
			this.to = to;
			this.weight = weight;
			this.k = k;
		}
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
	}
	
	static int N, M, K;
	static ArrayList<Edge> adj[]; 
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N+1];
		for(int i = 1; i <= N; i++)
			adj[i] = new ArrayList<Edge>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adj[from].add(new Edge(to, weight));
			adj[to].add(new Edge(from, weight));
		}
		System.out.println(dijkstra(1));
	}
	
	static long dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		
		long dist[][] = new long[N+1][K+1];
		for(int i = 1; i <= N; i++)
			Arrays.fill(dist[i], Long.MAX_VALUE);
		
		pq.offer(new Edge(start, 0, 0));
		dist[start][0] = 0;
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			
			if(edge.weight > dist[edge.to][edge.k])	continue;
			
			for(Edge next : adj[edge.to]) {
				// 도로 포장 없이 최소값 갱신  
				if(dist[next.to][edge.k] > next.weight + edge.weight) {
					dist[next.to][edge.k] = next.weight + edge.weight;
					pq.offer(new Edge(next.to, dist[next.to][edge.k], edge.k));
				}
				// 도로 포장이 가능할 경우 다음 도로 0으로 하고 최소값 갱신 
				if(edge.k < K && edge.weight < dist[next.to][edge.k + 1]) {
					dist[next.to][edge.k+1] = edge.weight;
					pq.offer(new Edge(next.to, dist[next.to][edge.k+1], edge.k+1));
				}
			}
		}
		long ans = Long.MAX_VALUE;
		for(int i = 0; i <= K; i++) 
			ans = Math.min(ans, dist[N][i]);
		return ans;
	}
}
