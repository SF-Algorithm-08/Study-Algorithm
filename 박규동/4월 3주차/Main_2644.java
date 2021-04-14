import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2644 {
	
	static int n,h1,h2,m,x,y,answer;
	static StringTokenizer st;
	static ArrayList<Integer>[] family;
	static int[] visit;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		h1 = Integer.parseInt(st.nextToken());
		h2 = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(br.readLine());
		family = new ArrayList[n+1];
		visit = new int[n+1];
		for(int i=1;i<=n;i++) {
			family[i] = new ArrayList<>();
		}
		
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			x=Integer.parseInt(st.nextToken());
			y=Integer.parseInt(st.nextToken());
			family[x].add(y);
			family[y].add(x);
		}
		answer=-1;
		bfs();
		System.out.println(answer);
	}

	private static void bfs() {
		visit[h1] = 1;
		Queue<Integer> q = new LinkedList<>();
		q.add(h1);
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(cur==h2) {
				answer = visit[cur]-1;
				return;
			}
			for(int i=0;i<family[cur].size();i++) {
				int next = family[cur].get(i);
				if(visit[next]>0) continue;
				visit[next] = visit[cur]+1;
				q.add(next);
			}
		}
		
	}
}
