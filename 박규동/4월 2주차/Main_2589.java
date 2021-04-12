import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2589 {

	static char[][] board;
	static int R,C;
	static StringTokenizer st;
	static ArrayList<Node> info;
	static boolean[][] visit;
	static int answer;
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	
	static class Node {
		int y;
		int x;
		int time;
		public Node(int y,int x) {
			this.y=y;
			this.x=x;
		}
		public Node(int y,int x,int time) {
			this.y=y;
			this.x=x;
			this.time=time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		board = new char[R][C];
		info = new ArrayList<>();
		answer = Integer.MIN_VALUE;
		String str="";
		for(int i=0;i<R;i++) {
			str = br.readLine();
			for(int j=0;j<C;j++) {
				board[i][j] = str.charAt(j);
				if(board[i][j]=='L') info.add(new Node(i,j));
			}
		}
		
		for(Node node : info) {
			bfs(node);
		}
		
		System.out.println(answer);
		
	}

	private static void bfs(Node node) {
		visit = new boolean[R][C];
		visit[node.y][node.x]=true;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(node.y,node.x,0));
		
		while(!q.isEmpty()) {
			int cy = q.peek().y;
			int cx = q.peek().x;
			int ctime = q.peek().time;
			q.poll();
			if(answer<ctime) answer=ctime;
			
			for(int i=0;i<4;i++) {
				int ny = cy+dy[i];
				int nx = cx+dx[i];
				if(ny<0||nx<0||ny>=R||nx>=C) continue;
				if(board[ny][nx]=='W') continue;
				if(visit[ny][nx]) continue;
				visit[ny][nx]=true;
				q.add(new Node(ny,nx,ctime+1));
			}
		}
	}

}
