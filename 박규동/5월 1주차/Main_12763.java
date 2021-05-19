import java.util.*;
import java.io.*;

public class Main_12763 {

	static int N,T,M,L,answer;
	static StringTokenizer st;
	static ArrayList<Node>[] info;
	static final int INF = Integer.MAX_VALUE;
	
	static int[] costs,times;
	
	static class Node {
		int dest;
		int time;
		int cost;
		public Node(int dest, int time, int cost) {
			this.dest = dest;
			this.time = time;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		st=new StringTokenizer(br.readLine());
		T=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		L=Integer.parseInt(br.readLine());
		info = new ArrayList[N+1];
		costs = new int[N+1];
		times = new int[N+1];
		
		for(int i=1;i<=N;i++) info[i] = new ArrayList<>();
	
		for(int i=0;i<L;i++) {
			st=new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			info[start].add(new Node(dest, time, cost));
			info[dest].add(new Node(start, time, cost));
		}
		
		answer = find(1,N);
		if(answer>M) answer = -1;
		System.out.println(answer);
	}

	private static int find(int n1, int n2) {
		Arrays.fill(costs, INF);
		Arrays.fill(times, INF);
		
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.cost-o2.cost;
			}
		});
		
		costs[n1]=0;
		times[n1]=0;
		
		pq.add(new Node(n1, 0, 0));
		while(!pq.isEmpty()) {
			int cur = pq.peek().dest;
			int ctime = pq.peek().time;
			int ccost = pq.peek().cost;
			if(cur==N) {
				return ccost;
			}
			pq.poll();
			for(int i=0;i<info[cur].size();i++) {
				int next = info[cur].get(i).dest;
				int ncost = info[cur].get(i).cost;
				int ntime = info[cur].get(i).time;
				
				if(ctime+ntime>T) continue;
				if(costs[next]>=ccost+ncost||times[next]>=ctime+ntime) {
					if(costs[next]>ccost+ncost) costs[next] = ccost+ncost;
					if(times[next]>ctime+ntime) times[next] = ctime+ntime;
					pq.add(new Node(next, ctime+ntime, ccost+ncost));
				}
			}
			
		}
		return -1;
	}

}
