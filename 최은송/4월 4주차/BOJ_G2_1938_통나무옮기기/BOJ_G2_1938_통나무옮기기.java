package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_G2_1938_통나무옮기기 {
	static char[][] map;
	static int N, tdir;
	static int[][] target, pos;
	static int[] dr = {-1,1,0,0,0};
	static int[] dc = {0,0,-1,1,0};
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		map = new char[N][N];
		pos = new int[3][2];
		target = new int[3][2];
		int bidx = 0, eidx = 0;

		for(int i=0; i<N; i++) {
			String line = in.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'B') {
					pos[bidx][0] = i;
					pos[bidx++][1] = j;
				}else if(map[i][j] == 'E') {
					target[eidx][0] = i;
					target[eidx++][1] = j;
				}
			}
		}
		tdir = target[0][0] - target[1][0] == 0 ? 0 : 1;
		System.out.println(bfs(pos[1][0] - pos[0][0]));
	}
	// 0: 수평, 1: 수직
	static int bfs(int dir) {
		int cnt = 0;
		Queue<int[]> q = new LinkedList<int[]>();
		boolean visited[][][] = new boolean[N][N][2];
		q.add(new int[] {pos[1][0], pos[1][1], dir}); //0: r, 1: c, 2: dir 
		visited[pos[1][0]][pos[1][1]][dir] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int s=0; s<size; s++) {
				int[] cur = q.poll();
				
				// 가로일 때 
				if(cur[2] == tdir && cur[0] == target[1][0] && cur[1] == target[1][1]) {
					return cnt;
				}
				for(int d=0; d<5; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					dir = d == 4 ? (cur[2] + 1) % 2 : cur[2];
					if(valid(nr, nc, dir, d) && !visited[nr][nc][dir]) {
						visited[nr][nc][dir] = true;
						q.offer(new int[] {nr, nc, dir});
					}
				}
			}
			cnt++;
		}
		return 0;
	}
	static boolean valid(int r, int c, int dir, int d) {
		int nr = r, nc = c;
		if(d == 4) {
			for(int i=-1; i<=1; i++) {
				for(int j=-1; j<=1; j++) {
					nr = r + i;
					nc = c + j;
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == '1')
						return false;
				}
			}
		}
		// 수평일때 
		else if(dir == 0) {
			if(d == 0 || d == 1) {		//상,하
				for(int i=-1; i<=1; i++) {
					nc = c + i;
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == '1')
						return false;
				}
			}else if(d == 2 || d == 3) { //좌,우 
				nc += d == 2 ? -1 : 1 ;
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == '1')
					return false;
			}
		}// 수직일때 
		else {
			if(d == 0 || d == 1) {		//상,하
				nr += d == 0 ? -1 : 1;
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == '1')
					return false;
			}else if(d == 2 || d == 3) { //좌,우
				for(int i=-1; i<=1; i++) {
					nr = r + i;
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == '1')
						return false;
				}
			}
		}
		return true;
	}
}
