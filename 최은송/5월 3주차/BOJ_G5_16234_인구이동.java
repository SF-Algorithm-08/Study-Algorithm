package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_16234_인구이동 {
	static int N, L, R, map[][];
	static int[] dr =  {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(getMove());
	}
	static int getMove() {
		int ret = 0;
		while(true) {
			boolean flag = false;
			boolean visited[][] = new boolean[N][N];
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c <N; c++) {
					if(visited[r][c]) 	continue;
					visited[r][c] = true;
					
					Deque<int[]> pos = new ArrayDeque<int[]>();	//연합국 좌표 저장 
					Queue<int[]> q = new LinkedList<int[]>();	

					q.offer(new int[] {r, c});
					int sum = 0, cnt = 0;
					while(!q.isEmpty()) {
						int[] cur = q.poll();
						pos.add(cur);
						sum += map[cur[0]][cur[1]];
						cnt++; 
						
						for(int d = 0; d < 4; d++) {
							int nr = cur[0] + dr[d];
							int nc = cur[1] + dc[d];
							
							if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
							
							int diff = Math.abs(map[nr][nc] - map[cur[0]][cur[1]]);
							
							if(L <= diff && diff <= R) {
								q.offer(new int[] {nr, nc});
								visited[nr][nc] = true;
							}
						}
					}
					// 연합국 인구 이동 
					if(cnt > 1) {
						flag = true;
						int avg = sum / cnt;
						while(!pos.isEmpty()) {
							int[] cur = pos.pollFirst();
							map[cur[0]][cur[1]] = avg;
						}
					}
				}
			}
			// 인구이동이 없으면 break
			if(!flag) {
				break;
			}
			ret++;
		}
		return ret;
	}
}
