package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 별자리 만들기
 * Kruskal
 */

public class BOJ_G4_4386 {
	static int N;
	static int[] parents;
	static double[][] stars;
	static ArrayList<Edge> edgeList;
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		double distance;
		public Edge(int from, int to, double distance) {
			super();
			this.from = from;
			this.to = to;
			this.distance = distance;
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.distance, o.distance);
		}
	}
	
	static void make() {
		for(int n = 0 ; n<N ; n++) {
			parents[n] = n;
		}
	}
	static int findSet(int a) {
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		parents = new int[N];
		stars = new double[N][2];
		edgeList = new ArrayList<Edge>();
		for(int n = 0 ; n<N ; n++) {
			st = new StringTokenizer(br.readLine());
			double star_x = Double.parseDouble(st.nextToken());
			double star_y = Double.parseDouble(st.nextToken());
			stars[n][0] = star_x;
			stars[n][1] = star_y;
		}
		
		for(int i = 0 ; i<N-1 ; i++) {
			double[] temp = stars[i];
			for(int j = i+1 ; j<N ; j++) {
				double[] next = stars[j];
				double dis_x = Math.pow(next[0]-temp[0], 2);
				double dis_y = Math.pow(next[1]-temp[1], 2);
				double dis = Math.sqrt(dis_x+dis_y);
				edgeList.add(new Edge(i, j, dis));
			}
		}
		
		Collections.sort(edgeList);
		make();
		double result = 0.0;
		int cnt = 0;
		for (Edge edge : edgeList) {
			if(union(edge.from, edge.to)) {
				result += edge.distance;
				if(++cnt == N-1) break;
			}
		}
		System.out.printf("%.2f", result);
	}
}
