package boj.Gold;
//G5 N번째 큰 수(자료구조, 우선순위 큐)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G5_2075_N번째큰수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int[][] map = new int[N][N];
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(map[i]);
		}
		
		Point cur;
		int nr, nc;
		int[][] dir = {{-1,0},{0,-1}};
		
		int idx = 1;
		PriorityQueue<Point> queue = new PriorityQueue<Point>();
		queue.offer(new Point(N-1, N-1, map[N-1][N-1]));
		boolean[][] visited = new boolean[N][N];
		visited[N-1][N-1] = true;
		
		while(!queue.isEmpty()) {
			cur = queue.poll();
			if(idx++==N) {
				System.out.println(cur.num);
				break;
			}
			
			for(int i=0; i<2; i++) {	
				nr = cur.r + dir[i][0];
				nc = cur.c + dir[i][1];
				if(nr<0 || nr>=N || nc<0 || nc>=N || visited[nr][nc]) continue;
				visited[nr][nc] = true;
				queue.offer(new Point(nr, nc, map[nr][nc]));
			}
		}
	}

	static class Point implements Comparable<Point>{
		int r, c, num;
		
		public Point(int r, int c, int num) {
			super();
			this.r = r; 
			this.c = c;
			this.num = num;
		}
		
		@Override
		public int compareTo(Point o) {
			if(this.num > o.num) return -1;
			else if(this.num < o.num) return 1;
			else return 0;
		}
	}
}
