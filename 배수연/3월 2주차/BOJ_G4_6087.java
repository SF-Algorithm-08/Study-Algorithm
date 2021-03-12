package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 레이저 통신
 */

class Pos{
	int x;
	int y;
	public Pos(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class BOJ_G4_6087 {
	static int W, H;
	static char[][] map;
	static int[][] visited;
	static Pos first, last;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new char[W][H];
		visited = new int[W][H];
		boolean isFirst = true;
		for(int w = 0 ; w<W ;w++) {
			String line = br.readLine();
			for(int h = 0, k = 0 ; h<H ; h++) {
				map[w][h] = line.charAt(k++);
				if(map[w][h] == 'C') {
					if(isFirst) {
						first = new Pos(w, h);
						isFirst = false;
					}
					else last = new Pos(w, h);
				}
			}
		}
//		for(int i = 0 ; i<W ; i++) {
//			System.out.println(Arrays.toString(visited[i]));
//		}
		System.out.println(BFS(first, last)-1);
		
	}

	private static int BFS(Pos first, Pos last) {
		Queue<Pos> queue = new LinkedList<Pos>();
		queue.offer(first);
		while(!queue.isEmpty()) {
			Pos temp = queue.poll();
			if(last.x == temp.x && last.y == temp.y) return visited[last.x][last.y];
			for(int d = 0 ; d<4 ; d++) {
				int nr = temp.x+dr[d];
				int nc = temp.y+dc[d];
				while(nr>-1 && nr<W && nc>-1 && nc<H && map[nr][nc]!= '*') {
					if(visited[nr][nc] == 0) {
						visited[nr][nc] = visited[temp.x][temp.y]+1;
						queue.offer(new Pos(nr, nc));
					}
					nr += dr[d];
					nc += dc[d];
				}
			}
		}
		
		return 0;
	}
}
