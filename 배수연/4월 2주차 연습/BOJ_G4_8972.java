package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_8972 {
	static int R, C;
	static char[][] map;
	static int[][] cntArduino;
	static Pos jongsu;
	static int[] dr = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
	static int[] dc = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
	static Queue<Pos> crazyArdu;
	static boolean canPlay;
	static int cnt = 0;
	
	private static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		cntArduino = new int[R][C];
		crazyArdu = new LinkedList<Pos>();
		for(int r = 0 ; r<R ; r++) {
			String line = br.readLine();
			for(int c = 0 ; c<C ; c++) {
				map[r][c] = line.toCharArray()[c];
				if(map[r][c] == 'R') crazyArdu.offer(new Pos(r, c));
				else if(map[r][c] == 'I') jongsu = new Pos(r, c);
			}
		}
		
		String op = br.readLine();
		canPlay = true;
		for(int i = 0, size = op.length() ; i<size ; i++) {
			if(moveJongsu(op.charAt(i)-'0')) {
				if(!moveCrazyArdu()) {
					System.out.println("kraj " + cnt);
					return;
				}
				checkArdu();
			}
			else {
				System.out.println("kraj " + cnt);
				return;
			}
		}
		
		for(int r = 0 ; r<R ; r++) {
			for(int c = 0 ; c<C ; c++) {
				System.out.print(map[r][c]);
			}
			System.out.println();
		}
	}

	private static void checkArdu() {
		for(int r = 0 ; r<R ; r++) {
			for(int c = 0 ; c<C ; c++) {
				if(cntArduino[r][c]>1) map[r][c] = '.';
				else if(cntArduino[r][c] == 1) crazyArdu.offer(new Pos(r, c));
				cntArduino[r][c] = 0;
			}
		}
	}

	private static boolean moveCrazyArdu() {
		int nr, nc, minDis, minX, minY;
		Queue<Pos> pos = new LinkedList<Pos>();
		while(!crazyArdu.isEmpty()) {
			Pos temp = crazyArdu.poll();
			minDis = Integer.MAX_VALUE;
			minX = temp.x;
			minY = temp.y;
			for(int d = 1 ; d<10 ; d++) {
				nr = temp.x + dr[d];
				nc = temp.y + dc[d];
				if(nr>-1 && nr<R && nc>-1 && nc<C) {
					int dis = Math.abs(jongsu.x-nr)+Math.abs(jongsu.y-nc);
					if(dis<minDis) {
						minDis = dis;
						minX = nr;
						minY = nc;
					}
				}
			}
			map[temp.x][temp.y] = '.';
			if(map[minX][minY] == 'I') return false;
			pos.offer(new Pos(minX, minY));
			cntArduino[minX][minY]++;
		}
		while(!pos.isEmpty()) {
			Pos next = pos.poll();
			map[next.x][next.y] = 'R';
		}
		return true;
	}

	private static boolean moveJongsu(int dir) {
		cnt++;
		int nr = jongsu.x + dr[dir];
		int nc = jongsu.y + dc[dir];
		if(map[nr][nc] == 'R') return false;
		
		map[jongsu.x][jongsu.y] = '.';
		map[nr][nc] = 'I';
		jongsu.x = nr;
		jongsu.y = nc;
		return true;
	}
}
