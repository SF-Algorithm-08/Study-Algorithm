import java.io.*;
import java.util.*;

public class Main_16234 {

	private static int N, L, R, color, answer;
	private static int[][] board, visit;
	private static int[] dy = { 0, 0, 1, -1 };
	private static int[] dx = { 1, -1, 0, 0 };
	private static ArrayList<Integer> check;
	private static class Pair {
		int y;
		int x;
		public Pair(int y, int x) {
			this.y=y;
			this.x=x;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solve();
		System.out.println(answer);

	}

	private static void solve() {
		while (true) {
			init();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visit[i][j] > 0) continue;
					open(i, j);
				}
			}
			merge();
			if(color==N*N) break;
			answer++;
		}
	}

	private static void merge() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				board[i][j] = check.get(visit[i][j]-1);
			}
		}
		
	}

	private static void open(int y, int x) {
		color++;
		visit[y][x] = color;
		int cnt=1;
		int sum=board[y][x];
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(y,x));
		while(!q.isEmpty()) {
			int cy = q.peek().y;
			int cx = q.peek().x;
			q.poll();
			for(int i=0;i<4;i++) {
				int ny = cy+dy[i];
				int nx = cx+dx[i];
				if(ny<0||nx<0||ny>=N||nx>=N) continue;
				if(visit[ny][nx]>0) continue;
				
				int val = Math.abs(board[cy][cx]-board[ny][nx]);
				if(val>=L&&val<=R) {
					visit[ny][nx]=color;
					q.add(new Pair(ny, nx));
					sum+=board[ny][nx];
					cnt++;
				}
			}
		}
		check.add(sum/cnt);
	}

	private static void init() {
		visit = new int[N][N];
		color = 0;
		check = new ArrayList<>();
	}

}
