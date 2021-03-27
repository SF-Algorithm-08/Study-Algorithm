package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G3_20666_인물이와정수 {
	static class Edge{
		int to, level;
		public Edge(int to, int level) {
			this.to = to;
			this.level = level;
		}
	}
	static class Node implements Comparable<Node>{
		public int idx, level;
		public Node(int idx, int level) {
			this.idx = idx;
			this.level = level;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.level, o.level);
		}
	}
	static int N;
	static int M;
	static int p;
	static int[] monster;
	static boolean[] visited;
	static LinkedList<Edge> hint[];

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//in = new BufferedReader(new StringReader(input));
		
		StringTokenizer st = new StringTokenizer(in.readLine().trim(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		monster = new int[N+1];
		visited = new boolean[N+1];
		
		st = new StringTokenizer(in.readLine().trim(), " ");
		for(int i=1; i<=N; i++) {
			monster[i] = Integer.parseInt(st.nextToken());
		}
		
		// 힌트를 LinkedList로 받아오기 
		p = Integer.parseInt(in.readLine().trim());
		if(p > 0) {
			hint = new LinkedList[N+1];
			for(int i=1; i<=N; i++) {
				hint[i] = new LinkedList<Edge>();
			}
			for(int i=0; i<p; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int level = Integer.parseInt(st.nextToken());
				hint[from].add(new Edge(to, level));
				monster[to] += level;
			}
		}
		
		int result = dijkstra();
		System.out.println(result);
	}
	
	static int dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		int minLevel = Integer.MAX_VALUE, minIdx = 0;
		for(int i=1; i<=N; i++) {
			if(monster[i] < minLevel) {
				minIdx = i;
				minLevel = monster[i];
			}
		}
		pq.add(new Node(minIdx, minLevel));
		int max = 0;
		for(int i=0; i<M-1; i++) {
			// 난이도가 가장 작은 애를 골라줌 
			Node node = pq.poll();
			visited[node.idx] = true;
//			System.out.printf("first idx: %d, level: %d\n",node.idx, node.level);
			
			// monster를 돌면서 hint[node.idx]에 있는 놈들은 값을 바꿔줌
			int min = Integer.MAX_VALUE;
			for(int j=1; j<=N; j++) {
				if(visited[j])	continue;
				
				// 만약 hint에 i->j가 존재하면 monster[j]의 레벨을 감소시켜줘야 함  
				if(p>0 && hint[node.idx] != null) {
					for(Edge edge : hint[node.idx]) {
						if(edge.to == j) {
							monster[j] -= edge.level;
							break;
						}
					}
				}
				// 최소값 갱신 
				if(monster[j] < min) {
					minIdx = j;
					min = monster[j];
				}
			}
			pq.add(new Node(minIdx, min));
			// 뽑은 레벨 중 가장 큰 값 갱신 
			max = Math.max(node.level, min);
		}
		return max;
	}
	static String input = "5 3\n" + 
			"2 1 5 6 3\n" + 
			"3\n" + 
			"3 4 3\n" + 
			"4 3 2\n" + 
			"4 2 3";
}
