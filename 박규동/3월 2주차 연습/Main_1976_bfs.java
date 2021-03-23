import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1976_bfs {

	static int[][] travel;
	static boolean[] visit;
	static int N,M;
	static StringTokenizer st;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		M=Integer.parseInt(br.readLine());
		travel = new int[N+1][N+1];
		visit = new boolean[N+1];
		
		for(int i=1;i<N+1;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=1;j<N+1;j++) {
				travel[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st=new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		bfs(start);
		for(int i=0;i<M-1;i++) {
			int tmp = Integer.parseInt(st.nextToken());
			if(!visit[tmp]) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}
	
	private static void bfs(int start) {
		visit[start]=true;
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i=1;i<N+1;i++) {
				if(travel[cur][i]==0) continue;
				if(visit[i]==true) continue;
				visit[i]=true;
				q.add(i);
			}
		}
		
	}
	

}
