package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 양치기 꿍
 */

public class BOJ_S2_3187 {
	static int R, C, sheep, wolf, sheep_ans, wolf_ans;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		for(int r = 0 ; r<R ; r++) {
			String line = br.readLine();
			for(int c = 0 ; c<C ; c++) {
				map[r][c] = line.charAt(c);
			}
		}
		
		sheep_ans = 0;
		wolf_ans = 0;
		for(int r = 0 ; r<R ; r++) {
			for(int c = 0 ; c<C ; c++) {
				if(!visited[r][c] && map[r][c] != '#') {
					sheep = 0;
					wolf = 0;
					DFS(r, c);
					
					if(sheep>wolf) sheep_ans += sheep;
					else wolf_ans += wolf;
				}
			}
		}
		System.out.println(sheep_ans + " " + wolf_ans);
	}
	public static void DFS(int r, int c) {
		visited[r][c] = true;
		if(map[r][c] == 'k') sheep++;
		else if(map[r][c] == 'v') wolf++;
		
		for(int d = 0 ; d<4 ; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr>-1 && nr<R && nc>-1 && nc<C && !visited[nr][nc]) {
				if(map[nr][nc] == '#') continue;
				DFS(nr, nc);
			}
		}
	}

}
