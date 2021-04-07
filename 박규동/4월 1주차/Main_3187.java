import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3187 {

	static char[][] board;
	static int[][] visit;
	static int R,C;
	static int remain_w,remain_s;
	static ArrayList<Animal> info;
	static StringTokenizer st;
	static int areaNum;
	
	static int[] dx = { 0,0,1,-1 };
	static int[] dy = { 1,-1,0,0 };
	
	
	static class Animal {
		int y;
		int x;
		boolean wOrs;
		
		public Animal(int y, int x, boolean wOrs) {
			this.y = y;
			this.x = x;
			this.wOrs = wOrs;
		}
		
		public Animal(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		board = new char[R][C];
		visit = new int[R][C];
		info = new ArrayList<>();
		areaNum=1;
		for(int i=0;i<R;i++) {
			String str=br.readLine();
			for(int j=0;j<C;j++) {
				board[i][j]=str.charAt(j);
				if(board[i][j]=='v') info.add(new Animal(i, j, false));
				if(board[i][j]=='k') info.add(new Animal(i, j, true));
			}
		}
		
		for(int i=0;i<info.size();i++) {
			bfs(info.get(i));
		}
		
		System.out.println(remain_s+" "+remain_w);
		
	}


	private static void bfs(Animal animal) {
		int y=animal.y;
		int x=animal.x;
		boolean wOrs = animal.wOrs;
		if(visit[y][x]>0) return;
		int wolfs=0, sheeps=0;
		
		if(wOrs) sheeps++;
		else wolfs++;
		
		visit[y][x]=areaNum;
		Queue<Animal> q = new LinkedList<>();
		q.add(new Animal(y, x));
		while (!q.isEmpty())
		{
			int cy = q.peek().y;
			int cx = q.peek().x;
			q.poll();
			for (int i = 0; i < 4; i++)
			{
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				if (ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
				if (board[ny][nx] == '#') continue;
				if (visit[ny][nx] > 0) continue;
				
				if (board[ny][nx] == 'v') wolfs++;
				if (board[ny][nx] == 'k') sheeps++;

				visit[ny][nx] = areaNum;
				q.add(new Animal(ny, nx));
			}
		}
		if (wolfs >= sheeps) remain_w += wolfs;
		else remain_s += sheeps;
		areaNum++;
		
	}

}
