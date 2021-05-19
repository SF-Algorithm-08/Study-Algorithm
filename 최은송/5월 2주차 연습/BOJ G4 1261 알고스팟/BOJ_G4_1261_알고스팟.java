package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G4_1261_알고스팟 {
	static int R, C;
	static char map[][];
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	
	static class Node implements Comparable<Node>{
		public int r, c, cnt;
		public Node(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Node o) {
			return this.cnt - o.cnt;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for(int r = 0; r < R; r++) 
			map[r] = in.readLine().toCharArray();
		
		System.out.println(bfs());
	}
	static int bfs() {
		boolean visited[][] = new boolean[R][C];
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		q.offer(new Node(0, 0, 0));		//r, c, cnt
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			if(cur.r == R-1 && cur.c == C-1) 
				return cur.cnt;
			
			for(int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr < 0 || nr >= R || nc < 0 ||nc >= C || visited[nr][nc]) continue;
				
				if(map[nr][nc] == '1') 		q.offer(new Node(nr, nc, cur.cnt+1));
				else						q.offer(new Node(nr, nc, cur.cnt));
				
				visited[nr][nc] = true;
				
			}
		}
		return 0;
	}
}
