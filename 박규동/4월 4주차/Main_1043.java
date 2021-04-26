import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1043 {

	static int[] parents;
	static StringTokenizer st;
	static int N,M,real,answer;
	static ArrayList<Integer>[] party; 

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		parents = new int[N+1];
		for(int i=0;i<N+1;i++) {
			parents[i]=i;
		}
		M=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		real = Integer.parseInt(st.nextToken());
		for(int i=0;i<real;i++) {
			int target = Integer.parseInt(st.nextToken());
			union(0,target);
		}
		party = new ArrayList[M];
		
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			party[i]= new ArrayList<>();
			int total = Integer.parseInt(st.nextToken());
			for(int j=0;j<total;j++) {
				party[i].add(Integer.parseInt(st.nextToken()));
			}

			for(int j=0;j<total-1;j++) {
				int h1 = party[i].get(j);
				int h2 = party[i].get(j+1);
				if(!check_cycle(h1,h2)) {
					union(h1,h2);
				}
			}
		}
		
		answer=M;
		for(int i=0;i<M;i++) {
			for(int j=0;j<party[i].size();j++) {
				if(check_cycle(0,party[i].get(j))||parents[party[i].get(j)]==0) {
					answer--;
					break;
				}
			}
		}	
		
		System.out.println(answer);
		
	}
	
	private static int find_parent(int x) {
		if(x==parents[x]) return x;
		else return parents[x] = find_parent(parents[x]);
	}
	
	private static void union(int x,int y) {
		x=find_parent(x);
		y=find_parent(y);
		if(x!=y) {
			parents[y]=x;
		}
	}
	
	private static boolean check_cycle(int x,int y) {
		if(find_parent(x)==find_parent(y)) return true;
		else return false;
	}

}
