package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_8972_미친아두이노 {
	static class Pos{
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static Queue<Pos>  q= new LinkedList<Pos>();
	static char[][] map;
	static int[][] temp;
	static int R;
	static int C;
	static String cmds;
	static int[] dr = {1,1,1,0,0,0,-1,-1,-1};
	static int[] dc = {-1,0,1,-1,0,1,-1,0,1};
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		temp = new int[R][C];
		Pos I = null;
		for(int i=0; i<R; i++) {
			map[i] = in.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				if(map[i][j] == 'I')
					I = new Pos(i,j);
				else if(map[i][j] == 'R')
					q.offer(new Pos(i,j));
			}
		}
		cmds = in.readLine();
		find(I);
	}
	static void find(Pos I) {
		int nr, nc, size, dist, minr=0, minc=0;
		// 명령어 수만큼 이동 
		for(int c=1; c<=cmds.length(); c++) {
			int cmd = cmds.charAt(c-1) - 48;
			// 종두이노 이동 
			map[I.r][I.c] = '.';
			I.r += dr[cmd-1];
			I.c += dc[cmd-1];
			map[I.r][I.c] = 'I';
			
			// 미두이노 이동 
			size = q.size();
			for(int i=0; i<size; i++) {
				Pos robot = q.poll();
				int minDist = Integer.MAX_VALUE;
				// I와 가장 가까운 거리 구하기 
				for(int d=0; d<9; d++) {
					nr = robot.r + dr[d];
					nc = robot.c + dc[d];
					if(0 <= nr && nr < R && 0 <= nc && nc < C) {
						dist = Math.abs(I.r - nr) + Math.abs(I.c - nc);
						if(dist < minDist) {
							minDist = dist;
							minr = nr;
							minc = nc;
						}
					}
				}
				// 종두이노와 부딪히면 종료 
				if(map[minr][minc] == 'I') {
					System.out.printf("kraj %d\n",c);
					return;
				}
				// 좌표에 미두이노가 몇개 있는지 체크  
				temp[minr][minc]++;
				// 그 전 미두이노 좌표는 clear 
				map[robot.r][robot.c] = '.';
			}
			// 좌표에 미두이노가 한개만 있을 때 큐에 offer 
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(temp[i][j] == 1) {
						map[i][j] = 'R';
						q.offer(new Pos(i,j));
					}
					temp[i][j] = 0;
				}
			}
		}
		// 게임이 끝났을 때 좌표 출력 
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++)
				System.out.print(map[r][c]);
			System.out.println();
		}
	}
}
