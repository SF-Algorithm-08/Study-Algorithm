// 전기가 부족해 => kruskal

package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_10423 {
	
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
//			return this.weight-o.weight;
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static int V, E, K;
	static int parents[];
	static Edge[] edgeList;
	
	static void make() { // 크기가 1인 단위 집합을 만든다.
		for (int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}

	static int findSet(int a) {
		if (parents[a] == -1) return -1;
		if (parents[a] == a) return a;
//		return findSet(parents[a]); // path compression 전
		return parents[a] = findSet(parents[a]); // path compression 후
	}

	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) return false;
		
		if (aRoot == -1) parents[bRoot] = aRoot;
		else if (bRoot == -1) parents[aRoot] = bRoot;
		else {
			if (aRoot == -1 && bRoot == -1) ; // 둘 다 발전소인 경우 아무것도 하지 않음
			else parents[bRoot] = aRoot;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		parents = new int[V+1];
		make();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			parents[Integer.parseInt(st.nextToken())] = -1; // 발전소끼리 연결되지 않도록 발전소의 루트 노드를 -1로 지정
		}
		
		edgeList = new Edge[E];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(edgeList); // 간선리스트를 가중치 기준으로 오름차순 정렬
		
		int result = 0;
		int count = 0; // 선택한 간선 수
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) { // 싸이클이 발생하지 않았다면
				result += edge.weight;
				if(++count == V-1) break;
			}
		}
		
		System.out.println(result);
	}

}
