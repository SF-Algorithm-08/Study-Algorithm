package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_G2_10423_전기가부족해 {
	static class Edge implements Comparable<Edge>{
		int from, to, weight;
		
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
	}
	
	static void makeSet() {
		for(int i=1; i<N+1; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(parents[a] == a) {
			return a;
		}
		return parents[a] = findSet(parents[a]);
	}
	
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		// 두 부모가 모두 발전소일 경우 
		if(aRoot == bRoot || (power.contains(aRoot) && power.contains(bRoot))) {
			return false;
		}
		// a의 부모만 발전소일 경우 
		if(power.contains(aRoot)) {
			parents[bRoot] = aRoot;
			for(int i=1; i<N+1; i++) {
				if(parents[i] == bRoot)
					parents[i] = aRoot;
			}
			return true;
		}
		// b의 부모만 발전소일 경우  
		else if(power.contains(bRoot)) {
			parents[aRoot] = bRoot;
			for(int i=1; i<N+1; i++) {
				if(parents[i] == aRoot)
					parents[i] = bRoot;
			}
			return true;
		}
		
		//a, b의 부모가 둘 다 발전소가 아닐 경우 
		parents[bRoot] = aRoot;
		for(int i=1; i<N+1; i++) {
			if(parents[i] == bRoot)
				parents[i] = aRoot;
		}
		return true;
		
	}
	
	// 모든 노드의 부모가 발전소일 경우에 return true 
	static boolean check() {
		for(int i=1; i<N+1; i++) {
			if(!power.contains(parents[i])) {
				return false;
			}
		}
		return true;
	}
	
	static int N;
	static int M;
	static int K;
	static Edge[] edgeList;
	static Set<Integer> power = new HashSet<Integer>();
	static int[] parents;
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		in = new BufferedReader(new StringReader(input));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		edgeList = new Edge[M];
		
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<K; i++) {
			power.add(Integer.parseInt(st.nextToken()));
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(edgeList);
		
		makeSet();
		int dist = 0;
		for(Edge edge : edgeList) {
			
			if(union(edge.from, edge.to)) {
				dist += edge.weight;
				if(check()) {
					System.out.println(dist);
					break;
				}
			}
		}
	}
	
	static String input = "6 7 1\n" + 
			"1\n" + 
			"1 2 9\n" + 
			"2 3 1\n" + 
			"3 4 1\n" + 
			"4 5 1\n" + 
			"5 2 1\n" + 
			"6 2 3\n" + 
			"6 5 2";
}
