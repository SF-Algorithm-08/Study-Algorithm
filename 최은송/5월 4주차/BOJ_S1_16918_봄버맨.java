package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S1_16918_봄버맨 {
	static char map[][];
	static int R, C, N;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static ArrayList<int[]> bomb = new ArrayList<int[]>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for(int i = 0; i < R; i++) { 
			String line = in.readLine().trim();
			for(int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'O') 
					bomb.add(new int[] {i, j});
			}
		}
		
		if(N % 2 == 0) {
			fillMap();
			printMap();
		}else {
			solve();
//			System.out.println("================");
			printMap();
		}
	}
	public static void solve() {
		for(int i = 3; i <= N; i+=2) {
//			System.out.printf("N: %d\n",i);
			fillMap();
			explode();
			insertBomb();
//			printMap();
		}
	}
	private static void explode() {
//		System.out.println("explode");
		for(int[] pos : bomb) {
//			System.out.printf("r: %d, c: %d\n", pos[0], pos[1]);
			map[pos[0]][pos[1]] = '.';
			for(int d = 0; d <4; d++) {
				int nr = pos[0] + dr[d];
				int nc = pos[1] + dc[d];
				if(nr < 0 || nr >= R || nc < 0|| nc >= C) continue;
				map[nr][nc] = '.';
			}
		}
	}
	
	private static void insertBomb() {
		bomb.clear();
		for(int i = 0; i < R; i++) {
			for(int j = 0; j <C; j++) {
				if(map[i][j] == 'O') {
					bomb.add(new int[] {i, j});
				}
			}
		}
	}
	
	private static void printMap() {
		for(int i = 0; i <R; i++) {
			for(int j = 0; j < C; j++)
				System.out.printf("%c", map[i][j]);
			System.out.println();
		}
	}
	
	private static void fillMap() {
		for(int i = 0; i <R; i++)
			Arrays.fill(map[i], 'O');
	}
}
