package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S2_3187_양치기꿍 {
	static int R, C;
	static int k, v; // k: 양, v:늑대 
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new boolean[R][C];
		for(int r = 0; r <R; r++) {
			String line = in.readLine();
			for(int c = 0; c <C; c++) {
				map[r][c] = line.charAt(c);
			}
		}
		// for문을 돌면서 !visited[r][c]이고  #이 아닌 애들 bfs 돌려라   
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(!visited[r][c] && map[r][c] != '#') {
					bfs(r, c);
				}
			}
		}
		System.out.printf("%d %d\n",k, v);
	}
	
	static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<int[]>();
		
		q.add(new int[] {r, c});
		int nr, nc;
		int lv = 0, lk = 0;
		visited[r][c] = true;
		if(map[r][c] == 'v')		lv++;
		else if(map[r][c] == 'k') 	lk++;
		
		while(!q.isEmpty()) {
			r = q.peek()[0];
			c = q.peek()[1];
			q.poll();
			
			
			for(int d=0; d<4; d++) {
				nr = r + dr[d];
				nc = c + dc[d];
				// map[nr][nc] 경계 안에 있고 #이 아닌 애들 넣어라 
				if(0 <= nr && nr < R && 0 <= nc && nc < C && !visited[nr][nc]) {
					if(map[nr][nc] == '#')	continue;
					else if(map[nr][nc] == 'v')	lv++;
					else if(map[nr][nc] == 'k') lk++;
					
					visited[nr][nc] = true;
					q.add(new int[] {nr, nc});
				}
			}
		}
		if(lv >= lk) 	v += lv;	
		else			k += lk;	
	}

}
