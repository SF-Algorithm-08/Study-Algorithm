import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1774 {

	static int N,M;
	static StringTokenizer st;
	static ArrayList<Pair> info,loc;
	static int[] parent;
	static double answer;
	
	static class Pair {
		int start;
		int dest;
		long y;
		long x;
		double dist;
		
		public Pair(int start, int dest, double dist) {
			this.start = start;
			this.dest = dest;
			this.dist = dist;
		}
		
		public Pair(long y, long x) {
			this.y=y;
			this.x=x;
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Pair [start=").append(start).append(", dest=").append(dest).append(", dist=").append(dist)
					.append("]");
			return builder.toString();
		}

	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		parent = new int[N+1];
		for(int i=1;i<N+1;i++) parent[i]=i; //root 배열 init
		info = new ArrayList<>();
		loc = new ArrayList<>();
		loc.add(new Pair(0, 0)); //index를 1부터 시작
		
		for(int i=1;i<N+1;i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			loc.add(new Pair(y, x));
		}
		
		for(int i=1;i<N+1;i++) {
			long cy = loc.get(i).y;
			long cx = loc.get(i).x;
			for(int j=i+1;j<N+1;j++) {
				long ny = loc.get(j).y;
				long nx = loc.get(j).x;
				double dist = Math.sqrt((ny-cy)*(ny-cy) + (nx-cx)*(nx-cx));
				info.add(new Pair(i, j, dist));
				info.add(new Pair(j, i, dist));
			}
		}
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int god1 = Integer.parseInt(st.nextToken());
			int god2 = Integer.parseInt(st.nextToken());
			if(check(god1,god2)) continue;
			union(god1,god2);
		}
		
		MST();
		System.out.printf("%.2f",answer);
		
	}
	private static void MST() {
		Collections.sort(info, new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				return Double.compare(o1.dist, o2.dist);
			}
		});
		
		for(int i=0;i<info.size();i++) {
			int start = info.get(i).start;
			int dest = info.get(i).dest;
			double dist = info.get(i).dist;
			if(!check(start, dest)) {
				union(start, dest);
				answer+=dist;
			}
		}
	}
	
	private static int find_parent(int x) {
		if(parent[x]==x) return x;
		else return parent[x] = find_parent(parent[x]);
	}
	
	private static void union(int y, int x) {
		y=find_parent(y);
		x=find_parent(x);
		parent[y]=x;
	}
	
	private static boolean check(int y, int x) {
		if(find_parent(x)==find_parent(y)) return true;
		else return false;
	}

}
