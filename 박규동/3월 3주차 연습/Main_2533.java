import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2533 {

	static int N,answer,flag;
	static ArrayList<Node>[] tree,inputtree;
	static boolean[] visit,check;
	static StringTokenizer st;
	static int a,b;
	
	static class Node {
		int next;
		public Node(int next) {
			this.next = next;
		}
		
	}
	
	static class Pair {
		int cur;
		int depth;
		
		public Pair(int cur,int depth) {
			this.cur = cur;
			this.depth = depth;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		tree = new ArrayList[N+1];
		inputtree = new ArrayList[N+1];
		visit = new boolean[N+1];
		check = new boolean[N+1];
		for(int i=1;i<=N;i++) {
			tree[i] = new ArrayList<>();
			inputtree[i] = new ArrayList<>();
		}
		
		for(int i=0;i<N-1;i++) {
			st = new StringTokenizer(br.readLine());
			int node = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			inputtree[node].add(new Node(next));
			inputtree[next].add(new Node(node));
		}
//		solution();
		sel_root(1);
		a=dfs(1,true,1);
		System.out.println(a);
		b=dfs(1,false,0);
		System.out.println(b);
		answer = Math.min(a, b);
//		cal();
		System.out.println(flag);
		System.out.println(answer);
	}

	private static void sel_root(int cur) {
		flag=cur;
		visit[cur] = true;
		for(int i=0;i<inputtree[cur].size();i++) {
			int next = inputtree[cur].get(i).next;
			if(visit[next]) continue;
			tree[cur].add(new Node(next));
			sel_root(next);
		}
		
	}

	private static void cal() {
		for(int i=1;i<=N;i++) {
			if(check[i]) answer++;
		}
		System.out.println(answer);
		answer = Math.min(answer, N-answer);		
	}

	private static int dfs(int cur,boolean prev,int tmp) {

		if(cur==flag) {
			return tmp;
		}
		
		if(prev==true) {
			for(int i=0;i<tree[cur].size();i++) {
				int next = tree[cur].get(i).next;
				return answer = Math.min(dfs(next,false,tmp),dfs(next,true,tmp+1));
			}
		}
		
		else {
			for(int i=0;i<tree[cur].size();i++) {
				int next = tree[cur].get(i).next;
				return answer=dfs(next,true,tmp+1);
			}
		}
		
		return tmp;
	}

	private static void solution() {
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(1,1));
		visit[1]=true;
		while(!q.isEmpty()) {
			int cur = q.peek().cur;
			int depth = q.poll().depth;
			if(depth%2==0) answer++;
			
			for(int i=0;i<tree[cur].size();i++) {
				int next = tree[cur].get(i).next;
				if(visit[next]) continue;
				visit[next] = true;
//				System.out.printf("next:%d depth:%d\n",next,depth);
				q.add(new Pair(next,depth+1));
			}
		}
		System.out.println(answer);
		answer = Math.min(answer, N-answer);
	}

}
