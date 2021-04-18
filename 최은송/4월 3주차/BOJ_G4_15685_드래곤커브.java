package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_G4_15685_드래곤커브 {
	static boolean[][] map;
	static int[] dr = {0,-1,0,1};
	static int[] dc = {1,0,-1,0};
	static class Dragon{
		public int r, c, gen, dir, dist;
		public Dragon(int c, int r, int dir, int gen) {
			this.c = c;
			this.r = r;
			this.dir = dir;
			this.gen = gen;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		map = new boolean[101][101];
		int N = Integer.parseInt(in.readLine());
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			Dragon dragon = new Dragon(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			curve(dragon);
		}
		System.out.println(square());
	}
	static int square() {
		int cnt = 0;
		for(int r=0; r<101; r++) {
			for(int c=0; c<101; c++) {
				if(r+1 < 101 && c+1 < 101 && map[r][c] && map[r][c+1] && map[r+1][c] && map[r+1][c+1]) {;
					cnt++;
				}
			}
		}
		return cnt;
	}
	static void curve(Dragon dragon) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		map[dragon.r][dragon.c] = true;
		dragon.r += dr[dragon.dir];
		dragon.c += dc[dragon.dir];
		map[dragon.r][dragon.c] = true;
		list.add(dragon.dir);
		for(int g = 1; g <= dragon.gen; g++) {
			int size = list.size()-1;
			for(int s = size; s >=0; s--) {
				int dir = (list.get(s) + 1) % 4;
				list.add(dir);
				dragon.r += dr[dir];
				dragon.c += dc[dir];
				map[dragon.r][dragon.c] = true;
			}
		}
	}
}
