import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_13549 {

	static int N,K;
	static int[] dir = {1,-1};
	static boolean[] visit;
	static StringTokenizer st;
	
	static class Pair {
		Integer loc;
		Integer time;
		
		public Pair(Integer loc,Integer time) {
			this.loc = loc;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());	
		visit = new boolean[200001];
		bfs(N,0);
	}
	
	private static void bfs(int subin,int time) {
		
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(subin,time));
		
		visit[subin]=true;
		
		while(!q.isEmpty()) {
			int cur = q.peek().loc;
			int ctime = q.peek().time;
			q.poll();
			if(cur==K) {
				System.out.println(ctime);
				return;
			}
			
			for(int i=2;i>-1;i--) {
				int next;
				if(i!=2) {
					next = cur+dir[i];
					if(next<0||next>200000) continue;
					if(visit[next]) continue;
					visit[next]=true;
					q.add(new Pair(next,ctime+1));
				}
				else {
					next = cur*2;
					if(next<0||next>200000) continue;
					if(visit[next]) continue;
					visit[next]=true;
					q.add(new Pair(next,ctime));
				}
			}
		}
	}
	

}
