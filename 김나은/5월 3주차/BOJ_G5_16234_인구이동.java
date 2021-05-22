package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_16234_인구이동 {

	private static class Pos {
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	static int N, L, R, ans;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve();
		
		System.out.println(ans);
	}

	private static boolean bfs(Pos start) {
		Queue<Pos> queue = new LinkedList<>();
		List<Pos> union = new LinkedList<>();
		
		visited[start.r][start.c] = true;
		queue.offer(start);
		union.add(start);	
		int sum = map[start.r][start.c];
		
		while (!queue.isEmpty()) {
			Pos cur = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr<0 || nr>= N || nc<0 || nc>= N || visited[nr][nc] ) continue;
				if(L <= Math.abs(map[cur.r][cur.c] - map[nr][nc]) && Math.abs(map[cur.r][cur.c] - map[nr][nc]) <= R) {
					sum += map[nr][nc];
					queue.offer(new Pos(nr,nc));
					union.add(new Pos(nr,nc));
					visited[nr][nc] = true;
				}
			}
		}
		
		if(union.size() == 1) return false;
		else {
			int avg = sum / union.size();
			for (Pos pos : union) {
				map[pos.r][pos.c] = avg;
			}
			return true;
		}
	}

	private static void solve() {
		boolean flag;
		
		while (true) {
			visited = new boolean[N][N];
			flag = false;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(visited[i][j]) continue;
					if(bfs(new Pos(i,j))) flag = true;
				}
			}
			
			if(flag) ans++;
			else return;
		}
	}
	
}
