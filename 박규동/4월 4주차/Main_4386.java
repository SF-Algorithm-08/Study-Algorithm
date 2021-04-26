import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_4386 {

	static int n;
	static ArrayList<Star> info;
	static Star[] input;
	static StringTokenizer st;
	static int[] parents;
	static double answer;
	
	static class Star {
		int start;
		int dest;
		double cost;
		double y;
		double x;

		public Star(double y, double x) {
			this.y = y;
			this.x = x;
		}

		public Star(int start, int dest, double cost) {
			this.start = start;
			this.dest = dest;
			this.cost = cost;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		input = new Star[n + 1];
		parents = new int[n+1];
		for(int i=1;i<=n;i++) parents[i]=i;
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			input[i] = new Star(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
		}
		info = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			for (int j = i + 1; j <= n; j++) {
				double dest = Math.sqrt(Math.pow(input[i].y - input[j].y, 2) + Math.pow(input[i].x - input[j].x, 2));
				info.add(new Star(i, j, dest));
				info.add(new Star(j, i, dest));
			}
		}
		
		Collections.sort(info, new Comparator<Star>() {
			@Override
			public int compare(Star o1, Star o2) {
				if(o1.cost-o2.cost<0) return -1;
				return 1;
			}
		});
		
		for(int i=0;i<info.size();i++) {
			int start = info.get(i).start;
			int dest = info.get(i).dest;
			double cost = info.get(i).cost;
			
			if(!check_cycle(start, dest)) {
				union(start,dest);
				answer+=cost;
			}
		}
		System.out.println(Math.floor(answer*100)/100.0); 
		

	}
	
	private static int find_parent(int x) {
		if(x==parents[x]) return x;
		else return parents[x]=find_parent(parents[x]);
	}
	
	private static void union(int x, int y) {
		x=find_parent(x);
		y=find_parent(y);
		if(x!=y) parents[y]=x;
	}
	
	private static boolean check_cycle(int x, int y) {
		if(find_parent(x)==find_parent(y)) return true;
		else return false;
	}

}
