import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_9205 {
	
	static int T,n;
	static Pair[] info;
	static StringTokenizer st;
	static boolean[] visit;
	static class Pair {
		Integer y;
		Integer x;
		public Pair(Integer y, Integer x) {
			this.y=y;
			this.x=x;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=0;tc<T;tc++) {
			n=Integer.parseInt(br.readLine());
			visit = new boolean[n+2];
			info = new Pair[n+2];
			for(int i=0;i<n+2;i++) {
				st = new StringTokenizer(br.readLine());
				info[i]=new Pair(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			
			if(bfs()) sb.append("happy\n");
			else sb.append("sad\n");
		}
		System.out.print(sb.toString());
		
	}
	private static boolean bfs() {
		visit[0]=true;
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(0);
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(cur==n+1) return true;
			for(int i=1;i<n+2;i++) {
				if(visit[i]) continue;
				if(canGo(cur,i)) {
					visit[i]=true;
					q.add(i);
				}
			}
		}
		return false;
	}
	private static boolean canGo(int cur,int i) {
		int dist = Math.abs(info[cur].y-info[i].y)+Math.abs(info[cur].x-info[i].x);
		if(dist<=20*50) {
			return true;
		}
		return false;
	}
	
}
