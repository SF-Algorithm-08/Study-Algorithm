import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_6087 {

	static int[] dy = { 0, 0, 1, -1 };
	static int[] dx = { 1, -1, 0, 0 };
	static char[][] board;
	static int[][] dist;
	static int W, H;
	static int answer;
	static StringTokenizer st;
	static Pair[] info;

	static class Pair {
		Integer y;
		Integer x;
		Integer dir;
		Integer cnt;
		
		public Pair(Integer y, Integer x) {
			this.y = y;
			this.x = x;
		}
		
		public Pair(Integer y, Integer x, Integer dir,Integer cnt) {
			this.y = y;
			this.x = x;
			this.dir=dir;
			this.cnt=cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		answer = Integer.MAX_VALUE;
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		board = new char[H][W];
		dist = new int[H][W];
		String str;
		info = new Pair[2];
		int idx=0;
		for (int i = 0; i < H; i++) {
			str = br.readLine();
			for (int j = 0; j < W; j++) {
				board[i][j] = str.charAt(j);
				if(board[i][j]=='C') {
					info[idx++] = new Pair(i,j);
				}
			}
		}
		bfs(info[0].y,info[0].x);
		System.out.println(answer);
	}

	private static void bfs(Integer sy, Integer sx) {
		for(int i=0;i<H;i++)
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(sy,sx,-1,0));
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			int cy=cur.y;
			int cx=cur.x;
			int cdir=cur.dir;
			int ccnt=cur.cnt;
			
			if(ccnt>answer) continue;
			
			if(cy==info[1].y&&cx==info[1].x) {
				answer = Math.min(ccnt, answer);
				continue;
			}
			
			for(int i=0;i<4;i++) {
				int ny=cy+dy[i];
				int nx=cx+dx[i];
				if(ny<0||nx<0||ny>=H||nx>=W) continue;
				if(board[ny][nx]=='*') continue;
				if(dist[ny][nx]<=ccnt) continue;
				if(cdir==-1) {
					q.add(new Pair(ny,nx,i,ccnt));
					dist[ny][nx]=ccnt;
					continue;
				}

				if(cdir!=i) {
					dist[ny][nx]=Math.min(ccnt+1,dist[ny][nx]);
					q.add(new Pair(ny,nx,i,ccnt+1));
				}
				else {
					dist[ny][nx]=Math.min(ccnt,dist[ny][nx]);
					q.add(new Pair(ny,nx,i,ccnt));
				}
			}
		}
	}
}
