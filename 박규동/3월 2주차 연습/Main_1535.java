import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1535 {

	
	static int N,answer;
	static StringTokenizer st;
	static int[] joys,losts;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		joys = new int[N];
		losts = new int[N];
		
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			losts[i]=Integer.parseInt(st.nextToken());
		}
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			joys[i]=Integer.parseInt(st.nextToken());
		}
		dfs(0,0,100);
		System.out.println(answer);
	}

	private static void dfs(int cnt,int happy, int life) {
		
		if(life<1) return;
		
		if(cnt==N) {
			answer = Math.max(answer, happy);
			return;
		}
		
		dfs(cnt+1,happy+joys[cnt],life-losts[cnt]);
		dfs(cnt+1,happy,life);
		
	}

}
