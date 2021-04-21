import java.util.*;
import java.io.*;

public class Main_1938 {

	static int N,answer;
	static char[][] board;
	static boolean[][][] visit;
	static int[] dy = { 0, 0, 1, -1,1,1,-1,-1 };
	static int[] dx = { 1, -1, 0, 0,-1,1,-1,1 };
	static int[] lcr = { -1, 0, 1 };
	static Namu[] init,finish;
	
	static class Namu {
		int y;
		int x;
		int state;
		int dist;

		public Namu(int y, int x, int state) {
			this.y = y;
			this.x = x;
			this.state = state;
		}
		
		public Namu(int y, int x, int state,int dist) {
			this.y = y;
			this.x = x;
			this.state = state;
			this.dist = dist;
		}
		
	}

	private static void bfs(Namu namu) {
		visit[namu.y][namu.x][namu.state] = true;
		int sy = namu.y;
		int sx = namu.x;
		int sstate = namu.state;
		
		Queue<Namu> q = new LinkedList<>();
		q.add(new Namu(sy,sx,sstate,0));
		while (!q.isEmpty()) {
			int cy = q.peek().y;
			int cx = q.peek().x;
			int cstate = q.peek().state;
			int cdist = q.peek().dist;
			q.poll();
			if(cy==finish[1].y&&cx==finish[1].x&&cstate==finish[1].state) {
				answer = cdist;
				return;
			}
			
			
			for (int i = 0; i < 4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				if(!ischeck(ny,nx,cstate)) continue;
				visit[ny][nx][cstate]=true;
				q.add(new Namu(ny, nx, cstate,cdist+1));
			}
			int nstate = 0;
			if(cstate==0) nstate=1;
			
			if(ischeckRotate(cy,cx,nstate)) {
				visit[cy][cx][nstate]=true;
				q.add(new Namu(cy, cx, nstate,cdist+1));
			}

		}

	}

	private static boolean ischeckRotate(int cy, int cx, int nstate) {
		if(visit[cy][cx][nstate]) return false;
		for(int i=0;i<8;i++) {
			int ny = cy+dy[i];
			int nx = cx+dx[i];
			if(ny<0||nx<0||ny>=N||nx>=N) return false;
			if(board[ny][nx]=='1') return false;
		}
		
		return true;
	}

	private static boolean ischeck(int ny, int nx, int cstate) {
		
		if(ny<0||nx<0||ny>=N||nx>=N) return false;
		if(visit[ny][nx][cstate]) return false;
		
		if(cstate==0) {
			for(int i=0;i<3;i++) {
				int tx = nx+lcr[i];
				if(tx<0||tx>=N) return false;
				if(board[ny][tx]=='1') return false;
			}
		}
		
		else {
			for(int i=0;i<3;i++) {
				int ty = ny+lcr[i];
				if(ty<0||ty>=N) return false;
				if(board[ty][nx]=='1') return false;
			}
		}
		
		return true;
	}

	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new char[N][N];
		visit = new boolean[N][N][2];
		init = new Namu[3];
		finish = new Namu[3];
		int idx1 = 0;
		int idx2 = 0;
		for (int i = 0; i < N; i++) {
			board[i] = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 'B')
					init[idx1++] = new Namu(i, j, 0);
				if (board[i][j] == 'E')
					finish[idx2++] = new Namu(i, j, 0);
			}
		}
		if (init[0].y != init[1].y)
			init[1].state = 1;
		
		if (finish[0].y != finish[1].y)
			finish[1].state = 1;

		bfs(init[1]);
		System.out.println(answer);
	}

}
