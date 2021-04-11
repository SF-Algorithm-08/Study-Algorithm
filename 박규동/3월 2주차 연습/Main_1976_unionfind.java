import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1976_unionfind {

	static int[] parent;
	static boolean[] travel;
	static int N,M,start;
	static StringTokenizer st;
	static boolean flag;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		M=Integer.parseInt(br.readLine());
		flag=true;
		parent = new int[N+1];
		for(int i=1;i<N+1;i++) {
			parent[i]=i;
		}
		travel = new boolean[N+1];

		for(int i=1;i<N+1;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=1;j<N+1;j++) {
				if(i<=j) continue;
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp==1) {
						union(i,j);
				}
			}
		}
		
		st=new StringTokenizer(br.readLine());
		
		for(int i=0;i<M;i++) {
			int place = Integer.parseInt(st.nextToken());
			travel[place]=true;
			start=place;
		}
		
		solution();
		if(flag) System.out.println("YES");
		else System.out.println("NO");
		
	}
	
	private static void solution() {
		
		for(int i=1;i<N+1;i++) {
			if(travel[i]) {
				if(!check_cycle(i,start)) {
					flag=false;
				}
			}
		}
		
	}

	private static int find_parent(int x) {
		if(x==parent[x]) return x;
		else return parent[x]=find_parent(parent[x]);
	}
	
	private static void union(int x,int y) {
		x=find_parent(x);
		y=find_parent(y);
		if(x!=y) {
			parent[y]=x;
		}
	}
	
	private static boolean check_cycle(int x,int y) {
		if(find_parent(x)==find_parent(y)) return true;
		else return false;
	}

}
