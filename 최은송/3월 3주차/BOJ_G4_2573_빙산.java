package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_2573_빙산 {
	static int[][] map;
	static int R;
	static int C;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static boolean[][] visited;
	static int time = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		in = new BufferedReader(new StringReader(input));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		visited = new boolean[R][C];
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<C; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		while(sum() != 0) {
			time++;
			melting();

			int cnt = cntIsland();
			if(cnt > 1) {
				System.out.println(time);
				return;
			}
		}
		System.out.println(0);
		
	}
	
	
	static int sum() {
		int sum = 0;
		for(int r=1; r<R-1; r++) {
			for(int c=1; c<C-1; c++) {
				sum += map[r][c];
			}
		}
		return sum;
	}
	
	// 섬의 개수를 카운팅 
	static int cntIsland() {
		int cnt = 0;
		
		for(int r=1; r<R-1; r++) {
			for(int c=1; c<C-1; c++) {
				if(map[r][c] != 0 && !visited[r][c]) {
					bfs(r,c);
					cnt++;
				}
			}
		}
		for(int i=0; i<R; i++)
			Arrays.fill(visited[i], false);
		return cnt;
	}
	
	static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {r, c});
		visited[r][c] = true;
		
		int nr = 0, nc = 0;
		boolean noNeighbor = true;
		while(!q.isEmpty()) {
			r = q.peek()[0];
			c = q.peek()[1];
			q.poll();
			for(int d = 0; d < 4; d++) {
				nr = r + dr[d];
				nc = c + dc[d];
				if(map[nr][nc] != 0 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
					noNeighbor = false;
				}
			}
			if(noNeighbor) {
				return;
			}
		}
	}
	
	// 빙산 깎는 메서드 
	static void melting() {
		int cnt, nr, nc;
		int[][] temp = new int[R][C];
		for(int r=1; r<R-1; r++) {
			for(int c=1; c<C-1; c++) {
				if(map[r][c] != 0) {
					cnt = 0;
					for(int d=0; d<4; d++) {
						nr = r + dr[d];
						nc = c + dc[d];
						if(map[nr][nc] == 0)
							cnt++;
					}
					temp[r][c] = map[r][c] - cnt <= 0 ? 0 : map[r][c] - cnt;
				}
			}
		}
		for(int i=1; i<R-1; i++)
			System.arraycopy(temp[i], 0, map[i], 0, C);
	}
	
	static String input = "5 7\n" + 
			"0 0 0 0 0 0 0\n" + 
			"0 2 4 5 3 0 0\n" + 
			"0 3 0 2 5 2 0\n" + 
			"0 7 6 2 4 0 0\n" + 
			"0 0 0 0 0 0 0";
}
