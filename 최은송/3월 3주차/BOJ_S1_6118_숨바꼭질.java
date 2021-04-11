package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S1_6118_숨바꼭질 {
	static int N;
	static int M;
	static	LinkedList<Integer>[] adj;
	static boolean visited[];
	static int idx = 20000;
	static int maxDist = 0;
	static int cnt = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//in = new BufferedReader(new StringReader(input));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N+1];
		adj = new LinkedList[N+1];
		for(int i=0; i<=N; i++)
			adj[i] = new LinkedList<Integer>();
		
		int from = 0, to = 0;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			adj[from].add(to);
			adj[to].add(from);
		}
		
		bfs();
		System.out.printf("%d %d %d\n", idx, maxDist, cnt);
	}
	
	static void bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {1, 0});
		visited[1] = true;
		
		while(!q.isEmpty()) {
			int cur = q.peek()[0];
			int dist = q.peek()[1];
			q.poll();
			
			int size = adj[cur].size();
			boolean visit = false;
			for(int i=0; i <size; i++) {
				int next = adj[cur].get(i);
				if(!visited[next]) {
					visited[next] = true;
					visit = true;
					q.offer(new int[] {next, dist+1});
				}
			}
			if(!visit) {
				if(dist > maxDist) {
					maxDist = dist;
					idx = cur; 
					cnt = 1;
				}else if(dist == maxDist) {
					idx = Math.min(idx, cur);
					cnt++;
				}
			}
		}
	}
	static String input = "6 7\n" + 
			"3 6\n" + 
			"4 3\n" + 
			"3 2\n" + 
			"1 3\n" + 
			"1 2\n" + 
			"2 4\n" + 
			"5 2";
}
