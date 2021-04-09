package boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_2589_보물섬 {
	static class Land {
		int r, c, dist;	//행, 열, 기준점으로부터의 거리
		public Land(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}
	static int ans, M, N;
	static char[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[M][N];
		
		for(int i=0; i<M; i++) map[i] = br.readLine().trim().toCharArray();
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++)
				if(map[i][j]=='L') bfs(i,j);
		}
		
		System.out.println(ans);
	}

	static void bfs(int i, int j) {
		Queue<Land> queue = new LinkedList<Land>();
		queue.offer(new Land(i, j, 0));
		boolean[][] visited = new boolean[M][N];
		visited[i][j] = true;
		
		Land cur;
		int nr, nc;
		int[][] dir = {{-1,1,0,0},{0,0,-1,1}};
		while(!queue.isEmpty()) {
			cur = queue.poll();
			
			for(int k=0; k<4; k++) {
				nr = cur.r + dir[0][k];
				nc = cur.c + dir[1][k];
				if(nr==-1 || nr==M || nc==-1 || nc==N || visited[nr][nc] || map[nr][nc]=='W') continue;
				queue.offer(new Land(nr, nc, cur.dist+1));
				ans = Math.max(ans, cur.dist+1);
				visited[nr][nc]=true;
			}
		}
	}
}
