package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G1_1162 {
	static int N, M, K;
	static long[][] cost;
	static ArrayList<int[]>[] map;
	
	static class Node implements Comparable<Node> {
		long cost;
		int road;
		int node;
		public Node(long cost, int road, int node) {
			super();
			this.cost = cost;
			this.road = road;
			this.node = node;
		}
		@Override
		public int compareTo(Node o) {
			return Long.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		if(M<=K) {
			System.out.println(0);
			return;
		}
		cost = new long[N+1][K+1]; // N번 정점까지 도달하는 데에 포장한 도로 K
		map = new ArrayList[N+1];
		for(int i = 1 ; i<=N ; i++) {
			map[i] = new ArrayList<int[]>();
			Arrays.fill(cost[i], Long.MAX_VALUE);
		}
		for(int k = 0 ; k<=K ; k++) {
			cost[1][k] = 0;
		}
		for(int m = 0 ; m<M ; m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			map[from].add(new int[] {to, time});
			map[to].add(new int[] {from, time});
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(0, 0, 1)); // 비용, 포장한 도로, 노드
		while(!pq.isEmpty()) {
			Node temp = pq.poll();
			long tcost = temp.cost;
			int troad = temp.road;
			int tnode = temp.node;
			if(cost[tnode][troad]<tcost) continue;
			for(int i = 0 ; i<map[tnode].size() ; i++) {
				int next = map[tnode].get(i)[0];
				long ncost = tcost+map[tnode].get(i)[1];
				// 도로 포장 안하고 그냥 지나감
				if(ncost<cost[next][troad]) {
					cost[next][troad] = ncost;
					pq.offer(new Node(ncost, troad, next));
				}
				// 도로 포장
				if(troad<K && tcost<cost[next][troad+1]) {
					cost[next][troad+1] = tcost;
					pq.offer(new Node(tcost, troad+1, next));
				}
			}
		}
		System.out.println(cost[N][K]);
	}
}


