import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_6118 {

	static int N,M;
	static StringTokenizer st;
	static int[] visit;
	static int[] hut;
	static ArrayList<Integer>[] info;
	static int anshut,dist,count;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visit = new int[N+1];
		hut = new int[N+1];
		info = new ArrayList[N+1];
		for(int i=1;i<N+1;i++) {
			info[i]=new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			info[start].add(dest);
			info[dest].add(start);
		}
		
		bfs();
		System.out.printf("%d %d %d",anshut,dist,count);

	}
	private static void bfs() {
		Arrays.fill(visit, -1);
		visit[1]=0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(1);
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i=0;i<info[cur].size();i++) {
				int next = info[cur].get(i);
				if(visit[next]>-1) continue;
				visit[next] = visit[cur]+1;
				q.add(next);
			}
		}
		dist=visit[1];
		for(int i=2;i<=N;i++) {
			if(visit[i]>dist) {
				dist=visit[i];
				anshut=i;
				count=1;
			}
			else if(visit[i]==dist) {
				count++;
			}
		}
	}
}
