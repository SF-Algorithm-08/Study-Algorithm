import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2573 {

	static int N, M;
	static StringTokenizer st;
	static int[][] board, copymap;
	static boolean[][] visit;
	static int[] dy = { 0, 0, 1, -1 };
	static int[] dx = { 1, -1, 0, 0 };
	static int answer;

	static class Pair {
		int y;
		int x;

		public Pair(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solution();
		System.out.println(answer);

	}

	private static void solution() {
		
		while (true) {
			if (isTwo()) break;
			melt();
			answer++;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(board[i][j]!=0) {
					return;
				}
			}
		}
		answer=0;
		
	}

	private static void melt() {

		copymap = new int[N][M];
		Queue<Pair> q = new LinkedList<Pair>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				q.add(new Pair(i,j));
			}
		}
		
		while(!q.isEmpty()) {
			int cy = q.peek().y;
			int cx = q.peek().x;
			q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				if (ny >= N || nx >= M || ny < 0 || nx < 0)
					continue;
				if (board[ny][nx] == 0) {
					copymap[cy][cx]++;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				board[i][j]-=copymap[i][j];
				if(board[i][j]<0) board[i][j]=0;
			}
		}
		
	}

	private static boolean isTwo() {

		for (int i = 0; i < N; i++) {
			Arrays.fill(visit[i], false);
		}
		Queue<Pair> q = new LinkedList<Pair>();

		int y = 0;
		int x = 0;
		top: for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] > 0) {
					y = i;
					x = j;
					break top;
				}
			}
		}

		q.add(new Pair(y, x));
		visit[y][x] = true;

		while (!q.isEmpty()) {
			int cy = q.peek().y;
			int cx = q.peek().x;
			q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				if (ny >= N || nx >= M || ny < 0 || nx < 0)
					continue;
				if (visit[ny][nx] || board[ny][nx] == 0)
					continue;
				visit[ny][nx] = true;
				q.add(new Pair(ny, nx));
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] > 0 && visit[i][j])
					continue;
				if (board[i][j] == 0 && !visit[i][j])
					continue;
				return true;
			}
		}

		return false;
	}
}
