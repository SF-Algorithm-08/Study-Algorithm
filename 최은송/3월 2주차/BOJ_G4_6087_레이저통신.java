package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_6087_레이저통신 {
	static char[][] map;
	static int[][] visited;
	static int W;
	static int H;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int min = Integer.MAX_VALUE;
	static int Pos[][] = new int[2][2];
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new char[H][W];
		visited = new int[H][W];
		int idx = 0;
		
		for(int i = 0; i<H; i++) {
			String line = in.readLine();
			for(int j=0; j<W; j++) {
				visited[i][j] =  Integer.MAX_VALUE;
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'C') {
					Pos[idx][0] = i;
					Pos[idx++][1] = j;
				}
			}
		}
		
		bfs();
		System.out.println(min);
	}
	
	static void bfs() {
		int cnt = 0, dir = 0, nr = 0, nc = 0, r = 0, c = 0;
		Queue<int[]> q = new LinkedList<int[]>();
		
		int y = Pos[0][0], x = Pos[0][1];
		q.add(new int[] {y, x, cnt, 4});
		visited[y][x] = -1;
		map[y][x] = '*';
		
		while(!q.isEmpty()) {
			r = q.peek()[0];	
			c = q.peek()[1];
			cnt = q.peek()[2];
			dir = q.peek()[3];
			q.poll();
			
			if(r == Pos[1][0] && c == Pos[1][1]) {
				min = Math.min(cnt, min);
			}
			
			for(int i=0; i<4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				
				if(-1 < nr && nr < H && -1 < nc && nc < W && map[nr][nc] != '*') {
					int _cnt = cnt;
					if(dir != i && dir != 4)
						_cnt++;
					if(visited[nr][nc] < _cnt) {
						continue;
					}
					visited[nr][nc] = _cnt;
					q.offer(new int[] {nr, nc, _cnt, i});
				}
			}
		}
	}
}
