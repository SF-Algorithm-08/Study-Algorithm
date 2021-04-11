package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G3_2146_다리만들기 {
	static int[][] dir = {{-1,1,0,0},{0,0,-1,1}};
	static int[][] map;
	static boolean[][] visited;
	static int N;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		StringTokenizer st;
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int color = 1;
		// 전처리 => 섬별로 숫자를 다르게 붙여야 함
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					init(i, j, color);
					color++;
				}
			}
		}
//		System.out.println(color);
//		for(int i=0; i<N; i++)
//			System.out.println(Arrays.toString(map[i]));
		
		fill(); 
		
		// 전처리 끝나면 bfs로 가장 짧은 다리 길이 구함. bfs로 탐색하면서 다른 숫자가 나오는 가장 짧은 순간 저장
		for(int i=1; i<color; i++) {
			fill();
			int dist = bfs(i);
//			System.out.println(dist);
			min = Math.min(min, dist);
		}
		System.out.println(min);
	}
	
	static void fill() {
		// visited를 다시 false로 
		for(int i=0; i<N; i++)
			Arrays.fill(visited[i], false);

	}
	
	static int bfs(int color) {
		Queue<int[]> q = new LinkedList<int[]>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j] && map[i][j] == color) {
					q.offer(new int[] {i, j, 0});
				}
			}
		}
		int r = 0, c = 0, nr=0, nc=0, cnt = 0;
		while(!q.isEmpty()) {
			r = q.peek()[0];
			c = q.peek()[1];
			cnt = q.peek()[2];
			q.poll();
//			System.out.printf("r: %d, c: %d, cnt:%d\n",r,c,cnt);
			for(int k = 0; k <4; k++) {
				nr = r + dir[0][k];
				nc = c + dir[1][k];
				if(-1 < nr && nr < N && -1 < nc && nc < N && !visited[nr][nc]) {
					if(map[nr][nc] != 0 && map[nr][nc] != color) {
//						System.out.printf("nr: %d, nc: %d, cnt: %d\n",nr,nc, cnt);
						return cnt;
					}
					if(map[nr][nc] == 0) {
						visited[nr][nc] = true;
						q.offer(new int[] {nr, nc, cnt+1});
//						System.out.printf("nr: %d, nc:%d, cnt: %d\n",nr,nc,cnt+1);
					}
				}
			}
		}
		return -1;
	}
	
	static void init(int i, int j, int color) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {i, j});
		visited[i][j] = true;
		map[i][j] = color;
		int nr = 0, nc = 0, r = 0, c = 0;
		while(!q.isEmpty()) {
			r = q.peek()[0];
			c = q.peek()[1];
			q.poll();
			for(int k=0; k<4; k++) {
				nr = r + dir[0][k];
				nc = c + dir[1][k];
				if(-1 < nr && nr < N && -1 < nc && nc <N) {
					if(!visited[nr][nc] && map[nr][nc] == 1) {
						visited[nr][nc] = true;
						map[nr][nc] = color;
						q.offer(new int[] {nr, nc});
					}
				}
			}
		}
	}
	
}
