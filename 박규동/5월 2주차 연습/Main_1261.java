import java.util.*;
import java.io.*;

public class Main_1261 {

	static int N,M;
	static int[][] board;
	static boolean[][] visit;
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	
	private static class Info implements Comparable<Info>{
		int y;
		int x;
		int cnt;
		
		public Info(int y, int x, int cnt) {
			this.y=y;
			this.x=x;
			this.cnt=cnt;
		}
		
		@Override
		public int compareTo(Info o) {
			return this.cnt-o.cnt;
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		board = new int[N+1][M+1];
		visit = new boolean[N+1][M+1];
		for(int i=1;i<N+1;i++) {
			String str = br.readLine();
			for(int j=1;j<M+1;j++) {
				board[i][j] = str.charAt(j-1)-'0';
			}
		}
		
		System.out.println(bfs());
		
		
	}

	private static int bfs() {
		visit[1][1]=true;
		PriorityQueue<Info> pq = new PriorityQueue<Info>();
		pq.add(new Info(1,1,0));
		while(!pq.isEmpty()) {
			int cy = pq.peek().y;
			int cx = pq.peek().x;
			int ccnt = pq.peek().cnt;
			
			if(cy==N&&cx==M) return ccnt;
			
			pq.poll();
			for(int i=0;i<4;i++) {
				int ny = cy+dy[i];
				int nx = cx+dx[i];
				if(ny<1||nx<1||ny>N||nx>M) continue;
				if(visit[ny][nx]) continue;
				visit[ny][nx]=true;
				if(board[ny][nx]==1) pq.add(new Info(ny, nx, ccnt+1));
				else pq.add(new Info(ny, nx, ccnt));
			}
		}
		
		return -1;
	}

}
