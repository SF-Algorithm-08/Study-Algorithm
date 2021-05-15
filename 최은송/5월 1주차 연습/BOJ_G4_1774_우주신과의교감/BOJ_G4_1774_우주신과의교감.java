package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G4_1774_우주신과의교감 {
	static class Edge implements Comparable<Edge>{
		public int from;
		public int to;
		public double weight;
		
		public Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
		
	}
	static int N, M, root[], x[], y[];
	static PriorityQueue<Edge> pq;
	public static void main(String[] args) throws IOException{
		init();
		makeEdgeList();	
		System.out.printf("%.2f\n" , kruskal());
	}
	
	static int findSet(int a) {
		if(root[a] == a) return a;
		return root[a] = findSet(root[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot)	return false;
		root[bRoot] = aRoot;
		return true;
	}
	
	static double kruskal() {
		double ret = 0;
		int cnt = 0;
		while(!pq.isEmpty() && cnt != N-1) {
			Edge cur = pq.poll();
			if(union(cur.from, cur.to)) {
				ret += cur.weight;
				cnt++;
			}
		}
		return ret;
	}
	
	static void init() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		x = new int[N+1];
		y = new int[N+1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		// 연결된 통로 이어주기 
		root = new int[N+1];
		for(int i = 1; i <= N; i++) {
			root[i] = i;
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			union(r1, r2);
		}
	}
	
	static void makeEdgeList() {
		pq = new PriorityQueue<Edge>();
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == j)	continue;
				double dist = Math.sqrt(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2));
				pq.offer(new Edge(i, j, dist));
			}
		}
	}
}
