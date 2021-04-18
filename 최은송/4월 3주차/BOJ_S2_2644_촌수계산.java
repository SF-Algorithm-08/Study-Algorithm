package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S2_2644_촌수계산 {
	static int x, y;
	static int[][] arr;
	static boolean[] visited;
	static int N;
	public static void main(String[] args) throws IOException{
		init();
		bfs();
	}
	static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(x);
		visited[x] = true;
		int dist = -1;
		while(!q.isEmpty()) {
			int size = q.size();
			dist++;
			for(int i=0; i<size; i++) {
				x = q.poll();
				if(x == y) {
					System.out.println(dist);
					return;
				}
				for(int j=1; j<=N; j++) {
					if(!visited[j] && arr[x][j] == 1) {
						visited[j] = true;
						q.offer(j);
					}
				}
			}
		}		
		System.out.println(-1);
	}
	static void init() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		arr = new int[N+1][N+1];
		visited = new boolean[N+1];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		
		int m = Integer.parseInt(in.readLine());
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(in.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr[p][c] = arr[c][p] = 1;
		}
	}
}
