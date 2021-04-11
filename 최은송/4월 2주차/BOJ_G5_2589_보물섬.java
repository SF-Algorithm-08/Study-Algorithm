package boj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_2589_보물섬 {
	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static Queue<int[]> q = new LinkedList<int[]>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for(int i=0; i<R; i++)
			map[i] = in.readLine().toCharArray();
		
		System.out.println(solve());
	}
	
	static int solve() {
		int max = 0;
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(map[r][c] == 'L') {
					max = Math.max(max, bfs(r,c));
				}
				fill();
			}
		}
		return max;
	}
	static void fill() {
		for(int r=0; r<R; r++)
			Arrays.fill(visited[r], false);
	}
	static int bfs(int r, int c) {
		q.offer(new int[] {r, c});
		visited[r][c] = true;
		int level = 0, size = 0, nr, nc;
		
		while(!q.isEmpty()) {
			size = q.size();
			while(--size >= 0) {
				r = q.peek()[0];
				c = q.peek()[1];
				q.poll();
				
				for(int d=0; d<4; d++) {
					nr = r + dr[d];
					nc = c + dc[d];
					if(0<= nr && nr < R && 0 <= nc && nc < C && !visited[nr][nc] && map[nr][nc] == 'L') {
						visited[nr][nc] = true;
						q.offer(new int[] {nr, nc});
					}
				}
			}
			level++;
		}
		return level-1;
	}
	
}
