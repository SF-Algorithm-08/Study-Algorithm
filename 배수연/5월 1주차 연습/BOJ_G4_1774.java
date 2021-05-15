package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 우주신과의 교감 - Kruskal
 * 
 */
public class BOJ_G4_1774 {
	static int N, M;
	static int parents[];
	static Pos[] pos;
	static ArrayList<Edge> edgelist;
	
	static class Pos {
		int x, y;
		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int from, to;
		double weight;

		public Edge(int from, int to, double weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}
	
	static void make() {
		for(int n = 0 ; n<=N ; n++) {
			parents[n] = n;
		}
	}
	static int findSet(int a) {
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot != bRoot) parents[bRoot] = aRoot;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parents = new int[N+1];
		pos = new Pos[N+1];
		edgelist = new ArrayList<Edge>();
		make();
		for(int n = 1 ; n<=N ; n++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			pos[n] = new Pos(x, y);
		}
		for(int m = 0 ; m<M ; m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			union(from, to);
		}
		
		for(int i = 1 ; i<N ; i++) {
			for(int j = i+1 ; j<=N ; j++) {
				double weight = Math.sqrt(Math.pow(pos[i].x-pos[j].x, 2)
										+ Math.pow(pos[i].y-pos[j].y, 2));
				edgelist.add(new Edge(i, j, weight));
			}
		}
		Collections.sort(edgelist);
		
		double ans = 0.0;
		for(int i = 0 ; i<edgelist.size() ; i++) {
			Edge temp = edgelist.get(i);
			if(findSet(temp.from) != findSet(temp.to)) {
				ans += temp.weight;
				union(temp.from, temp.to);
			}
		}
		System.out.printf("%.2f", ans);
	}
}
