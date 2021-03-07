import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2146 {

	static int[] dy = { 0, 0, 1, -1 };
	static int[] dx = { 1, -1, 0, 0 };
	static int[][] island;
	static int[][] board;
	static boolean[][] visit;
	static int N, answer;
	static StringTokenizer st;
	static int number;
	
	//좌표마다  bfs2를 돌리니 메모리초과가 떴다 c++이면 안났을텐데;;

	static class Pair {
		Integer y;
		Integer x;
		Integer dist;

		public Pair(Integer y, Integer x) {
			this.y = y;
			this.x = x;
		}
		
		public Pair(Integer y, Integer x, Integer dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		answer = Integer.MAX_VALUE;
		island = new int[N][N];
		board = new int[N][N];
		visit = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		numbering();
		for(int i=1;i<=number;i++) {
			makebridge(i);
		}
		
		System.out.println(answer);
	}

	private static void makebridge(int inumber) {
		for(int i=0;i<N;i++) {
			Arrays.fill(visit[i], false);
		}
		
		Queue<Pair> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (island[i][j] == inumber)
				{
					q.add(new Pair(i,j,0));
					visit[i][j]=true;
				}
					
			}
		}
		int result = bfs(inumber,q);
		answer = Math.min(answer, result);
	}

	private static int bfs(int inumber,Queue<Pair> q2) {
		

		while(!q2.isEmpty()) {
			int cy = q2.peek().y;
			int cx = q2.peek().x;
			int cdist = q2.peek().dist;
			q2.poll();
			for (int dir = 0; dir < 4; dir++) {
				int ny = cy + dy[dir];
				int nx = cx + dx[dir];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
				if(visit[ny][nx]==true) continue;
				if(island[ny][nx]!=0&&island[ny][nx]!=inumber) {
					return cdist;
				}
				visit[ny][nx]=true;
				q2.add(new Pair(ny,nx,cdist+1));
			}
		}
		return Integer.MAX_VALUE;
	}

	private static void numbering() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1) {
					if (island[i][j] > 0)
						continue;
					bfs1(i, j);
				}
			}
		}
	}

	private static void bfs1(int i, int j) {
		number++;
		Queue<Pair> q = new LinkedList<>();
		island[i][j] = number;
		q.add(new Pair(i, j));

		while (!q.isEmpty()) {
			int cy = q.peek().y;
			int cx = q.peek().x;
			q.poll();
			for (int dir = 0; dir < 4; dir++) {
				int ny = cy + dy[dir];
				int nx = cx + dx[dir];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N)
					continue;
				if (board[ny][nx] == 0 || island[ny][nx] > 0)
					continue;
				island[ny][nx] = number;
				q.add(new Pair(ny, nx));
			}
		}
	}

}
