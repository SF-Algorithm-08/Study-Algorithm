package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 중량제한 - UnionFind
 * cost의 max를 알아야 하기 때문에 pq를 이용해서 노드 별로 cost를 저장했고,
 * 노드들을 union해가다가 두 공장이 연결되면 그 때의 cost를 바로 리턴
 */

public class BOJ_G4_1939 {
	static int N;
	static int[] parents;
	
	static class Edge implements Comparable<Edge> {
		int from, to, cost;
		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return o.cost-this.cost;
		}
	}
	static int find(int a) {
		if(a == parents[a]) return a;
		return find(parents[a]);
	}
	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if(rootA != rootB) parents[rootB] = rootA;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		parents = new int[N+1];
		for(int n = 0 ; n<=N ; n++) {
			parents[n] = n;
		}
		for(int m = 0 ; m<M ; m++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			pq.add(new Edge(A, B, C));
		}
		st = new StringTokenizer(br.readLine());
		int factory1 = Integer.parseInt(st.nextToken());
		int factory2 = Integer.parseInt(st.nextToken());
		
		int ans = 0;
		while(!pq.isEmpty()) {
			Edge temp = pq.poll();
			int temp_from = temp.from;
			int temp_to = temp.to;
			int temp_cost = temp.cost;
			union(temp_from, temp_to);
			if(find(factory1) == find(factory2)) {
				ans = temp.cost;
				break;
			}
		}
		System.out.println(ans);
	}
}
