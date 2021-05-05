import java.util.*;
import java.io.*;
public class Main_19238 {

	static StringTokenizer st;
	static int[][] board,visit;
	static int N,M,gas,sy,sx;
	static Pair[] saram,dest;
	static Queue<Pair> q;
	static PriorityQueue<Pair> pq;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,-1,0,1};
	static class Pair {
		int y;
		int x;
		int dist;
		public Pair(int y,int x) {
			this.y=y;
			this.x=x;
		}
		public Pair(int y,int x,int dist) {
			this.y=y;
			this.x=x;
			this.dist=dist;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		gas=Integer.parseInt(st.nextToken());
		board = new int[N+1][N+1];
		visit = new int[N+1][N+1];
		dest = new Pair[M+1];
		q = new LinkedList<Pair>();
		pq = new PriorityQueue<>(new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				 if(o1.dist==o2.dist) {
					 if(o1.y==o2.y) {
						 return o1.x-o2.x;
					 }else return o1.y-o2.y;
				 }
				 else return o1.dist-o2.dist;
			}
		});
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j]==1) board[i][j]=-1;
			}
		}
		
		st=new StringTokenizer(br.readLine());
		sy = Integer.parseInt(st.nextToken());
		sx = Integer.parseInt(st.nextToken());
		for(int i=1;i<=M;i++) {
			st=new StringTokenizer(br.readLine());
			board[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]=i; //사람
			dest[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		solve();
		if(gas<0) System.out.println(-1);
		else System.out.println(gas);
		
	}

	private static void solve() {
		
		for(int i=0;i<M;i++) {
			int target = choose();
			if(gas<0) return;
			drive(target);
			if(gas<0) return;
		}
		
	}

	private static void drive(int target) {
		//승객까지 갔음 일단
		q.clear();
		for(int i=1;i<=N;i++) {
			Arrays.fill(visit[i], 0);
		}
		board[sy][sx]=0;
		visit[sy][sx]=1;
		boolean flag=false;
		q.add(new Pair(sy, sx));
		while(!q.isEmpty()) {
			int cy = q.peek().y;
			int cx = q.peek().x;
			q.poll();
			if(cy==dest[target].y&&cx==dest[target].x) {
				gas -= visit[cy][cx]-1;
				if(gas<0) return;
				else {
					gas += 2*(visit[cy][cx]-1);
					sy=cy;
					sx=cx;
					return;
				}
			}
			for(int i=0;i<4;i++) {
				int ny = cy+dy[i];
				int nx = cx+dx[i];
				if(ny<1||nx<1||ny>N||nx>N) continue;
				if(board[ny][nx]==-1) continue;
				if(visit[ny][nx]>0) continue;
				visit[ny][nx] = visit[cy][cx]+1;
				q.add(new Pair(ny,nx));
			}
		}
		if(!flag) gas=-1;
		
	}

	private static int choose() {
		pq.clear();
		for(int i=1;i<=N;i++) {
			Arrays.fill(visit[i], 0);
		}
		visit[sy][sx]=1;
		pq.add(new Pair(sy, sx, 0));
		while(!q.isEmpty()) {
			int cy = pq.peek().y;
			int cx = pq.peek().x;
			int cdist = pq.peek().dist;
			pq.poll();
			if(board[cy][cx]>0) {
				gas -= cdist;
				System.out.println(cy+","+cx);
				sy=cy;
				sx=cx;
				return board[cy][cx];
			}
			for(int i=0;i<4;i++) {
				int ny = cy+dy[i];
				int nx = cx+dx[i];
				if(ny<1||nx<1||ny>N||nx>N) continue;
				if(board[ny][nx]==-1) continue;
				if(visit[ny][nx]>0) continue;
				visit[ny][nx] = visit[cy][cx]+1;
				q.add(new Pair(ny,nx,cdist+1));
			}
		}
		return gas=-1;
	}

}
